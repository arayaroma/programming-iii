package code;

class DefaultInterface {
    public static void main(String[] args) {
        Driver();
    }

    public static void Driver() {
        CalculatorInterface calculator = new Calculator();
        System.out.println("Addition: " + calculator.add(10, 20));
        System.out.println("Subtraction: " + calculator.subtract(10, 20));
        System.out.println("Multiplication: " + calculator.multiply(10, 20));
        System.out.println("Division: " + calculator.divide(10, 20));
    }
}

interface CalculatorInterface {
    int add(int a, int b);

    int subtract(int a, int b);

    default int multiply(int a, int b) {
        return a * b;
    }

    default int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}

class Calculator implements CalculatorInterface {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

}