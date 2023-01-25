package com.example.abc_bangk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private static final double DAILY_TRANSACTION_LIMIT = 1000.0;
    private Long accountId;


    public void withdraw(double amount, Long accountId) {
        // Retrieve the account from the repository
        Account account = accountRepository.findById(accountId).orElse(null);
        // Check if the account has sufficient funds
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        // Check if the daily transaction limit has been reached
        double totalTransactionsToday = getTotalTransactionsToday(accountId);
        if (totalTransactionsToday + amount > DAILY_TRANSACTION_LIMIT) {
            throw new DailyTransactionLimitExceededException();
        }
        // Update the balance of the account
        account.setBalance(account.getBalance() - amount);
        // Save the updated account to the repository
        accountRepository.save(account);
        // Create a new transaction object
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType("withdrawal");
        transaction.setDate(new Date());
        // Save the transaction to the repository
        transactionRepository.save(transaction);
    }

    private double getTotalTransactionsToday(long accountId) {
        // Query the TransactionRepository to get the total amount of transactions for the current day
        // for the specified account
    }

    public void deposit(double amount) {
        // Retrieve the account from the repository
        Account account = accountRepository.findById(accountId).orElse(null);
        // Update the balance of the account
        account.setBalance(account.getBalance() + amount);
        // Save the updated account to the repository
        accountRepository.save(account);
        // Create a new transaction object
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType("deposit");
        transaction.setDate(new Date());
        // Save the transaction to the repository
        transactionRepository.save(transaction);
    }
}