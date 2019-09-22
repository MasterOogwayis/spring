package com.zsw;

import org.springframework.cloud.zookeeper.discovery.ZookeeperServerList;

/**
 * @author Administrator on 2019/9/18 22:22
 **/
public class ZookeeperTests {

    public static void main(String[] args) {

        ZookeeperServerList list = new ZookeeperServerList(null);
        ZookeeperServerListUpdater z = new ZookeeperServerListUpdater();

    }

}
