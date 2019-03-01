package com.dygstudio.testspringboot.controller;

import com.dygstudio.testspringboot.dao.BookEsDao;
import com.dygstudio.testspringboot.entity.BookEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author: diyaguang
 * @date: 2019/03/01 10:30 AM
 * @description: com.dygstudio.testspringboot.controller
 */
@RestController
@RequestMapping("/es")
public class BookEsController {

    @Autowired
    BookEsDao bookEsDao;

    @RequestMapping("/book/{id}")
    public @ResponseBody BookEs getBookById(@PathVariable String id){
        Optional<BookEs> opt = bookEsDao.findById(id);
        BookEs book = opt.get();
        return book;
    }

    @RequestMapping("/search/{key}")
    public @ResponseBody List<BookEs> search(@PathVariable String key){
        List<BookEs> list = bookEsDao.getByMessage(key);
        return list;
    }

    @RequestMapping("/search/{key}/{page}")
    public @ResponseBody List<BookEs> search(@PathVariable int page,@PathVariable String key){
        int numberOfPage = 5;
        PageRequest request = PageRequest.of(page,numberOfPage);

        Page<BookEs> pages = bookEsDao.getByMessage(key,request);
        long total = pages.getTotalElements();
        long totalPage = pages.getTotalPages();
        List<BookEs> list = pages.getContent();
        return list;
    }
}
