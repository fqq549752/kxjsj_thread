package com.sino.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sino.dao.ConstantDao;
import com.sino.dao.OtherFileDao;
import com.sino.dao.PrCompanyDao;
import com.sino.dao.PrDecunitViewDao;
import com.sino.dao.PrEnclosureDao;
import com.sino.dao.PrInfoDao;
import com.sino.dao.PrNoticeDao;
import com.sino.dao.PrParticipantDao;
import com.sino.dao.PrRateDao;
import com.sino.dao.PrTotalEnclosureDao;
import com.sino.dao.PrWordProceDureDao;
import com.sino.dao.SequenceDao;
import com.sino.pojo.OtherFile;
import com.sino.pojo.Participant;
import com.sino.pojo.PrEnclosure;
import com.sino.pojo.PrInfo;
import com.sino.pojo.PrRate;
import com.sino.pojo.PrWordProcedure;
import com.sino.pojo.SysConstant;
import com.sino.pojo.TotalEnclosure;
import com.sino.service.FileHandleService;
import com.sino.util.OpenOffice2PDF;
import com.sino.util.PdfHelper;
import com.sino.util.PdfUtil;
import com.sino.util.PdfUtils;
import com.sino.util.PropertiesUtil;
import com.sino.util.StringUtil;
import com.sino.util.WDWUtil;
import com.sino.util.WaterMarkUtil;
import com.sino.util.WordUtil;
import com.sino.util.ZipUtils;

@Service("FileHandleService")
public class FileHandlerImpl implements FileHandleService {
	private static Logger log = LoggerFactory.getLogger(FileHandlerImpl.class);
	@Resource
	private PrWordProceDureDao prWordProcedureDao;
	@Resource
	private PrInfoDao prInfoDao;
	@Resource
	private PrNoticeDao prNoticeDao;
	@Resource
	private PrParticipantDao prParticipantDao;
	@Resource
	private PrCompanyDao prCompanyDao;
	@Resource
	private OtherFileDao otherFileDao;
	@Resource
	private PrDecunitViewDao prDecunitViewDao;
	@Resource
	private PrRateDao prRateDao;
	@Resource
	private PrEnclosureDao prEnclosureDao;
	@Resource
	private OpenOffice2PDF openOffice2PDF;
	@Resource
	private ConstantDao constantDao;
	@Resource
	private SequenceDao sequenceDao;
	@Resource
	private PrTotalEnclosureDao prTotalEnclosureDao;

	@Override
	public Vector<PrWordProcedure> findPrWords() {
		Vector<PrWordProcedure> vectors = new Vector<PrWordProcedure>();
		// 查询一批待处理的数据
		List<PrWordProcedure> procedures = prWordProcedureDao.findPrWord();
		System.out.println(procedures);
		for (PrWordProcedure procedure : procedures) {
			int id = procedure.getId();
			PrWordProcedure procedure2 = new PrWordProcedure();
			procedure2.setId(id);
			procedure2.setStatus(1);
			procedure2.setOperate_time(new Date());
			// 将这批数据的状态先改为1，标识待处理
			prWordProcedureDao.updateStatus(procedure2);
			// 将这批数据放到任务队列里去
			vectors.add(procedure);
		}
		return vectors;
	}

