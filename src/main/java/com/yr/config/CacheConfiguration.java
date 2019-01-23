//package com.yr.config;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
///**
// * 缓存配置. 
// * 如果ehcache文件在resources文件夹下的文件夹中需配置路径,在resources文件夹下不需要配置路径,它可自动扫描到
// * 如果application.properties 文件中配置了ecache.xml文件 所在位置就需将这里全部注释掉
// * @author zxy-un
// * 
// * 2018年7月19日 上午10:36:41
// */
//@Configuration
//@EnableCaching // 标注启动缓存.
//public class CacheConfiguration {
//
//	/**
//	 * ehcache 主要的管理器
//	 * @author zxy-un
//	 * 
//	 * @param bean
//	 * @return
//	 * 
//	 * 上午9:07:57
//	 */
//	@Bean
//	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
//		System.out.println("CacheConfiguration.ehCacheCacheManager()");
//		return new EhCacheCacheManager(bean.getObject());
//	}
//
//	/**
//	 * 据shared与否的设置,
//	 * Spring分别通过CacheManager.create()
//	 * 或new CacheManager()方式来创建一个ehcache基地.
//	 * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
//	 * 
//	 * @author zxy-un
//	 * 
//	 * @return
//	 * 
//	 * 上午9:08:05
//	 */
//	@Bean
//	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//		System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
//		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//		cacheManagerFactoryBean.setShared(true);
//		return cacheManagerFactoryBean;
//	}
//
//}