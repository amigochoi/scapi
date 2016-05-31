package scapi.model.display;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ResultJson implements Serializable {
	private static final long serialVersionUID = 1L;
	Object data;
	ResultMeta meta;
	ResultPagination pageination;
	
	public ResultJson(){
		
	}
	
	//noraml data callback
	public ResultJson(Integer resultCode){
		this.meta = new ResultMeta(resultCode, null);
	}
	
	//data callback for aop
	public ResultJson(Integer resultCode, String resultMessage){
		this.meta = new ResultMeta(resultCode, resultMessage);
	}
	
	public ResultJson(Integer resultCode,Object data, ResultPagination pageination) {
		this.data = data;
		this.meta = new ResultMeta(resultCode, null);
		this.pageination = pageination;
	}
	
	public ResultJson(Integer resultCode,Object data) {
		this.data = data;
		this.meta = new ResultMeta(resultCode, null);
	}

	public ResultJson(List<FieldError> allErrors) {
		List<ResultError> resultErrors = new ArrayList<ResultError>();
		
		for(FieldError error : allErrors){
			resultErrors.add(new ResultError(error.getField(),error.getRejectedValue(),error.getDefaultMessage()));
		}
		
		this.meta = new ResultMeta(4001, null, resultErrors);
	}
}