	/**
	 * 项目基本信息处理
	 */
	@Override
	public void executeFileType1(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		String salt = PropertiesUtil.getProperty("basePath", "SALT");
		log.debug("开始处理项目基本信息。。。");
		String project_id = procedure.getProject_id();
		PrInfo prInfo = new PrInfo();
		prInfo.setProject_id(project_id);
		// Map<String, Object> prInfoMap = new HashMap<String, Object>();
		PrWordProcedure procedure2 = new PrWordProcedure();

		// 查询基本信息
		Map<String, Object> prInfoMap = prInfoDao.queryByProjectId(prInfo);
		if (prInfoMap == null || prInfoMap.isEmpty()) {
			procedure.setError_log("项目不存在！");
			procedure.setIs_flag(1);
		} else {
			// 查询完成人
			Participant participant1 = new Participant();
			participant1.setProject_id(procedure.getProject_id());
			participant1.setSalt(salt);
			ArrayList<Map<String, Object>> partLists = prParticipantDao.queryByProjectId(participant1);
			// 查询完成单位
			ArrayList<Map<String, Object>> compLists = prCompanyDao.queryByProjectId(project_id);
			Date sbDate = (Date) prInfoMap.get("sb_time");
			Calendar cal = Calendar.getInstance();
			if (sbDate != null) {
				cal.setTime(sbDate);
			} else {
				cal.setTime(new Date());
			}
			prInfoMap.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			// 第一步，先生成专家版pdf

			// 第二部，再生成公会版pdf
			Map<String, Object> prInfoMap2 = new HashMap<>();
			prInfoMap2.putAll(prInfoMap);
			// 获取完成人信息
			String participant = "";
			for (Map<String, Object> partMap : partLists) {
				// 1.获取完成人姓名
				participant += partMap.get("name").toString() + "；";
			}
			if (participant.indexOf("；") >= 0) {
				participant = participant.substring(0, participant.length() - 1);
			}
			prInfoMap2.put("participant", participant);
			// 获取完成单位信息
			String company = "";
			for (Map<String, Object> compMap : compLists) {
				// 1.获取完成人姓名
				company += compMap.get("name").toString() + "；";
			}
			if (company.indexOf("；") >= 0) {
				company = company.substring(0, company.length() - 1);
			}
			prInfoMap2.put("company", company);
			// 默认成功
			procedure2.setIs_flag(0);

			try {
				File dirFile = new File(basePath + project_id + File.separator + "temp");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				String path = PdfHelper.getPath();
				PdfUtils.generateToFile(path, "ftl/proBasicInfo.ftl", path + "image/", prInfoMap2,
						basePath + project_id + File.separator + "temp/学会版_项目基本情况.pdf");
				PdfUtils.generateToFile(path, "ftl/proBasicInfo.ftl", path + "image/", prInfoMap,
						basePath + project_id + File.separator + "temp/专家版_项目基本情况.pdf");
				procedure.setError_log("");
				procedure2.setPdf_path(basePath + project_id + File.separator + "temp/学会版_项目基本情况.pdf");
				procedure2.setEx_pdf_path(basePath + project_id + File.separator + "temp/专家版_项目基本情况.pdf");
			} catch (Exception e) {
				log.error("msg", e);
				procedure.setIs_flag(1);
				if (e.getMessage() != null) {
					if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				} else {
					if (e.toString().length() < 1000) {
						procedure.setError_log(e.toString());
					} else {
						procedure.setError_log(e.toString().substring(0, 1000));
					}
				}

			}
		}
		// 修改流程表状态为2
		procedure2.setFile_status(procedure.getPr_status());
		procedure2.setStatus(2);
		procedure2.setOperate_time(new Date());
		procedure2.setId(procedure.getId());
		prWordProcedureDao.updatePath(procedure2);

	}

	/**
	 * 公示公告处理
	 */
	@Override
	public void executeFileType2(PrWordProcedure procedure) {
		// 直接将公告的地址插入到流程表中，并把状态改为2
		log.debug("开始处理公示公告....");
		Map<String, Object> noticeMap = prNoticeDao.query(procedure.getProject_id());
		log.debug("公示公告：" + noticeMap);
		if (null != noticeMap && !noticeMap.isEmpty()) {
			procedure.setPdf_path(noticeMap.get("pic_path").toString());
			procedure.setOperate_time(new Date());
			procedure.setIs_flag(0);
			procedure.setError_log("");

		} else {
			procedure.setOperate_time(new Date());
			procedure.setIs_flag(1);
			procedure.setError_log("公示公告不存在!");

		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);

	}

