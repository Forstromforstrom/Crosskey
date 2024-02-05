import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class MortgageCalculatorTest {



    @Test
    public void paymentCalculatorTest() {
        MortgageCalculator mortgageTest = new MortgageCalculator();
        double expectedMonthlyPayment = 5.1;
        double actualMonthlyPayment = mortgageTest.calculateMonthlyPayment(2000,3,4);
        assertEquals(expectedMonthlyPayment, actualMonthlyPayment);
    }
}