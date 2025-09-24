package OOP;

// Абстрактный класс Operation
public abstract class Operation {
    protected double num_1;
    protected double num_2;

    public Operation(double num_1, double num_2) {
        this.num_1 = num_1;
        this.num_2 = num_2;
    }

    public abstract double getResult();
}

// Операции
class Addition extends Operation {
    public Addition(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        return num_1 + num_2;
    }
}

class Subtraction extends Operation {
    public Subtraction(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        return num_1 - num_2;
    }
}

class Multiplication extends Operation {
    public Multiplication(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        return num_1 * num_2;
    }
}

class Division extends Operation {
    public Division(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        if (num_2 == 0) throw new ArithmeticException("Деление на ноль");
        return num_1 / num_2;
    }
}

class Remainder extends Operation {
    public Remainder(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        if (num_2 == 0) throw new ArithmeticException("Деление на ноль");
        return num_1 % num_2;
    }
}

class Exponentiation extends Operation {
    public Exponentiation(double num_1, double num_2) {
        super(num_1, num_2);
    }

    @Override
    public double getResult() {
        return Math.pow(num_1, num_2);
    }
}
