package com.dygstudio.testspringboot2.api;

import com.dygstudio.testspringboot2.dao.BCompanyDao;
import com.dygstudio.testspringboot2.entity.BCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/11-15:03
 * @Description:
 */
@RestController
@RequestMapping("/api/company")
public class BCompanyAPI {
    @Autowired
    BCompanyDao companyDao;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<BCompany> getCompany(){
        return companyDao.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BCompany getCompanyById(@PathVariable("id") String id){
        return companyDao.findById(id).get();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateCompany(@PathVariable("id") String id, @RequestParam(value = "name",required = true) String name){
        BCompany company = new BCompany();
        company.setId(id);
        company.setName(name);
        BCompany companyNew = companyDao.saveAndFlush(company);
        return companyNew.toString();
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postCompany(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "name") String name){
        BCompany company = new BCompany();
        company.setId(id);
        company.setName(name);
        BCompany companyNew = companyDao.save(company);
        return companyNew.toString();
    }
}
