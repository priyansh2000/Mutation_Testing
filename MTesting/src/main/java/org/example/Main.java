package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MathUtility
{
    public int factorial(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        int result = 1;
        for (int i = 1; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }
    public int gcd(int a, int b)
    {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public int lcm(int a, int b)
    {
        if (a == 0 || b == 0) return 0;
        int ans= Math.abs(a * b) / gcd(a, b);
        return ans;
    }
    public int fibonacci(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException("Fibonacci is not defined for negative numbers");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++)
        {
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }
    public boolean isPrime(int n)
    {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }
    public int sum(int[] array)
    {
        int result = 0;
        for (int value : array)
        {
            result += value;
        }
        return result;
    }

    public int max(int[] array)
    {
        if (array.length == 0)
        {
            throw new IllegalArgumentException("Array must not be empty");
        }
        int maxValue = array[0];
        for (int value : array)
        {
            if (value > maxValue)
            {
                maxValue = value;
            }
        }
        return maxValue;
    }
    public double power(double base, int exponent)
    {
        double result = 1.0;
        for (int i = 0; i < Math.abs(exponent); i++)
        {
            result *= base; // AOR applicable
        }
        return exponent < 0 ? 1 / result : result;
    }

    // Sort an array in ascending order
    public int[] sortArray(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < sortedArray.length - 1; i++)
        {
            for (int j = i + 1; j < sortedArray.length; j++)
            {
                if (sortedArray[i] > sortedArray[j])
                {
                    int temp = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = temp;
                }
            }
        }
        return sortedArray;
    }
}



class TransactionsCheck
{
    //This is for filtering valid transactions from the list
    public List<Transaction> filterValidTransactions(List<Transaction> transactions)
    {
        List<Transaction> validTransactions = new ArrayList<>();

        for (Transaction transaction : transactions)
        {
            if ((transaction.getAmount() > 0 && transaction.isApproved())
                    || (transaction.isHighPriority() && !transaction.isFraudulent()))
            {
                validTransactions.add(transaction);
            }
        }
        return validTransactions;
    }

    // Method to calculate rewards for eligible transactions
    public double calculateTotalRewards(List<Transaction> transactions, double rewardRate)
    {
        double totalRewards = 0.0;

        for (Transaction transaction : transactions)
        {
            if (transaction.getAmount() > 0 && transaction.isApproved() && !transaction.isFraudulent())
            {
                totalRewards += transaction.getAmount() * rewardRate;
            }
        }
        return totalRewards;
    }

    // Method to check if any transaction is suspicious
    public boolean hasSuspiciousTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions)
        {
            if (transaction.isFraudulent() || (transaction.getAmount() > 10000 && !transaction.isApproved()))
            {
                return true;
            }
        }
        return false;
    }
}





class Transaction
{
    private double amount;
    private boolean approved;
    private boolean fraudulent;
    private boolean highPriority;

    public Transaction(double amount, boolean approved, boolean fraudulent, boolean highPriority)
    {
        this.amount = amount;
        this.approved = approved;
        this.fraudulent = fraudulent;
        this.highPriority = highPriority;
    }

    public double getAmount()
    {
        return amount;
    }

    public boolean isApproved()
    {
        return approved;
    }

    public boolean isFraudulent()
    {
        return fraudulent;
    }

    public boolean isHighPriority()
    {
        return highPriority;
    }
}

class EmployeeManager
{
    public String determineBonusEligibility(int performanceScore, int tenureYears)
    {
        if (performanceScore >= 85 && tenureYears > 5)
        {
            return "Eligible for Bonus";
        }
        else if (performanceScore >= 70 && tenureYears >= 3)
        {
            return "Considered for Bonus";
        }
        else
        {
            return "Not Eligible";
        }
    }

    // Method to get a list of employees in a specific age range
    public List<Employee> getEmployeesInAgeRange(List<Employee> employees, int minAge, int maxAge)
    {
        List<Employee> eligibleEmployees = new ArrayList<>();
        for (Employee emp : employees)
        {
            if (emp.getAge() >= minAge && emp.getAge() <= maxAge)
            {
                eligibleEmployees.add(emp);
            }
        }
        return eligibleEmployees;
    }

    public String evaluateStaffingStatus(List<Employee> employees, int maxStaff)
    {
        if (employees.size() > maxStaff)
        {
            return "Overstaffed";
        }
        else if (employees.size() == maxStaff)
        {
            return "Optimal Staffing";
        } else
        {
            return "Understaffed";
        }
    }
    public double calculateHighPerformersPercentage(List<Employee> employees, int performanceThreshold)
    {
        int highPerformers = 0;
        for (Employee emp : employees)
        {
            if (emp.getPerformanceScore() >= performanceThreshold)
            {
                highPerformers++;
            }
        }
        return (highPerformers / (double) employees.size()) * 100;
    }
}

