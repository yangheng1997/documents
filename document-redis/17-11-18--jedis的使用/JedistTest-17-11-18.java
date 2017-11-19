package cn.e3mall.content.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
/**
 * JEDIS 的特点 每一个 redis的命令都对应一个方法。
 * @author yang
	项目 content-service 
	引入jar包 



 *
 */
public class JedistTest {
	
	//单机版测试 
	@Test
	public void testJedis(){
		//创建一个连接Jedis对象，参数：host、port
		//直接使用jedis操作redis。所有jedis的命令都对应一个方法。
		//关闭连接

		Jedis jedis = new Jedis("192.168.25.147",6379);
		jedis.set("张三", "zhangsan");
		System.out.println(jedis.get("张三"));
		
		for (int i = 0; i < 100; i++) {
			jedis.incr("李四");
		}
		
		System.out.println(jedis.get("李四"));
		jedis.close();
	}
	
	
	
	/**
	 * //连接池版
	 */	
	@Test
	public void testJedisPool(){
		//创建一个连接池对象，两个参数host、port
		//从连接池获得一个连接，就是一个jedis对象。
		//使用jedis操作redis
		//关闭连接，每次使用完毕后关闭连接。连接池回收资源。
		//关闭连接池。
		
		JedisPool jedisPool = new JedisPool("192.168.25.147",6379);
		Jedis jedis = jedisPool.getResource();
		
		jedis.hset("myhase", "field1", "field");
		
		System.out.println(jedis.hget("myhase", "field1"));
		jedis.close();
		jedisPool.close();
	
	}
	
	
	
	/**
	 * 连接集群
	 */
	@Test
	public void TestJedisCluster(){
		//创建一个JedisCluster对象。 有一个参数 nodes是SET类型，set中包含着若干HostAndPort对象。。
		//直接使用JedisCluster对象操作redis。
		//关闭JedisCluster对象

		Set< HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.147", 7001));
		nodes.add(new HostAndPort("192.168.25.147", 7002));
		nodes.add(new HostAndPort("192.168.25.147", 7003));
		nodes.add(new HostAndPort("192.168.25.147", 7004));
		nodes.add(new HostAndPort("192.168.25.147", 7005));
		nodes.add(new HostAndPort("192.168.25.147", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		jedisCluster.set("jedisCluster", "jedisCluster192.168.25.147");
		System.out.println(jedisCluster.get("jedisCluster"));
		
		System.out.println(jedisCluster.get("如果key不存在"));
		jedisCluster.close();
	}
	
	
	
	
	
	
	
	
	
}