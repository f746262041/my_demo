package com.yypt.zk.config;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Auther: IceBear
 * @Date: 2021/10/31 20:38
 * @Description:
 */
public class TestConfig {
    ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZK();
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConf() throws InterruptedException {
        WatchCallBack watchCallBack = new WatchCallBack();

        watchCallBack.setZk(zk);

        MyConf myConf = new MyConf();
        watchCallBack.setMyConf(myConf);

        watchCallBack.aWait();

        while (true) {
            if (myConf.getConf().equals("")) {
                System.out.println("conf is lost.....");
                watchCallBack.aWait();

            } else {
                System.out.println(myConf.getConf());
            }
            Thread.sleep(3000);
        }
    }
}
