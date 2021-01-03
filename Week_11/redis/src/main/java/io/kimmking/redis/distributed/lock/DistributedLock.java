package io.kimmking.redis.distributed.lock;

// https://programmer.group/redis-based-distributed-lock-implementation.html
// https://xiaomi-info.github.io/2019/12/17/redis-distributed-lock/
public interface DistributedLock {

    boolean lock(String key, String value, int timeout);

    boolean unlock(String key, String value);

}
