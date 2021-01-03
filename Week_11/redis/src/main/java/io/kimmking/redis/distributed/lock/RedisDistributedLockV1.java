package io.kimmking.redis.distributed.lock;

import io.kimmking.redis.cluster.ClusterJedis;
import redis.clients.jedis.JedisCluster;

public class RedisDistributedLockV1 implements DistributedLock {

    private final JedisCluster jedisCluster = ClusterJedis.getJedisCluster();

    // setnx，expire 是 2 个操作，因此 lock() 不是原子的
    // 假设进程 1 的线程 A 调用执行 setnx 成功之后进程挂掉，来不及执行 expire 指令，
    // 则这个 key 将永不过时，锁就永远不会被释放
    @Override
    public boolean lock(String key, String value, int timeout) {
        Long result = jedisCluster.setnx(key, value);
        if (result == 1L) {
            return jedisCluster.expire(key, timeout) == 1L;
        } else {
            return false;
        }
    }

    // 假设进程 1 的线程 A 执行的非常慢，在 timeout 时间内没有执行完成，此时锁释放，
    // 由进程 2 的线程 B 获得锁，当线程 A 执行完成时，将会误删除线程 B 持有的锁
    @Override
    public boolean unlock(String key, String value) {
        return jedisCluster.del(key) == 1L;
    }
}
