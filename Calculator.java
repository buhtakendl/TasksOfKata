import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ваше выражение (принимаются числа от 1 до 10):");
        String expression = sc.nextLine();
        System.out.println("Результат выражения: " + estimation(expression));
    }

    static String estimation(String str) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean minus = false;
        if (str.charAt(0) == '-'){
            minus = true;
            StringBuffer sb = new StringBuffer(str);
            sb.deleteCharAt(0);
            str = sb.toString();
        }
        String[] tokens = str.split("[+\\-*/]");

        if (tokens.length != 2) throw new Exception("Необходимо два операнда или вы ввели недопустимую операцию");

        operation = whichOperation(str);

        if (ToRoman.isInRoman(tokens[0]) && ToRoman.isInRoman(tokens[1])) {
            if (minus) System.out.println("Римское число не может быть меньше 0");
            number1 = ToRoman.RomanNum(tokens[0]);
            number2 = ToRoman.RomanNum(tokens[1]);

            if (number1 > 10 || number2 > 10) throw new Exception("Числа не могут быть больше 10");
            if (number1 < 1 || number2 < 1) throw new Exception("Числа не могут быть меньше 1");

            int itog = estimation(number1, number2, operation);
            if (itog < 1) throw new Exception("Резльтатом работы с римскими числами могут быть только положительные числа");
            result = ToRoman.convertFromRomanToArabic(itog);
        }

        else if (!ToRoman.isInRoman(tokens[0]) && !ToRoman.isInRoman(tokens[1])) {
            number1 = Integer.parseInt(tokens[0]);
            number2 = Integer.parseInt(tokens[1]);
            if (number1 > 10 || number2 > 10) throw new Exception("Числа не могут быть больше 10");
            if (number1 < 1 || number2 < 1) throw new Exception("Числа не могут быть меньше 1");
            if (minus) number1 = -number1;
            int itog = estimation(number1, number2, operation);
            result = String.valueOf(itog);
        }

        else {
            throw new Exception("Числа должны быть в одной системе счисления");
        }
        return result;
    }

    static String whichOperation(String expression) {
        if (expression.contains("+")) return "+";
        if (expression.contains("-")) return "-";
        if (expression.contains("*")) return "*";
        if (expression.contains("/")) return "/";
        else return null;
    }
    static int estimation(int num1, int num2, String operation) throws Exception {
        int result;
        switch (operation) {
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
                result = num1 / num2;
                break;
            default:
                throw new Exception("Такой операции не существует.");
        }
        return result;
    }
static class ToRoman {
        static String[] roman = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        public static boolean isInRoman(String num) {
            for (int i = 0; i < roman.length; i++) {
                if (num.equals(roman[i])) return true;
            }
            return false;
        }

        public static int RomanNum(String str) throws Exception {
            for (int i = 0; i < roman.length; i++) {
                if (str.equals(roman[i])) return i;
            }
            return -1;
        }

        public static String convertFromRomanToArabic(int result) throws Exception {
            return roman[result];
        }
    }
}
