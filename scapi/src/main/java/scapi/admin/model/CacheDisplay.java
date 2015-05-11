package scapi.admin.model;

import java.io.Serializable;
import lombok.Data;
@Data
public class CacheDisplay implements Serializable{
	
	private static final long serialVersionUID = 7203158199668797252L;
	private String cacheName;
	private Integer numberOfElements;
	private String avgTime;
	private String status;
	private Long liveSeconds;
	private Long sizeOfMomery;
	private Long inMemorySize;

}