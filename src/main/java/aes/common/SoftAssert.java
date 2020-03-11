package aes.common;

import java.util.Map;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import aes.reports.Reports;

//public class SoftAssert extends org.testng.asserts.SoftAssert{

public class SoftAssert extends Assertion{
	private final Map<AssertionError, IAssert>	m_errors	= Maps.newHashMap();
	
	public  SoftAssert() {
	}
//	@Override
//	public void executeAssert(IAssert a) {
//	    try {
//	        a.doAssert();
//	    } catch (AssertionError ex) {
//	        m_errors.put(ex, a);
//	      //  throw ex;
//	    }
//	}
	
	protected void doAssert(IAssert assertCommand) {
		  onBeforeAssert(assertCommand);
		  try {
		    executeAssert(assertCommand);
		    onAssertSuccess(assertCommand);
		  } catch(AssertionError ex) {
			  m_errors.put(ex, assertCommand);
		    onAssertFailure(assertCommand, ex);
		   // throw ex;
		  } finally {
		    onAfterAssert(assertCommand);
		  }
		}


	public void assertAll()
	{
		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:\n");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(ae.getValue().getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}
	@Override
	public void onBeforeAssert(IAssert a)
	{
		Reporter.log("");
		Reporter.log("ASSERT DESCRIPTION: " + a.getMessage());
	}
	@Override
	public void onAssertFailure(IAssert a, AssertionError ex)
	{
		try {
			Reports.addLogs("fail",a.getMessage());
			System.out.println(a.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAfterAssert(IAssert a)
	{
	}
	@Override
	public void onAssertSuccess(IAssert a){
		try{
			Reports.addLogs("pass","Assertion Passed");
		}catch(Exception e){
			
		}
	}
}