package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorObject {

	WebDriver driver;

	public CalculatorObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "com.google.android.calculator:id/op_add")
	WebElement addition;
	@FindBy(id = "com.google.android.calculator:id/eq")
	WebElement equal;
	@FindBy(id = "com.google.android.calculator:id/result_final")
	WebElement result;

	public void addition(int a, int b) {
		if (a > 9 || b > 9 || a < 0 || b < 0) {

		} else {
			WebElement firstDigit = driver.findElement(By.id("com.google.android.calculator:id/digit_" + a));
			firstDigit.click();
			addition.click();
			WebElement secondDigit = driver.findElement(By.id("com.google.android.calculator:id/digit_" + b));
			secondDigit.click();
			equal.click();
			System.out.println(result.getText());
		}
	}

	public void multiAddition(int... a) {
		for(int i : a) {
			WebElement firstDigit = driver.findElement(By.id("com.google.android.calculator:id/digit_" + i));
			firstDigit.click();
			addition.click();
		}
		equal.click();
		System.out.println(result.getText());
	}

}
