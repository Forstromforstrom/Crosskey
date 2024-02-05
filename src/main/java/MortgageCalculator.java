import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MortgageCalculator {

    // Cleans the input string by replacing commas with spaces.
    public static String Clean(String name) {
        name = name.replace(",", " ");
        return name;
    }

    public static void main(String[] args) {
        // Read customer information from the file
        String fileName = "prospects.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int prospectNumber = 1;

            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (tokens.length >= 4) {
                    String customerName = Clean(tokens[0]);
                    double totalLoan = Double.parseDouble(tokens[1]);
                    double yearlyInterestRate = Double.parseDouble(tokens[2]);
                    int loanPeriodInYears = Integer.parseInt(tokens[3]);
                    double monthlyPayment = calculateMonthlyPayment(totalLoan, yearlyInterestRate, loanPeriodInYears);
                    // Print the result for each customer
                    System.out.println("****************************************************************************************************");
                    System.out.println("Prospect " + prospectNumber + ": " + customerName +
                            " has a loan of " + totalLoan + " euro and will pay " + monthlyPayment + " euro each month" +
                            " for " + loanPeriodInYears + " years");
                    System.out.println("****************************************************************************************************");
                } else {

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Calculates the monthly loan payment based on the total loan amount, yearly interest rate, and loan period,
    // rounding the result to two decimal
    public static double calculateMonthlyPayment(double totalLoan, double yearlyInterestRate, int loanPeriodInYears) {
        double monthlyInterestRate = yearlyInterestRate / 12 / 100;
        int numberOfPayments = loanPeriodInYears * 12;

        double monthlyPayment = totalLoan * (monthlyInterestRate * powerOf(1 + monthlyInterestRate, numberOfPayments))
                / (powerOf(1 + monthlyInterestRate, numberOfPayments) - 1);

       return roundToTwoDecimal(monthlyPayment); // Round to 2 decimal places
    }


    // Calculates the power of a given base raised to the specified integer exponent
    public static double powerOf(double base, int power) {
        double num = 1;
        for (int i = 0; i < power; i++) {
            num = num * base;
        }
        return num;
    }

    //Rounds the given number to two decimal
    public static double roundToTwoDecimal(double number) {
        double scaledNumber = number * 100;
        scaledNumber += 0.5;
        int truncatedNumber = (int) scaledNumber;
        return truncatedNumber / 100.0;
    }
}
