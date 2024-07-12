package com.ivoronline.springboot_db_transaction_proxy_annotation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class MyRepository {

  //PROPERTIES
  @Autowired private Connection connection;

  //=========================================================================================================
  // INSERT
  //=========================================================================================================
  public void insert(String name, int age) throws SQLException {
    String    sql       = "INSERT INTO PERSON(NAME, AGE) VALUES('"+name+"',"+age+")";
    Statement statement = connection.createStatement();
              statement.executeUpdate(sql);
  }

}

