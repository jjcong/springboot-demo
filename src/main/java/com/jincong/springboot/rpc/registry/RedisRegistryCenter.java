package com.jincong.springboot.rpc.registry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
public class RedisRegistryCenter {

    /**
     * jedis客户端
     */
    private static Jedis jedis;


    /**
     * 初始化redis连接
     * @param host  主机
     * @param port  端口
     */
    public static void init(String host, int port) {
        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxIdle(0);
        cfg.setTestOnBorrow(false);

        JedisPool pool = new JedisPool(cfg, host, port);
        jedis  = pool.getResource();
    }


    /**
     * 注册服务
     * @param nozzle 接口
     * @param alias  别名
     * @param info    服务信息
     * @return  注册结果
     */
    public static Long registryProvider(String nozzle, String alias, String info) {
        return jedis.sadd(nozzle + "_" + alias, info);
    }


    /**
     * 获取服务
     * @param nozzle 接口
     * @param alias 别名
     * @return 服务提供方
     */
    public static String obtainProvider(String nozzle, String alias) {
        return jedis.srandmember(nozzle + "_" + alias);
    }


    /**
     * 获取jedis连接
     * @return
     */
    public static Jedis jedis() {
        return jedis;
    }
}
