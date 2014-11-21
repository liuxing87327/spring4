package com.dooioo.mongodb.service;

import com.dooioo.mongodb.model.Employee;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 功能说明：MongodbService
 * 作者：liuxing(2014-11-21 04:01)
 */
@Service
public class MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据主键查询一个记录
     * @param userCode
     * @return
     */
    public Employee findOne(String userCode) {
        return mongoTemplate.findOne(new Query(Criteria.where("userCode").is(userCode)), Employee.class);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Employee> findAll() {
        return mongoTemplate.find(new Query(), Employee.class);
    }

    /**
     * 根据正则表达式查询数据
     * @param regex
     * @return
     */
    public List<Employee> findByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria("userName").regex(pattern.toString());
        return mongoTemplate.find(new Query(criteria), Employee.class);
    }

    /**
     * 新增一个记录
     * @param employee
     */
    public void insert(Employee employee) {
        mongoTemplate.insert(employee);
    }

    /**
     * 更新第一条数据
     * @param userCode
     * @return
     */
    public boolean updateFirst(String userCode){
        Query query = new Query(Criteria.where("userCode").is(userCode));
        Update update = new Update().set("updatedAt", DateTime.now().getMillis());
        return mongoTemplate.updateFirst(query, update, Employee.class).getN() > 0;
    }

    /**
     * 删除所有的记录
     * @return
     */
    public boolean removeAll() {
        List<Employee> list = this.findAll();
        if (list != null) {
            for (Employee employee : list) {
                mongoTemplate.remove(employee);
            }
        }
        return true;
    }

    /**
     * 根据主键删除一条记录
     * @param userCode
     * @return
     */
    public boolean removeOne(String userCode) {
        Criteria criteria = Criteria.where("userCode").in(userCode);
        if (criteria != null) {
            return false;
        }

        Query query = new Query(criteria);
        if (query == null) {
            return false;
        }

        Employee employee = mongoTemplate.findOne(query, Employee.class);
        if (employee == null) {
            return false;
        }

        return mongoTemplate.remove(employee).getN() > 0;
    }

}
