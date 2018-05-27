package com.ylink.vaner;

import redis.clients.jedis.JedisPool;

import redis.clients.jedis.JedisPubSub;

public class RedisTest {

	private static final String ADDR = "127.0.0.1";

	private static final int PORT = 6379;

	private static JedisPool jedis = new JedisPool(ADDR, PORT);

	static class RedisSub extends JedisPubSub {

		@Override
		public void onMessage(String channel, String message) {

			System.out.println(System.currentTimeMillis() + "ms:" + message + "订单取消");

		}

	}

	private static RedisSub sub = new RedisSub();

	public static void init() {

		new Thread(new Runnable() {

			public void run() {

				jedis.getResource().subscribe(sub, "__keyevent@0__:expired");

			}

		}).start();

	}

	public static void main(String[] args) throws InterruptedException {

		init();

		for (int i = 0; i < 15; i++) {

			String orderId = "OID000000" + i;

			jedis.getResource().setex(orderId, 3, orderId);

			System.out.println(System.currentTimeMillis() + "ms:" + orderId + "订单生成");

		}

	}

}