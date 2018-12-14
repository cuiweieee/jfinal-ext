package com.masterc.log;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

/**   
 * @ClassName:  LogbackLogFactory   
 * @Description:实现JFinal的ILogFactory
 * @author: Master.C
 * @date:   2017年9月1日 上午10:46:15   
 *     
 * @Copyright: 2017 www.aft100.com Inc. All rights reserved. 
 * 注意：本内容仅限于鹰路科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class LogbackLogFactory implements ILogFactory {

	@Override
	public Log getLog(Class<?> clazz) {
		return new LogbackLog(clazz);
	}

	@Override
	public Log getLog(String name) {
		return new LogbackLog(name);
	}

}
