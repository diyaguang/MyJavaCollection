package com.dygstudio.testspringboot2.api;

import com.dygstudio.testspringboot2.service.SAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: diyaguang
 * @date: 2019/03/11 12:00 PM
 * @description: com.dygstudio.testspringboot2.api
 */
@RestController
@RequestMapping("/api/account")
public class AccountAPI {
    @Autowired
    SAccountService accountService;

    
}
