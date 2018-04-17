package com.sino.common;

import java.util.Vector;

import com.sino.pojo.PrWordProcedure;

/**
 * 格式转换任务队列 队列中放的是PrWordProcedure类型对象
 * 
 * @author Administrator
 *
 */
public class FileTaskQueue {

	// private static FileTaskQueue instance = null;

	// 实际存放转换对象信息的队列，采用线程安全的Vercor容器
	public static Vector<PrWordProcedure> taskQueue = new Vector<PrWordProcedure>();

	/*
	 * public static FileTaskQueue getInstance() { if (instance == null) {
	 * instance = new FileTaskQueue(); } return instance; }
	 */

	/**
	 * 向队列中添加对象
	 * 
	 * @param info
	 */
	public static void add(PrWordProcedure info) {
		taskQueue.add(info);
	}

	/**
	 * 从队列中删除对象
	 * 
	 * @param info
	 */
	public static void remove(PrWordProcedure info) {
		if (taskQueue.size() > 0 && taskQueue.contains(info)) {
			taskQueue.remove(info);
		}
	}

}