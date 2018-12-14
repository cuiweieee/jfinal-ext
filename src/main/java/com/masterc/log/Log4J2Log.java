package com.masterc.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jfinal.log.Log;

/**   
 * @ClassName:  Log4J2Log   
 * @author: Master.C
 * @date:   2017年9月20日 上午10:06:10   
 *     
 * @Copyright: 2017 www.aft100.com Inc. All rights reserved. 
 * 注意：本内容仅限于鹰路科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Log4J2Log extends Log {

	private Logger logger;

	public Log4J2Log(String name) {
		logger = LogManager.getLogger(name);
	}

	public Log4J2Log(Class<?> clazz) {
		logger = LogManager.getLogger(clazz);
	}

	public static Log4J2Log getLog(String name) {
		return new Log4J2Log(name);
	}

	public static Log4J2Log getLog(Class<?> clazz) {
		return new Log4J2Log(clazz);
	}

	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void debug(String message, Throwable t) {
		logger.debug(message, t);
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void info(String message, Throwable t) {
		logger.info(message, t);
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void error(String message, Throwable t) {
		logger.error(message, t);
	}

	@Override
	public void fatal(String message) {
		logger.fatal(message);
	}

	@Override
	public void fatal(String message, Throwable t) {
		logger.fatal(message, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}

}
