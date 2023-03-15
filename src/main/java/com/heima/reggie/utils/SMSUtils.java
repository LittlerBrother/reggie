package com.heima.reggie.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;

/**
 * 短信发送工具类
 */
public class SMSUtils {

	public static void main(String[] args) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tRcBncQ97QA6q3qFEeB", "G8nHrYZlY5PzKJIh6wTZ5DNWTqKPZA");
		/** use STS Token
		 DefaultProfile profile = DefaultProfile.getProfile(
		 "<your-region-id>",           // The region ID
		 "<your-access-key-id>",       // The AccessKey ID of the RAM account
		 "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
		 "<your-sts-token>");          // STS Token
		 **/
		IAcsClient client = new DefaultAcsClient(profile);

		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers("13290733669");//接收短信的手机号码
		request.setSignName("阿里云短信测试");//短信签名名称
		request.setTemplateCode("SMS_154950909");//短信模板CODE
		request.setTemplateParam("{\"code\":\""+"1234"+"\"}");//短信模板变量对应的实际值

		try {
			SendSmsResponse response = client.getAcsResponse(request);
			System.out.println(new Gson().toJson(response));
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			System.out.println("ErrCode:" + e.getErrCode());
			System.out.println("ErrMsg:" + e.getErrMsg());
			System.out.println("RequestId:" + e.getRequestId());
		}
	}

}
