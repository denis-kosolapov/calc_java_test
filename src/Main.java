import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            String result = calc(input);
            System.out.println("Output: " + result);
        } catch (Exception e) {
            System.out.println("Output: throws Exception");
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3) {
            throw new Exception("Invalid input format");
        }

        String firstOperand = parts[0].trim();
        String operator = parts[1].trim();
        String secondOperand = parts[2].trim();

        boolean isFirstRoman = isFirstOperandRoman(firstOperand);
        boolean isSecondRoman = isSecondOperandRoman(secondOperand);
        if (isFirstRoman != isSecondRoman) {
            throw new Exception("Roman numerals cannot be used together with Arabic numerals");
        }

        int a;
        int b;

        if (isFirstRoman) {
            a = fromRomanNumeral(firstOperand);
            b = fromRomanNumeral(secondOperand);
        } else {
            a = Integer.parseInt(firstOperand);
            b = Integer.parseInt(secondOperand);
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new Exception("Division by zero is not allowed");
                }
                result = a / b;
                break;
            default:
                throw new Exception("Invalid operator");
        }

        if (isFirstRoman) {
            return toRomanNumeral(result);
        }

        return String.valueOf(result);
    }

    private static boolean isFirstOperandRoman(String operand) {
        // Проверяем, является ли операнд римским числом
        return operand.matches("[IVXLCDM]+");
    }

    private static boolean isSecondOperandRoman(String operand) {
        return isFirstOperandRoman(operand);
    }

    private static int fromRomanNumeral(String romanNumeral) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int number = 0;
        int prevValue = 0;

        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            int value = romanMap.get(romanNumeral.charAt(i));

            if (value < prevValue) {
                number -= value;
            } else {
                number += value;
            }

            prevValue = value;
        }

        return number;
    }

    private static String toRomanNumeral(int number) {
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException("Roman numerals are only valid for numbers between 1 and 10");
        }

        String[] numerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        return numerals[number - 1];
    }

}
