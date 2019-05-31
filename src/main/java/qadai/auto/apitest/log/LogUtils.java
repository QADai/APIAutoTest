package qadai.auto.apitest.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bsh.This;


public class LogUtils {
	
	public static Logger className(Class clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		return logger;
	}
}
