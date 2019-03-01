package com.dygstudio.testspringboot.dao;

import com.dygstudio.testspringboot.entity.BookEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/01 10:31 AM
 * @description: com.dygstudio.testspringboot.dao
 */
@Repository
public interface BookEsDao extends CrudRepository<BookEs,String> {
    public List<BookEs> getByMessage(String key);
    public Page<BookEs> getByMessage(String key, Pageable pageable);
}
