package com.masterc.log;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

/**
 * @ClassName: Log4J2LogFactory
 * @author: Master.C
 * @date: 2017年9月20日 上午9:52:37
 * 
 * @Copyright: 2017 www.aft100.com Inc. All rights reserved.
 *             注意：本内容仅限于鹰路科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Log4J2LogFactory implements ILogFactory {

	@Override
	public Log getLog(Class<?> clazz) {
		return new Log4J2Log(clazz);
	}

	@Override
	public Log getLog(String name) {
		return new Log4J2Log(name);
	}

}
