package com.ylink.vaner;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {

	private static final int threadNum = 10;

	private static CountDownLatch cdl = new CountDownLatch(threadNum);

	static class DelayMessage implements Runnable {

		public void run() {

			try {

				cdl.await();

			} catch (InterruptedException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			AppTest appTest = new AppTest();

			appTest.consumerDelayMessage();

		}

	}

	public static void main(String[] args) {

		AppTest appTest = new AppTest();

		appTest.productionDelayMessage();

		for (int i = 0; i < threadNum; i++) {

			new Thread(new DelayMessage()).start();

			cdl.countDown();

		}

	}

}