package com.shadyblog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.shady4j.framework.util.StringUtil;


public class IpUtil {

	/**
	 * txt|jsonp|xml
	 */
	public static String DATATYPE = "text";

	private static String get(String urlString, String token) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("token", token);
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				StringBuilder builder = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				for (String s = br.readLine(); s != null; s = br.readLine()) {
					builder.append(s);
				}
				br.close();
				return builder.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 查询IP归属地
	 */
	public static String queryIP(String ip) {
		String url = "http://api.ip138.com/query/?ip=" + ip + "&datatype=" + DATATYPE;
		String token = "d5eba0edeca7f7c17c918e77809fb491";
		return get(url, token);
	}
	
	/**
	 * 获取客户端IP
	 */
	public static String getip(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtil.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtil.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
