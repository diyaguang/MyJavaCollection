package com.dygstudio.testspringboot2.api;

import com.dygstudio.testspringboot2.entity.SAccount;
import com.dygstudio.testspringboot2.service.SAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/11 12:00 PM
 * @description: com.dygstudio.testspringboot2.api
 */
@RestController
@RequestMapping("/api/account")
public class SAccountAPI {
    @Autowired
    SAccountService sAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SAccount> getSAccounts(){
        return sAccountService.findAccountList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SAccount getAccountById(@PathVariable("id") String id){
        return sAccountService.findAccountById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") String id, @RequestParam(value = "name",required = true) String name){
        SAccount account = new SAccount();
        account.setId(id);
        account.setName(name);
        int t = sAccountService.update(account);
        if(t==1){
            return account.toString();
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name){
        SAccount account = new SAccount();
        account.setId(id);
        account.setName(name);
        int t = sAccountService.add(account);
        if(t==1){
            return account.toString();
        }else{
            return "fail";
        }
    }

}
