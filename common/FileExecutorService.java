package com.sino.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sino.pojo.PrWordProcedure;

import sun.util.logging.resources.logging;

public class FileExecutorService {
	private final ExecutorService pool;
	private static FileExecutorService instance;
	// 线程池大小,即每次最多允许开启几个线程执行转换操作
	private static final int THREAD_SIZE = 5;
	private static Logger log =LoggerFactory.getLogger(FileExecutorService.class);
	public static FileExecutorService getInstance() {
		if (instance == null) {
			instance = new FileExecutorService();
		}
		return instance;
	}

	private FileExecutorService() {
		pool = Executors.newCachedThreadPool();
		//pool = Executors.newFixedThreadPool(THREAD_SIZE);
	}

	/**
	 * 开启新线程，执行转换操作
	 * 
	 * @param info
	 */
	public void execute(PrWordProcedure info) {
		try {
			//System.out.println(SpringContextUtil.getApplicationContext().getBeanDefinitionNames());;
			FileExecutor fileExecutor = SpringContextUtil.getBean(FileExecutor.class);
			fileExecutor.setPrWordProcedure(info);
			log.debug("info:"+fileExecutor);

			Thread thread = new Thread(fileExecutor);
			thread.start();
			//pool.submit(fileExecutor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void shutdown() {
		pool.shutdown();
	}
}
