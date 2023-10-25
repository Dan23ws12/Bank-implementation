package Transactions;

import java.util.ArrayList;

/**
 * This class is a Queue of Transaction.
 * This class operates like a queue with dequeue(), enqueue() and isEmpty() working as they
 * would in any queue but this queue contains Transactions.
 */
class TransactionQueue {
    private final ArrayList<Transaction> queue;

    TransactionQueue() {
        this.queue = new ArrayList<Transaction>();
    }

    boolean isEmpty() {
        return (this.queue.isEmpty());
    }

    void enqueue(Transaction transaction) {
        this.queue.add(transaction);
    }

    public Transaction dequeue() {
        if (!isEmpty()){
            Transaction transaction = this.queue.get(0);
            this.queue.remove(transaction);
            return transaction;
        }
        return null;
    }

}