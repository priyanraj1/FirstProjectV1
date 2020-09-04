package DemoSelenium;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int counter=0;
	int retryLimit=3;
	public boolean retry(ITestResult result) {
		
		if(counter < retryLimit)
		 {
		 counter++;
		 return true;
		 }
		
		 return false;
		 }
		}
		
		/*if(!result.isSuccess())
		{
		
		if(counter<retryLimit)
		{
			counter++;
			result.setStatus(result.FAILURE);
			return true;			
		}
		else
		{
			result.setStatus(result.FAILURE);
		}
		
		
	}else
	{
		result.setStatus(result.SUCCESS);
	}
	
		return false;
	
}}*/

