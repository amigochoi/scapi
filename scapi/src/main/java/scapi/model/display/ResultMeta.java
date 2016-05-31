package scapi.model.display;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;
@Data
public class ResultMeta implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer resultCode;
	private String resultMessage;
	private List<ResultError> resultErrors;
	
	public ResultMeta(){
		
	}
	
	public ResultMeta(Integer resultCode,String resultMessage){
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;

	}
	
	public ResultMeta(Integer resultCode, String resultMessage, List<ResultError> resultErrors) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.resultErrors = resultErrors;
	}
	
}
