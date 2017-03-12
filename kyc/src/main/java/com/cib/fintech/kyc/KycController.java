/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: QrcodeController.java
 * Author:   Haiteng wang
 * Date:     2017年2月26日 下午5:42:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cib.fintech.kyc;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cib.fintech.kyc.api.blockchain.CibBlockChainService;
import com.cib.fintech.kyc.api.blockchain.IcbcBlockChainService;
import com.cib.fintech.kyc.api.blockchain.RestClient;
import com.cib.fintech.kyc.util.GenerateQrCodeUtil;
/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author Haiteng wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class KycController {

	@Autowired
	private RestClient restClient;
	@Autowired
	private CibBlockChainService cibBlockChainService;
	@Autowired
	private IcbcBlockChainService icbcBlockChainService;
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	@RequestMapping("/apply.do")
	public String apply() {
		return "apply";
	}
	@RequestMapping("/userlogin.do")
	public String userlogin() {
		return "userlogin";
	}
	/*@RequestMapping("/myidentity.do")
	public String myidentity() {
		return "myidentity";
	}*/
	@RequestMapping("/identity.do")
	public String identity() {
		return "identity";
	}
	@RequestMapping("/icbcAuthentication.do")
	public String icbcAuthentication() {
		return "icbcAuthentication_app";
	}
	@RequestMapping("/apply_app.do")
	public String apply_app() {
		return "apply_app";
	}
	@RequestMapping("/createEPASS.do")
	public String register() {
		return "register_app";
	}
	@RequestMapping("/cibApply.do")
	public String cibApply() {
		return "cibApply_app";
	}
	@RequestMapping("/authorization_app.do")
	public String authorization_app() throws ClientProtocolException, IOException {
		
		return "authorization_app";
	}
	@RequestMapping("/alipay_app.do")
	public String alipay_app(){
		
		return "alipay_app";
	}
	
	@RequestMapping("/write2blockchain.do")
	public String write2blockchain() throws ClientProtocolException, IOException {
		icbcBlockChainService.setUserEnInfo();
		System.out.println("********************************+icbcBlockChainService.setUserEnInfo()");
		return "identity";
	}
	@RequestMapping("/readfromblockchain.do")
	public String readfromblockchain() throws ClientProtocolException, IOException {
		cibBlockChainService.getUserEnInfo();
		System.out.println("********************************+cibBlockChainService.getUserEnInfo()");
		return "identity";
	}
	/**
	 * 
	 *
	 生成二维码图片并直接以流的形式输出到页面
	 * 
	 *
	 @param code_url
	 * 
	 *
	 @param response
	 */
	@RequestMapping("/qr_code.img.do")
	public void getQRCode(String code_url, HttpServletResponse response) {

		GenerateQrCodeUtil.encodeQrcode(code_url, response);

	}
	
	
	@RequestMapping("/restTestGet.do")
	public String restTestGet() throws ClientProtocolException, IOException {
		return restClient.restGet();
	}
	@RequestMapping("/restTestPost.do")
	public String restTestPost() throws ClientProtocolException, IOException {
		return restClient.restPost(null);
	}
	
	public RestClient getRestClient() {
		return restClient;
	}
	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}
}
