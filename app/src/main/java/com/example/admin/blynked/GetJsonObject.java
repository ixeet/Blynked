package com.example.admin.blynked;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJsonObject {
	
	


	public String getWebServceObj(String url) {
		try{
		HttpURLConnection connection=null;
		
		//Proxy config start
		/*String isEnable = Utility.getProperties("env.properties").getProperty("enableProxy");
		if(isEnable != null && isEnable.equalsIgnoreCase("yes"))
		{
		//**"Proxy enabled..."+url);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyarray.mazdausa.com",80));			
		connection = (HttpURLConnection) new URL(url).openConnection(proxy);
		}
		else{
			connection = (HttpURLConnection) new URL(url).openConnection();	
		}*/
		//Proxy config end
		connection = (HttpURLConnection) new URL(url).openConnection();	
		BufferedReader	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null){
			sb.append(line + '\n');
		}
		String response = sb.toString();
		//**"Resp ="+response);
		return response;
	}catch (Exception e) {
		e.printStackTrace();
            return "fail";
	}
		
	}
	
	
	public static void main(String[] arg)
	{
		
		//#("test ......... "+"\u2605".toUpperCase());
	}
	
}
