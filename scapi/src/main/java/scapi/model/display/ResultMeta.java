package scapi.model.display;

import java.io.Serializable;

import lombok.Data;
@Data
public class ResultMeta implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer resultCode;
	private String resultMessage;
	
	public ResultMeta(){
		
	}
	
	public ResultMeta(Integer resultCode,String resultMessage){
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;

	}
}
