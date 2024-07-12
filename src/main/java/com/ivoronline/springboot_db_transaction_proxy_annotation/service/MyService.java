package com.ivoronline.springboot_db_transaction_proxy_annotation.service;

import com.ivoronline.springboot_db_transaction_proxy_annotation.config.MyTransaction;
import com.ivoronline.springboot_db_transaction_proxy_annotation.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private MyRepository repository;

  //=========================================================================================================
  // INSERT
  //=========================================================================================================
  @MyTransaction
  public void insert() throws Exception {

    //INSERT RECORDS
    for (int i = 1; i <= 2; i++) {
      if(i==2) { throw new Exception("Exception"); }
      repository.insert("Person " + i, 10 * i);
    }

  }

}


