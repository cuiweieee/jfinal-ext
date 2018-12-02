package com.infoland.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.infoland.utils.excel.ExportExcel;

public class TestExcel {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String s = "[{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":1,\"salesmanCode\":\"123123123\"},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":2,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":3,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":4,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":5,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312333\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":6,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312322\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":7,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312333\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":8,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312312\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":9,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"codeTwo\":\"\",\"customerPhone\":\"12312312333\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":10,\"salesmanCode\":\"123123123\",\"wechatId\":72},{\"codeOne\":\"123123123\",\"customerPhone\":\"12312312333\",\"handleCardType\":\"0\",\"handleCardValue\":\"相同的卡种\",\"id\":11,\"salesmanCode\":\"123123123\",\"wechatId\":72}]";
		List<Apply> list = JSON.parseObject(s, new TypeReference<List<Apply>>() {
		});
		ExportExcel exportExcel = new ExportExcel("1234", Apply.class);
		exportExcel.setDataList(list);
		exportExcel.writeFile("C:\\Users\\Administrator\\Desktop\\1.xlsx");
	}
}
