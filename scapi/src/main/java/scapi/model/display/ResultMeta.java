package scapi.model.display;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;
@Data
public class ResultMeta implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	private List<ResultError> errors;
	
	public ResultMeta(){
		
	}
	
	public ResultMeta(Integer code,String message){
		this.code = code;
		this.message = message;

	}
	
	public ResultMeta(Integer code, String message, List<ResultError> errors) {
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	
}
