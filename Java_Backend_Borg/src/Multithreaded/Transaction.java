package Multithreaded;

/**
 * Класс описывает банковскую транзакцию.
 * Может представлять депозит, снятие, перевод или обмен валюты.
 */
public class Transaction {

    // Типы возможных транзакций
    public enum Type {
        DEPOSIT, WITHDRAW, TRANSFER, EXCHANGE
    }

    private final Type type;
    private final int clientId;
    private final int targetClientId;
    private final String fromCurrency;
    private final String toCurrency;
    private final double amount;

    /**
     * Создаёт новую транзакцию.
     *
     * @param type тип транзакции
     * @param clientId ID клиента-инициатора
     * @param targetClientId ID получателя (для перевода)
     * @param fromCurrency исходная валюта
     * @param toCurrency целевая валюта
     * @param amount сумма операции
     */
    public Transaction(Type type, int clientId, int targetClientId,
                       String fromCurrency, String toCurrency, double amount) {
        this.type = type;
        this.clientId = clientId;
        this.targetClientId = targetClientId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

    public Type getType() { return type; }
    public int getClientId() { return clientId; }
    public int getTargetClientId() { return targetClientId; }
    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Transaction{type=%s, clientId=%d, targetClientId=%d, amount=%.2f, from='%s', to='%s'}",
                type, clientId, targetClientId, amount, fromCurrency, toCurrency);
    }
}
