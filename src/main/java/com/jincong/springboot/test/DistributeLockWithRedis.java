package com.jincong.springboot.test;

import com.jincong.springboot.service.RedisTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * DistributeLockWithRedis
 * 使用Redis实现分布式锁
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/12
 */
@Slf4j
@Service
public class DistributeLockWithRedis {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisTemplateService redisTemplate;
    /**
     * Redis中的锁对应的键
     */
    private static final String LOCK_KEY = "redisLock";
    /**
     * 获取锁的失效时间
     */
    private long intervalLockLeaseTime = 30;
    /**
     * 锁过期时间
     */
    private long timeout = 60 * 1000;




    /**
     * 加锁
     * @param uid  线程唯一标识，集群场景可使用UUID+threadId实现
     * @return
     */
    public boolean lock(String uid) {


        long startTime = System.currentTimeMillis();
        while (true) {
            boolean lock = redisTemplate.setIfAbsent(LOCK_KEY, uid, intervalLockLeaseTime, TimeUnit.SECONDS);
            if (lock) {
                return true;
            }

            long remainingTime = System.currentTimeMillis() - startTime;
            if (remainingTime > timeout) {
                return false;
            }


            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





    /**
     * 解锁
     * 内部使用Lua脚本实现获取与删除的原子性
     * @param uid
     * @return
     */
    public boolean unlock(String uid) {
        try (Jedis jedis = jedisPool.getResource()){

            String luaScript=
                       "if redis.call('get', KEYS[1]) == ARGV[1] then"
                    + "  return redis.call('del', KEYS[1])"
                    + " else "
                    + "     return 0"
                    + " end";

            Object evalResult = jedis.eval(luaScript, Collections.singletonList(LOCK_KEY), Collections.singletonList(uid));

            return Objects.equals("1", evalResult.toString());

        }
    }


    public static void main(String[] args) {
        Config config  = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setPassword("jincong");

        final RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("rLock");

        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }



}
