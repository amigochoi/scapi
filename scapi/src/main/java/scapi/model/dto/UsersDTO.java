package scapi.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UsersDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	List<UserDTO> users;
	int total;
	
	public UsersDTO() {

	}
	
	public UsersDTO(List<UserDTO> userDTOs, int total) {
		this.users = userDTOs;
		this.total = total;
	}
	
}