class Employee {
    private String name;
    private int age;
    private int performanceScore;

    public Employee(String name, int age, int performanceScore)
    {
        this.name = name;
        this.age = age;
        this.performanceScore = performanceScore;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public int getPerformanceScore()
    {
        return performanceScore;
    }
}



 class BitsPlaying
 {

    // Compute a simple checksum using shift operators
    public int computeChecksum(int[] data)
    {
        int checksum = 0;
        for (int value : data)
        {
            checksum ^= (value << 1) | (value >>> 1); // Combines left and right shifts
        }
        return checksum;
    }

     // Count the number of set bits (1s) in a number using shifts
    public int countSetBits(int num)
    {
        int count = 0;
        while (num > 0)
        {
            count += num & 1; // Check last bit
            num >>= 1; // Right shift to remove the last bit
        }
        return count;
    }

    // Rotate bits of a number to the left by n positions
    public int rotateLeft(int num, int positions)
    {
        int numBits = Integer.SIZE;
        int ans= (num << positions) | (num >>> (numBits - positions));
        return ans;
    }

    // Rotate bits of a number to the right by n positions
    public int setBit(int num, int index)
    {
        return num | (1 << index);
    }

     public int clearBit(int num, int index)
     {
         return num & ~(1 << index);
     }

     public int toggleBit(int num, int index)
     {
         return num ^ (1 << index);
     }

     public int countOnes(int num)
     {
         int count = 0;
         while (num != 0)
         {
             count += num & 1;
             num >>>= 1; // Unsigned right shift
         }
         return count;
     }



     // Swap two integers without using a temporary variable
    public int[] swapNumbers(int a, int b)
    {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new int[]{a, b};
    }

    // Extract a specific bit from a number (0-based index)
    public int extractBit(int num, int bitIndex)
    {
        return (num >> bitIndex) & 1;
    }

    // Check if a number is a power of 2 using bitwise operations
    public boolean isPowerOfTwo(int num)
    {
        return num > 0 && (num & (num - 1)) == 0;
    }

    // Reverse the bits in an integer
    public int reverseBits(int num)
    {
        int result = 0;
        for (int i = 0; i < Integer.SIZE; i++)
        {
            result = (result << 1) | (num & 1); // Shift result left and add last bit of num
            num >>>= 1; // Shift num right
        }
        return result;
    }

    // Find the most significant set bit (highest 1-bit position)
    public int mostSignificantBit(int num)
    {
        if (num == 0) return -1; // No bits are set
        int msb = 0;
        while (num > 1)
        {
            num >>= 1; // Keep shifting right
            msb++;
        }
        return msb;
    }
}

class BankAccount
{
    private String accountHolderName;
    private double balance;
    private String accountType;
    private int accountNumber;
    private static int accountCounter = 1000; // Static counter to generate account numbers

    // Constructor to initialize bank account details



    public BankAccount(String accountHolderName, double initialBalance, String accountType)
    {
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.accountNumber = accountCounter++;
    }
    // Deposit money into account
    public void deposit(double amount)
    {
        if (amount > 0)
        {
            balance += amount;
        } else
        {
            System.out.println("Invalid deposit amount");
        }
    }

    // Withdraw money from account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance");
        }
    }

    // Transfer funds to another account
    public void transferFunds(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);  // Withdraw from this account
            toAccount.deposit(amount);  // Deposit into the other account
            System.out.println("Transferred: " + amount + " to Account No. " + toAccount.getAccountNumber());
        } else {
            System.out.println("Transfer failed due to invalid amount or insufficient balance");
        }
    }

    // Calculate interest based on account type and balance
    public void calculateInterest() {
        double interestRate = 0.0;
        switch (accountType.toLowerCase()) {
            case "checking":
                interestRate = 0.01;
                break;
            case "savings":
                interestRate = 0.05;
                break;
            case "fixed":
                interestRate = 0.07;
                break;
            default:
                System.out.println("Unknown account type");
                return;
        }
        double interestAmount = balance * interestRate;
        balance += interestAmount;  // Update balance after applying interest
    }

    // Display account details
    public void displayAccountDetails()
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
    }

    // Getter for account number
    public int getAccountNumber()
    {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
    }
}