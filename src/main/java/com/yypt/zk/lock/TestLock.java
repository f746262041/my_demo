package com.yypt.zk.lock;

import com.yypt.zk.config.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Auther: IceBear
 * @Date: 2021/11/1 20:11
 * @Description:
 */
public class TestLock {
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
    public void lock() throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                WatchCallBack watchCallBack = new WatchCallBack();
                watchCallBack.setZk(zk);
                //线程名称
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "创建成功！");
                watchCallBack.setThreadName(threadName);
                //抢锁
                watchCallBack.tryLock();
                //干活
                System.out.println(threadName + "work...............");
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //释放锁
                watchCallBack.unLock();

            }).start();


        }
        System.in.read();
    }
}
