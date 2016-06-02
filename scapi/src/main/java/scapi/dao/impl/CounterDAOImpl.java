package scapi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import scapi.dao.CounterDAO;
import scapi.model.domain.Counter;

@Repository("counterDAO")
public class CounterDAOImpl implements CounterDAO {

	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public Integer getNextSequence(String collectionName) {
		Counter counter = mongoOperation.findAndModify(
				new Query(Criteria.where("_id").is(collectionName)),
				new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
				Counter.class);
		return counter.getSeq();
	}

}
