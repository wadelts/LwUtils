package lw.utils;

import java.util.*;
import java.io.*;

/**
  * Encapsulates logging activity activity.
  * @author Liam Wade
  * @version 1.0 03/09/2008
  */
public class LwLogger extends FileWriter
{
  /**
    * Will create a new Logger, overwriting the file given.
	* @param fileName the system-dependent filename.
	* @param append boolean if true, then data will be written to the end of the file rather than the beginning.
    */
	public LwLogger(String fileName, boolean append)
							throws IOException {
		super(fileName, append);
	}


	public void appendln(String line)
							throws IOException {

		this.append(getDateTime("-") + ": " + line + "\n");
	}

  /**
    * Get the date and time to stamp record.
	* @param separator the separator to use between Date and Time
    */
	public static String getDateTime(String separator) {
	     TimeZone tz = TimeZone.getDefault();       // Get the local time zone.
		 Calendar c1 = Calendar.getInstance(tz);    // Get the time using the local time zone.

		 // Format Date into nice format
		 int y = c1.get(Calendar.YEAR);
		 String year = new Integer(y).toString();

		 int m = c1.get(Calendar.MONTH);
		 String month = new Integer(m + 1).toString();   // month is 0..11
		 if (month.length() == 1)
			month = "0" + month;

		 int d = c1.get(Calendar.DAY_OF_MONTH);
		 String day = new Integer(d).toString();
		 if (day.length() == 1)
			day = "0" + day;

		 int h = c1.get(Calendar.HOUR_OF_DAY);
		 String hour = new Integer(h).toString();   // hour is 0..23
		 if (hour.length() == 1)
			hour = "0" + hour;

		 int mm = c1.get(Calendar.MINUTE);
		 String minute = new Integer(mm).toString();   // minute is 0..59
		 if (minute.length() == 1)
			minute = "0" + minute;

		 int s = c1.get(Calendar.SECOND);
		 String second = new Integer(s).toString();   // minute is 0..59
		 if (second.length() == 1)
			second = "0" + second;

		 return (year + month + day + separator + hour + minute + second);
	}

	/**
	  * Using the template, construct a real file name.
	  * An asterisk in the template will be replaced by the audit key, a ? by "current date & time & sequence number"
	  *
	  * @param fileNameTemplate the template to use.
	  * @param key the key value to replace the * character
	  *
	  * @return the constructed file name, null if fileNameTemplate was null
	  */
	public static String createFileNameFromTemplate(String fileNameTemplate, String key) {

		if (fileNameTemplate == null) {
			return null;
		}

		StringBuilder constructedFileName = new StringBuilder(fileNameTemplate);

		if (key != null) {
			int startAsterisk = constructedFileName.indexOf("*");

			if (startAsterisk >= 0) { // then "*" found, replace with key
				constructedFileName.replace(startAsterisk, (startAsterisk+1), key);
			}
		}

		int startQuestionMark = constructedFileName.indexOf("?");

		if (startQuestionMark >= 0) { // then "?" found, replace with key
			constructedFileName.replace(startQuestionMark, (startQuestionMark+1), (getDateTime("") + (new Integer(safetySequenceNo++)).toString()) );
		}

		return constructedFileName.toString();
	}

	/**
	  * Using the template, construct a new string, replacing the given chars/strings with the supplied values.
	  *
	  * For AuditKey value replacement, suggest * is used as searchKey for this, but, of course, anything can be used by caller.
	  * For DateTime value replacement, suggest ? is used as searchKey for this, but, of course, anything can be used by caller.
	  * For Sequence Number value replacement, suggest # is used as searchKey for this, but, of course, anything can be used by caller.
	  * For Process ID value replacement, suggest $ is used as searchKey for this, but, of course, anything can be used by caller.
	  *
	  * @param template the template to use to construct the new string.
	  * @param substitutes the set of marker/value pairs driving the replacement of chars/strings.
	  *
	  * @return the re-constructed string, null if template was null
	  */
	public static String createStringFromTemplate(String template, Properties substitutes) {

		if (template == null) {
			return null;
		}

		StringBuilder constructedString = new StringBuilder(template);


		Enumeration<?> e = substitutes.propertyNames();

		if (e != null) {
			while (e.hasMoreElements()) {
				String searchKey = (String)e.nextElement();
				String replacementValue = substitutes.getProperty(searchKey);

				// Replace 'special' values (markers surrounded by [%%]) with actual values
				if (replacementValue.equals("[%datetime%]")) {
					replacementValue = getDateTime("");
				}

				if (replacementValue.equals("[%seqno%]")) {
					replacementValue = (new Integer(safetySequenceNo++)).toString();
				}


				int startPos = constructedString.indexOf(searchKey);
				if (startPos >= 0) { // then pattern found found, replace with new value
					constructedString.replace(startPos, (startPos + searchKey.length()), replacementValue);
				}
			}
		}

		return constructedString.toString();
	}

	private static int safetySequenceNo = 0;						// to help make filenames unique
}
