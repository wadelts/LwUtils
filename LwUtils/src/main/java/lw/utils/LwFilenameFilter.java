package lw.utils;

import java.io.*;
import java.util.regex.*;

/**
  * Encapsulates a filtering mechanism for file names.
  * @author Liam Wade
  * @version 1.0 29/10/2008
  */
public class LwFilenameFilter implements FilenameFilter
{
  /**
    * Will create a new exception with the given reason.
    *
	* @param pathToName the path to the TAG, including the TAG's name e.g Applic.Auditing.AuditKeys.KeyName
	* @param the value retrieved for the TAG
    */
	public LwFilenameFilter(String pattern) {
		p = Pattern.compile(pattern);
	}


	/**
	  * Match the given name with the pattern
	  * @return true if name matches the pattern, otherwise false
	  */
	public boolean accept(File dir, String name) {
		Matcher m = p.matcher(name);
		return m.matches();
	}

	/**
	  * Return the pattern
	  * @return the pattern for this filter
	  */
	public Pattern getPattern() {
			return p;
	}

  /**
    * Set pattern for comparisons
    *
	* @param pattern the new pattern for comparisons
	*/
	public void setPattern(String newPattern) {
		p = Pattern.compile(newPattern);
	}

	private Pattern p = null;	// the pattern for comparisons
}