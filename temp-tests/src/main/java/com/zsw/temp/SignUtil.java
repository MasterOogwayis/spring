package com.zsw.temp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @filename: SignUtil.java	CopyRight (c) 2016 Company, Inc. All rights reserved.
 * @author	: zhangshaowei
 * @date	: 2016年6月2日 上午11:04:25
 *
 * 描      述 :
 */
public class SignUtil {
	
	/**
	 * 
	 * 描    述	:验证签名
	 *
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @author 	: zhangshaowei
	 * @version	: v1.0
	 * @since 	: v1.0
	 * @date 	: 2016年6月2日 上午11:06:03
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		String tmpStr = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
	
	
	
	/**
	 * 
	 * 描    述	:将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 * @author 	: zhangshaowei
	 * @version	: v1.0
	 * @since 	: v1.0
	 * @date 	: 2016年6月2日 上午11:15:46
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	
	
	
	/**
	 * 
	 * 描    述	:将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 * @author 	: zhangshaowei
	 * @version	: v1.0
	 * @since 	: v1.0
	 * @date 	: 2016年6月2日 上午11:16:06
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	
}
