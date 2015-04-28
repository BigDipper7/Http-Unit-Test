package com.eva.me.httpunit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpUnitTest {
	private WebConversation wc = null;
	private final String webLink = "http://sse.tongji.edu.cn/zhaoqinpei/";
	private final String charSet = "gb2312";

	@Before
	public void setUp() throws Exception {
		HttpUnitOptions.setScriptingEnabled(false);
		HttpUnitOptions.setDefaultCharacterSet(charSet);//解决中文乱码问题，设定获得的html的格式是utf-8
	}

	@Test
	public void testResponce() {
		wc =  new WebConversation();
//		WebRequest wr = new GetMethodWebRequest("http://www.baidu.com/");
		WebRequest wr = new GetMethodWebRequest(webLink);
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
			fOutputStream.write(text.getBytes(charSet));//解决中文乱码问题，解决显示问题，要求存进文件的时候存入格式是utf-8
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
	public void testLinkClick() {
		if (wc == null) {
			wc = new WebConversation();
		}
		OutputStream oStream = null;
		try {
			WebResponse wrps = wc.getResource(new GetMethodWebRequest(webLink));
			WebLink wLink = wrps.getLinkWith("Study and Teaching");
			WebResponse wResponse = wLink.click();
			
			String htmlContent = wResponse.getText();
			oStream = new FileOutputStream("Study and Teaching.html");
			oStream.write(htmlContent.getBytes(charSet));
			oStream.flush();
			
			System.out.println("\nText:\n"+htmlContent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Link get exception...");
			e.printStackTrace();
		} finally {
			if (oStream !=null) {
				try {
					oStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
