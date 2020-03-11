package mainclass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.os.CommandLine;
import org.testng.CommandLineArgs;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import com.beust.jcommander.JCommander;

public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println("Program Arguments:");
        for (String arg : args) {
            System.out.println("\t" + arg);
        }
		
        String userid = args[0];
        String password = args[1];
        String server = args[2];
        System.out.println("Userid is : " + userid);
        System.out.println("Server is : " + server); */
		String xmlFileName = "./testng.xml";
		List<XmlSuite> suite;
		TestNG testng = new TestNG();

		try
		{
		    suite = (List <XmlSuite>)(new Parser(xmlFileName).parse());
		    testng.setXmlSuites(suite);
		    testng.run();
		}
		catch (ParserConfigurationException e)
		{
		    e.printStackTrace();
		}
		catch (SAXException e)
		{
		    e.printStackTrace();
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	
	}
	

}

