package com.dygstudio.testspringboot2.dao;

import com.dygstudio.testspringboot2.entity.BCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BCompanyDao extends JpaRepository<BCompany,String> {
}
