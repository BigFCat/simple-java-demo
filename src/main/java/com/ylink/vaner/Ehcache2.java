package com.ylink.vaner;

import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Ehcache2 {
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.net.preferIPv6Addresses", "true");
		URL url = Ehcache2.class.getClassLoader().getResource("ehcache/rmi-auto.xml");
		CacheManager manager = new CacheManager(url);
		final Cache cache = manager.getCache("userCache");
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("正在获取value:");
				Element element = cache.get("key");
				if(element!=null){
					System.out.println(element.getObjectValue());
				}
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
}
