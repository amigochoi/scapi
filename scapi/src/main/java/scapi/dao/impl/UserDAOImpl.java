package scapi.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import scapi.dao.CounterDAO;
import scapi.dao.UserDAO;
import scapi.model.domain.User;
import scapi.model.request.ListParam;

@Repository("userDAO")
@Slf4j
public class UserDAOImpl implements UserDAO {
	
	private String collectionName = "users";
	
	@Autowired
	private MongoOperations mongoOperation;
	
	@Autowired
	private CounterDAO counterDAO;
	
	@Override
	public List<User> getUsers(User user,ListParam listParam) {
		Query q = this.buildQuery(user);
		String[] sortsArray;
		if(listParam.getSorts() != null && !listParam.getSorts().isEmpty()){
			sortsArray = listParam.getSorts().split(",");
			log.info("sortsArray length : {}",sortsArray.length);
			for(String sort : sortsArray){
				Direction sortOrder  = sort.charAt(0) == '-'  ? Direction.DESC : Direction.ASC;
				String sortField = sort.charAt(0) == '-' ? sort.substring(1,sort.length()) : sort;
				log.info("sort : {} {}", sortOrder,sortField);
				q.with(new Sort(new Order(sortOrder,sortField)));
			}
		}
		Pageable pageableRequest = new PageRequest(listParam.getPage(), listParam.getCount());
		q.with(pageableRequest);
			
		return mongoOperation.find(q, User.class);
	}

	@Override
	public int getUsersCount(User user) {
		return (int)mongoOperation.count(this.buildQuery(user), User.class);
	}

	@Override
	public User getUser(Integer id) {
		return mongoOperation.findOne(new Query().addCriteria(Criteria.where("_id").is(id)), User.class);
	}

	@Override
	public User create(User user) {
		user.setUserId(counterDAO.getNextSequence(collectionName));
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		mongoOperation.insert(user);
		return user;
	}
	
	@Override
	public User update(User oldUser, User user) {
		user.setCreatedAt(oldUser.getCreatedAt());
		user.setUpdatedAt(new Date());
		mongoOperation.save(user);
		return user;
	}

	@Override
	public void delete(User user) {
		mongoOperation.remove(user);
	}
	
	private Query buildQuery(User user) {
		Query q = new Query();
		if(!StringUtils.isEmpty(user.getUserEmail()))
			q.addCriteria(Criteria.where("userEmail").regex(user.getUserEmail(),"i"));
		if(!StringUtils.isEmpty(user.getUserName()))
			q.addCriteria(Criteria.where("userName").regex(user.getUserName(),"i"));
		if(!StringUtils.isEmpty(user.getUserPhone()))
			q.addCriteria(Criteria.where("userPhone").regex(user.getUserPhone(),"i"));
		return q;
	}

	

	
}
