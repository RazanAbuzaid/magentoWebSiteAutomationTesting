package magentoWebsiteTesting;

import java.awt.Container;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTest {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://magento.softwaretestingboard.com/";
	String logoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";

	Random rand = new Random();
	String password = "Password@123";
	String emailAddress = "";

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get(myWebsite);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}
	// @Test (invocationCount = 6) thats mean The program will run 6 times
	// automatically.

	@Test(priority = 1)
	public void CreatAnAccount() {

		// xpath

		// WebElement createAccountPage =
		// driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));

		// partialLinkText

		// WebElement createAccountPage =
		// driver.findElement(By.partialLinkText("Account"));

		// linkText

		// WebElement createAccountPage = driver.findElement(By.linkText("Create an
		// Account"));

		// cssSelector
		WebElement createAccountPage = driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));

		createAccountPage.click();

		// example
		// String [] ArrayForTheFirstName={"firstname1","firstname2","firstname3"};

		// first name

		String[] First_Names = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Ivy", "Jack" };

		// last name

		String[] Last_Names = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
				"Taylor" };

		String[] email_Domains = { "@gmail.com", "@yahoo.com", "@outlook.com", "@hotmail.com", "@icloud.com" };

		int randomIndexForTheFirstName = (rand.nextInt(First_Names.length));
		int randomIndexForTheLastName = (rand.nextInt(Last_Names.length));
		int randomIndexForemailDomain = (rand.nextInt(email_Domains.length));

		// that will print a number between 0 and arrayLength
		System.out.println(randomIndexForTheFirstName);
		System.out.println(randomIndexForTheLastName);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAddressInput = driver.findElement(By.id("email_address"));
		WebElement passwordInput = driver.findElement(By.id("password"));
		WebElement ConfirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		WebElement CreateAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		String firstname = First_Names[randomIndexForTheFirstName];
		String lastname = Last_Names[randomIndexForTheLastName];
		String domainName = email_Domains[randomIndexForemailDomain];
		int randomnumber = rand.nextInt(9999);
		firstNameInput.sendKeys(firstname);
		lastNameInput.sendKeys(lastname);
		emailAddressInput.sendKeys(firstname + lastname + randomnumber + domainName);
		passwordInput.sendKeys(password);
		ConfirmPasswordInput.sendKeys(password);
		CreateAccountButton.click();

		emailAddress = firstname + lastname + randomnumber + domainName;

		
		
		WebElement ConfirmationMessage = driver.findElement(By.className("message-success"));
		
		String ActualMessage = ConfirmationMessage.getText();
		
		String ExpectedMessage = "Thank you for registering with Main Website Store.";
		
		Assert.assertEquals(ActualMessage,ExpectedMessage);
		
		
		
	}

	@Test(priority = 2)
	public void logout() {

		driver.get(logoutPage);
		
       WebElement SignedOutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper\']"));
		
		String ActualMessage = SignedOutMessage.getText();
		
		String ExpectedMessage = "You are signed out";
		
		Assert.assertEquals(ActualMessage,ExpectedMessage);

	}

	@Test(priority = 3)
	public void loginTest() throws InterruptedException {

		WebElement loginPage = driver.findElement(By.linkText("Sign In"));
		loginPage.click();

		WebElement emailLogin = driver.findElement(By.id("email"));
		WebElement passwordLogin = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		emailLogin.sendKeys(emailAddress);
		passwordLogin.sendKeys(password);
		loginButton.click();
		
		Thread.sleep(3000);

		String WelcomeMessage = driver.findElement(By.cssSelector(".greet.welcome")).getText();

		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true;

		Assert.assertEquals(ActualValue, ExpectedValue);

		
	}

	@Test(priority = 4)
	public void AddMenItem() {
		WebElement MenSection = driver.findElement(By.id("ui-id-5"));

		MenSection.click();

		WebElement ItemsContainer = driver.findElement(By.className("product-items"));

		// System.out.println(ItemsContainer.findElements(By.className("product-items")).size());

		List<WebElement> Items = ItemsContainer.findElements(By.tagName("li"));

		// System.out.println(Items.size());

		int numberOfItems = Items.size();
		int randomItem = rand.nextInt(numberOfItems);

		Items.get(randomItem).click();

		WebElement theContainerOfSizes = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));

		int numberOfSizes = ListOfSizes.size();
		int indexOfSize = rand.nextInt(numberOfSizes);

		ListOfSizes.get(indexOfSize).click();

		WebElement ColorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOfColors = ColorsContainer.findElements(By.tagName("div"));

		// System.out.println(ListOfColors.size());

		int numberOfColor = ListOfColors.size();
		int randomColor = rand.nextInt(numberOfColor);
		ListOfColors.get(randomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		AddToCartButton.click();
		
		
		String MessageAdded = driver.findElement(By.className("message-success")).getText();
		
		// True or False if it's contain "You added" 
		
		boolean ActualValue = MessageAdded.contains("You added");
		boolean ExpectedValue = true;
		System.out.println(ActualValue);

		
		Assert.assertEquals(ActualValue, ExpectedValue );
		
	}
	
	
	
	@Test (priority = 5) 
	public void AddWomenItem() throws InterruptedException {
		
		WebElement WomenSection = driver.findElement(By.id("ui-id-4"));
		
		WomenSection.click();
		
		WebElement ItemsContainer = driver.findElement(By.className("product-items"));

		// System.out.println(ItemsContainer.findElements(By.className("product-items")).size());

		List<WebElement> Items = ItemsContainer.findElements(By.tagName("li"));

		// System.out.println(Items.size());

		int numberOfItems = Items.size();
		int randomItem = rand.nextInt(numberOfItems);

		Items.get(randomItem).click();

		WebElement theContainerOfSizes = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));

		int numberOfSizes = ListOfSizes.size();
		int indexOfSize = rand.nextInt(numberOfSizes);

		ListOfSizes.get(indexOfSize).click();

		WebElement ColorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOfColors = ColorsContainer.findElements(By.tagName("div"));

		// System.out.println(ListOfColors.size());

		int numberOfColor = ListOfColors.size();
		int randomColor = rand.nextInt(numberOfColor);
		ListOfColors.get(randomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		
		AddToCartButton.click();
		
		
		String MessageAdded = driver.findElement(By.className("message-success")).getText();
		
		// True or False if it's contain "You added" 
		
		boolean ActualValue = MessageAdded.contains("You added");
		boolean ExpectedValue = true;
		System.out.println(ActualValue);

		
		Assert.assertEquals(ActualValue, ExpectedValue );
		
		// review section
		
		driver.navigate().refresh();

		
		WebElement ReviewSection = driver.findElement(By.id("tab-label-reviews-title"));
		ReviewSection.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1200)");

		Thread.sleep(2000);

		WebElement RatingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));
		
		
		
		Thread.sleep(2000);

		System.out.println(RatingStars.findElements(By.tagName("label")).size() + "*****************");
//		RatingStars.findElements(By.tagName("label")).get(2).click();
		
		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };
		int randomIndex = rand.nextInt(ids.length);
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		
		JavascriptExecutor StarRate= ((JavascriptExecutor) driver);
				StarRate.executeScript("arguments[0].click();", element);
		
		WebElement nickname = driver.findElement(By.id("nickname_field"));
		nickname.sendKeys("Razan");

		WebElement summary = driver.findElement(By.id("summary_field"));

		summary.sendKeys("NA");

		WebElement review = driver.findElement(By.id("review_field"));

		review.sendKeys("hello this is a test");
	
		WebElement submitReviewButton = driver.findElement(By.cssSelector(".action.submit.primary"));
		
		submitReviewButton.click();
		
			
			String ActualMessage = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
			
			String ExpectedMessage = "You submitted your review for moderation.";
			
			Assert.assertEquals(ActualMessage,ExpectedMessage);
		
		
		
		
		
		
		
		
	}

}
