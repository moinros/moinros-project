package com.moinros.project.tool.util.file;

import java.io.*;

/**
 * 类注释: 生成本地文件的工具类
 *
 * @Title: CreateFileUtil
 * @author moinros
 * @date 2019年5月9日 上午9:55:51
 */
public class FileUtil {

	private FileUtil() {
	}

	/**
	 * 注释: 判断文件夹是否存在;不存在则创建
	 *
	 * @param path
	 */
	public synchronized static void createFile(String path) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	

	public synchronized static File[] getFiles(String path) {
		File file = new File(path);
		File[] temp = file.listFiles();
		if (temp == null || temp.length <= 0) {
			return null;
		}
		return temp;
	}

	/**
	 * 注释: 将指定的字符串生成实体文件；并写入到指定的磁盘位置
	 *
	 * @param fileString 字符串形式的文件文本信息
	 * @param fileName   文件名字 + 文件后缀
	 * @param filePath   生成的文件存放位置;默认为:C:\\javabean
	 * @return boolean 返回true表示文件生成成功；返回false文件生成表示失败
	 */
	public synchronized static boolean createFile(String fileString, String fileName, String filePath) {
		if (filePath == null) {
			filePath = "C:\\javabean";
		}
		File f = new File(filePath);
		if (!f.exists()) { // 判断文件夹是否存在;不存在则创建
			f.mkdirs();
		}
		// 将生成的javaBean结构信息写出到指定的磁盘位置
		BufferedWriter bw = null;
		boolean flag = false;
		try {
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile() + "\\" + fileName));
			bw.write(fileString);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (flag) {
			System.out.println(">> 文件已经成功生成！！=> 路径: " + filePath + "/" + fileName);
		} else {
			System.out.println(">> 文件生成失败！");
		}
		return flag;
	}

	/**
	 * 注释: 读取指定磁盘位置的文件；并转换为字符串形式
	 *
	 * @param filePath
	 * @return String
	 */
	public synchronized static String readFile(String filePath) {
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					sb.append(lineTxt);
				}
				br.close();
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件读取错误!");
		}
		return sb.toString();
	}

	/**
	 * 注释: 读取项目下的文件；并转换为字符串形式
	 *
	 * @param filePath
	 * @return String
	 */
	public synchronized static String readFileToBety(String filePath) {
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		is = FileUtil.class.getClassLoader().getResourceAsStream(filePath);
		int dataLength = 0;
		byte[] by = new byte[1024];
		try {
			while ((dataLength = is.read(by)) != -1) {
				sb.append(new String(by, 0, dataLength, "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
