package io.kimmking.redis.distributed.demo;

import io.kimmking.redis.distributed.lock.DistributedLock;
import io.kimmking.redis.distributed.lock.RedisDistributedLockV1;
import io.kimmking.redis.distributed.lock.RedisDistributedLockV2;
import io.kimmking.redis.distributed.lock.RedisDistributedLockV3;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class DistributedLockDemo {

    @SneakyThrows
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6380", "redis://127.0.0.1:6381");
        RedissonClient client = Redisson.create(config);
        RMap<String, String> rmap = client.getMap("map1");

        DistributedLock distributedLock = new RedisDistributedLockV3();
        String key = "key";
        String value = "val";
        try {
            while (!distributedLock.lock(key, value, 10)) {
                distributedLock.lock(key, value, 10);
            }
            for (int i = 0; i < 15; i++) {
                rmap.put("rkey:" + i, "rvalue:" + i);
            }
            while(true) {
                Thread.sleep(2000);
                System.out.println(rmap.get("rkey:10"));
            }
        } finally {
            distributedLock.unlock(key, value);
        }
//        while(true) {
//            Thread.sleep(2000);
//            System.out.println(rmap.get("rkey:10"));
//        }

    }
}
