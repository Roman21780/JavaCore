package Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);

        // перед делением нужно проверить, является ли делитель нулем
        int c;
        if (b != 0) {
            c = calc.devide.apply(a, b);
        } else {
            calc.println.accept(0);
            System.out.println("Деление на ноль недопустимо");
            return;
        }

        calc.println.accept(c);
    }
}

/* Комментарий к коду:
В данном коде ошибка возникает в строчке int c = calc.devide.apply(a, b);,
 когда переменная b равна нулю. Это приводит к исключению ArithmeticException,
 поскольку деление на ноль недопустимо */