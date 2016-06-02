package scapi.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import scapi.model.domain.User;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	
	@NotEmpty
	@Length(min = 2,max = 30)
	private String userName;
	
	@Email
	@NotEmpty
	private String userEmail;
	
	@NotEmpty
	@Length(min = 8,max = 13)
	private String userPhone;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Hongkong")
	private Date createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Hongkong")
	private Date updatedAt;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(User user) {
		// TODO Auto-generated constructor stub
		this.userId= user.getUserId();
		this.userName = user.getUserName();
		this.userEmail = user.getUserEmail();
		this.userPhone = user.getUserPhone();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
	}
}
