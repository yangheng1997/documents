package cn.e3mall.content.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

/**
 * 对jedis客户端(工具类)进行测试
 * 分为单机版和集群版 ，通过修改 配置文件application-redis.xml 来进行替换
 * @author yang
 *
 */
public class JedisClientTest {
	
	@Test
	public void testJedisClient() {
		String xmlfile = "classpath:spring/applicationContext-redis.xml";
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlfile);
		
		//引用的接口，而不是 单机或集群的实现类，
		JedisClient jedisClient =  applicationContext.getBean(JedisClient.class);
		jedisClient.set("jedisClusterClient", "jedisClusterClientClient");
		
		System.err.println(jedisClient.get("jedisClusterClient"));
		
	}

}
