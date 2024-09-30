import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser2 {

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
                out.println("This is an invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        String input;
        double number = -1;
        while (number <= 0 || number == 1) {
            out.print("Enter a positive real number (not 1): ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                number = Double.parseDouble(input);
                if (number <= 0 || number == 1) {
                    out.println(
                            "Please enter a number greater than 0 and not equal to 1.");
                }
            } else {
                out.println("This is an invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    private static double computeApproximation(double a, double b, double c, double d,
            double w, double x, double y, double z) {
        return Math.pow(a, w) * Math.pow(b, x) * Math.pow(c, y) * Math.pow(d, z);
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

        for (int i = 0; i < deJagerArray.length; i++) {
            for (int j = 0; j < deJagerArray.length; j++) {
                for (int k = 0; k < deJagerArray.length; k++) {
                    for (int l = 0; l < deJagerArray.length; l++) {

                        double approximation = computeApproximation(w, x, y, z,
                                deJagerArray[i], deJagerArray[j], deJagerArray[k],
                                deJagerArray[l]);

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
                    }
                }
            }
        }

        out.println("Best approximation: " + bestApproximation);
        out.println("deJagerArray: a =" + bestA + ", b =" + bestB + ", c =" + bestC
                + ", d =" + bestD);
        out.println("Relative error: " + (bestRelativeError * 100) + "%");

        in.close();
        out.close();
    }

}
