package priv.kkh.ms;

public class Logger {
	String location;
	
	public Logger(String loc) {		
		location=loc;
	}
	
	public void info(String message) {
		System.out.println("location : "+message);
	}
}
