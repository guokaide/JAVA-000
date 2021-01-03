package io.kimmking.redis.distributed.counter;

import io.kimmking.redis.cluster.ClusterJedis;
import io.kimmking.redis.distributed.lock.DistributedLock;
import io.kimmking.redis.distributed.lock.RedisDistributedLockV3;
import redis.clients.jedis.JedisCluster;

public class DistributedCounter {

    private final DistributedLock lock = new RedisDistributedLockV3();
    private final JedisCluster jedisCluster = ClusterJedis.getJedisCluster();

    private void decrStock(String key, String val, int timeout) {
        if (!acquireLock(key, val, timeout)) {
            return;
        }
        Long availStock = jedisCluster.decr(key);
        releaseLock(key, val);
        if (availStock < 0) {
            throw new RuntimeException("库存不足");
        }
        // 处理订单
    }

    private boolean acquireLock(String key, String val, int timeout) {
        return lock.lock(key, val, timeout);
    }

    private void releaseLock(String key, String val) {
        lock.unlock(key, val);
    }

}
