package scapi.model.display;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ResultError(){
		
	}
	
	public ResultError(String field, Object rejectedValue, String defaultMessage) {
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = defaultMessage;
	}
}
