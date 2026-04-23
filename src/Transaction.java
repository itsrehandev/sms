import java.time.LocalDate;

abstract class Transaction {
    protected String transactionId;
    protected LocalDate transactionDate;
    protected double amount;
    
    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.now();
        this.amount = amount;
    }
    
    abstract void recordTransaction();
    
    public double getAmount() {
        return amount;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}