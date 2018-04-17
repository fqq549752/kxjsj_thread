package com.sino.common;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sino.dao.PrWordProceDureDao;
import com.sino.pojo.PrWordProcedure;
import com.sino.service.FileHandleService;

@Service
@Scope("prototype")
public class FileExecutor implements Runnable, Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static Logger log = LoggerFactory.getLogger(FileExecutor.class);
	private static final long serialVersionUID = -6076409369994365555L;
	private PrWordProcedure prWordProcedure;

	@Resource
	private PrWordProceDureDao prWordProceDureDao;

	public PrWordProcedure getPrWordProcedure() {
		return prWordProcedure;
	}

	public void setPrWordProcedure(PrWordProcedure prWordProcedure) {
		this.prWordProcedure = prWordProcedure;
	}

	@Autowired
	private FileHandleService fileHandleService;

	public FileExecutor() {

	}

	/*
	 * public FileExecutor(PrWordProcedure prWordProcedure) {
	 * this.prWordProcedure = prWordProcedure; }
	 */
	@Override
	public void run() {
		// 业务处理
		log.debug("正在处理----》" + prWordProcedure);
		// fileHandleService = new FileHandlerImpl();
		int type = prWordProcedure.getType();
		log.debug("type" + type);
		try {
			switch (type) {
			case 1:// 项目基本信息
				fileHandleService.executeFileType1(prWordProcedure);
				break;
			case 2:// 公示公告
				fileHandleService.executeFileType2(prWordProcedure);
				break;
			case 3:// 项目评价简表处理
				fileHandleService.executeFileType3(prWordProcedure);
				break;
			case 4:// 申报意见处理
				fileHandleService.executeFileType4(prWordProcedure);
				break;
			case 5:// 项目其他内容
				fileHandleService.executeFileType5(prWordProcedure);
				break;
			case 6:// 完成人
				fileHandleService.executeFileType6(prWordProcedure);
				break;
			case 7:// 完成单位
				fileHandleService.executeFileType7(prWordProcedure);
				break;
			case 8:// 项目附件
				fileHandleService.executeFileType8(prWordProcedure);
				break;
			case 9:// 预览
				fileHandleService.executeFileType9(prWordProcedure);
				break;
			case 10:// 提交
				fileHandleService.executeFileType10(prWordProcedure);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			log.error("错误信息：" + e);
			prWordProcedure.setOperate_time(new Date());
			prWordProcedure.setIs_flag(1);

			prWordProcedure.setFile_status(prWordProcedure.getPr_status());
			prWordProcedure.setStatus(2);
			if (e.getMessage() != null) {
				if (e.getMessage().length() < 1000) {
					prWordProcedure.setError_log(e.getMessage());
				} else {
					prWordProcedure.setError_log(e.getMessage().substring(0, 1000));
				}
			} else {
				if (e.toString().length() < 1000) {
					prWordProcedure.setError_log(e.toString());
				} else {
					prWordProcedure.setError_log(e.toString().substring(0, 1000));
				}
			}
			prWordProceDureDao.updatePath(prWordProcedure);
		}
	}

}
