package io.kimmking.redis.distributed.lock;

import io.kimmking.redis.cluster.ClusterJedis;
import redis.clients.jedis.JedisCluster;

public class RedisDistributedLockV2 implements DistributedLock {

    private final JedisCluster jedisCluster = ClusterJedis.getJedisCluster();

    @Override
    public boolean lock(String key, String value, int timeout) {
        return jedisCluster.set(key, value, "NX", "EX", timeout).equals("OK");
    }

    @Override
    public boolean unlock(String key, String value) {
        return jedisCluster.del(key) == 1L;
    }
}
