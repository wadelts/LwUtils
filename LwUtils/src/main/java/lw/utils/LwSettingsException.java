package lw.utils;

/**
  * Encapsulates exceptions resulting from errors returned when getting config settings for an application.
  * @author Liam Wade
  * @version 1.0 07/11/2008
  */
public class LwSettingsException extends Exception
{
  /**
    * Will create a new exception.
    */
	public LwSettingsException() {
	}

  /**
    * Will create a new exception with the given reason.
	* @param reason the text explaining the error
    */
	public LwSettingsException(String reason) {
		super(reason);
	}
}