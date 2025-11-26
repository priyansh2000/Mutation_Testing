package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest
{

    private BankAccount checkingAccount;
    private BankAccount savingsAccount;
    private BankAccount fixedDepositAccount;

    @BeforeEach
    void setUp()
    {
        // Initialize the accounts before each test
        checkingAccount = new BankAccount("John Doe", 1000.0, "Checking");
        savingsAccount = new BankAccount("Jane Doe", 5000.0, "Savings");
        fixedDepositAccount = new BankAccount("Alice Johnson", 10000.0, "Fixed");
    }


    MathUtility utility = new MathUtility();
    @Test
    void testFactorialPositiveNumber()
    {
        assertEquals(120, utility.factorial(5), "Factorial of 5 should be 120");
    }

    @Test
    void testFactorialZero()
    {
        assertEquals(1, utility.factorial(0), "Factorial of 0 should be 1");
    }
    @Test
    void testFactorialNegativeNumber()
    {
        assertThrows(IllegalArgumentException.class, () -> utility.factorial(-5), "Factorial of negative number should throw exception");
    }
    @Test
    void testGCDPositiveNumbers()
    {
        assertEquals(6, utility.gcd(12, 18), "GCD of 12 and 18 should be 6");
    }

    @Test
    void testGCDAZeroValue()
    {
        assertEquals(10, utility.gcd(10, 0), "GCD of 10 and 0 should be 10");
    }

    @Test
    void testLCMPositiveNumbers()
    {
        assertEquals(36, utility.lcm(12, 18), "LCM of 12 and 18 should be 36");
    }

    @Test
    void testLCMZeroValue()
    {
        assertEquals(0, utility.lcm(0, 18), "LCM of 0 and any number should be 0");
    }

    @Test
    void testFibonacciPositiveIndex()
    {
        assertEquals(55, utility.fibonacci(10), "10th Fibonacci number should be 55");
    }

    @Test
    void testFibonacciZero()
    {
        assertEquals(0, utility.fibonacci(0), "0th Fibonacci number should be 0");
    }

    @Test
    void testFibonacciNegativeNumber()
    {
        assertThrows(IllegalArgumentException.class, () -> utility.fibonacci(-1), "Negative index for Fibonacci should throw exception");
    }

    @Test
    void testIsPrimePrimeNumber()
    {
        assertTrue(utility.isPrime(7), "7 should be a prime number");
    }

    @Test
    void testIsPrimeNonPrimeNumber()
    {
        assertFalse(utility.isPrime(28), "28 should not be a prime number");
    }

    @Test
    void testIsPrimeEdgeCases()
    {
        assertFalse(utility.isPrime(1), "1 is not a prime number");
        assertFalse(utility.isPrime(0), "0 is not a prime number");
        assertFalse(utility.isPrime(-5), "-5 is not a prime number");
    }

    @Test
    void testSumPositiveNumbers()
    {
        int[] array = {3, 8, 1, 6, 7};
        assertEquals(25, utility.sum(array), "Sum of array should be 25");
    }
    @Test
    void testSumEmptyArray()
    {
        int[] array = {};
        assertEquals(0, utility.sum(array), "Sum of an empty array should be 0");
    }

    @Test
    void testMaxPositiveNumbers()
    {
        int[] array = {3, 8, 1, 6, 7};
        assertEquals(8, utility.max(array), "Maximum value of array should be 8");
    }

    @Test
    void testMaxSingleElement()
    {
        int[] array = {5};
        assertEquals(5, utility.max(array), "Maximum value of single-element array should be 5");
    }

    @Test
    void testMaxEmptyArray()
    {
        int[] array = {};
        assertThrows(IllegalArgumentException.class, () -> utility.max(array), "Empty array should throw exception");
    }

    @Test
    void testPowerPositiveExponent()
    {
        assertEquals(32.0, utility.power(2, 5), "2^5 should be 32");
    }

    @Test
    void testPowerNegativeExponent()
    {
        assertEquals(0.125, utility.power(2, -3), "2^-3 should be 0.125");
    }

    @Test
    void testPowerZeroExponent()
    {
        assertEquals(1.0, utility.power(2, 0), "2^0 should be 1");
    }
    @Test
    void testSortArrayNormalCase()
    {
        int[] array = {3, 8, 1, 6, 7};
        assertArrayEquals(new int[]{1, 3, 6, 7, 8}, utility.sortArray(array), "Array should be sorted in ascending order");
    }
    @Test
    void testSortArrayAlreadySorted()
    {
        int[] array = {1, 2, 3, 4, 5};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, utility.sortArray(array), "Already sorted array should remain the same");
    }
    @Test
    void testSortArrayEmptyArray()
    {
        int[] array = {};
        assertArrayEquals(new int[]{}, utility.sortArray(array), "Empty array should return empty array");
    }

    TransactionsCheck check=new TransactionsCheck();
    @Test
    void testFilterValidTransactions()
    {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(500, true, false, false), // Valid
                new Transaction(-100, true, false, false), // Invalid (negative amount)
                new Transaction(200, false, false, true), // Valid (high priority)
                new Transaction(1000, false, true, true)  // Invalid (fraudulent)
        );

        List<Transaction> validTransactions =check.filterValidTransactions(transactions);
        assertEquals(2, validTransactions.size());
    }


    @Test
    void testCalculateTotalRewards()
    {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(500, true, false, false), // Valid
                new Transaction(300, true, false, false), // Valid
                new Transaction(-100, false, false, false) // Invalid
        );

        double totalRewards = check.calculateTotalRewards(transactions, 0.1); // 10% reward rate
        assertEquals(80.0, totalRewards); // 500*0.1 + 300*0.1
    }

    @Test
    void testHasSuspiciousTransactions()
    {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(500, true, false, false), // Not suspicious
                new Transaction(12000, false, false, false), // Suspicious (large unapproved)
                new Transaction(300, true, true, false) // Suspicious (fraudulent)
        );

        assertTrue(check.hasSuspiciousTransactions(transactions));
    }

    @Test
    void testHasNoSuspiciousTransactions() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(500, true, false, false), // Not suspicious
                new Transaction(100, true, false, false)  // Not suspicious
        );
        assertFalse(check.hasSuspiciousTransactions(transactions));
    }

    EmployeeManager manager = new EmployeeManager();

    @Test
    void testDetermineBonusEligibility()
    {
        assertEquals("Eligible for Bonus", manager.determineBonusEligibility(90, 6));
        assertEquals("Considered for Bonus", manager.determineBonusEligibility(75, 3));
        assertEquals("Not Eligible", manager.determineBonusEligibility(65, 2));
    }

    @Test
    void testGetEmployeesInAgeRange()
    {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, 80),
                new Employee("Bob", 30, 70),
                new Employee("Charlie", 45, 90)
        );

        List<Employee> filtered = manager.getEmployeesInAgeRange(employees, 30, 50);

        assertEquals(2, filtered.size());
        assertEquals("Bob", filtered.get(0).getName());
        assertEquals("Charlie", filtered.get(1).getName());
    }

    @Test
    void testEvaluateStaffingStatus()
    {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, 80),
                new Employee("Bob", 30, 70)
        );

        assertEquals("Understaffed", manager.evaluateStaffingStatus(employees, 5));
        assertEquals("Optimal Staffing", manager.evaluateStaffingStatus(employees, 2));
        employees = Arrays.asList(
                new Employee("Alice", 25, 80),
                new Employee("Bob", 30, 70),
                new Employee("Charlie", 45, 90),
                new Employee("David", 35, 85)
        );
        assertEquals("Overstaffed", manager.evaluateStaffingStatus(employees, 3));
    }

    @Test
    void testCalculateHighPerformersPercentage()
    {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, 80),
                new Employee("Bob", 30, 70),
                new Employee("Charlie", 45, 90)
        );

        assertEquals(66.67, manager.calculateHighPerformersPercentage(employees, 75), 0.01);
        assertEquals(33.33, manager.calculateHighPerformersPercentage(employees, 85), 0.01);
    }


    BitsPlaying ops=new BitsPlaying();

    @Test
    void testComputeChecksum() {
        int[] data = {1, 2, 3, 4, 5};
        assertEquals(ops.computeChecksum(data), ops.computeChecksum(data)); // Consistent checksum
    }

    @Test
    void testSetBit() {
        int num = 0b1010; // 10 in decimal
        int result = ops.setBit(num, 0);
        assertEquals(0b1011, result); // After setting bit at index 1, result = 11 (0b1011)
    }



    @Test
    void testCountSetBits() {
        assertEquals(3, ops.countSetBits(7)); // Binary: 111
        assertEquals(0, ops.countSetBits(0));
    }

    @Test
    void testRotateLeft() {
        assertEquals(0b101100, ops.rotateLeft(0b1011, 2));
    }

    @Test
    void testClearBit()
    {
        int num = 0b1010; // 10 in decimal
        int result = ops.clearBit(num, 1);
        assertEquals(0b1000, result); // Bit at index 1 should be cleared, result = 8
    }

    @Test
    void testToggleBit()
    {
        int num = 0b1010; // 10 in decimal
        int result = ops.toggleBit(num, 1);
        assertEquals(0b1000, result); // Bit at index 1 should be toggled, result = 8
    }

    @Test
    void testCountOnes()
    {
        int num = 0b1010; // 10 in decimal
        int result = ops.countOnes(num);
        assertEquals(2, result); // Two 1's in the binary representation of 10
    }


    @Test
    void testSwapNumbers() {
        int[] swapped = ops.swapNumbers(5, 9);
        assertEquals(9, swapped[0]);
        assertEquals(5, swapped[1]);
    }

    @Test
    void testExtractBit() {
        assertEquals(1, ops.extractBit(5, 0)); // Binary: 101
        assertEquals(0, ops.extractBit(5, 1));
    }

    @Test
    void testIsPowerOfTwo() {
        assertTrue(ops.isPowerOfTwo(16)); // 2^4
        assertFalse(ops.isPowerOfTwo(18));
    }

    @Test
    void testReverseBits() {
        assertEquals(Integer.reverse(13), ops.reverseBits(13)); // Java-provided reverse as reference
    }

    @Test
    void testMostSignificantBit()
    {
        assertEquals(3, ops.mostSignificantBit(8)); // Binary: 1000, MSB at index 3
        assertEquals(-1, ops.mostSignificantBit(0)); // No bits set
    }

    @Test
    void testDepositValidAmount() {
        checkingAccount.deposit(200.0);  // Testing valid deposit
        assertEquals(1200.0, checkingAccount.getBalance(), "Deposit failed. Expected balance: 1200.0");
    }

    @Test
    void testDepositInvalidAmount() {
        checkingAccount.deposit(-100.0);  // Testing invalid deposit (negative amount)
        assertEquals(1000.0, checkingAccount.getBalance(), "Balance should not change for invalid deposit");
    }

    @Test
    void testWithdrawValidAmount() {
        savingsAccount.withdraw(1500.0);  // Testing valid withdrawal
        assertEquals(3500.0, savingsAccount.getBalance(), "Withdrawal failed. Expected balance: 3500.0");
    }

    @Test
    void testWithdrawInvalidAmount() {
        savingsAccount.withdraw(6000.0);  // Testing invalid withdrawal (exceeds balance)
        assertEquals(5000.0, savingsAccount.getBalance(), "Withdrawal should not be allowed with insufficient funds");
    }

    @Test
    void testTransferFundsValid() {
        BankAccount sourceAccount = new BankAccount("John", 1200.0, "checking");
        BankAccount destinationAccount = new BankAccount("Mary", 500.0, "savings");

        // Transfer 500 from source to destination
        sourceAccount.transferFunds(destinationAccount, 500.0);

        // Assert that the balances are updated correctly
        assertEquals(700.0, sourceAccount.getBalance(), "Source account balance should be 700.0");
        assertEquals(1000.0, destinationAccount.getBalance(), "Destination account balance should be 1000.0");
    }


    @Test
    void testTransferFundsInvalidAmount() {
        checkingAccount.transferFunds(savingsAccount, 1200.0);  // Trying to transfer more than available
        assertEquals(1000.0, checkingAccount.getBalance(), "Transfer should fail if funds are insufficient");
        assertEquals(5000.0, savingsAccount.getBalance(), "Savings account balance should remain unchanged");
    }

    @Test
    void testInterestCalculationChecking() {
        BankAccount checkingAccount = new BankAccount("John", 1200.0, "checking");
        checkingAccount.calculateInterest();
        assertEquals(1212.0, checkingAccount.getBalance(), "Interest calculation failed for checking account");
    }

    @Test
    void testInterestCalculationSavings() {
        savingsAccount.calculateInterest();
        assertEquals(5250.0, savingsAccount.getBalance(), "Interest calculation failed for savings account");
    }

    @Test
    void testInterestCalculationFixedDeposit() {
        fixedDepositAccount.calculateInterest();
        assertEquals(10700.0, fixedDepositAccount.getBalance(), "Interest calculation failed for fixed deposit account");
    }

    @Test
    void testInvalidAccountType() {
        // Testing account with an invalid account type
        BankAccount invalidAccount = new BankAccount("Bob", 2000.0, "InvalidType");
        invalidAccount.calculateInterest();  // Should handle the invalid account type gracefully
        assertEquals(2000.0, invalidAccount.getBalance(), "Balance should not change if account type is invalid");
    }

    @Test
    void testDisplayAccountDetails()
    {
        checkingAccount.displayAccountDetails();
        // This test will print details, so no assert here
    }
}
