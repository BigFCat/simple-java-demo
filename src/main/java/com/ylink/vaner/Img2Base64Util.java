package com.ylink.vaner;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

public class Img2Base64Util {

	public static void main(String[] args) throws Exception {
		String imgFile = "c:\\ttt.jpg";// 待处理的图片
		String imgbese = getImgStr(imgFile);
		// System.out.println(imgbese.length());
		// System.out.println(imgbese);
		// String imgFilePath = "d:\\332.jpg";//新生成的图片
		// generateImage(imgbese,imgFilePath);

		URL url = new URL("http://localhost:3000/upload/base64");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
		// connection.setFollowRedirects(true);
		// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(false);
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		// 要传的参数
		String content = "fileName=ssh2.jpg";
		// 得到图片的base64编码
		content = content + "&" + URLEncoder.encode("imgData", "UTF-8") + "="
				+ URLEncoder.encode(imgbese, "UTF-8");
		// System.out.println(content);
		out.writeBytes(content);
		out.flush();
		out.close();
		InputStream inputStream = connection.getInputStream();
		System.out.println(inputStream.read());
		inputStream.close();
		connection.disconnect();
		// System.out.println("****************end************************");
	}

	/**
	 * 将图片转换成Base64编码
	 * 
	 * @param imgFile
	 *            待处理图片
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 *            图片数据
	 * @param imgFilePath
	 *            保存图片全路径地址
	 * @return
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) {
		//
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}