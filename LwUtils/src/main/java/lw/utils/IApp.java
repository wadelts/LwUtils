package lw.utils;

/**
  * Encapsulates start and run methods for startup & graceful shutdown of servers.
  * @author Liam Wade
  * @version 1.0 29/08/2008
  */
public interface IApp {

void start();

void shutDown();
}

