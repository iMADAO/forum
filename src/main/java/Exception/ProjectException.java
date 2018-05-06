package Exception;

public class ProjectException extends RuntimeException {
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ProjectException(String message){
		super(message);
		this.message = message;
		
	}
	
	
	
}
