package com.ylink.vaner;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Ehcache {
	public static void main(String[] args) {
		final CacheManager cacheManager = new CacheManager(Ehcache.class.getClassLoader().getResourceAsStream("ehcache-auto.xml"));
	    final Cache cache = cacheManager.getCache("users");
	    final String key = "greeting";
	    final Element putGreeting = new Element(key, "Hello, World!");
	    cache.put(putGreeting);
	    final Element getGreeting = cache.get(key);
	    System.out.println(getGreeting.getObjectValue());
	}
    
}
