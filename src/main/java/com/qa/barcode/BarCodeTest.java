package com.qa.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeTest {
	
	@Test
	public void barCodeTest() throws IOException, NotFoundException {
		
		System.setProperty("webdriver.chrome.driver", "E:/My Backups/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//driver.get("https://barcode.tec-it.com/en");
		
		//driver.get("https://barcode.tec-it.com/en/Code128?data=hi%20I%20am%20murugan%0A");
		
		driver.get("https://barcode.tec-it.com/en/Code128?data=hi how r u");
		
		String barcodeURL = driver.findElement(By.tagName("img")).getAttribute("src");
		
		System.out.println(barcodeURL);
		
		URL url = new URL(barcodeURL);
		
		BufferedImage bufferedimage = ImageIO.read(url);
		
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedimage);
		
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result = new MultiFormatReader().decode(binaryBitmap);
		
		System.out.println(result.getText());
	}

}
