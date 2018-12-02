package com.infoland.utils;

import java.util.UUID;

/**
 * @ClassName: CommonUtil
 * @Description:这是普通工具包
 * @author: Master.C
 * @date: 2017年9月1日 上午10:25:03
 * 
 * @Copyright: 2017 www.aft100.com Inc. All rights reserved.
 *             注意：本内容仅限于鹰路科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Deprecated
public class CommonKit {

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	/**
	 * 随机UUID，带横线
	 * 
	 * @return
	 */
	public static String generateUUIDWithLine() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

}
