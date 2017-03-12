/*
 * Copyright (C), 2015-2017, CIBFINTECH
 * FileName: User.java
 * Author:   Haiteng wang
 * Date:     2017年3月11日 下午2:42:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cib.fintech.kyc.api.blockchain;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author Haiteng wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class User {
	public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhK2ZztbnGRz1+IxKMSrThqZCvvMUoiOrm06x7r1Enki44+COSDefgzbltZuLJyi1D0LSqwVzBeE7s2Yr5xfrwJe9Kp3Hw7lf2BE06KH+Hoo9uNC/ivnDjgLwfJGgCi2Tz/eNLNQbbbQXGWXRt3iNbSPDDYDyilDIDYMBxV6zE1/ND1n5TJmT8dAdAuzyaqP9VSLrMT5/ewVfCztW7s8Zupp2C8vHcs7xDPlGQWeM7lH8lyYc+AjDCTRv1RPjnqRWtmCYokwdj0n93wuj4McxqoHIPd6sjRXbkTR4+0w4+eskYEWMDHa4n60bCksl4I/jVvv5CWPyiI8Dc+KFzPUqEQIDAQAB";
	public static String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCErZnO1ucZHPX4jEoxKtOGpkK+8xSiI6ubTrHuvUSeSLjj4I5IN5+DNuW1m4snKLUPQtKrBXMF4TuzZivnF+vAl70qncfDuV/YETToof4eij240L+K+cOOAvB8kaAKLZPP940s1BtttBcZZdG3eI1tI8MNgPKKUMgNgwHFXrMTX80PWflMmZPx0B0C7PJqo/1VIusxPn97BV8LO1buzxm6mnYLy8dyzvEM+UZBZ4zuUfyXJhz4CMMJNG/VE+OepFa2YJiiTB2PSf3fC6PgxzGqgcg93qyNFduRNHj7TDj56yRgRYwMdrifrRsKSyXgj+NW+/kJY/KIjwNz4oXM9SoRAgMBAAECggEAGUWDzW0LmbH1fAEkxJa2sagLgJmGbZfDwRJNO/8bCdcqOGV2VHUb0f9r45qb97p+OkjtHZwtG4qaN4KXjC33PrjoVCy3dyVhByPH4yOv5ptIRGKmiU8nm24d5mxKgoXJ6MvrPQWtr8ikuba2Km8w6P/t0C2mhQf8Au7YMILoMyE712rU6rsnP4wQ/x2alhv7Tq3QniIE+CxsmQ1sJUlIRsDzd4kHYFer/RwxKiGDKl7K44NNMpoWuFiMUdFZgV5k4Q4OHrRtbrrDBkQnOKVcIXJ1X8ujRAVnrtX1sH5QuSJ1KmWMePV7LYHVEkSp0/f0sJbUECoW4b4ImgFrGV1V0QKBgQD5dvBMFqEwuPVCHuAH0y5eDRudG2qNywL0CBHgL5iVKveFetrK04C+TGM2OtFHqQPZnI94ZgTceACe1XKoiaki9wfmBdDKqbJ/DOT/E93W+9cCcbvTn5vNYpvz0ETKb1iaR1/U9XGmGkQLwLYyLPXt0W6dOF8E82pbQT43mo8K9QKBgQCIJ2vINUrEqUXQJBWTqiFogwG4hbS1XrAFoc+DHoEdLhimhdDfjhPttOnaKlbAZwqjzZxZmHLnPCvTPzYHh+jXraZGhOLtRusR8aW93X2Rv/vyUJip+LjLjAQ8xBROaq3PDDqz3BoCEvSlIvNOid9NogbqgrJNAl61MvLzeXMpLQKBgQCHB9xWM0GY7+TBn5jAY40Ll6jEx5xLmAHHOb8DTIAY7iMA+1kGxKlBWHOzXO7/DPtqhRYbo40qEUK0tvDTTYBhzrlVxvwXVvepUb96WrSPs83UIt/6UsCw0Zl8xdUWhmK9T7kNurWDpWADxJJaYn/T9LMdIvA76848Cvq0elLHKQKBgF4y1/E2XbpeWry8mmkFhgOmz+P4LaRmFGZxIv7WdgHYSJm9b1ucEtGdH0YfkNGg+M2Hjaq3PJgbGz8WNq1TLJc7vxR/D/mV3UEW7NLFkQDr0MY/WMAQItpAzxWzm1D9oa7kCSEzIJNaxgQSgD9QSDyP4zUQo4eAIkI/M7ObzT8pAoGAZ/dCrNtTuXX0QUIjKjdDaW3q7g1KsLGRX3QbX/q9OD24Zd0B5GVb2CEhk7WnGewlSFsoE/kLKGTxavZD8dRyMrNO3Ek9upcdcjw/ZtnquMURVzON3drVyShSE0sTezbiz6+KrUz+5BX4X+Vu2R5Zo40q8vphkFwyzj9drwPPuQQ=";
	
	public class UserInfo{
		String name = "王五";
		String id = "431381198109106573";
		String mobile = "184123123123";
	}
	/**
	 * 
	 */
	public User() {
		
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
		// TODO Auto-generated method stub

	}

}
