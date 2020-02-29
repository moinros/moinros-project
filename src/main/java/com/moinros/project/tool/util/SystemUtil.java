package com.moinros.project.tool.util;

import java.util.Properties;

public class SystemUtil {

	public static boolean isWindows() {
		Properties props = System.getProperties();
		String osName = props.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return true;
		}
		return false;
	}
}