	/**
	 * 项目评价简表处理
	 */
	@Override
	public void executeFileType3(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");

		// 查询基本信息
		String project_id = procedure.getProject_id();
		PrInfo prInfo = new PrInfo();
		prInfo.setProject_id(project_id);
		procedure.setOperate_time(new Date());

		Map<String, Object> prInfoMap = prInfoDao.queryByProjectId(prInfo);
		if (prInfoMap != null && !prInfoMap.isEmpty()) {
			String sb_type = prInfoMap.get("sb_type") == null ? "" : prInfoMap.get("sb_type").toString();
			if (sb_type.equals("")) {
				procedure.setError_log("项目类型未填写");
				procedure.setIs_flag(1);
			} else {
				// 查询评价简表
				PrRate prRate = new PrRate();
				Map<String, Object> rootMap = new HashMap<String, Object>();
				rootMap.put("type", sb_type);
				prRate.setProject_id(project_id);
				ArrayList<Map<String, Object>> rateLists = prRateDao.queryByProjectId(prRate);
				List<Map<String, Object>> rateList = new ArrayList<Map<String, Object>>();
				if (rateLists.size() > 0) {
					Map<String, Object> map2 = rateLists.get(0);
					String type = map2.get("type").toString();
					SysConstant sysConstant = new SysConstant();
					sysConstant.setCode(type);
					sysConstant.setType("$PJZB");
					ArrayList<SysConstant> constList = constantDao.queryConstantByType(sysConstant);
					for (SysConstant constant : constList) {
						Map<String, Object> map4 = new HashMap<String, Object>();
						map4.put("comment", constant.getComment());
						map4.put("type", constant.getCode());
						map4.put("name", constant.getName());
						map4.put("content", "");

						for (Map<String, Object> map3 : rateLists) {
							if (constant.getName().equals(map3.get("name").toString())) {
								map4.putAll(map3);
							}
						}
						rateList.add(map4);
					}
					rootMap.put("rateList", rateList);
					String path = PdfHelper.getPath();
					try {
						File dirFile = new File(basePath + project_id + File.separator + "temp");
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						PdfUtils.generateToFile(path, "ftl/evaluation_walfare.ftl", path + "image/", rootMap,
								basePath + project_id + File.separator + "temp/评价简表.pdf");
						procedure.setIs_flag(0);
						procedure.setPdf_path(basePath + project_id + File.separator + "temp/评价简表.pdf");
						procedure.setError_log("");

					} catch (Exception e) {
						log.error("msg", e);
						procedure.setIs_flag(1);
						if (e.getMessage() != null) {
							if (e.getMessage().length() < 1000) {
								procedure.setError_log(e.getMessage());
							} else {
								procedure.setError_log(e.getMessage().substring(0, 1000));
							}
						} else {
							if (e.toString().length() < 1000) {
								procedure.setError_log(e.toString());
							} else {
								procedure.setError_log(e.toString().substring(0, 1000));
							}
						}
					}
				} else {
					procedure.setError_log("评价建表不存在!");
					procedure.setIs_flag(0);
					procedure.setPdf_path("");

				}
			}
		} else {
			procedure.setError_log("项目不存在!");
			procedure.setIs_flag(1);
		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);
		log.debug("评价简表pdf生成...");
	}

	/**
	 * 项目其他内容
	 */
	@Override
	public void executeFileType4(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		OtherFile otherFile = otherFileDao.queryByProjectId(procedure.getProject_id());
		if (otherFile != null) {
			String wordPath = otherFile.getWord_path();
			String wordName = otherFile.getFile_name();
			log.debug("wordName:" + wordName);
			String pdfName = "";
			if (WDWUtil.isWord2003(wordName)) {
				pdfName = wordName.substring(0, wordName.length() - 3) + "pdf";
			} else if (WDWUtil.isWord2007(wordName)) {
				pdfName = wordName.substring(0, wordName.length() - 4) + "pdf";
			}
			File dirFile = new File(basePath + procedure.getProject_id() + File.separator + "temp");
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			String pdfPath = basePath + procedure.getProject_id() + File.separator + "temp" + File.separator + pdfName;
			log.debug("pdfPath:" + pdfPath);

			File file = new File(wordPath + File.separator + wordName);
			long length = file.length();
			long start = System.currentTimeMillis();
			if (getOfficeHome()) {
				WordUtil wordUtil = new WordUtil();
				wordUtil.wordToPDF(wordPath + File.separator + wordName, pdfPath);
			} else {
				openOffice2PDF.openOffice2Pdf(wordPath + File.separator + wordName, pdfPath);
			}
			long end = System.currentTimeMillis();
			long time = end - start;
			// 修改流程表状态和文件地址
			procedure.setIs_flag(0);
			procedure.setOperate_time(new Date());
			procedure.setPdf_path(pdfPath);
			procedure.setWord_path(wordPath + File.separator + wordName);
			procedure.setFile_length((length / 1024) + "kb");
			procedure.setTrans_time(time);
			procedure.setError_log("");

		} else {
			procedure.setIs_flag(1);
			procedure.setOperate_time(new Date());
			procedure.setError_log("项目其他附件不存在!");
			procedure.setPdf_path("");

		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);

	}

