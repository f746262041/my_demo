package com.yypt.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 * zk是有session概念的，没有连接池的概念
 * watch的注册只发生在读，get,exist
 * watch:观察，回调
 * 第一类：new Zk时候传入的watch,这个watch是session级别的，跟path，node没有关系
 */
public class App {
//    private static String IPS = "192.168.8.11:2181,192.168.8.22:2181,192.168.8.33:2181,192.168.8.44:2181";
    private static String IPS = "47.100.178.173:2181";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(IPS, 3000, (watchedEvent) -> {
            Watcher.Event.KeeperState state = watchedEvent.getState();
            Watcher.Event.EventType type = watchedEvent.getType();
            String path = watchedEvent.getPath();
            System.out.println("new zk watch:" + watchedEvent.toString());
            switch (state) {
                case Unknown:
                    break;
                case Disconnected:
                    break;
                case NoSyncConnected:
                    break;
                case SyncConnected:
                    System.out.println("sysncConnected！");
                    countDownLatch.countDown();
                    break;
                case AuthFailed:
                    break;
                case ConnectedReadOnly:
                    break;
                case SaslAuthenticated:
                    break;
                case Expired:
                    break;
                case Closed:
                    break;
            }
            switch (type) {
                case None:
                    break;
                case NodeCreated:
                    break;
                case NodeDeleted:
                    break;
                case NodeDataChanged:
                    break;
                case NodeChildrenChanged:
                    break;
                case DataWatchRemoved:
                    break;
                case ChildWatchRemoved:
                    break;
                case PersistentWatchRemoved:
                    break;
            }
        });
        countDownLatch.await();
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("ing..........");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("end..........");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }
        String pathName = zk.create("/node01", "olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Stat stat = new Stat();

        //同步
        byte[] node = zk.getData("/node01", (w) -> {
            System.out.println("getdata watch:" + w.toString());
            try {
                //watch为true,default watch（new zk的那个watch） 重新被注册
                zk.getData("/node01", true, stat);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, stat);

        System.out.println("/node:" + new String(node));
        //触发
        Stat stat1 = zk.setData("/node01", "newData".getBytes(), 0);
        //下面还会触发回调
        Stat stat2 = zk.setData("/node01", "newData2".getBytes(), stat1.getVersion());
        System.out.println("async start");

//        zk.getData("/node01", false, new AsyncCallback.DataCallback() {
//            @Override
//            public void processResult(int i, String s, Object ctx, byte[] data, Stat stat) {
//                System.out.println("async call back");
//                System.out.println(ctx.toString());
//                System.out.println("async:data" + new String(data));
//            }
//        }, "abc");

        zk.getData("/node01", false, (int i, String s, Object ctx, byte[] data, Stat stat3) -> {
            System.out.println("async call back");
            System.out.println(ctx.toString());
            System.out.println("async:data" + new String(data));
        }, "abc");

        System.out.println("async over");
        Thread.sleep(333333333333L);
    }


}

