package scapi.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ListParam implements Serializable{
	private static final long serialVersionUID = 1L;
	String sorts;
	Integer page = 0;
	Integer count = 10;
}
