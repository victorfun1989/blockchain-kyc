/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: MethodOfPutCustomerInfo.java
 * Author:   Haiteng wang
 * Date:     2017年3月11日 下午2:57:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cib.fintech.kyc.api.blockchain.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cib.fintech.kyc.api.blockchain.User;
import com.google.gson.Gson;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author Haiteng wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MethodOfPutCustomerInfo {

	private String jsonrpc = "2.0";
	private String  method = "invoke";
	private Map params = new HashMap();
	private int id = 1;
	
	public MethodOfPutCustomerInfo(String user_public_key,String EncryptedUserInfo) {
		//初始化params参数
		params.put("type", 1);
		
		Map chaincodeID = new HashMap();
		//chaincodeID.put("name", "mycc");
		chaincodeID.put("name", "11141fe94b11d505ddcbc6fddca13ddf5106a436a02b65c924b82d35a514065f7060bbb10c16a7f84ec7fc4a5b2635399bc77286ddf9fbffc1377f21dbb32583");
		params.put("chaincodeID", chaincodeID);
		
		Map ctorMsg = new HashMap();
		ctorMsg.put("function", "putCustomerInfo");
		List args = new ArrayList();
		args.add(user_public_key);
		args.add(EncryptedUserInfo);
		ctorMsg.put("args", args);
		params.put("ctorMsg", ctorMsg);
		
		params.put("secureContext", "icbc");
		
		List attributes = new ArrayList();
		attributes.add("role");
		attributes.add("writer");
		params.put("attributes", attributes);
	}
	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @param args
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static void main(String[] args) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(new MethodOfPutCustomerInfo(User.publicKey,""));
		System.out.println(jsonString);
	}

}
