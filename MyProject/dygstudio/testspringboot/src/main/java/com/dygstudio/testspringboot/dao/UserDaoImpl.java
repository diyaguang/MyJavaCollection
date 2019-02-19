package com.dygstudio.testspringboot.dao;

import com.dygstudio.testspringboot.entity.User;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/02/18 10:24 AM
 * @description: com.dygstudio.testspringboot.dao
 */
@Repository
public class UserDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    SQLManager sqlManager;

    public int getCount(){
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from user",Integer.class);
        return rowCount;
    }

    public int getCountAtDepartment(int departmentId){
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from user where department_id=?",Integer.class,departmentId);
        return rowCount;
    }

    public int getCountAtDepartment2(int departmentId){
        String sql = "select count(1) from user where department_id=:deptId";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("deptId",departmentId);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql,namedParameters,Integer.class);
        return count;

    }

    public User getUser(Long userId){
        String sql = "select * from user where user_id=? ";
        User user = this.jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setDepartment_id(resultSet.getInt("department_id"));
                return user;
            }
        },userId);
        return user;
    }

    public List<User> getUserBySqlFile(){
        User query = new User();
        query.setName("NewName");
        //参数还可以使用 Map格式
        // Map paras = new HashMap();
        // paras.put("name","NewName");
        List<User> list = sqlManager.select("user.selectSample",User.class,query);
        return list;
    }

    public Integer insertUser(final User user){
        final String sql = "insert into user(name,department_id) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //指出自增主键的列名
                PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
                ps.setString(1,user.getName());
                ps.setInt(2,user.getDepartment_id());
                return ps;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }
}