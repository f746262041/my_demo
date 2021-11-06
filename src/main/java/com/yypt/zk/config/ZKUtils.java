package com.yypt.zk.config;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: IceBear
 * @Date: 2021/10/31 20:21
 * @Description:
 */
public class ZKUtils {
    private static ZooKeeper zk;
//    private static String address = "192.168.8.11:2181,192.168.8.22:2181,192.168.8.33:2181,192.168.8.44:2181/zklock";
    private static String address = "47.100.178.173:2181/zklock";
    private static MyWatch myWatch = new MyWatch();
    private static CountDownLatch init = new CountDownLatch(1);

    public static ZooKeeper getZK() {
        try {
            zk = new ZooKeeper(address, 1000, myWatch);
            myWatch.setCountDownLatch(init);
            init.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }
}
