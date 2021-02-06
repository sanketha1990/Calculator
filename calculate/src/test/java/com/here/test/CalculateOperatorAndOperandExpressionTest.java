package com.here.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Stack;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.here.main.CalculateOperatorAndOperandExpression;

public class CalculateOperatorAndOperandExpressionTest {

	@Test
	@Tag("Valid")
	public void testTrueValidExpression() {
		String exp = "2 3 +";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueValidExpressionIncludeZero() {
		String exp = "2 0 +";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseInvalidExpression() {
		String exp = "2 3 + &";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseWhenExprHasAlpha() {
		String exp = "2 3 + a";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseWhenExprHasSpecialCharacter() {
		String exp = "2 3 + %^";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertFalse(result);

	}

	@Test
	@Tag("Valid")
	public void testTrueWhenExprHasTrailingSpace() {
		String exp = " 2 3 + 5 + ";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueWhenExprHasZero() {
		String exp = "2 3 + 0 +";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueWhenExprHasMoreThanOneDigit() {
		String exp = "2 3 + 12 +";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueWhenExprHasTrailingSpaceBtwExp() {
		String exp = " 2 3 +  5 + ";
		String[] expArr = exp.split(" ");
		boolean result = CalculateOperatorAndOperandExpression.isInputValid(expArr);
		assertTrue(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueValidNumber() {
		String str = "2";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertTrue(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForInvalidNumber() {
		String str = "a";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForAlpha() {
		String str = "A";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForSpecialChar() {
		String str = ")";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForOperator() {
		String str = "+";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForSpace() {
		String str = "";
		boolean result = CalculateOperatorAndOperandExpression.isNumber(str);
		assertFalse(result);

	}

	@Test
	@Tag("Valid")
	public void testTrueValidOperator() {
		String str = "+";
		boolean result = CalculateOperatorAndOperandExpression.isOperator(str);
		assertTrue(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseInvalidOperator() {
		String str = "%";
		boolean result = CalculateOperatorAndOperandExpression.isOperator(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseSpecialCharInOperator() {
		String str = "&";
		boolean result = CalculateOperatorAndOperandExpression.isOperator(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseAlphaInOperator() {
		String str = "S";
		boolean result = CalculateOperatorAndOperandExpression.isOperator(str);
		assertFalse(result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseSpaceInOperator() {
		String str = "";
		boolean result = CalculateOperatorAndOperandExpression.isOperator(str);
		assertFalse(result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForCalculate() {
		int num1 = 5, num2 = 6;
		String operator = "+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(11, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForCalculateWithZero() {
		int num1 = 5, num2 = 0;
		String operator = "+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(5, result);
	}

	@Test
	@Tag("Invalid")
	public void testFalseForCalculate() {
		int num1 = 5, num2 = 6;
		String operator = "+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertNotSame(12, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForSubCalculate() {
		int num1 = 5, num2 = 6;
		String operator = "-";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(-1, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForSubCalculateWithZero() {
		int num1 = 0, num2 = 6;
		String operator = "-";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(-6, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForMultiplicationCalculate() {
		int num1 = 5, num2 = 6;
		String operator = "*";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(30, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForMultiplicationCalculateWithZero() {
		int num1 = 5, num2 = 0;
		String operator = "*";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(0, result);
	}

	@Test
	@Tag("Valid")
	public void testTrueForDivisionCalculate() {
		int num1 = 6, num2 = 2;
		String operator = "/";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(3, result);
	}

	@Test
	@Tag("Valid")
	public void testForDivisionByZeroCalculate() {
		int num1 = 6, num2 = 0;
		String operator = "/";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(num1, result);
	}

	@Test
	@Tag("Valid")
	public void testForNegativeNumber() {
		int num1 = 6, num2 = -8;
		String operator = "+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(-2, result);
	}

	@Test
	@Tag("Valid")
	public void testForNegativeNumbers() {
		int num1 = -6, num2 = -8;
		String operator = "+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(-14, result);
	}

	@Test
	@Tag("Valid")
	public void testMultiplicationForNegativeNumbers() {
		int num1 = -6, num2 = 2;
		String operator = "*";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertSame(-12, result);
	}

	@Test
	@Tag("Invalid")
	public void testCalculationForInvalidOperator() {
		int num1 = -6, num2 = 2;
		String operator = "*+";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertEquals(Integer.MIN_VALUE, result);
	}
	@Test
	@Tag("Invalid")
	public void testCalculationForIncrement() {
		int num1 = -6, num2 = 2;
		String operator = "++";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertEquals(Integer.MIN_VALUE, result);
	}
	
	@Test
	@Tag("Invalid")
	public void testCalculationForDecrement() {
		int num1 = -6, num2 = 2;
		String operator = "--";
		int result = CalculateOperatorAndOperandExpression.calculate(num1, num2, operator);
		assertEquals(Integer.MIN_VALUE, result);
	}

	@Test
	@Tag("Valid")
	public void testResultCalculation() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("2");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertSame(5, result);
	}

	@Test
	@Tag("Valid")
	public void testResultCalculationWithZero() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("0");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertSame(3, result);
	}

	@Test
	@Tag("Invalid")
	public void testWrongResultForResultCalculation() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("2");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertNotSame(6, result);
	}

	@Test
	@Tag("Invalid")
	public void testResultCalculationForNotNumber() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("a");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertEquals(Integer.MIN_VALUE, result);
	}

	@Test
	@Tag("Invalid")
	public void testResultCalculationForNotBalancedExp() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("2");
		stack.add("3");
		stack.add("7");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertEquals(Integer.MIN_VALUE, result);
	}

	@Test
	@Tag("Invalid")
	public void testResultCalculationForNotOperator() {
		Stack<String> stack = new Stack<>();
		stack.add("(");
		stack.add("2");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertEquals(Integer.MIN_VALUE, result);
	}

	@Test
	@Tag("Invalid")
	public void testResultCalculationForNotValidOperator() {
		Stack<String> stack = new Stack<>();
		stack.add("++");
		stack.add("2");
		stack.add("3");
		int result = CalculateOperatorAndOperandExpression.resultCalculation(stack);
		assertEquals(Integer.MIN_VALUE, result);
	}

	@Test
	@Tag("CornerCase")
	public void testCornerCaseMaxValue() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("2147483649");
		stack.add("3");
		Exception exception = assertThrows(NumberFormatException.class,
				() -> CalculateOperatorAndOperandExpression.resultCalculation(stack));

		assertTrue(exception.getClass().toString().contains("NumberFormatException"));
	}

	@Test
	@Tag("CornerCase")
	public void testCornerCaseMinValue() {
		Stack<String> stack = new Stack<>();
		stack.add("+");
		stack.add("-2147483649");
		stack.add("3");
		Exception exception = assertThrows(NumberFormatException.class,
				() -> CalculateOperatorAndOperandExpression.resultCalculation(stack));

		assertTrue(exception.getClass().toString().contains("NumberFormatException"));
	}

}
