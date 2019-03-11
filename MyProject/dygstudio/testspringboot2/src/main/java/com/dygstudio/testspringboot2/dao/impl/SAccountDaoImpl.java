package com.dygstudio.testspringboot2.dao.impl;

import com.dygstudio.testspringboot2.dao.SAccountDao;
import com.dygstudio.testspringboot2.entity.SAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/11 11:29 AM
 * @description: com.dygstudio.testspringboot2.dao.impl
 */
@Repository
public class SAccountDaoImpl implements SAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(SAccount account){
        return jdbcTemplate.update("insert into s_account(id,name) values (?,?)",account.getId(),account.getName());
    }
    @Override
    public int update(SAccount account){
        return jdbcTemplate.update("Update s_account set name=? where id=?",account.getName(),account.getId());
    }
    @Override
    public int delete(String id){
        return jdbcTemplate.update("delete  from s_account where id=?",id);
    }
    @Override
    public SAccount findAccountById(String id){
        List<SAccount> list = jdbcTemplate.query("select * from s_account where id=?",new Object[]{id},new BeanPropertyRowMapper<>(SAccount.class));
        if(list!=null&&list.size()>0){
            SAccount account = list.get(0);
            return account;
        }else{
            return null;
        }
    }
    @Override
    public List<SAccount> findAccountList(){
        List<SAccount> list = jdbcTemplate.query("select * from s_account ",new Object[]{},new BeanPropertyRowMapper<>(SAccount.class));
        if(list!=null&&list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
