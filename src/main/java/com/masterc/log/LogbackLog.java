package com.masterc.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.log.Log;


/**   
 * @ClassName:  LogbackLog   
 * @Description:LogbackLog  
 * @author: Master.C
 * @date:   2017年9月1日 上午10:46:13   
 *     
 * @Copyright: 2017 www.aft100.com Inc. All rights reserved. 
 * 注意：本内容仅限于鹰路科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class LogbackLog extends Log {

	private Logger logger;

	LogbackLog(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	LogbackLog(Class<?> clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	public static LogbackLog getLog(String name) {
		return new LogbackLog(name);
	}

	public static LogbackLog getLog(Class<?> clazz) {
		return new LogbackLog(clazz);
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

	}

	@Override
	public void fatal(String message, Throwable t) {

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
		return false;
	}

}
