package com.dooioo.mongodb.service;

import com.dooioo.mongodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 功能说明：MongodbService
 * 作者：liuxing(2014-11-21 04:01)
 */
@Service
public class MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Employee findOne(String userCode) {
        return mongoTemplate.findOne(new Query(Criteria.where("userCode").is(userCode)), Employee.class);
    }

}
