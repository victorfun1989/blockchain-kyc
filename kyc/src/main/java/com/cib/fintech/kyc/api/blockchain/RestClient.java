package com.cib.fintech.kyc.api.blockchain;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Service
public class RestClient {
	//static String blockChainRestBaseUri = "http://petstore.swagger.io/v2";
	static String blockChainRestBaseUri = "http://localhost:1050";
	
	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String restGet() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
		// instead
		HttpGet request = new HttpGet(
				blockChainRestBaseUri+"/chaincode");
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		// get with json
		HttpResponse httpResponse = httpClient.execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		System.out.println(response);
		Gson gson = new Gson();
		/*List list = gson.fromJson(response, new TypeToken<List>() {
		}.getType());*/
		return response; 
	}
	
	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String restPost(Object pojo) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
					// post data with json
					HttpPost httpPost = new HttpPost(blockChainRestBaseUri +"/chaincode");
					httpPost.addHeader("content-type", "application/json");
					httpPost.addHeader("Accept", "application/json");
					Gson gson = new Gson();
					String jsonString = gson.toJson(pojo);
					System.out.println(jsonString);
					StringEntity entity = new StringEntity(jsonString);
					//StringEntity entity = new StringEntity("{\"id\": 0,\"username\": \"string\", \"firstName\": \"string\",\"lastName\": \"string\",\"email\": \"string\",\"password\": \"string\",\"phone\": \"string\",\"userStatus\": 0}");
					httpPost.setEntity(entity);
					HttpResponse httpResponse = httpClient.execute(httpPost);
					String response = EntityUtils.toString(httpResponse.getEntity());
					System.out.println(response);
					return response;
	}
}
