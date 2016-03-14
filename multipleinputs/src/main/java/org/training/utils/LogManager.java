package org.training.utils;

import org.apache.log4j.Logger;

public class LogManager {

	static Logger logger = Logger.getLogger(LogManager.class);
	private static boolean traceLoggingOn = false;
	private static boolean infoLoggingOn = false;
	private static boolean debugLoggingOn = false;
	private static boolean errorLoggingOn = true;

	public static void init(){
		if(logger == null){
			logger = Logger.getLogger(LogManager.class);
		}
		//set logging on/off -- how to set
		setTraceLoggingOn(true);
		setInfoLoggingOn(false);
		setDebugLoggingOn(false);
		setErrorLoggingOn(true);

	}

	public static void writeInfoToLog(String className, String methodName, String message){
		if(isInfoLoggingOn())
			logger.info(prepareLog(message, className, methodName));
	}

	public static void writeTraceToLog(String className, String methodName, String message){
		if(isTraceLoggingOn())
			logger.trace(prepareLog(message, className, methodName));
	}

	public static void writeDebugToLog(String className, String methodName, String message){
		if(isDebugLoggingOn())
			logger.debug(prepareLog(message, className, methodName));
	}

	public static void writeErrorToLog(String className, String methodName, String message){
		if(isErrorLoggingOn())
			logger.error(prepareLog(message, className, methodName));
	}

	private static String prepareLog(String text, String className, String methodName) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(className);
		buffer.append(":");
		buffer.append(methodName);
		buffer.append(":");
		buffer.append(text);

		return buffer.toString();
	}

	public static boolean isTraceLoggingOn() {
		return traceLoggingOn;
	}

	public static void setTraceLoggingOn(boolean isTraceLoggingOn) {
		LogManager.traceLoggingOn = isTraceLoggingOn;
	}

	public static boolean isInfoLoggingOn() {
		return infoLoggingOn;
	}

	public static void setInfoLoggingOn(boolean isInfoLoggingOn) {
		LogManager.infoLoggingOn = isInfoLoggingOn;
	}

	public static boolean isDebugLoggingOn() {
		return debugLoggingOn;
	}

	public static void setDebugLoggingOn(boolean isDebugLoggingOn) {
		LogManager.debugLoggingOn = isDebugLoggingOn;
	}

	public static boolean isErrorLoggingOn() {
		return errorLoggingOn;
	}

	public static void setErrorLoggingOn(boolean isErrorLoggingOn) {
		LogManager.errorLoggingOn = isErrorLoggingOn;
	}

}
