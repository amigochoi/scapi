package scapi.model.domain;

import java.io.Serializable;

import lombok.Data;
import scapi.model.dto.UserDTO;

@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(UserDTO userDTO) {
		this.userId = userDTO.getUserId();
		this.userName = userDTO.getUserName();
		this.userEmail = userDTO.getUserEmail();
		this.userPhone = userDTO.getUserPhone();

	}
}
