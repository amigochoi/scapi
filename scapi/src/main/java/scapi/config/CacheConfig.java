//package scapi.config;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport{
////	
////	@Resource(name = "redisCacheTimeMap")
////	Map<String,String> redisCacheTimeMap;
////	
////	@Value("${redis.cache.hostname}")
////	private String redisHostName;
////	
////	@Value("${redis.cache.port}")
////	private int redisPort;
////	
////	@Value("${redis.cache.default.cache.second}")
////	private long defaultCacheTime;
////	
////	  @Bean
////	  public JedisConnectionFactory redisConnectionFactory() {
////	    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
////
////	    // Defaults
////	    redisConnectionFactory.setHostName(redisHostName);
////	    redisConnectionFactory.setPort(redisPort);
////	    return redisConnectionFactory;
////	  }
////
////	  @Bean
////	  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
////	    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
////	    redisTemplate.setConnectionFactory(cf);
////	    return redisTemplate;
////	  }
////
////	  @Bean
////	  public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
////	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
////
////	    // Number of seconds before expiration. Defaults to unlimited (0)
////	    cacheManager.setDefaultExpiration(defaultCacheTime);
////	    
////	    Map<String, Long > newMap = new HashMap<String, Long>();
////	    for(Map.Entry<String, String> entry : redisCacheTimeMap.entrySet()) {
////	       newMap.put(entry.getKey(), new Long(entry.getValue()));
////	    }
////	    cacheManager.setExpires(newMap);
////	    return cacheManager;
////	  }
////	  
//	  @Bean
//	  public KeyGenerator keyGenerator() {
//	    return new KeyGenerator() {
//	      @Override
//	      public Object generate(Object o, Method method, Object... objects) {
//	        // This will generate a unique key of the class name, the method name,
//	        // and all method parameters appended.
//	        StringBuilder sb = new StringBuilder();
//	        sb.append(o.getClass().getName());
//	        sb.append(method.getName());
//	        for (Object obj : objects) {
//	          sb.append(obj.toString());
//	        }
//	        return sb.toString();
//	      }
//	    };
//	  }
//}
