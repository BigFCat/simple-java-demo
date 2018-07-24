package com.ylink.vaner;

import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Ehcache1 {
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("java.net.preferIPv6Addresses", "true");
		URL url = Ehcache1.class.getClassLoader().getResource("ehcache-auto.xml");
		CacheManager manager = new CacheManager(url);
		final Cache cache = manager.getCache("userCache");
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			private final AtomicLong acl = new AtomicLong(1);
			@Override
			public void run() {
				long value = acl.getAndIncrement();
				System.out.println("正在put,value值:"+value);
				cache.put(new Element("key", value));
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
}
