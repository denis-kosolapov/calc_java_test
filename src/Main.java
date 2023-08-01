import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ввод: ");
            String input = scanner.nextLine();
            String result = calc(input);
            System.out.println("Вывод: " + result);
        } catch (Exception e) {
            System.out.println("Вывод: throws Exception");
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3) {
            System.out.println("Неверный формат ввода, попробуйте вводить данные через пробел, например: 6 + 7");
            throw new Exception();
        }

        String firstOperand = parts[0].trim();
        String operator = parts[1].trim();
        String secondOperand = parts[2].trim();

        boolean isFirstRoman = isFirstOperandRoman(firstOperand);
        boolean isSecondRoman = isSecondOperandRoman(secondOperand);
        if (isFirstRoman != isSecondRoman) {
            System.out.println("Римские цифры нельзя использовать вместе с арабскими цифрами.");
            throw new Exception();
        }

        int a;
        int b;

        if (isFirstRoman) {
            a = fromRomanNumeral(firstOperand);
            b = fromRomanNumeral(secondOperand);

            if (a < 0 || a > 10) {
                System.out.println("Римское число больше 10 или меньше 0");
                throw new IllegalArgumentException("Арабское число больше 10 или меньше 0");
            }

            if (b < 0 || b > 10) {
                System.out.println("Римское число больше 10 или меньше 0");
                throw new IllegalArgumentException("Арабское число больше 10 или меньше 0");
            }

        } else {
            a = Integer.parseInt(firstOperand);
            b = Integer.parseInt(secondOperand);

            if (a < 0 || a > 10) {
                System.out.println("Арабское число больше 10 или меньше 0");
                throw new IllegalArgumentException("Арабское число больше 10 или меньше 0");
            }

            if (b < 0 || b > 10) {
                System.out.println("Арабское число больше 10 или меньше 0");
                throw new IllegalArgumentException("Арабское число больше 10 или меньше 0");
            }
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
                    throw new Exception("Деление на ноль не допускается");
                }
                result = a / b;
                break;
            default:
                throw new Exception("Недопустимый оператор");
        }

        if (isFirstRoman) {
            return toRomanNumeral(result);
        }

        return String.valueOf(result);
    }

    private static boolean isFirstOperandRoman(String operand) {
        return operand.matches("[IVXLCDM]+"); // Проверяем, является ли операнд римским числом
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
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Roman numerals are only valid for numbers between 1 and 3999");
        }

        StringBuilder romanNumeral = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                romanNumeral.append(numerals[i]);
            }
        }

        return romanNumeral.toString();
    }
}

// new comment

