package com.shadyblog.util;

import java.util.Date;

public final class FileRenameUtil {
	
	public static String renameFile(String filename) {
		String body = String.valueOf(new Date().getTime());
		String suffix = filename.substring(filename.lastIndexOf("."));
		return body + suffix;
	}
}
