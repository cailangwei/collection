/**
 * 文   件：	Config.java
 * 创建日期：	2012-5-23
 * 文件说明：	
 * 创 建 者：
 * 重大修改记录：
 * 2012-5-23 by  ： 创建
 * 
 * Copyright (C) 2012 - 版权所有   2012 21CN Corp. Ltd
 */
package com.cn21.edrive.product.tool.v2massemail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author 
 * 
 */
public class Config {
	private static Logger log = Logger.getLogger(Config.class);
	private static Config instance = null;
	private Properties properties;

	private static String DEFAULT_CONFIG_FILE = "/v2massemail.properties";
	private static String ENCODING = "utf-8";

	private Config() {
		init();
	}

	public static synchronized Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	private void init() {
		properties = new Properties();
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = Config.class.getResourceAsStream(DEFAULT_CONFIG_FILE);
			br = new BufferedReader(new InputStreamReader(is, ENCODING));
			properties.load(br);
		} catch (IOException e) {
			log.warn("init() - configFile=" + DEFAULT_CONFIG_FILE + " config file not fount!");
		} finally {
			if (br != null) {
				try {
					br.close();
					is.close();
				} catch (Exception e) {
					log.warn("init() - configFile=" + DEFAULT_CONFIG_FILE + "," + e.getMessage(), e);
				}
			}

		}
	}

	public int getIntValue(String key) {
		String v = properties.getProperty(key);
		if (v == null || "".equals(v.trim())) {
			return 0;
		}
		int ret = Integer.parseInt(v);
		return ret;
	}

	public float getFloatValue(String key) {
		String v = properties.getProperty(key);
		if (v == null || "".equals(v.trim())) {
			return 0;
		}
		float ret = Float.parseFloat(v);
		return ret;
	}

	public String getStrValue(String key) {
		String v = properties.getProperty(key);
		if (v == null) {
			v = "";
		}
		return v.trim();
	}

	public boolean getBooleanValue(String key) {
		String v = properties.getProperty(key);
		if (v == null) {
			return false;
		}

		if ("true".equalsIgnoreCase(v)) {
			return true;
		}
		return false;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

}
