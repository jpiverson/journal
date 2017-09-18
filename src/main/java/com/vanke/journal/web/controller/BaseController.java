package com.vanke.journal.web.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanke.journal.exception.WebException;
import com.vanke.journal.web.common.ResponseData;

@Controller
public class BaseController {

	protected String REDIRECT = "redirect:"; // 执行redirect操作的前缀
	/** 当前页数标识 */
	protected static final String PAGE = "page";
	/** 当前页条数标识 */
	protected static final String SIZE = "size";
	protected static final String DEFAULT_PAGE_SIZE = "10"; // 分页,默认分页数量
	protected static final String DEFAULT_PAGE = "0"; // 分页,默认第一页
	protected static final Logger logger = Logger.getLogger(BaseController.class);

	protected static final Date now = new Date(); // 当前时间

	@Value("${host}")
	protected String host;

	/**
	 * 用于处理异常的
	 * 
	 * @return
	 */
	@ExceptionHandler({ WebException.class })
	@ResponseBody
	public ResponseData webException(WebException e) {
		e.printStackTrace();
		ResponseData res = new ResponseData(1, e.getErrMsg(), "错误");
		return res;
	}
}
