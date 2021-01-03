package io.kimmking.redis.distributed.lock;

import io.kimmking.redis.cluster.ClusterJedis;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.Collections;

public class RedisDistributedLockV3 implements DistributedLock {

    private final JedisCluster jedisCluster = ClusterJedis.getJedisCluster();

    @Override
    public boolean lock(String key, String value, int timeout) {
        String luaScript = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 " +
                "then redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end";
        return jedisCluster.eval(luaScript, Collections.singletonList(key),
                Arrays.asList(value, String.valueOf(timeout))).equals(1L);
    }

    @Override
    public boolean unlock(String key, String value) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) else return 0 end";
        return jedisCluster.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
