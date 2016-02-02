package scapi.model.dto;

import java.io.Serializable;

import scapi.model.domain.User;
import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(User user) {
		// TODO Auto-generated constructor stub
		this.userId= user.getUserId();
		this.userName = user.getUserName();
		this.userEmail = user.getUserEmail();
		this.userPhone = user.getUserPhone();
	}
}
