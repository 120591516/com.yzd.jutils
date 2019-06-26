package com.yzd.jutils.runnable;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSONObject;

public class CompletionServiceTest {
	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		CompletionService<JSONObject> cService = new ExecutorCompletionService<JSONObject>(pool);
		cService.submit(new Task());
		cService.submit(new Task1());
		long start = System.currentTimeMillis();
		Future<JSONObject> future = cService.take();
		System.out.println("method:" + future.get());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		pool.shutdown();
	}
}
