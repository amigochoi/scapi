package scapi.model.display;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
public class ResultJson implements Serializable {
	
	public ResultJson() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultJson(Object obj) {
		// TODO Auto-generated constructor stub
		this.dataObject = obj;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success = false;
	private Integer resultCode = -1;
	private Object dataObject;
	private String message = "";
	private String exceptionMsg = "";
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date responseDateTime;
	private String responseServer;
	
}
