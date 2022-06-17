package com.magento.testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
	
	static ExtentReports extent;
	
	public static void moveFiles(String destination, String source) {
	    File destinationFolder = new File(destination);
	    File sourceFolder = new File(source);

	    if (!destinationFolder.exists())
	    {
	        destinationFolder.mkdirs();
	    }

	    // Check weather source exists and it is folder.
	    if (sourceFolder.exists() && sourceFolder.isDirectory())
	    {
	        // Get list of the files and iterate over them
	        File[] listOfFiles = sourceFolder.listFiles();

	        if (listOfFiles != null)
	        {
	            for (File child : listOfFiles )
	            {
	                // Move files to destination folder
	                child.renameTo(new File(destinationFolder + "\\" + child.getName()));
	            }

	            // Add if you want to delete the source folder 
	            sourceFolder.delete();
	        }
	    }
	    else
	    {
	        System.out.println(sourceFolder + "  Folder does not exists");
	    }
	   
	}

	public static ExtentReports setupExtentReport() throws Exception {
		
		moveFiles(System.getProperty("user.dir")+"/previousReports/Screenshots", System.getProperty("user.dir")+"/reports/Screenshots");
		moveFiles(System.getProperty("user.dir")+"/previousReports", System.getProperty("user.dir")+"/reports");
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String reportPath = System.getProperty("user.dir")+
				"/reports/ExecutionReport_"+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("MagentoTestAutomationReport");
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("MagentoTestAutomation report");
		
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Test Engineers : ", "Luis, Sumit, Suwarna, Anmol, Chetana, Mohan");

		return extent;
	}
}