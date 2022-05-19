import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = reader.readLine();
            System.out.println(calc(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calc(String input) throws Exception {
        String[] strings = input.split(" ");
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10"));
        ArrayList<String> numbersRom = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX", "X"));
        if (strings.length == 1 || strings.length == 2) {
            throw new Exception("т.к. строка не является математической операцией");
        }
        if (strings.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет " +
                    "заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (strings[1].equals("+") || strings[1].equals("-") || strings[1].equals("*") || strings[1].equals("/")) {
            String numStrA = "";
            String numStrB = "";

            int count = 0;
            for (int i = 0; i < strings.length; i++) {
                for (String str : numbersRom) {
                    if (strings[i].equals(str)) {
                        count++;
                        if (count == 1) {
                            numStrA = strings[i];
                        } else if (count == 2) {
                            numStrB = strings[i];
                        }
                    }

                }
            }
            if (count == 2) {
                int a = perebor(numStrA);
                int b = perebor(numStrB);
                switch (strings[1]) {
                    case "+":
                        return ("" + (a + b));
                    case "-":
                        if (a < b) {
                            throw new Exception("т.к. в римской системе нет отрицательных чисел");
                        }
                        return ("" + (a - b));
                    case "*":
                        return ("" + (a * b));
                    default:
                        return ("" + (a / b));
                }
            } else if (count == 1) {
                throw new Exception("т.к. используются одновременно разные системы счисления");
            } else {
                count = 0;
                for (int i = 0; i < strings.length; i++) {
                    // int count=0;
                    for (String str : numbers) {
                        if (strings[i].equals(str)) {
                            count++;
                        }
                    }
                }
                if (count == 2) {
                    int a = Integer.parseInt(strings[0]);
                    int b = Integer.parseInt(strings[2]);
                    return switch (strings[1]) {
                        case "+" -> ("" + (a + b));
                        case "-" -> ("" + (a - b));
                        case "*" -> ("" + (a * b));
                        default -> ("" + (a / b));
                    };
                } else {
                    throw new Exception("т.к. формат математической операции не удовлетворяет " +
                            "заданию - два операнда и один оператор (+, -, /, *)");
                }
            }
        }
        return "метод полностью пройден но решение не выводилось.";
    }

    private static int perebor(String numStr) {
        return switch (numStr) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
}
