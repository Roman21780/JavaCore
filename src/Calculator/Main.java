package Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
        int c = calc.divide.apply(a, b);

        calc.println.accept(c);
    }
}

/* Комментарий к коду:
В данном коде ошибка возникает в строчке int c = calc.devide.apply(a, b);,
 когда переменная b равна нулю. Это приводит к исключению ArithmeticException,
 поскольку деление на ноль недопустимо */