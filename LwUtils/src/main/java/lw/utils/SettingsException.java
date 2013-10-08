package lw.utils;

/**
  * Encapsulates exceptions resulting from errors returned when getting config settings for an application.
  * @author Liam Wade
  * @version 1.0 07/11/2008
  */
public class SettingsException extends Exception
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * Will create a new exception.
    */
	public SettingsException() {
	}

  /**
    * Will create a new exception with the given reason.
	* @param reason the text explaining the error
    */
	public SettingsException(String reason) {
		super(reason);
	}
}