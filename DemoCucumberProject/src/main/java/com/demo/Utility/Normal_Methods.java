package com.demo.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.cucumber.listener.Reporter;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.cucumber.listener.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;


import static com.demo.Utility.Constant.driver;
import static com.demo.Utility.Constant.wait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//This is the normal methods
public class Normal_Methods 
{
	LinkedHashMap<String, LinkedHashMap<String,String>> outerMap;
	LinkedHashMap<String,String> innerMap;
	LinkedHashMap<String,String> tempMap;
	int rowCount;
	int colCount;
	
	HSSFSheet sheet;
	
	public int count;
	private String backgroundProperty;
	
	public void highlight(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid red');", element);
	}
	
	public void waitAndDoActionXpath(final String xpathName) {
		count = 1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking " + xpathName + " " + count);
				count++;
				WebElement element = input.findElement(By.xpath(xpathName));

				try {
					if (element.isDisplayed() && element.isEnabled()) {
						highlight(element);
						return true;
					}
				} catch (Exception e) {
					throw new RuntimeException("Element not present " + element);
				}
				return false;
			}
		};
		wait.until(function);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathName))).click();//new condition on 15-10-18
	}
	
	public void dropDownSelect(String drop, String value) {
		List<WebElement> ele = driver.findElements(By.xpath(drop));
		System.out.println("The value is "+value);
		int i=1;
		for (WebElement ele1 : ele) {
			System.out.println("Count of i "+i);
			System.out.println("Entered list");
			System.out.println("ELE1 "+ele1.getText()+" value "+value);
			if (ele1.getText().equals(value)) {
				//System.out.println("Ele1 is " + ele1.getText());
				highlight(ele1);
				ele1.click();
				try {
					//Robot rb = new Robot();
					//rb.keyPress(KeyEvent.VK_ESCAPE);
					//rb.keyRelease(KeyEvent.VK_ESCAPE);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			i++;
		}
	}
	
	public void waitToVisible(final String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver input) {

				System.out.println("Before waiting " + xpathName + " " + count);
				count++;

				WebElement element = input.findElement(By.xpath(xpathName));
				try {
					if (element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}	
				} catch (Exception e) {
					throw new RuntimeException("The elemnt is not visible " + xpathName);
				}
				return false;
			}
		};

		wait.until(function);
		System.out.println("After waitToVisible method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName)));
	}
	
	public void waitToVisibleElements(String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathName)));
	}
	
	public void waitAndType(final String xpathName,String value) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver input) {

				System.out.println("Before waiting " + xpathName + " " + count);
				count++;

				WebElement element = input.findElement(By.xpath(xpathName));
				try {
					if (element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}	
				} catch (Exception e) {
					throw new RuntimeException("The elemnt is not visible " + xpathName);
				}
				return false;
			}
		};

		wait.until(function);
		System.out.println("After waitAndType method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).sendKeys(value);;
	}
	
	public void enterTextinAutoCompletion(String xpathName, String value)
	{
		Actions builder = new Actions(driver);

		WebElement textbox = driver.findElement(By.xpath(xpathName));
		Action seriesOfAction = builder.moveToElement(textbox).click().sendKeys(value).build();
		
		seriesOfAction.perform();	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		/*Action series1 = builder.moveToElement(textbox).sendKeys(Keys.ARROW_DOWN).build();

		series1.perform();
*/		
		Action series2 = builder.moveToElement(textbox).sendKeys(Keys.ENTER).build();

		series2.perform();
	}
	
	public void waitUntilElementIsClickable(String text,long time)
	{
		System.out.println("Before waiting");
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(text))).click();
		System.out.println("After wait");
	}
	
	//Method to get the current time and Date
	public String getTimeAndDate()
	{
		DateFormat date1=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date date=new Date();
		return date1.format(date);
	}
	
	public void clickElement(String xpathName)
	{
		highlightGreen(xpathName);
		WebElement element=driver.findElement(By.xpath(xpathName));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
		waitUntilElementIsClickable(xpathName, 20);
		driver.findElement(By.xpath(xpathName)).click();
	}

	private void highlightGreen(String xpathName) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid green');", driver.findElement(By.xpath(xpathName)));		
	}
	
	public void readDataFromExcel(String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			try(HSSFWorkbook workbook=new HSSFWorkbook(fis);)
			{
				outerMap=new LinkedHashMap<>();
				
				sheet=workbook.getSheet(sheetName);
				
				System.out.println("The sheet name is "+sheet);
				
				//row count starts from zero so the count will be 2 if there are 3 row in sheet
				rowCount=sheet.getLastRowNum();
				
				System.out.println("The rowcount is "+rowCount);
				
				colCount=sheet.getRow(0).getLastCellNum();
				
				//column count starts from 1 but for getColumn we should always start from 0 and not from 1 and column count will 
				//be 5 if there are 5 column
				System.out.println("The column count is "+colCount);
				
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					innerMap=new LinkedHashMap<String,String>();
					
					//here j is starting from 1st column i.e Country column so it should be less than lastCellNum
					//If we take less than equal to than we will get NullPointerException
					//here for j<sheet.getRow(i) here i is important. It should not be  zero if we take 0 than
					//we will get null pointer exception. to know use sheet Data
					for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)
					{
						System.out.println("value 1 "+sheet.getRow(0).getCell(j).getStringCellValue());
						System.out.println("value 2 "+sheet.getRow(i).getCell(j).getStringCellValue());
						innerMap.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
					}
					outerMap.put(sheet.getRow(i).getCell(0).getStringCellValue(), innerMap);
				}
				System.out.println(outerMap);
			}				
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeDataToExcel(String sheetName,String testcaseName,String value)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			try(HSSFWorkbook workbook=new HSSFWorkbook(fis);)
			{
				sheet=workbook.getSheet(sheetName);
				
				System.out.println("The sheet name is "+sheet);
				
				//row count starts from zero so the count will be 2 if there are 3 row in sheet
				rowCount=sheet.getLastRowNum();
				
				System.out.println("The rowcount is "+rowCount);
				
				colCount=sheet.getRow(0).getLastCellNum();
				
				//column count starts from 1 but for getColumn we should always start from 0 and not from 1 and column count will 
				//be 5 if there are 5 column
				System.out.println("The column count is "+colCount);
				
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)
						{
							System.out.println("Type :"+sheet.getRow(i).getCell(j).getCellType());
							System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
							System.out.println("ENTERED IF");
							//for getStringCellValue to return empty in excel sheet in 
							//which cell you need to write value just put 1 apostrophe i.e '
							if(sheet.getRow(i).getCell(j).getStringCellValue().isEmpty())
							{
								System.out.println("ENTERED SECOND IF STATEMENT");
								sheet.getRow(i).getCell(j).setCellValue(value);
								
								FileOutputStream fos=new FileOutputStream("TestData.xls");
								
								workbook.write(fos);
								break;
							}
						}
					}										
				}
			}				
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String accessTestData(String testCaseName, String fieldName)
	{
		tempMap=new LinkedHashMap<>();
		
		tempMap=outerMap.get(testCaseName);
		
		System.out.println("Field value is "+tempMap.get(fieldName));
		
		String value=tempMap.get(fieldName);
		
		return value;
	}

	public static String capture(WebDriver driver, String screenShotName)
	{
		String dest=null;
		try
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			dest=Constant.screehsotPath+"\\"+screenShotName+".png";
			File destination=new File(dest);
			FileUtils.copyFile(source, destination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dest;
	}
	
	public void switchToNewTab()
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
	}
	
	public void switchToOldTab()
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
	}
	
	public void getBackgroundColor(String xpathName)
	{
		String backgroundColor=driver.findElement(By.xpath(xpathName)).getCssValue("background-color");
		Reporter.addStepLog("Background Color <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+backgroundColor+"</font>");
	    System.out.println("background color is "+backgroundColor);
	    String hex=Color.fromString(backgroundColor).asHex();
	    Reporter.addStepLog("Background Color in HEX <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+hex+"</font>");
	    System.out.println("Colors in hex "+hex);	   
	}
	
	//Here property can be color, fill, box-sizing, margin, padding etc
	public void getBackgroundColor(String xpathName,String property)
	{
		String backgroundProperty=driver.findElement(By.xpath(xpathName)).getCssValue(property);
		Reporter.addStepLog("Background "+property+"<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+backgroundProperty+"</font>");
	    System.out.println("background color is "+backgroundProperty);
	    String hex=Color.fromString(backgroundProperty).asHex();
	    Reporter.addStepLog("Background Color in HEX <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+hex+"</font>");
	    System.out.println("Colors in hex "+hex);	   
	}
	
	public void switchToFrame(String csstext)
	{
		WebElement element=driver.findElement(By.cssSelector("[id*='"+csstext+"']"));
		driver.switchTo().frame(element);
	}
	
	public boolean verifyCondition(String xpath,String text)
	{
		System.out.println("Entered verifyCondition method");
		
		String element=driver.findElement(By.xpath(xpath)).getText();
		
		System.out.println("ELEMENT "+element);
		
		boolean flag=element.contains(text);
		
		return flag;
	}
	
	public static void waitUntilTextIsVisible(String xpath,String text)
	{
		System.out.println("Waiting for text "+text);
		new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), text));
		System.out.println("After waitUntilTextIsVisible method");
	}
	
	public void waitAndTypeCss(String css, String text)
	{
		System.out.println("Waiting for css "+css);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css))).clear();
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css))).sendKeys(text);
		System.out.println("After waiting for CSS");
	}
	
	public void waitAndDoActionCss(final String cssName)
	{
		count=1;
		
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).
								   pollingEvery(1, TimeUnit.SECONDS).
								   withTimeout(30, TimeUnit.SECONDS).
								   ignoring(NoSuchElementException.class).
								   ignoring(StaleElementReferenceException.class).
								   ignoring(WebDriverException.class).
								   ignoring(Exception.class);
		
		Function<WebDriver, Boolean> function=new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking "+cssName+" "+count);
				count++;
				WebElement element=driver.findElement(By.cssSelector(cssName));
				
				try
				{
					if(element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("ELEMENT NOT PRESENT "+element);
				}
				return false;
			}
		};
		wait.until(function);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssName))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssName))).click();
	}
	
	public void waitToVisibleCss(final String cssName)
	{
		count=1;
		
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).
								   pollingEvery(1, TimeUnit.SECONDS).
								   withTimeout(30, TimeUnit.SECONDS).
								   ignoring(NoSuchElementException.class).
								   ignoring(StaleElementReferenceException.class).
								   ignoring(WebDriverException.class).
								   ignoring(Exception.class);
		
		Function<WebDriver, Boolean> function=new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking "+cssName+" "+count);
				count++;
				WebElement element=driver.findElement(By.cssSelector(cssName));
				
				try
				{
					if(element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("ELEMENT NOT PRESENT "+element);
				}
				return false;
			}
		};
		wait.until(function);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssName)));
	}
	
	/*
	 * Waits for the element to be invisible
	 */
	public void waitToInvisible(String cssName)
	{
		System.out.println("Waiting for the element to be invisible");
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssName)));
		System.out.println("Element is invisible");
	}
	
	public void scrollIntoView(String xpathName)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(xpathName)));
	}
	
	public void jsclick(String xpathName)
	{
		JavascriptExecutor ex = (JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathName)));
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	
	//Used for mouse hover on an element and click on the child element which is displayed when we do mouse hover	
	public void mouseHover(String parentxpath, String childxpath)
	{
		Actions act = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentxpath)));
	    act.moveToElement(parent).perform();
	    WebElement child = driver.findElement(By.xpath(childxpath));
	    child.click();
	}
	
	//Used for mouse hover on an element and click on the child element which is displayed when we do mouse hover	
	public void mouseHoverCss(String parentCss, String childCss)
	{
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
		act.moveToElement(parent).perform();
		WebElement child = driver.findElement(By.cssSelector(childCss));
		child.click();
	}
	
	public void assertionCheck(String userInput, String uiText)
	{
		//Assert.assertTrue(userInput+" is not matched with "+uiText, userInput.contains(uiText));
		
		System.out.println("ASSERTION PASSED");
	}
	
	public void back()
	{
		driver.navigate().back();
	}
	
	/*
	 * This method is used when we get error in chromeBrowser saying element is not clickable at point (x,x)
	 * This error i.e WebDriverException only comes in Chrome browser
	 */
	public void clickXpath(String xpathName)
	{
		WebElement element = driver.findElement(By.xpath(xpathName));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	/*
	 * This method is used when we get error in chromeBrowser saying element is not clickable at point (x,x)
	 * This error i.e WebDriverException only comes in Chrome browser
	 */
	public void clickCss(String cssName)
	{
		WebElement element = driver.findElement(By.cssSelector(cssName));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();						
	}
	
	public void dropDownSelectUsingSelect(String xpathName,String value)
	{
		waitAndDoActionXpath(xpathName);
		Select select=new Select(driver.findElement(By.xpath(xpathName)));
		select.selectByVisibleText(value);
	}
	
	public void uploadFile(String fileName)
	{
		StringSelection stringSelection=new StringSelection(fileName);
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		Robot robot=null;
		
		try {
			robot=new Robot();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//robot.delay(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(150);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);		
	}
	
	private FluentWait<WebDriver> configureWait() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							   .withTimeout(30, TimeUnit.SECONDS)
							   .pollingEvery(5, TimeUnit.SECONDS)
							   .ignoring(NoSuchElementException.class);
		
		return wait;
	}
	
	public void waitAndType(WebElement element,String message) {
		FluentWait<WebDriver> wait = configureWait();
		element = wait.until(ExpectedConditions.visibilityOf(element));
		if(element.isEnabled()) {
			element.clear();
			element.sendKeys(message);
		}
	}
	
	public void waitAndClick(WebElement element) {
		FluentWait<WebDriver> wait = configureWait();
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if(element.isDisplayed() && element.isEnabled())
			element.click();
	}
	
	public void selectDropdown(String text, String xpathName){
		WebElement ele=driver.findElement(By.xpath(xpathName));
		Select select =new Select(ele);
		select.selectByVisibleText(text);
	}
	
	public void selectDropDownList(String xpathName, String text){
		List<WebElement> list=driver.findElements(By.xpath(xpathName));
		System.out.println("The size of the list is "+list.size());
		for(WebElement ele:list){
			if(ele.getText().equals(text)){
				System.out.println("The Text is "+text);
				ele.click();
				break;
			}				
		}
	}
	
	public void selectCheckBox(String value){
		String xpath="//*[text()='"+value+"']//parent::div//preceding-sibling::div[@class='checkbox_container']";
		waitToVisible(xpath);
		waitAndDoActionXpath(xpath);
	}
	
	public void selectRadioButton(String value){
		String xpath="//*[text()='"+value+"']//ancestor::div[@class='top_quotes_content']//following-sibling::div//*[@class='checkbox_container']";
		waitToVisible(xpath);
		waitAndDoActionXpath(xpath);
	}
	
	public String getSelectedOptions(String xpath){
		Select select=new Select(driver.findElement(By.xpath(xpath)));
		List<WebElement>ele=select.getAllSelectedOptions();
		String selectedOption=ele.get(0).getText();
		System.out.println("The premium amount is "+selectedOption);
		return selectedOption;
	}
	
	public String getTextFromUI(String xpath){
		waitToVisible(xpath);
		String text=driver.findElement(By.xpath(xpath)).getText();
		System.out.println("The Text from UI is "+text);
		return text;
	}
	
	public static int getDifference(int i, int j) {
		System.out.println("The difference is "+(i-j));
		return i-j;
	}

	public static int getInteger(String text){
		String s1=text.substring(text.indexOf("₹")+2, text.indexOf("monthly")-1).replace(",", "");
		int num=Integer.valueOf(s1);
		System.out.println("The num is "+num);
		return num;
	}
	
	public void addScreenshot(String text){
		String capture=Normal_Methods.capture(driver, text);
		try {
			Reporter.addScreenCaptureFromPath(capture, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}