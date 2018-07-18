package com.ylink.vaner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Ehcache1 {
	
	public static void main(String[] args) {
		CacheManager manager = new CacheManager(Ehcache1.class.getClassLoader().getResource("ehcache-jgroup.xml"));
		final Cache cache = manager.getCache("userCache");
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			private final AtomicLong ll = new AtomicLong(1);
			@Override
			public void run() {
				long andIncrement = ll.getAndIncrement();
				System.out.println("正在put,value值:"+andIncrement);
				cache.put(new Element("key", andIncrement));
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
}
