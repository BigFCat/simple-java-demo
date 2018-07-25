package com.ylink.vaner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestHtppClient {

	private static final String IP_ADRESS = "10.241.48.47";

	public static void main(String[] args) throws IOException {

		String cookie_id = login();
		System.out.println(cookie_id);
//		 sign(cookie_id);
//		logout(cookie_id);
	}

	// 鐧诲叆
	public static String login() {
		String url = "http://itportal.crbank.com.cn/itportalweb/login/QuickLogin.do";
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("datapipe['userkey']", "PT-WEIXJ"));
		nvp.add(new BasicNameValuePair("datapipe['password']", "000000"));
		HttpResponse response;
		post.addHeader("x-forwarded-for", IP_ADRESS);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvp, "utf-8"));
			response = new DefaultHttpClient().execute(post);
			// 杈撳嚭鍝嶅簲澶�
			for (Header head : response.getAllHeaders()) {
				// System.out.println(head);
			}
			// 杈撳嚭鍝嶅簲浣�
			// System.out.println(EntityUtils.toString(response.getEntity()));
			String cookies = response.getHeaders("Set-Cookie")[0].getValue();
			System.out.println(cookies);
			String cookie = cookies.substring(0, cookies.indexOf(";"));
			System.out.println(cookie);
			return cookie;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 绛鹃��
	public static String logout(String cookie) {
		String url = "http://itportal.crbank.com.cn/itportalweb/sign/signoutAction.do";
		HttpPost post = new HttpPost(url);
		post.addHeader("Cookie", cookie);
		post.addHeader("x-forwarded-for", IP_ADRESS);
		try {
			HttpResponse response = new DefaultHttpClient().execute(post);
			// 杈撳嚭鍝嶅簲澶�
			for (Header head : response.getAllHeaders()) {
				// System.out.println(head);
			}
			// 杈撳嚭鍝嶅簲浣�
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 绛惧埌
	public static String sign(String cookie) {
		String url = "http://itportal.crbank.com.cn/itportalweb/sign/signAction.do";
		HttpPost post = new HttpPost(url);
		post.addHeader("Cookie", cookie);
		post.addHeader("x-forwarded-for", IP_ADRESS);
		try {
			HttpResponse response = new DefaultHttpClient().execute(post);
			// 杈撳嚭鍝嶅簲澶�
			for (Header head : response.getAllHeaders()) {
				// System.out.println(head);
			}
			// 杈撳嚭鍝嶅簲浣�
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