	/**
	 * 完成人处理
	 */
	@Override
	public void executeFileType5(PrWordProcedure procedure) {
		String salt = PropertiesUtil.getProperty("basePath", "SALT");
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		// 查询完成人
		Participant participant = new Participant();
		participant.setProject_id(procedure.getProject_id());
		participant.setSalt(salt);
		String project_id = procedure.getProject_id();
		ArrayList<Map<String, Object>> partLists = prParticipantDao.queryByProjectId(participant);
		for (Map<String, Object> partMap : partLists) {
			if (partMap.get("unit") != null) {
				partMap.put("unit", partMap.get("unit").toString().replace("&", "&amp;"));
			}
		}
		procedure.setOperate_time(new Date());
		if (partLists != null && partLists.size() > 0) {
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("partList", partLists);
			String path = PdfHelper.getPath();
			try {
				File dirFile = new File(basePath + project_id + File.separator + "temp");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				PdfUtils.generateToFile(path, "ftl/mainPerson.ftl", path + "image/", rootMap,
						basePath + project_id + File.separator + "temp/主要完成人.pdf");
				procedure.setIs_flag(0);
				procedure.setPdf_path(basePath + project_id + File.separator + "temp/主要完成人.pdf");
				procedure.setError_log("");

			} catch (Exception e) {
				log.error("msg", e);
				procedure.setIs_flag(1);
				if (e.getMessage() != null) {
					if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				} else {
					if (e.toString().length() < 1000) {
						procedure.setError_log(e.toString());
					} else {
						procedure.setError_log(e.toString().substring(0, 1000));
					}
				}
			}
		} else {
			procedure.setIs_flag(0);
			procedure.setError_log("主要完成人不存在!");
			procedure.setPdf_path("");

		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);
		log.debug("主要完成人PDF生成...");
	}

	/**
	 * 完成单位处理
	 */
	@Override
	public void executeFileType6(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		String project_id = procedure.getProject_id();
		// 查询完成单位
		ArrayList<Map<String, Object>> compLists = prCompanyDao.queryByProjectId(project_id);
		procedure.setOperate_time(new Date());
		if (compLists != null && compLists.size() > 0) {
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("compList", compLists);
			String path = PdfHelper.getPath();
			try {
				File dirFile = new File(basePath + project_id + File.separator + "temp");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				PdfUtils.generateToFile(path, "ftl/mainUnit.ftl", path + "image/", rootMap,
						basePath + project_id + File.separator + "temp/主要完成单位.pdf");
				procedure.setIs_flag(0);
				procedure.setPdf_path(basePath + project_id + File.separator + "temp/主要完成单位.pdf");
				procedure.setError_log("");

			} catch (Exception e) {
				log.error("msg", e);
				procedure.setIs_flag(1);
				if (e.getMessage() != null) {
					if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				} else {
					if (e.toString().length() < 1000) {
						procedure.setError_log(e.toString());
					} else {
						procedure.setError_log(e.toString().substring(0, 1000));
					}
				}
			}
		} else {
			procedure.setPdf_path("");
			procedure.setIs_flag(0);
			procedure.setError_log("主要完成单位不存在!");
		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);
		log.debug("主要完成单位PDF生成...");
	}

	/**
	 * 申报意见处理
	 */

