public class TempConver {

    public static double convert(double temp, char from, char to) {
        switch (from) {
            case 'C':
                switch (to) {
                    case 'F': return (temp * 9 / 5) + 32;
                    case 'K': return temp + 273.15;
                }
                break;

            case 'F':
                switch (to) {
                    case 'C': return (temp - 32) * 5 / 9;
                    case 'K': return ((temp - 32) * 5 / 9) + 273.15;
                }
                break;

            case 'K':
                switch (to) {
                    case 'C': return temp - 273.15;
                    case 'F': return ((temp - 273.15) * 9 / 5) + 32;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid conversion: " + from + " to " + to);
    }
}
