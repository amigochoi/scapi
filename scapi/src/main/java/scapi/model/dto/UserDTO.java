package scapi.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import scapi.model.domain.User;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	@NotEmpty
	private String userName;
	@Email
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
