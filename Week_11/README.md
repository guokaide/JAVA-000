# 作业

## 作业1

> 基于 Redis 封装分布式数据操作：
>
> - 在 Java 中实现一个简单的分布式锁；
> - 在 Java 中实现一个分布式计数器，模拟减库存。

解答：

分布式锁详见包：[`io.kimmking.redis.distributed.lock`](https://github.com/guokaide/JAVA-000/tree/main/Week_11/redis/src/main/java/io/kimmking/redis/distributed/lock)

分布式计数器详见包：[`io.kimmking.redis.distributed.counter`](https://github.com/guokaide/JAVA-000/tree/main/Week_11/redis/src/main/java/io/kimmking/redis/distributed/counter)

参考：

* https://programmer.group/redis-based-distributed-lock-implementation.html
* https://xiaomi-info.github.io/2019/12/17/redis-distributed-lock/
* https://time.geekbang.org/column/article/301092

## 作业2

> 基于 Redis 的 PubSub 实现订单异步处理

解答：

配置类见包: [`io.kimmking.redis.config`](https://github.com/guokaide/JAVA-000/tree/main/Week_11/redis/src/main/java/io/kimmking/redis/config)

实现类见包：[`io.kimmking.redis.message`](https://github.com/guokaide/JAVA-000/tree/main/Week_11/redis/src/main/java/io/kimmking/redis/message)



