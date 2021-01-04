package in.co.sunrays.proj0.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format Input data.
 * 
 * @author Iterator
 * @version 1.0 Copyright (c) Iterator
 * 
 */
public class Util {
	/**
	 * Gets the date.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date getDate(String date) {
		Date date1 = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if (date != null) {
				date1 = sdfr.parse(date);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return date1;
	}
    /**
     * Gets the date.
     *
     * @param indate the indate
     * @return the date
     */

	public static String getDate(Date inDate) {

		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateString = sdfr.format(inDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}
    /**
     * Convert string to date.
     *
     * @param indate the indate
     * @return the string
     */

	public static String convertingStringToDate(Date inDate) {

		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dateString = sdfr.format(inDate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dateString;
	}
}
