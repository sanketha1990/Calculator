package com.here.test;

import static org.mockito.Mockito.when;

import com.here.main.CalculateOperatorAndOperandExpression;

import junit.framework.TestCase;

public class CalculateInvalidValidTest extends TestCase {
	public void invalidOperator1() {
		String operator = "#";
		when(CalculateOperatorAndOperandExpression.isOperator(operator)).thenReturn(false);
		assertEquals(false, CalculateOperatorAndOperandExpression.isOperator(operator));
	}

	public void invalidOperator2() {
		String operator = "$";
		when(CalculateOperatorAndOperandExpression.isOperator(operator)).thenReturn(false);
		assertEquals(false, CalculateOperatorAndOperandExpression.isOperator(operator));
	}

	public void wrongInput() {
		String wrongInput = "3";
		when(CalculateOperatorAndOperandExpression.isNumber(wrongInput)).thenReturn(false);
		assertEquals(false, CalculateOperatorAndOperandExpression.isOperator(wrongInput));
	}
}
