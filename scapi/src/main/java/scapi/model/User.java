package scapi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
}
