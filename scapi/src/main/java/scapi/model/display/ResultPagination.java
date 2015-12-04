package scapi.model.display;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultPagination implements Serializable{
	private static final long serialVersionUID = 1L;
	Integer skip;
	Integer limit;
	Integer total;
	
	public ResultPagination(){
		
	}
	
	public ResultPagination(Integer skip,Integer limit, Integer total){
		this.skip = skip;
		this.limit = limit;
		this.total = total;
	}
}
