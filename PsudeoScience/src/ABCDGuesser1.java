import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser1 {

    private static final double[] deJagerArray = { -5, -4, -3, -2, -1, -1.0 / 2.0,
            -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };

    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String input;
        double number = -1;
        while (number <= 0) {
            out.print("Hello! Please enter a positive real number: ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                number = Double.parseDouble(input);
                if (number <= 0) {
                    out.println("Please enter a number greater than 0.");
                }
            } else {
                out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        String input;
        double number = -1;
        while (number <= 0 || number == 1) {
            out.print("Please enter a positive real number (not 1): ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                number = Double.parseDouble(input);
                if (number <= 0 || number == 1) {
                    out.println(
                            "Please enter a number greater than 0 and not equal to 1.");
                }
            } else {
                out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    public static void minMaxArray(SimpleReader in, SimpleWriter out) {
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        out.println("Maximum: " + max);
        out.println("Minimum: " + min);
    }

    public static void isOrdered(SimpleReader in, SimpleWriter out) {
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        boolean isOrdered = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                isOrdered = false;
                break;
            }
        }
        out.println("Is array ordered: " + isOrdered);
    }

    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        double annoyingAssSymbol = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double bestApproximation = 0;
        double bestRelativeError = Double.MAX_VALUE;

        double bestA = 0;
        double bestB = 0;
        double bestC = 0;
        double bestD = 0;

        int i = 0;
        while (i < deJagerArray.length) {
            int j = 0;
            while (j < deJagerArray.length) {
                int k = 0;
                while (k < deJagerArray.length) {
                    int l = 0;
                    while (l < deJagerArray.length) {
                        double approximation = Math.pow(w, deJagerArray[i])
                                * Math.pow(x, deJagerArray[j])
                                * Math.pow(y, deJagerArray[k])
                                * Math.pow(z, deJagerArray[l]);

                        double relativeError = Math.abs(
                                (approximation - annoyingAssSymbol) / annoyingAssSymbol);

                        if (relativeError < bestRelativeError) {
                            bestApproximation = approximation;
                            bestRelativeError = relativeError;
                            bestA = deJagerArray[i];
                            bestB = deJagerArray[j];
                            bestC = deJagerArray[k];
                            bestD = deJagerArray[l];
                        }

                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        out.println("Best approximation: " + bestApproximation);
        out.println("Exponents: a =" + bestA + ", b =" + bestB + ", c =" + bestC + ", d ="
                + bestD);
        out.println("Relative error: " + (bestRelativeError * 100) + "%");

        in.close();
        out.close();
    }

}
