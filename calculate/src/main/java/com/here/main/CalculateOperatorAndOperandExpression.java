package com.here.main;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>CalculateOperatorAndOperandExpression</code> class can provide
 * functionality to provide basic arithmetic operation for both positive and
 * negative integers
 */
public class CalculateOperatorAndOperandExpression {

	/**
	 * Regex constant which used for valid number and operator
	 */
	private static final Pattern VALID_OPERATOR_REGEX = Pattern.compile("[\\/\\+\\-\\*]");
	private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]");
	private static final Pattern VALID_INPUT_REGEX = Pattern.compile("[0-9\\(\\)\\+\\-\\*\\./\\\"]");

	/**
	 * main(args) to test the functionality it takes args[0] as input from
	 *
	 * @param args[0] as input
	 * @return void
	 */
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		String argStr = null;
		if (args.length != 0) {
			argStr = args[0];
		} else {
			System.out.println("Please enter the input from commandline argument !! Example '2 3 +' ");
			return;
		}
		String[] arr = argStr.split(" ");
		for (int i = arr.length - 1; i >= 0; i--) {
			if (!arr[i].isEmpty()) {
				stack.push(arr[i].trim());
			}
		}

		Integer result = Integer.MIN_VALUE;

		if (isInputValid(arr)) {
			result = resultCalculation(stack);
			if (result != Integer.MIN_VALUE) {
				System.out.println("Result : " + result);
			}
		} else {
			System.out.println("Invalid Input");
		}
	}

	/**
     * resultCalculation(stack) method Calculate result based on the num1 and num2 with operator
     *
     * @param stack values as input
     * @return  integer result
     */
	public static int resultCalculation(Stack<String> stack) {
		Integer result = Integer.MIN_VALUE;
		while (!stack.isEmpty()) {
			String num1 = stack.pop();
			if (isNumber(num1) && isNumber(stack.peek())) {
				String num2 = stack.pop();
				if (isOperator(stack.peek())) {
					String op = stack.pop();
					result = calculate(Integer.parseInt(num1), Integer.parseInt(num2), op);
					if (!stack.isEmpty()) {
						stack.push(result.toString());
					}
				} else {
					System.out.println("Not balanced expression");
					return Integer.MIN_VALUE;
				}
			} else {
				System.out.println("Not balanced expression");
				return Integer.MIN_VALUE;
			}
		}
		return result;
	}

	/**
	 * isOperator(operator) is verifies is basic operator or not
	 *
	 * @param operator the operator to validate whether is it operator -,/,*,+.
	 * @return boolean true/false.
	 */
	public static boolean isOperator(String operator) {
		Matcher matcher = VALID_OPERATOR_REGEX.matcher(operator);
		return matcher.find();
	}

	/**
	 * isNumber(numStr) validates number or not
	 *
	 * @param numStr validates is given String is number [0-9].
	 * @return boolean true/false.
	 */
	public static boolean isNumber(String numStr) {
		Matcher matcher = VALID_NUMBER_REGEX.matcher(numStr);
		return matcher.find();
	}

	/**
	 * isInputValid(str) before starting the process it validates whether input has
	 * valid input
	 *
	 * @param array of str[] validates is given array contains only number [0-9] and
	 *              [-,+,/,*].
	 * @return boolean true/false.
	 */
	public static boolean isInputValid(String[] str) {
		for (String s : str) {
			if (!s.isEmpty()) {
				Matcher matcher = VALID_INPUT_REGEX.matcher(s);
				if (!matcher.find())
					return false;
			}
		}
		return true;
	}

	/**
	 * calculate(num1,num2,operator) this method calculate the result based on
	 * num1,num2 and operator
	 *
	 * @param num1,num2 and operator. Ex : num1=2,num2=3, operator=+, result=5
	 * @return return integer result(positive/negative).
	 */
	public static int calculate(int num1, int num2, String operator) {
		Integer result = Integer.MIN_VALUE;
		switch (operator) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			if (num2 > 0) {
				result = num1 / num2;
			} else {
				result = num1;
			}
			break;
		default:
			System.out.println("Error! Invalid operator");
		}
		return result;
	}
}
