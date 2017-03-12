/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: IcbcBlockChainService.java
 * Author:   Haiteng wang
 * Date:     2017年3月11日 下午2:41:47
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

import com.cib.fintech.kyc.api.blockchain.User.UserInfo;
import com.cib.fintech.kyc.api.blockchain.method.MethodOfPutCustomerInfo;
import com.cib.fintech.kyc.api.blockchain.method.MethodOfRegistrar;
import com.cib.fintech.kyc.util.RSAUtil;
import com.cib.fintech.kyc.util.BASE64Util;
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
public class IcbcBlockChainService {

	static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj/i2ACEuU3D7xa2iPQi1P1vogv3X5T4UbsCm+9b2ds2Exn0feyz/XDX6XkFgrYyOnSVrsKRSmihMzWaPiBPdXCGnmBefnAFZOFTHoaLlDrnvEUPIB7vB2CmFVjNU0uur6jyu2W5SDLz8TaWMriJiOXHu8M4UzpJGcCtlJC5/ZsLuM/hAXbsEuz1bddngLq2Rz95krqOSbhtKRt5YZukUh3g0Ys3hgOWpBxuOnzsscXlYylaCgy9wty4pP5Of3hZnpBw0JPzLBX4K10w6QFY+Ma18mycL73LImiNdsqXbcOSIpV1U1KU/6m/MWGWyvCIlOp+RrHBxjQ2IRh/AA2tRIwIDAQAB";
	static String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCP+LYAIS5TcPvFraI9CLU/W+iC/dflPhRuwKb71vZ2zYTGfR97LP9cNfpeQWCtjI6dJWuwpFKaKEzNZo+IE91cIaeYF5+cAVk4VMehouUOue8RQ8gHu8HYKYVWM1TS66vqPK7ZblIMvPxNpYyuImI5ce7wzhTOkkZwK2UkLn9mwu4z+EBduwS7PVt12eAurZHP3mSuo5JuG0pG3lhm6RSHeDRizeGA5akHG46fOyxxeVjKVoKDL3C3Lik/k5/eFmekHDQk/MsFfgrXTDpAVj4xrXybJwvvcsiaI12ypdtw5IilXVTUpT/qb8xYZbK8IiU6n5GscHGNDYhGH8ADa1EjAgMBAAECggEBAIO5cPvpX/NkOleBKCFjqEFSHAhNrIzIIfW7OozM/y9vln3s2TLf1rxNfsdiVcwz///zzvYNQq5dFPgRUF2Wnz++YxKa+qPH8BPELerLmXvBzuQi+g9pUFAwnRhjWsaobeCgqPGzKEsTVlzMjUx9lRUuEwor44mk9+GFMsLJ8DLZ1gbEYgFmSWVpumrmblUw51zFLW5VDCjlpspu3sbHQpUIB+lNivJ4qZ+IFwX7gOK5UYDs/NX/7+JVqMU6urZ1V+lyD+Bn15L3Kl+uKZijHB4YloVyWJsW27gIyR7QnqG03973c91pQGlFauJkR6hjj0LXwqAfpjFIC/Qd7TklkLECgYEA/aOizkXtW/H4HFa8uw53otY6B8GlmZyQtRYFKvuMUwPlDZByMQkkbuiN1WRTTYP7zxuToXC7jBtYZtgvaaCueQGpvOcZ7CO/IvwjBtbjE+iKhnUQSeQLRGGG2EY8ok01BIBLo0c16FczwDe66PC4yFwazlauDivuQkNIf3zg+hcCgYEAkU/DFp5N6pZH5yCLc6ZSysW1BW5txrAULWkcj1w5FXLT5ZsL6So7tNH6ugysvH+fHHZrqC7KG/J4lHYuxKTYFBLViy+tz5xxMFrdM1lGQ+fB/AGXMo/cDu3eobj6obzSTgkU/pgG2IIFbKRNz0g5xyHTPIAPH+Bs3BiJzMlwJNUCgYAiiJQTF6xAl4pbeWfK5CZf0mEmSzRb3NhK/4uGlac+akpWdXNGv+LpAtrE0hsRc8XmJfAMzAeNBPAdWJPkAs+ehB5zLNf3scpFXiT6umlCfafWtLuaA+WQ0uKEMdPxmSd9zGX//iMV1kGiizP0oUnXZcr0ixK5tTuEZBrL2Nnc0wKBgAZBPa+KjuYLI/sx7m7C+QtbaUhbkYQc7jFqNd2B5b8jmzVl8CRQOhO1eipqA0F16N5rxoSNxwte64vQboDua5KpoOGF4uBt5n1jW+Qq8phXsITteJmnkZxe5tdlQ/yUSSUsxOeUma7WIe+kPukuPbZxoUsYZA5GNclfkQbsoNQlAoGAUMlWsoYhUXCEhIGd9WoILgLybKTKDIMxILfJx2Ac71VJKex5JI+vJYPe06C/SlZhnvaNTbCg8hdypkn567Ai9CEtx0YRX486cym/MluIis30EAT8ndci+KBoqINDNN8yFPWKNrb/wDQrV4tBLiZ1gFdDj68Sdiavhi4HfEYIiQo=";
	static String enrollId = "cibfintech";
    static String enrollSecret ="123456";
    
	@Autowired
	private RestClient restClient;
	boolean isLogin =false;
	public RestClient getRestClient() {
		return restClient;
	}
	/**
	 * 
	 */
	public IcbcBlockChainService() {
		// TODO Auto-generated constructor stub
	}

	public String registrar(String enrollId,String enrollSecret) throws ClientProtocolException, IOException{
		MethodOfRegistrar method = new MethodOfRegistrar(enrollId,enrollSecret);
		return restClient.restPost(method);
	}
	public String setUserEnInfo() throws ClientProtocolException, IOException{
		//if(!isLogin)
		//	registrar(enrollId,enrollSecret);
		User.UserInfo userInfo = new User().new UserInfo();
		Gson gson = new Gson();
		String jsonString = gson.toJson(userInfo);

        MethodOfPutCustomerInfo method = new MethodOfPutCustomerInfo(User.publicKey,BASE64Util.encode(RSAUtil.encryptByPublicKey(jsonString.getBytes(),User.publicKey)));



		//MethodOfPutCustomerInfo method = new MethodOfPutCustomerInfo(User.publicKey,new String(RSAUtil.encryptByPublicKey(jsonString.getBytes(),User.publicKey)));
		return restClient.restPost(method);
	}
}
