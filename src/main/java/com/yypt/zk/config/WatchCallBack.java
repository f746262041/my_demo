package com.yypt.zk.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: IceBear
 * @Date: 2021/10/31 20:51
 * AsyncCallback.StatCallback :exit 的回调
 * AsyncCallback.DataCallback：get的回调
 */
public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
    ZooKeeper zk;
    MyConf myConf;
    CountDownLatch countDownLatch = new CountDownLatch(1);


    /**
     * 事件回调
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf", this, this, "AAA");
                break;
            case NodeDeleted:
                myConf.setConf("");
                countDownLatch=new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/AppConf", this, this, "AAA");
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


    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        if (bytes != null) {
            String s1 = new String(bytes);
            myConf.setConf(s1);
            countDownLatch.countDown();
        }
    }


    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        if (stat != null) {
            zk.exists("/AppConf", this, this, "ABC");
        }
    }

    public void aWait() {
        zk.exists("/AppConf", this, this, "ABC");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }
}
