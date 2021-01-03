package io.kimmking.redis;

import io.kimmking.redis.cluster.ClusterJedis;
import io.kimmking.redis.message.MessagePublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.JedisCluster;

import java.util.stream.IntStream;

@SpringBootApplication(scanBasePackages = "io.kimmking.redis")
public class RedisApplication implements CommandLineRunner {

	private final MessagePublisher messagePublisher;

	public RedisApplication(MessagePublisher messagePublisher) {
		this.messagePublisher = messagePublisher;
	}

	@Override
	public void run(String... args) throws Exception {
		IntStream.range(0, 100).forEach(x -> messagePublisher.publish("message" + x));
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	public static void test() {
		// C1.最简单demo
//		Jedis jedis = new Jedis("localhost", 6379);
//		System.out.println(jedis.info());
//		jedis.set("uptime", Long.toString(System.currentTimeMillis()));
//		System.out.println(jedis.get("uptime"));

		// C2.基于sentinel和连接池的demo
//		Jedis sjedis = SentinelJedis.getJedis();
//		System.out.println(sjedis.info());
//		sjedis.set("uptime1", Long.toString(System.currentTimeMillis()));
//		System.out.println(sjedis.get("uptime1"));
//		SentinelJedis.close();

		// C3. 直接连接sentinel进行操作
//		Jedis jedisSentinel = new Jedis("localhost", 26380); // 连接到sentinel
//		List<Map<String, String>> masters = jedisSentinel.sentinelMasters();
//		System.out.println(JSON.toJSONString(masters));
//		List<Map<String, String>> slaves = jedisSentinel.sentinelSlaves("mymaster");
//		System.out.println(JSON.toJSONString(slaves));


		// 作业：
		// 1. 参考C2，实现基于Lettuce和Redission的Sentinel配置
		// 2. 实现springboot/spring data redis的sentinel配置
		// 3. 使用jedis命令，使用java代码手动切换 redis 主从
		// 	  Jedis jedis1 = new Jedis("localhost", 6379);
		//    jedis1.info...
		//    jedis1.set xxx...
		//	  Jedis jedis2 = new Jedis("localhost", 6380);
		//    jedis2.slaveof...
		//    jedis2.get xxx
		// 4. 使用C3的方式，使用java代码手动操作sentinel


		// C4. Redis Cluster
		// 作业：
		// 5.使用命令行配置Redis cluster:
		//  1) 以cluster方式启动redis-server
		//  2) 用meet，添加cluster节点，确认集群节点数目
		//  3) 分配槽位，确认分配成功
		//  4) 测试简单的get/set是否成功
		// 然后运行如下代码
		JedisCluster cluster = ClusterJedis.getJedisCluster();
		for (int i = 0; i < 100; i++) {
			cluster.set("cluster:" + i, "data:" + i);
		}
		System.out.println(cluster.get("cluster:92"));
		System.out.println(cluster.del("key"));
		System.out.println(cluster.get("key"));
		ClusterJedis.close();
	}

}
