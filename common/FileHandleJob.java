package com.sino.common;

import java.util.Date;
import java.util.Vector;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sino.pojo.PrWordProcedure;
import com.sino.service.FileHandleService;

public class FileHandleJob implements Job {
	private static Logger log = LoggerFactory.getLogger(FileHandleJob.class);
	@Resource
	private FileHandleService fileHandleService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("当前时间:" + new Date());
	}

	@SuppressWarnings("static-access")
	public void execute() {
		//Vector<PrWordProcedure> infos = new FileTaskQueue().taskQueue;
		Vector<PrWordProcedure> infos =fileHandleService.findPrWords();
		log.debug("本批次待处理条数:" + infos.size());
		if (infos.size() > 0) {
			for (int i = 0; i < infos.size(); i++) {
				FileExecutorService.getInstance().execute(infos.get(i));
			}
		}


	}

}
