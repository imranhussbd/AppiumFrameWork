package app.installed.calculator;

import org.testng.annotations.Test;
import base.BaseClass;

public class TestCalculator extends BaseClass{

	@Test(enabled = false)
	public void addition() {
		calculatorObject.addition(8,7);
	}
	
	@Test
	public void multipleNumberAddition() {
		int [] arr = {2,3,5,6,7,8};
		calculatorObject.multiAddition(arr);
	}
}
