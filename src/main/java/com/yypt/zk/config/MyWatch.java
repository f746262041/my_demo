package com.yypt.zk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: IceBear
 * @Date: 2021/10/31 20:27
 * @Description:
 */
public class MyWatch implements Watcher {
    CountDownLatch countDownLatch;


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString());

        switch (watchedEvent.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
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
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
