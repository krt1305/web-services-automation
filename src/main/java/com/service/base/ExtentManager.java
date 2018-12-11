package com.service.base;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {


   public ExtentKlovReporter klov;
   public ExtentHtmlReporter htmlReporter;
   public ExtentReports reporter;
   public String filePath=System.getProperty("user.dir")+"/resources/ExtentReports.html";

        public ExtentReports getReportInstance()
        {
            System.out.println("IN get report");
            if(reporter==null) {
                System.out.println("reporter is null..initializing");
                klov = new ExtentKlovReporter();
                klov.initKlovServerConnection("http://localhost");
                klov.initMongoDbConnection("localhost", 27017);



                reporter = new ExtentReports();
                reporter.attachReporter(klov,getHtmlReports());
                reporter.setSystemInfo("OS", System.getProperty("os.name"));
                reporter.setSystemInfo("Env", System.getProperty("QA"));

            }
            return reporter;

        }

        public ExtentHtmlReporter getHtmlReports()
        {
            htmlReporter=new ExtentHtmlReporter(filePath);
            htmlReporter.config().setDocumentTitle("WEb service automation");
            htmlReporter.config().setReportName("WEb service automation reports");
            htmlReporter.config().setTheme(Theme.DARK);
            return  htmlReporter;

        }






}
