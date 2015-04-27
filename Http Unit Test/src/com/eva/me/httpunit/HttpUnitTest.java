package com.eva.me.httpunit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpUnitTest {

	@Before
	public void setUp() throws Exception {
		HttpUnitOptions.setScriptingEnabled(false);
		HttpUnitOptions.setDefaultCharacterSet("UTF-8");//解决中文乱码问题，设定获得的html的格式是utf-8
	}

	@Test
	public void testResponce() {
		WebConversation wc =  new WebConversation();
//		WebRequest wr = new GetMethodWebRequest("http://www.baidu.com/");
		WebRequest wr = new GetMethodWebRequest("http://waimai.tzwm.me/");
		WebResponse wrps =null;
		try {
			 wrps= wc.getResponse(wr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		File fw = new File("result.html");
		FileOutputStream fOutputStream = null;
		try {
			if (fw.exists()) {
				fw.createNewFile();
			}
			fOutputStream = new FileOutputStream(fw);
		
			String text = wrps.getText();
			System.out.println("text: \n"+text);
			fOutputStream.write(text.getBytes("UTF-8"));//解决中文乱码问题，解决显示问题，要求存进文件的时候存入格式是utf-8
			fOutputStream.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fOutputStream != null) {
				try {
					fOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Test
	public void test() {
//		fail("Not yet implemented");
	}

}
