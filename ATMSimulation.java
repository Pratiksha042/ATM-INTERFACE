import java.util.*;

public class ATMSimulation {
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    // Map to hold accounts with account numbers as keys
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        // Adding a sample account for demonstration purposes
        accounts.put("1234", new Account("1234", 1000, "1111"));

        // Infinite loop to keep the ATM machine running
        while (true) {
            System.out.println("Welcome to the ATM Machine");
            System.out.print("Please enter your account number: ");
            String accountNumber = scanner.nextLine();
            // Check if the entered account number exists
            if (accounts.containsKey(accountNumber)) {
                Account account = accounts.get(accountNumber);
                System.out.print("Please enter your PIN: ");
                String pin = scanner.nextLine();
                // Validate the entered PIN
                if (account.validatePIN(pin)) {
                    boolean exit = false;
                    // Loop until user chooses to exit
                    while (!exit) {
                        // Displaying the menu options
                        System.out.println("1. Balance Inquiry");
                        System.out.println("2. Cash Withdrawal");
                        System.out.println("3. Cash Deposit");
                        System.out.println("4. Change PIN");
                        System.out.println("5. Transaction History");
                        System.out.println("6. Exit");
                        System.out.print("Please choose an option: ");
                        int choice = Integer.parseInt(scanner.nextLine());

                        // Perform actions based on user's choice
                        switch (choice) {
                            case 1:
                                account.balanceInquiry();
                                break;
                            case 2:
                                account.cashWithdrawal();
                                break;
                            case 3:
                                account.cashDeposit();
                                break;
                            case 4:
                                account.changePIN();
                                break;
                            case 5:
                                account.printTransactionHistory();
                                break;
                            case 6:
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid PIN. Please try again.");
                }
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }
    }

    static class Account {
        private String accountNumber;
        private double balance;
        private String pin;
        private List<String> transactionHistory;

        // Constructor to initialize account details
        public Account(String accountNumber, double balance, String pin) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.pin = pin;
            this.transactionHistory = new ArrayList<>();
        }

        // Method to validate the entered PIN
        public boolean validatePIN(String pin) {
            return this.pin.equals(pin);
        }

        // Method for balance inquiry
        public void balanceInquiry() {
            System.out.println("Your current balance is: Rs" + balance);
            transactionHistory.add("Balance Inquiry: Rs" + balance);
        }

        // Method for cash withdrawal
        public void cashWithdrawal() {
            System.out.print("Enter the amount to withdraw: ");
            double amount = Double.parseDouble(ATMSimulation.scanner.nextLine());
            if (amount > balance) {
                System.out.println("Insufficient funds.");
            } else {
                balance -= amount;
                System.out.println("You have withdrawn: Rs" + amount);
                System.out.println("Your new balance is: Rs" + balance);
                transactionHistory.add("Withdrawal: Rs" + amount);
            }
        }

        // Method for cash deposit
        public void cashDeposit() {
            System.out.print("Enter the amount to deposit: ");
            double amount = Double.parseDouble(ATMSimulation.scanner.nextLine());
            balance += amount;
            System.out.println("You have deposited: Rs" + amount);
            System.out.println("Your new balance is: Rs" + balance);
            transactionHistory.add("Deposit: Rs" + amount);
        }

        // Method to change the PIN
        public void changePIN() {
            System.out.print("Enter your current PIN: ");
            String currentPIN = ATMSimulation.scanner.nextLine();
            if (validatePIN(currentPIN)) {
                System.out.print("Enter your new PIN: ");
                String newPIN = ATMSimulation.scanner.nextLine();
                pin = newPIN;
                System.out.println("Your PIN has been changed successfully.");
                transactionHistory.add("PIN Change");
            } else {
                System.out.println("Invalid current PIN. Please try again.");
            }
        }

        // Method to print transaction history
        public void printTransactionHistory() {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
