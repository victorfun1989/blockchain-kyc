/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: CibBlockChainService.java
 * Author:   Haiteng wang
 * Date:     2017年3月11日 下午2:41:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cib.fintech.kyc.api.blockchain;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cib.fintech.kyc.api.blockchain.method.MethodOfPutCustomerInfo;
import com.cib.fintech.kyc.api.blockchain.method.MethodOfQueryCustomerInfo;
import com.cib.fintech.kyc.api.blockchain.method.MethodOfRegistrar;
import com.google.gson.Gson;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author Haiteng wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class CibBlockChainService {

	static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjsZtkpFsGG7cUmRgYdt4o7X4vV5rQoxokJPVesdhS1wWqQr07XS8ls3F5UQUAOjJpMBYRQjvRSnVoqx2rUK3psVrWgp6S3A0RIuKYeuzg1VAVdFPfmUoO67UfKxTN8BUaPcKwXLm2bfxYYnfCvPZLQfKUxi/uTWIBiyMJfeYqwbY452bGmbuhkgv/58Y6U7Nc0jzCFFdqCUrcZRrdvFmswZHNxYTZrfulAzHYOaRekOcXcyDwNMu1CqpEY+w2nWT5iZiIimFzWaoMzuTwhyN9EWnOREdoqLZdrGFkuZu32X4K/x6Q88TeI+JyzVuwU7y0i8t9kNsowV6vr3djkqiGQIDAQAB";
	static String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCOxm2SkWwYbtxSZGBh23ijtfi9XmtCjGiQk9V6x2FLXBapCvTtdLyWzcXlRBQA6MmkwFhFCO9FKdWirHatQremxWtaCnpLcDREi4ph67ODVUBV0U9+ZSg7rtR8rFM3wFRo9wrBcubZt/Fhid8K89ktB8pTGL+5NYgGLIwl95irBtjjnZsaZu6GSC//nxjpTs1zSPMIUV2oJStxlGt28WazBkc3FhNmt+6UDMdg5pF6Q5xdzIPA0y7UKqkRj7DadZPmJmIiKYXNZqgzO5PCHI30Rac5ER2iotl2sYWS5m7fZfgr/HpDzxN4j4nLNW7BTvLSLy32Q2yjBXq+vd2OSqIZAgMBAAECggEBAI2fZrEsbv0Nep5/0ts+yLwblVDCh8LLZC2Fdw0DGQ+XmSK1K58fXLJ6CwMQNIdyYhJ6eRSBeFYy2Sczy8SxmVpkGciG/Cg4dKBJikr3cmmj9t0pcqD91prdIQlKJoH21cQIyTJ6t2LfgyAtkoFawOJaB+fFdOhSoUr+hUZkA5whA0JV6cqhPDFOYyTSai+gHs/iFjf/UI61LwpJFltPUp61LVlvgM6mjrSxYg50inj0EQBU/hhWqgXAsGnIzHVxFfYX5NYollFE/819P5x6IIVKuMwTE6PL6Vt7ezMpls5nSZrhWWXBFaFOtygO0V94UWpBe4kS2QrtE8J5J/ftpcECgYEAw4d28cVXdwY2w48bOVimQnFTKbjEmrWscVUmUK69qTXsrvAERZ1hzasFqcJSilxYlfcXzKKUhG7lXoKicVg8280CQQvUiJbizuMyQf3CsXGHrvxSG9Yk6gneSYyGL2PI68bmk3cNhRFqJPqosqvh1fq4NkqbbRNmIq8fbPt7v0sCgYEAuu5K5/Wvuzk0sLomL1MfUYtwcC2hRLAJ9+xzBEVgO1KaJVtu86lMsBUR3QmKCZWDJjPl7jKDtnG0OTChjSQZC12zrjtj/7HGwj32/t+ocZbMF0X2I8u80/zXbf7L02SvqsmaQ4Ti16veyrv8WNp51jMxQhqeec350/+wq7u9sasCgYEAmQIn5TiNonm9Xzlcj3q82F/AOXT6fSrP3/xHOHoVtxWYuqjeXu+yunQrwVVsrBNpyykKebt8R0pUuam2nsXzEfoS2n38ekrBAC8whA2lTqBOEnQneNsSFgdU6O9Ljkgi0cbbZuiSLuCa1IdGflMdLgq+HtYxXKECmv+5stn1U5MCgYEArSVL/M91ltXGGGxUMkGsILwnycB6QEVO9nXtk2cvW8OHRFMM0mVewzrA8RyE35u9fwVTVO81USjA5rhE65ckG4MPi7M3TQA+Om1q+2nvfEdRg70p4L42iaUJZrk70vLQREG4whG0jFCGbQLzGtpDQETqu3AsmmZPoi6UVn3vl0ECgYAINLQ+ULB7hjMdizA0hAKnbQ5YGC2AN8fCyHD3YXRDXEbDzYJbMrqUIPDCP8IJA/FogaOm7JQxnI0UGol1z01acQw5PP6+joanuGgKG8AAv6fHox7j3wH7l0ILArt3T1Hc0DHjfnH73nN3UbvLFxN0ES/mrRbgBBjGH1/a3A1p6g==";

	static String enrollId = "cibfintech";
    static String enrollSecret ="123456";
    boolean isLogin =false;
	@Autowired
	private RestClient restClient;
	
	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * 
	 */
	public CibBlockChainService() {
		// TODO Auto-generated constructor stub
	}
	public String registrar(String enrollId,String enrollSecret) throws ClientProtocolException, IOException{
		MethodOfRegistrar method = new MethodOfRegistrar(enrollId,enrollSecret);
		return restClient.restPost(method);
	}
	public String getUserEnInfo() throws ClientProtocolException, IOException{
		//if(!isLogin)
			//registrar(enrollId,enrollSecret);
		MethodOfQueryCustomerInfo method = new MethodOfQueryCustomerInfo(User.publicKey);
		return restClient.restPost(method);
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CibBlockChainService service = new CibBlockChainService();
		service.setRestClient(new RestClient());
		service.getUserEnInfo();
	}

}
