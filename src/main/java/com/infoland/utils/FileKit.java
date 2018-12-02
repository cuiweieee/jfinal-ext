package com.infoland.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;

import com.jfinal.kit.LogKit;

/**
 * @Description
 * @author Master.C
 * @date 2017年5月17日 下午1:36:25
 * @version V1.0.0
 */
@Deprecated
public class FileKit {

	public static String readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				sb.append((char) tempchar);
			}
		} catch (IOException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LogKit.warn(ExceptionKit.getStackTraceAsString(e));
				}
			}
		}
		return sb.toString();
	}

	public static String readFileByLine(String fileName, String encodeType) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuilder str = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			for (; (tempString = reader.readLine()) != null;) {
				str.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					LogKit.warn(ExceptionKit.getStackTraceAsString(e1));
				}
			}
		}
		try {
			return new String(str.toString().getBytes(encodeType));
		} catch (UnsupportedEncodingException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
			return null;
		}
	}

	public static void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();
			out = fo.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				LogKit.warn(ExceptionKit.getStackTraceAsString(e));
			}
		}
	}

	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	public static void download(String path, String url, String fileName) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		InputStream in = connection.getInputStream();
		FileOutputStream os = new FileOutputStream(path + File.separator + fileName);
		byte[] buffer = new byte[4 * 1024];
		int read;
		for (; (read = in.read(buffer)) > 0;) {
			os.write(buffer, 0, read);
		}
		os.close();
		in.close();
	}

	public static void writeString2File(String content, String fileName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new ByteArrayInputStream(content.getBytes()));
			bos = new BufferedOutputStream(new FileOutputStream(fileName));
			byte[] bys = new byte[1024];
			int len = 0;
			for (; (len = bis.read(bys)) != -1;) {
				bos.write(bys, 0, len);
			}
		} catch (IOException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					LogKit.warn(ExceptionKit.getStackTraceAsString(e));
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					LogKit.warn(ExceptionKit.getStackTraceAsString(e));
				}
			}
		}
	}

	public static void appendString2File(String content, String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			LogKit.warn(ExceptionKit.getStackTraceAsString(e));
		}
	}
}
