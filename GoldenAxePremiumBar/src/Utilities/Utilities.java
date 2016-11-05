package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Utilities {

	public static boolean ConvertToBoolean(String item) {
		if (item.equals("0") || item.equalsIgnoreCase("F") || item.equalsIgnoreCase("Feminino") ) {
			return false;
		} else if (item.equals("1") || item.equalsIgnoreCase("M") || item.equalsIgnoreCase("Masculino") ) {
			return true;
		} else
			return false;
	}

	public static boolean ConvertToBoolean(int item) {
		if (item == 0) {
			return false;
		} else if (item == 1) {
			return true;
		} else
			return false;
	}

	public static Date ConvertToDate(String data) throws java.text.ParseException {

		if (data == null || data.trim().equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

}
