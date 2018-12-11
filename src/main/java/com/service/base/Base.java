package com.service.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.service.base.ExtentManager;

public class Base {


         public ExtentManager extentManager=new ExtentManager();
         public ExtentReports reports=extentManager.getReportInstance();
         public ExtentTest test;





}
