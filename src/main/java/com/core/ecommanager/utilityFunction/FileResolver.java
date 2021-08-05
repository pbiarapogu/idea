/*
 * File: FileResolver.java
 * Copyright 2009 cobornsdelivers Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * cobornsdelivers INC. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with cobornsdelivers Inc.
 *
 * Created on: May 22, 2009 / 3:45:26 PM
 * Created by: bnsgg1
 * Version: 1.0
 * Project Name: OnlineGrocery - SiteApp
 * Last updated / updated by: May 22, 2009 3:45:26 PM / bnsgg1
 */
package com.core.ecommanager.utilityFunction;

import java.io.File;
import java.net.URL;

/**
 * Class to perform operations on file
 *
 */
public class FileResolver {
	/**
	 * Method to find existing file
	 * @param fileName
	 * @return File
	 */
	public File findExistingFile(final String fileName) {
		return this.findExistingFile(fileName, null);
	}

	/**
	 * Method to find existing file
	 * @param fileName
	 * @param defaultPath
	 * @return File
	 */
	public File findExistingFile(final String fileName, final String defaultPath) {
		//See if the file exists as is
		File f = new File(fileName);

		//If the file doesn't exist, see if it exists on the classPath
		if (f == null || !f.exists()) {
			f = getFileFromClassPath(fileName);
		}

		//If the file doesn't exist as is, or on the classPath
		if (f == null || !f.exists()) {
			if (defaultPath != null) {
				f = new File(fixFilePath(defaultPath));
			}
		}

		//Don't return files that don't exist
		if (f != null && !f.exists()) {
			f = null;
		}

		return f;
	}

	/**
	 * Method to get file from classpath
	 * @param fileName
	 * @return File
	 */
	private File getFileFromClassPath(final String fileName) {
		String filePath = fileName;
		URL url = getClass().getClassLoader().getResource(fileName);
		if (url == null) {
			if (!fileName.startsWith("/")) {
				url = getClass().getClassLoader().getResource("/" + fileName);
			}
		}

		if (url != null) {
			filePath = url.getFile();
		}
		return new File(fixFilePath(filePath));
	}

	/**
	 * Method to fix file path
	 * @param filePath
	 * @return String
	 */
	private String fixFilePath(final String filePath) {
		String s = filePath;
		int i = s.indexOf("\\.");
		if (i < 0) {
			i = s.indexOf("/.");
		}

		if (i > 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(s.substring(0, i));
			buffer.append(s.substring(i + 2));
			s = buffer.toString();
		}

		if (s.indexOf("%20") > 0) {
			s = s.replaceAll("%20", " ");
		}

		return s;
	}
}