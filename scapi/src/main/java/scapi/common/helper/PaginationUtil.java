package scapi.common.helper;


public class PaginationUtil {
	//if use the spring mongo
	/*public static Pageable getPageable(CommonParam param){
		if(ValidationUtil.isNullOrEmpty(param.getSortBy()) || ValidationUtil.isNullOrEmpty(param.getSortOrder()))
			return new PageRequest(param.getPage() - 1,param.getCount());
		else{
			Direction d = param.getSortOrder().equalsIgnoreCase("dsc") ? Sort.Direction.DESC : Sort.Direction.ASC; 
			return new PageRequest(param.getPage() - 1 ,param.getCount(), d, param.getSortBy());
		}
	}*/
	
}