	@Override
	public void executeFileType7(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		// 获取推荐（申报）意见模版
		Map<String, Object> decMap = new HashMap<String, Object>();
		String project_id = procedure.getProject_id();
		decMap.put("project_id", project_id);
		ArrayList<Map<String, Object>> prDecunitView = prDecunitViewDao.queryByProjectId(decMap);
		procedure.setOperate_time(new Date());
		if (prDecunitView.size() > 0) {
			Map<String, Object> decunitMap = new HashMap<>();
			for (Map<String, Object> map2 : prDecunitView) {
				if (Integer.valueOf(map2.get("type").toString()) == 0) {
					decunitMap.put("sb_advice", map2.get("content"));
				} else if (Integer.valueOf(map2.get("type").toString()) == 1) {
					decunitMap.put("tj_advice", map2.get("content"));
				}
			}
			String path = PdfHelper.getPath();
			try {
				File dirFile = new File(basePath + project_id + File.separator + "temp");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				PdfUtils.generateToFile(path, "ftl/recommendation.ftl", path + "image/", decunitMap,
						basePath + project_id + File.separator + "temp/申报意见.pdf");
				procedure.setIs_flag(0);
				procedure.setPdf_path(basePath + project_id + File.separator + "temp/申报意见.pdf");
				procedure.setError_log("");

			} catch (Exception e) {
				log.error("msg", e);
				procedure.setIs_flag(1);
				if (e.getMessage() != null) {
					if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				} else {
					if (e.toString().length() < 1000) {
						procedure.setError_log(e.toString());
					} else {
						procedure.setError_log(e.toString().substring(0, 1000));
					}
				}
			}
		} else {
			procedure.setIs_flag(1);
			procedure.setError_log("推荐意见不存在!");
			procedure.setPdf_path("");

		}
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);
		log.debug("申报意见pdf生成...");

	}

	/**
	 * 项目附件处理
	 */
	@Override
	public void executeFileType8(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		List<PrEnclosure> prEnclosures = prEnclosureDao.queryAll(procedure.getProject_id());
		if (prEnclosures != null && prEnclosures.size() > 0) {
			List<String> fileList = new ArrayList<String>();
			List<String> ex_fileList = new ArrayList<String>();

			for (int i = 0; i < prEnclosures.size(); i++) {
				String filePath = prEnclosures.get(i).getFile_path() + File.separator
						+ prEnclosures.get(i).getFile_name();
				if (prEnclosures.get(i).getFile_type() == 4 || prEnclosures.get(i).getFile_type() == 5) {
					continue;
				}
				fileList.add(filePath);
			}
			for (int i = 0; i < prEnclosures.size(); i++) {
				String filePath = prEnclosures.get(i).getFile_path() + File.separator
						+ prEnclosures.get(i).getFile_name();
				if (prEnclosures.get(i).getFile_type() == 3) {
					continue;
				}
				ex_fileList.add(filePath);
			}
			try {

				File dirFile = new File(basePath + procedure.getProject_id() + File.separator + "temp");
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				String outPath = basePath + procedure.getProject_id() + File.separator + "temp/mergeEnclosure.pdf";
				PdfUtil.mergePdfFiles(fileList, outPath);
				String ex_outPath = basePath + procedure.getProject_id() + File.separator
						+ "temp/ex_mergeEnclosure.pdf";
				PdfUtil.mergePdfFiles(ex_fileList, ex_outPath); // 开始更新流程表数据
				procedure.setPdf_path(outPath);
				procedure.setEx_pdf_path(ex_outPath);

				procedure.setIs_flag(0);

			} catch (Exception e) {
				log.error("msg", e);
				procedure.setIs_flag(1);
				if (e.getMessage() != null) {
					if (e.getMessage().indexOf("org.bouncycastle.asn1.ASN1Primitive") >= 0
							|| e.getMessage().indexOf("PdfReader not opened with owner password") >= 0
							|| e.getMessage().indexOf("Unknown encryption type R = 5") >= 0) {
						procedure.setError_log("请不要上传加密附件!");

					} else if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				} else {
					if (e.toString().length() < 1000) {
						procedure.setError_log(e.toString());
					} else {
						procedure.setError_log(e.toString().substring(0, 1000));
					}
				}
			}

		} else {
			procedure.setPdf_path("");
			procedure.setEx_pdf_path("");
			procedure.setIs_flag(0);
			procedure.setError_log("项目附件不存在");

		}
		procedure.setOperate_time(new Date());
		procedure.setFile_status(procedure.getPr_status());
		procedure.setStatus(2);
		prWordProcedureDao.updatePath(procedure);
	}

	/**
	 * 预览
	 */
	@Override
	public void executeFileType9(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		// 查找该项目下的所有分部件
		List<PrWordProcedure> procedures = prWordProcedureDao.findInfoByProjectId(procedure.getProject_id());
		procedure.setStatus(2);
		procedure.setOperate_time(new Date());
		boolean flag = true;// 文件是否正常
		boolean flag2 = true;// 文件是未完成等待还是直接失败
		if (procedures != null && procedures.size() > 0) {
			List<String> fileList = new ArrayList<String>();
			for (PrWordProcedure procedure2 : procedures) {
				// 过滤掉预览和提交指令
				if (procedure2.getType() == 9 || procedure2.getType() == 10) {
					continue;
				}
				// 遇到有未完成的分部件，就跳出，并重新加入扫描队列
				if (procedure2.getStatus() != 2) {
					procedure.setStatus(0);
					procedure.setError_log("");
					flag = false;
					break;
				}
				// 遇到分部件生成错误的就终止
				if (procedure2.getIs_flag() != 0) {
					procedure.setIs_flag(1);
					procedure.setError_log(procedure2.getError_log());
					flag = false;
					flag2 = false;
					break;
				}

				// 如果有附件，加上目录
				if (procedure2.getType() == 8 && StringUtil.isNotNullAndEmpty(procedure2.getPdf_path())
						&& StringUtil.isNotNullAndEmpty(procedure2.getEx_pdf_path())) {
					String path = (FileHandlerImpl.class.getClassLoader().getResource("").getPath()
							+ "com/sino/util/ftl/中国公路学会科学技术奖推荐（申报）书-12附件.pdf").substring(1);
					fileList.add(path);
				}
				if (procedure2.getPdf_path() != null && !"".equals(procedure2.getPdf_path())) {
					fileList.add(procedure2.getPdf_path());
				}
			}
			if (flag) {
				try {
					// 合并所有分部件
					PdfUtil.mergePdfFiles(fileList,
							basePath + procedure.getProject_id() + File.separator + "temp/项目信息.pdf");
					procedure.setIs_flag(0);
					procedure.setError_log("");
					procedure.setPdf_path(basePath + procedure.getProject_id() + File.separator + "temp/项目信息.pdf");
					PrInfo prInfo = new PrInfo();
					// 每次预览文件生成，都修改一下pdf时间
					prInfo.setProject_id(procedure.getProject_id());
					prInfo.setPdf_time(new Date());
					prInfoDao.updateBaseInfo(prInfo);

				} catch (Exception e) {
					log.error("msg", e);
					procedure.setIs_flag(1);
					if (e.getMessage() != null) {
						if (e.getMessage().length() < 1000) {
							procedure.setError_log(e.getMessage());
						} else {
							procedure.setError_log(e.getMessage().substring(0, 1000));
						}
					} else {
						if (e.toString().length() < 1000) {
							procedure.setError_log(e.toString());
						} else {
							procedure.setError_log(e.toString().substring(0, 1000));
						}
					}
				}
			}
		} else {
			procedure.setIs_flag(1);
			procedure.setError_log("项目不存在！");

		}
		if (flag) {
			procedure.setFile_status(procedure.getPr_status());
			procedure.setStatus(2);
		} else {
			if (flag2) {
				procedure.setStatus(0);
			} else {
				procedure.setFile_status(procedure.getPr_status());
				procedure.setStatus(2);
			}
		}

		prWordProcedureDao.updatePath(procedure);
	}

	/**
	 * 提交
	 */
	@Override
	public void executeFileType10(PrWordProcedure procedure) {
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");
		String projectId = procedure.getProject_id();
		// 查找该项目下的所有分部件
		List<PrWordProcedure> procedures = prWordProcedureDao.findInfoByProjectId(projectId);
		procedure.setStatus(2);
		procedure.setOperate_time(new Date());
		boolean flag = true;
		boolean flag2 = true;

		if (procedures != null && procedures.size() > 0) {
			List<String> fileList = new ArrayList<String>();
			List<String> ex_fileList = new ArrayList<String>();
			String otherWord = "";
			for (PrWordProcedure procedure2 : procedures) {
				// 过滤掉预览和提交指令
				if (procedure2.getType() == 9 || procedure2.getType() == 10) {
					continue;
				}
				// 遇到有未完成的分部件，就跳出，并重新加入扫描队列
				if (procedure2.getStatus() != 2) {
					procedure.setStatus(0);
					procedure.setError_log("");
					flag = false;
					break;
				}
				// 遇到分部件生成错误的就终止
				if (procedure2.getIs_flag() != 0) {
					procedure.setIs_flag(1);
					procedure.setError_log(procedure2.getError_log());
					flag = false;
					flag2 = false;
					break;
				}
				// 项目基本信息区分公会版和专家版
				if (procedure2.getType() == 1) {

					fileList.add(procedure2.getPdf_path());
					ex_fileList.add(procedure2.getEx_pdf_path());
					continue;
				}
				if (procedure2.getType() == 4) {
					otherWord = procedure2.getWord_path();
				}
				// 公会版才添加完成人和完成单位信息
				if (procedure2.getType() == 6 || procedure2.getType() == 7) {
					if (procedure2.getPdf_path() != null && !"".equals(procedure2.getPdf_path())) {
						fileList.add(procedure2.getPdf_path());
					}
					continue;
				}
				// 如果有附件，加上目录
				if (procedure2.getType() == 8 && StringUtil.isNotNullAndEmpty(procedure2.getPdf_path())
						&& StringUtil.isNotNullAndEmpty(procedure2.getEx_pdf_path())) {
					PrInfo prInfo = new PrInfo();
					prInfo.setProject_id(projectId);
					Map<String, Object> prInfoMap = prInfoDao.queryByProjectId(prInfo);
					if (null != prInfoMap && !prInfoMap.isEmpty()) {
						if ("".equals(prInfoMap.get("tj_dept") == null ? "" : prInfoMap.get("tj_dept").toString())) {
							String path1 = (FileHandlerImpl.class.getClassLoader().getResource("").getPath()
									+ "com/sino/util/ftl/11推荐单位意见.pdf").substring(1);
							fileList.add(path1);
							ex_fileList.add(path1);

						}

					}

					String path = (FileHandlerImpl.class.getClassLoader().getResource("").getPath()
							+ "com/sino/util/ftl/中国公路学会科学技术奖推荐（申报）书-12附件.pdf").substring(1);
					fileList.add(path);
					fileList.add(procedure2.getPdf_path());
					String ex_path = (FileHandlerImpl.class.getClassLoader().getResource("").getPath()
							+ "com/sino/util/ftl/中国公路学会科学技术奖推荐（申报）书-12附件-专家.pdf").substring(1);
					ex_fileList.add(ex_path);
					ex_fileList.add(procedure2.getEx_pdf_path());
					continue;
				}
				if (procedure2.getPdf_path() != null && !"".equals(procedure2.getPdf_path())) {
					fileList.add(procedure2.getPdf_path());
					ex_fileList.add(procedure2.getPdf_path());
				}

			}
			if (flag) {
				try {
					// 生成项目版本号和编号
					PrInfo prInfo = new PrInfo();
					prInfo.setProject_id(projectId);
					Map<String, Object> prInfoMap = prInfoDao.queryByProjectId(prInfo);
					if (prInfoMap != null && !prInfoMap.isEmpty()) {
						String projectNum = prInfoMap.get("project_num") == null ? ""
								: prInfoMap.get("project_num").toString();
						String version = prInfoMap.get("version") == null ? "" : prInfoMap.get("version").toString();
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						String year = sdf.format(date);
						File waterDir = new File(basePath + projectId);
						if (!waterDir.exists()) {
							waterDir.mkdirs();
						}
						String outputPath = basePath + projectId + File.separator + "water.png";
						// 生成水印
						WaterMarkUtil.create(version, projectNum, year, outputPath);
						// 合并所有分部件
						File downloadDir = new File(basePath + procedure.getProject_id() + File.separator + "download");
						if (!downloadDir.exists()) {
							downloadDir.mkdirs();
						}
						PdfUtil.mergePdfFiles(fileList,
								basePath + procedure.getProject_id() + File.separator + "download/temp_公会版_项目信息.pdf");
						File tempDir = new File(basePath + procedure.getProject_id() + File.separator + "temp");
						if (!tempDir.exists()) {
							tempDir.mkdirs();
						}
						PdfUtil.mergePdfFiles(ex_fileList,
								basePath + procedure.getProject_id() + File.separator + "temp/temp_专家版_项目信息.pdf");
						// 给文档加水印
						PdfUtil.addPdfMark(
								basePath + procedure.getProject_id() + File.separator + "download/temp_公会版_项目信息.pdf",
								basePath + procedure.getProject_id() + File.separator + "download/公会版_项目信息.pdf",
								outputPath);
						PdfUtil.addPdfMark(
								basePath + procedure.getProject_id() + File.separator + "temp/temp_专家版_项目信息.pdf",
								basePath + procedure.getProject_id() + File.separator + "temp/专家版_项目信息.pdf",
								outputPath);
						// 讲项目其他内容的word拷贝到下载目录，并且压缩
						if (!"".equals(otherWord)) {
							int index = otherWord.lastIndexOf(File.separator);
							String wordName = otherWord.substring(index);
							copyFile(otherWord, basePath + procedure.getProject_id() + File.separator + "download"
									+ File.separator + wordName);
						}
						// 压缩download文件夹，并改名
						ZipUtils.zip(basePath + procedure.getProject_id() + File.separator + "download",
								basePath + projectId + File.separator + projectNum + ".zip");

						procedure.setIs_flag(0);
						procedure.setError_log("");
						procedure.setPdf_path(
								basePath + procedure.getProject_id() + File.separator + "download/公会版_项目信息.pdf");
						procedure.setEx_pdf_path(
								basePath + procedure.getProject_id() + File.separator + "temp/专家版_项目信息.pdf");

						// 插入到附件总表
						// 不管表里是否有数据，都先删后插，不然多了一部查询的操作
						prTotalEnclosureDao.deleteTotalEnclosure(projectId);
						// 新增附件总表数据
						TotalEnclosure totalEnclosur = new TotalEnclosure();
						totalEnclosur.setProject_id(projectId);
						totalEnclosur.setPdf_path(
								basePath + procedure.getProject_id() + File.separator + "download/公会版_项目信息.pdf");
						totalEnclosur.setEx_pdf_path(
								basePath + procedure.getProject_id() + File.separator + "temp/专家版_项目信息.pdf");
						totalEnclosur.setZip_path(basePath + projectId + File.separator + projectNum + ".zip");
						totalEnclosur.setTimes(new Date());
						totalEnclosur.setIs_del(0);
						prTotalEnclosureDao.insert(totalEnclosur);
					} else {
						procedure.setIs_flag(1);
						procedure.setError_log("项目不存在！");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("错误信息" + e);
					procedure.setIs_flag(1);
					if (e.getMessage().length() < 1000) {
						procedure.setError_log(e.getMessage());
					} else {
						procedure.setError_log(e.getMessage().substring(0, 1000));
					}
				}
			}
		} else {
			procedure.setIs_flag(1);
			procedure.setError_log("项目不存在！");
		}
		if (flag) {
			procedure.setFile_status(procedure.getPr_status());
			procedure.setStatus(2);
		} else {
			if (flag2) {
				procedure.setStatus(0);
			} else {
				procedure.setFile_status(procedure.getPr_status());
				procedure.setStatus(2);
			}
		}

		prWordProcedureDao.updatePath(procedure);

	}

	public String getPrNum(String grade) {
		String seq = sequenceDao.queryOldSeq(grade);
		if (seq == null || seq.equals("")) {
			String seq_name = "";
			if (grade.equals("T")) {
				seq_name = "projectNumSeqT";
			} else if (grade.equals("1")) {
				seq_name = "projectNumSeq1";
			} else if (grade.equals("2")) {
				seq_name = "projectNumSeq2";
			} else if (grade.equals("3")) {
				seq_name = "projectNumSeq3";
			}
			Map<String, Object> map = new HashMap<>();
			map.put("seq_name", seq_name);
			int seq_num = sequenceDao.nextval(map);
			String seq_str = String.valueOf(seq_num);
			for (int i = 0; i < 3 - seq_str.length(); i++) {
				seq_str = "0" + seq_str;
			}
			seq = grade + "-" + seq_str;
		}
		return seq;
	}

	/**
	 * 
	 * @throws IOException
	 * @Title: copyFile @Description:单个文件复制 @param @param oldPath @param @param
	 *         newPath 设定文件 @return void 返回类型 @throws
	 */
	public static void copyFile(String oldPath, String newPath) throws IOException {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {

					fs.write(buffer, 0, byteread);
				}
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		} finally {
			fs.close();
			inStream.close();

		}

	}

	public static void main(String[] args) throws IOException {
		String otherWord = "D:\\project\\e62a2084-bfda-4bf7-9469-4e179cbce79d\\申报书.doc";
		String basePath = PropertiesUtil.getProperty("basePath", "BASE_PATH");

		if (!"".equals(otherWord)) {
			int index = otherWord.lastIndexOf(File.separator);
			String wordName = otherWord.substring(index);
			copyFile(otherWord, basePath + "e62a2084-bfda-4bf7-9469-4e179cbce79d" + File.separator + "download"
					+ File.separator + wordName);
		}
	}

	public boolean getOfficeHome() {
		String osName = System.getProperty("os.name");
		if (Pattern.matches("Linux.*", osName)) {
			return false;
		} else if (Pattern.matches("Windows.*", osName)) {
			return true;
		}
		return true;
	}
}
