import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // ввод данных для проверки работы метода calc
        Scanner in= new Scanner(System.in);
        System.out.print("Введите операцию (a + b, a - b, a * b, a / b):");
        String  input= in.nextLine();
        try {
            calc(input);
            System.out.println(calc(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String calc(String input) throws IOException {

        input = input.trim(); //убирает пробелы

        //char operationInStr = 0;
        String operationInStr = " ";
        char[] simbols = input.toCharArray(); // массив символов введенного выражения.

        // определение введенной операции (+;-;*;/)
        for (int i = 0; i < input.length(); i++) {

            if (simbols[i]== '+') {
                operationInStr = "\\+";
            } else if (simbols[i] == '-') {
                operationInStr = "-";
            } else if (simbols[i] == '*') {
                operationInStr = "\\*";
            } else if (simbols[i] == '/') {
                operationInStr = "/";
            }
        }

        // Проверка на корректность введенной операции
        if (operationInStr.equals(" ")) {
            throw new IOException("т.к. строка не является математической операцией");
        }

        // деление введенной строки на слова
        String[] words = input.split(operationInStr);
        //удаление проболов
        for (int i=0; i< words.length;i++){
            words[i]=words[i].trim();
        }

        if (words.length > 2) {
            throw new IOException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        String[] ar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] roman = {"C","XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        boolean rom = false;


        // проверка на наличие  римских цифр
        for (String s : roman) {
            if (words[0].contains(s)) {
                rom = true;
            }
            if (words[1].contains(s)) {
                rom = true;
            }

        }

        for (String s : ar) {
            if (rom) {
                if (words[0].contains(s) || words[1].contains(s)) {
                    throw new IOException("т.к. используются одновременно разные системы счисления");

                }
            }
        }

        int[] Numb = {0, 0};

        // определение введенных чисел
        for (int i = 0; i < 2; i++) {
            switch (words[i]) {
                case "I", "1" -> Numb[i] = 1;
                case "II", "2" -> Numb[i] = 2;
                case "III", "3" -> Numb[i] = 3;
                case "IV", "4" -> Numb[i] = 4;
                case "V", "5" -> Numb[i] = 5;
                case "VI", "6" -> Numb[i] = 6;
                case "VII", "7" -> Numb[i] = 7;
                case "VIII", "8" -> Numb[i] = 8;
                case "IX", "9" -> Numb[i] = 9;
                case "X", "10" -> Numb[i] = 10;
                default -> throw new IOException("Число должно быть от 1 до 10 и только целым");
            }
        }

        //вычисление результата
        int result = switch (operationInStr) {
            case "\\+" -> Numb[0] + Numb[1];
            case "-" -> Numb[0] - Numb[1];
            case "\\*" -> Numb[0] * Numb[1];
            case "/" -> Numb[0] / Numb[1];
            default -> 0;

        };

        // в случае введенных римских чисел - перевод результата из арабских в римские цифры (результат от 0 до 100)
        if (rom == true) {
            if (result > 0) {
                
                int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};

                String res = new String();


                for (int i = 0; i < arab.length; i++) {
                    while(result >= arab[i]) {
                        res = res.concat(roman[i]);
                        result -= arab[i];
                    }
                }


                return res;
            } else { throw new IOException("т.к. в римской системе нет отрицательных чисел"); }
        }




        return String.valueOf(result);
    }



}