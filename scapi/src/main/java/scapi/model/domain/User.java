package scapi.model.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.support.IsNewStrategy;

import lombok.Data;
import scapi.model.dto.UserDTO;

@Document(collection = "users")
@Data
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private Date createdAt;
	private Date updatedAt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(UserDTO userDTO) {
		this.userId = userDTO.getUserId();
		this.userName = userDTO.getUserName();
		this.userEmail = userDTO.getUserEmail();
		this.userPhone = userDTO.getUserPhone();
		this.createdAt = userDTO.getCreatedAt();
		this.updatedAt = userDTO.getUpdatedAt();
	}

	public User(Integer userId, String userName, String userEmail, String userPhone) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}
}
