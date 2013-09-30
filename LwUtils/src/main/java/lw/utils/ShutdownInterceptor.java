package lw.utils;

/**
  * Will be the thread called when the VM is shutting down.
  * Used graceful shutdown of servers.
  * @author Liam Wade
  * @version 1.0 29/08/2008
  */
public class ShutdownInterceptor extends Thread {

private IApp app;

public ShutdownInterceptor(IApp app) {
	this.app = app;
}

	public void run() {
		app.shutDown();
	}
}
