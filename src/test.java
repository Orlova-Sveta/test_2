import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // получаем строку из консоли
        String[] mas = input.split(" "); // разделяем строку на элементы по пробелу

        if (mas.length != 3) { // кол-во элементов не может быть больше трех, иначе выражение неверное
            throw new Exception("Неверное выражение");
        }

        if (mas[0].charAt(0) != '\"' && mas[0].charAt(mas[0].length() - 1) != '"') { // проверка на наличие кавычек в строке
            throw new Exception("Неверное выражение");
        }

        // начальное значение
        String a = mas[0];
        String b = mas[2];

        if (a.length() > 12 || b.length() > 12) { // если строка длиннее 12 символов, то выражение неверное
            throw new Exception("Неверное значение");
        }
        String result;
        switch (mas[1]) { // находим нужную операцию и высчитываем результат
            case "+":
                checkNumber(b);
                result = a + b;
                break;
            case "-":
                checkNumber(b);
                b = b.replaceAll("\"", "");
                result = a.replace(b, "");
                break;
            case "*":
                int number;
                try {
                    number = Integer.parseInt(mas[2]);
                    checkLength(number);
                } catch (Exception e) {
                    throw new Exception("Неверное выражение");
                }
                String multiply = "";
                for (int i = 0; i < number; i++) {
                    multiply = multiply + mas[0];
                }
                result = multiply;
                break;
            case "/":
                int number1;
                try {
                    number1 = Integer.parseInt(mas[2]);
                    checkLength(number1);
                } catch (Exception e) {
                    throw new Exception("Неверное выражение");
                }
                int del = mas[0].length() / number1;
                result = mas[0].substring(0, del);
                break;
            default:
                throw new Exception("Неверная операция"); // если не нашли совпадений, значит выражение или операция неверные
        }
        if (result.length() > 40) {
            result = result.substring(0, 39) + "..."; // если строка, длиннее 40 символов, то после 40 символа выводится многоточие
        }
        result = result.replaceAll("\"", "");
        System.out.println("\"" + result + "\""); // выводим результат с добавлением кавычек
    }

    // метод на проверку числового значения
    public static void checkNumber(String b) throws Exception {
        if (b.charAt(0) != '\"' && b.charAt(b.length() - 1) != '"') {
            throw new Exception("Неверное значение");
        }
    }

    // Метод на проверку, что число меньше 10 и больше 0
    public static void checkLength(int number) throws Exception {
        if (number > 10 || number < 1) {
            throw new Exception("Неверное выражение");
        }
    }
}

