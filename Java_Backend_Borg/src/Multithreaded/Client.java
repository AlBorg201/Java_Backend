package Multithreaded;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Представляет клиента банка.
 * Хранит уникальный ID, баланс и валюту.
 */
public class Client {
    private final int id;
    private final AtomicReference<Double> balance;
    private final AtomicReference<String> currency;

    /**
     * Создаёт нового клиента.
     *
     * @param id уникальный идентификатор клиента
     * @param balance начальный баланс
     * @param currency валюта счёта
     */
    public Client(int id, double balance, String currency) {
        this.id = id;
        this.balance = new AtomicReference<>(balance);
        this.currency = new AtomicReference<>(currency);
    }

    /** @return ID клиента */
    public int getId() {
        return id;
    }

    /** @return текущий баланс */
    public double getBalance() {
        return balance.get();
    }

    /** @return текущая валюта счёта */
    public String getCurrency() {
        return currency.get();
    }

    /**
     * Обновляет баланс клиента.
     *
     * @param newBalance новый баланс
     */
    public void setBalance(double newBalance) {
        balance.set(newBalance);
    }

    /**
     * Устанавливает новую валюту счёта.
     *
     * @param newCurrency новая валюта
     */
    public void setCurrency(String newCurrency) {
        currency.set(newCurrency);
    }

    @Override
    public String toString() {
        return String.format("Client{id=%d, balance=%.2f, currency='%s'}",
                id, balance.get(), currency.get());
    }
}
