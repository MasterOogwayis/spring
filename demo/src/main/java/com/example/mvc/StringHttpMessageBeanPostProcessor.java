/**
 * 文 件 名：StringHttpMessageBeanPostProcessor.java
 * CopyRight (c) 2012 Anze, Inc. All rights reserved.
 * 创 建 人：lurf
 * 日      期：2012-9-26 下午3:52:58
 * 描      述：
 */
package com.example.mvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：设置使用@ResponseBody向页面返回字符串时的响应编码
 * @version 2.0.0
 * @author lurf
 */

public class StringHttpMessageBeanPostProcessor implements BeanPostProcessor {
	
	private String encode = "UTF-8";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 * postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	@Bean
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 * postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	@Bean
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof org.springframework.http.converter.StringHttpMessageConverter) {
			MediaType mediaType = new MediaType("text", "plain", Charset.forName(encode));
			List<MediaType> types = new ArrayList<>();
			types.add(mediaType);
			types.add(MediaType.ALL);
			((org.springframework.http.converter.StringHttpMessageConverter) bean).setSupportedMediaTypes(types);
		}
		return bean;
	}

	/**
	 * @return 
	 */
	public String getEncode() {
		return encode;
	}

	/**
	 * @param encode 
	 */
	public void setEncode(String encode) {
		this.encode = encode;
	}

}
