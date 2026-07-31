public class ScientificLogic {

    public double sin(double x) {
        return Math.sin(Math.toRadians(x));
    }

    public double cos(double x) {
        return Math.cos(Math.toRadians(x));
    }

    public double tan(double x) {
        return Math.tan(Math.toRadians(x));
    }

    public double log(double x) {
        if(x <= 0)
            throw new IllegalArgumentException("Log is defined only for positive numbers.");
        return Math.log10(x);
    }

    public double ln(double x) {
        if(x <= 0)
            throw new IllegalArgumentException("Natural log is defined only for positive numbers.");
        return Math.log(x);
    }

    public double sqrt(double x) {
        if(x < 0)
        throw new IllegalArgumentException("Square root of negative number is not possible.");
        return Math.sqrt(x);
    }

    public double square(double x) {
        return x * x;
    }

    public double power(double x, double y) {
        return Math.pow(x, y);
    }

    public double reciprocal(double x) {
        if(x == 0)
        throw new ArithmeticException("Cannot divide by zero.");
        return 1 / x;
    }

    public double absolute(double x) {
        return Math.abs(x);
    }

    public long factorial(int n) {
         if(n < 0)
        throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        long fact = 1;
        for(int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
        
    }

}