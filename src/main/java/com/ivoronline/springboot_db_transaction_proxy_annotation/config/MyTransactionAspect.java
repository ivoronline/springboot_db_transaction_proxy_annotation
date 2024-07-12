package com.ivoronline.springboot_db_transaction_proxy_annotation.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Connection;

@Aspect
@Component
public class MyTransactionAspect {

  //PROPERTIES
  @Autowired private Connection connection;

  //========================================================
  // LOG START END
  //========================================================
  @Around("@annotation(MyTransaction)")
  public void logStartEnd(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
 
    //LOG
    String methodName = proceedingJoinPoint.getSignature().getName();
    String className  = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
    System.out.println("STARTED METHOD: " + className + "." + methodName + "()");
  
    //TRANSACTION
    try {

      //START TRANSACTION
      connection.setAutoCommit(false);

      //CALL SERVICE
      proceedingJoinPoint.proceed();

      //COMMIT TRANSACTION
      connection.commit();

    }
    catch (Exception e) {
      //ROLLBACK TRANSACTION
      connection.rollback();
    }
    finally {
      connection.close();
    }

  }

}
