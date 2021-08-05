/*
 * File: FileChangedListener.java
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

/**
 * Interface to listen to file changes
 *
 */
public interface FileChangedListener {
	/**
	 * Method called by file watcher when a file is modified
	 * @param w
	 * @param changedFile
	 */
	public void handleFileChanged(FileWatcher w, File changedFile);
}
