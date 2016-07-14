package scapi.model.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "counters")
@Data
public class Counter implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	 private String id;
	 private int seq;
}
