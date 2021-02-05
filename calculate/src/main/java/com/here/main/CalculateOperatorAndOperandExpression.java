package com.here.main;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateOperatorAndOperandExpression {
	private static final Pattern VALID_OPERATOR_REGEX = Pattern.compile("[\\/\\+\\-\\*]");
	private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]");
	private static final Pattern VALID_INPUT_REGEX = Pattern.compile("[0-9\\(\\)\\+\\-\\*\\./\\\"]");

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();

		String str = "2 0 + 12 - 5 ++";
		String[] arr = str.split(" ");
		for (int i = arr.length - 1; i >= 0; i--) {
			if (!arr[i].isEmpty()) {
				stack.push(arr[i].trim());
			}
		}

		Integer result = 0;

		if (isInputValid(arr)) {
			result = resultCalculation(stack);
			if (result != Integer.MIN_VALUE) {
				System.out.println("Result : " + result);
			}
		} else {
			System.out.println("Invalid Input");
		}
	}

	public static int resultCalculation(Stack<String> stack) {
		Integer result = 0;
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

	public static boolean isOperator(String operator) {
		Matcher matcher = VALID_OPERATOR_REGEX.matcher(operator);
		return matcher.find();
	}

	public static boolean isNumber(String numStr) {
		Matcher matcher = VALID_NUMBER_REGEX.matcher(numStr);
		return matcher.find();
	}

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
