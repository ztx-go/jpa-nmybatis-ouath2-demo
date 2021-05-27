package com.example.jpamybatisplusdemo.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorHelloworld {
    private static final String CONNECT_ADDR = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {
        //重试策略，初试时间1秒，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);

        //通过工厂创建Curator
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(policy)
                .build();

        //开启连接
        curator.start();

//
//        ExecutorService executor = Executors.newCachedThreadPool();
//
//        /**创建节点，creatingParentsIfNeeded()方法的意思是如果父节点不存在，则在创建节点的同时创建父节点；
//         * withMode()方法指定创建的节点类型，跟原生的Zookeeper API一样，不设置默认为PERSISTENT类型。
//         * */
//        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
//                .inBackground((framework, event) -> { //添加回调
//                    System.out.println("Code：" + event.getResultCode());
//                    System.out.println("Type：" + event.getType());
//                    System.out.println("Path：" + event.getPath());
//                }, executor).forPath("/super/c1", "c1内容".getBytes());
//        Thread.sleep(5000); //为了能够看到回调信息
//        String data = new String(curator.getData().forPath("/super/c1")); //获取节点数据
//        System.out.println(data);
//        Stat stat = curator.checkExists().forPath("/super/c1"); //判断指定节点是否存在
//        System.out.println(stat);
//        curator.setData().forPath("/super/c1", "c1新内容".getBytes()); //更新节点数据
//        data = new String(curator.getData().forPath("/super/c1"));
//        System.out.println(data);
//        List<String> children = curator.getChildren().forPath("/super"); //获取子节点
//        for (String child : children) {
//            System.out.println(child);
//        }
//        //放心的删除节点，deletingChildrenIfNeeded()方法表示如果存在子节点的话，同时删除子节点
//        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
//        curator.close();

        //创建节点:creatingParentContainersIfNeeded 自动递归创建所有所需的父节点
        System.out.println("======================创建节点============");
        curator.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/temp", "c1ddd内容".getBytes());
        System.out.println("======================获取数据============");
        byte[] bytes = curator.getData().forPath("/temp");
        System.out.println(new String(bytes));
        System.out.println("======================判断节点============");
        System.out.println(curator.checkExists().forPath("/temp"));
        System.out.println("======================修改节点数据============");
//        curator.setData().withVersion(10086).forPath("/temp","dfadsf".getBytes());
        curator.setData().forPath("/temp", "dfadsf".getBytes());
        System.out.println(new String(curator.getData().forPath("/temp")));
        System.out.println("======================删除============");
        curator.delete()
                .guaranteed()  // 强制保证删除
                .deletingChildrenIfNeeded() // 递归删除子节点
//                .withVersion(10086) // 指定删除的版本号
                .forPath("/temp");
        System.out.println("======================事务============");
        curator.inTransaction().check().forPath("/nodeA")
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeB", "init".getBytes())
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeC", "init".getBytes())
                .and()
                .commit();
    }
}
