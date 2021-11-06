package com.yypt.zk.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: IceBear
 * @Date: 2021/11/1 20:11
 * @Description:
 */
public class WatchCallBack implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {
    ZooKeeper zk;
    String threadName;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }


    public void tryLock() {
        try {
            //this 是StringCallBack的回调
            System.out.println(threadName + "create....");
            zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "aaa");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unLock() {
        try {
            zk.delete(pathName, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * getStringCallback
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            pathName = name;
            System.out.println(threadName + "create node:" + name);
            zk.getChildren("/", false, this, "qqq");
        }

    }

    /**
     * 核心
     * getChildren callback
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
//        System.out.println(threadName + "look locks........");
//        for (String child : children) {
//            System.out.println(child);
//
//        }
        Collections.sort(children);
        int i = children.indexOf(pathName.substring(1));
        if (i == 0) {
            System.out.println(threadName + "i am first........");
            try {

                zk.setData("/", threadName.getBytes(), -1);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cc.countDown();
        } else {
            zk.exists("/" + children.get(i - 1), this, this, "11");
        }

    }

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/", false, this, "qqq");
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
    }
}
