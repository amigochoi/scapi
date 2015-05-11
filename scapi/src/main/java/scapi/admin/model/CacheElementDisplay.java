package scapi.admin.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class CacheElementDisplay implements Serializable{
	
	private static final long serialVersionUID = -7086733527885858132L;
	private String cacheKey;
	private String creationTime;
	private String expirationTime;
	private String lastAccessTime;
	private Integer timeToLive;
	private Long hitCount;
	private Integer remainTime;
	private Long sizeOfMomery;

}
