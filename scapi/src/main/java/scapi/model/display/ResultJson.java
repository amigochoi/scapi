package scapi.model.display;

import java.io.Serializable;

import scapi.model.User;
import lombok.Data;

@Data
public class ResultJson implements Serializable {
	private static final long serialVersionUID = 1L;
	Object data;
	ResultMeta meta;
	ResultPagination pageination;
	
	public ResultJson(){
		
	}
	
	public ResultJson(Object data, Integer resultCode, String resultMessage, ResultPagination pageination) {
		this.data = data;
		this.meta = new ResultMeta(resultCode, resultMessage);
		this.pageination = pageination;
	}
}
