package com.lokiz.ibatis.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utils {

	private static final Log log = LogFactory.getLog(Utils.class);
	private static final int STRING_LIMIT = 8000;
	private static final int STRING_MAX_CAPACITY = 7500;

	public static <T extends CSVObjectWriter> String generateFile(List<T> list, String filePath) {

		log.info("Saving results into csv file: " + filePath);
		File f = null;
		f = new File(filePath);
		FileWriter writer = null;
		try {
			writer = new FileWriter(f);

			for (T record : list) {
				writer.append(record.objectWriter());
				writer.append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return f.getAbsolutePath();
	}

	/**
	 * If the string exceeds the amount of characters allowed by the DB then
	 * shrink it.
	 * 
	 * Replace all "," and "\n" characters in string so the data can be saved
	 * into a csv file
	 * 
	 * @param str
	 * @return
	 */
	public static String getStringValue(String str) {
		if (str != null) {
			if (str.length() > STRING_LIMIT) {
				return str.substring(0, STRING_MAX_CAPACITY).replaceAll(",", "").replaceAll("\n", "").replaceAll("\r",
						"");
			} else {
				return str.replaceAll(",", "").replaceAll("\n", "").replaceAll("\r", "");
			}
		}
		return str;
	}

	/**
	 * Number to string converter
	 * 
	 * @param num
	 * @return
	 */
	public static <T extends Number> String getStringValue(T num) {
		if (num == null) {
			return 0 + "";
		} else {
			return num + "";
		}
	}
}
