import java.util.*;
import java.time.LocalTime;

// Stock class (NOT public)
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// Transaction class (NOT public)
class Transaction {
    Stock stock;
    int quantity;
    String type;
    String time;

    Transaction(Stock stock, int quantity, String type) {
        this.stock = stock;
        this.quantity = quantity;
        this.type = type;
        this.time = LocalTime.now().withNano(0).toString();
    }

    void display() {
        System.out.println(
            time + " - " +
            type.toUpperCase() + " " +
            quantity + " shares of " +
            stock.symbol + " @ ₹" + stock.price
        );
    }
}

// User class (NOT public)
class User {
    String name;
    double balance;
    HashMap<String, Integer> portfolio;
    ArrayList<Transaction> transactions;

    User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
        transactions = new ArrayList<>();
    }

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost <= balance) {
            balance -= cost;
            portfolio.put(stock.symbol,
                    portfolio.getOrDefault(stock.symbol, 0) + quantity);
            transactions.add(new Transaction(stock, quantity, "buy"));
        }
    }

    void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            portfolio.put(stock.symbol, owned - quantity);
            balance += stock.price * quantity;
            transactions.add(new Transaction(stock, quantity, "sell"));
        }
    }

    double portfolioValue(HashMap<String, Stock> market) {
        double value = 0;
        for (String symbol : portfolio.keySet()) {
            value += portfolio.get(symbol) * market.get(symbol).price;
        }
        return value;
    }

    void displayPortfolio(HashMap<String, Stock> market) {
        System.out.println("\nPortfolio Details:");
        for (String symbol : portfolio.keySet()) {
            System.out.println(symbol + " : " +
                    portfolio.get(symbol) +
                    " shares @ ₹" + market.get(symbol).price);
        }
        System.out.println("Total Portfolio Value: ₹" + portfolioValue(market));
        System.out.println("Available Balance: ₹" + balance);
    }
}

// MAIN CLASS (ONLY THIS IS public)
public class Main {
    public static void main(String[] args) {

        HashMap<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOG", new Stock("GOOG", 2800));
        market.put("TSLA", new Stock("TSLA", 700));

        User user = new User("Santhiya", 100000);

        System.out.println("Market Prices:");
        for (Stock stock : market.values()) {
            System.out.println(stock.symbol + " : ₹" + stock.price);
        }

        user.buyStock(market.get("AAPL"), 100);
        user.buyStock(market.get("TSLA"), 50);
        user.sellStock(market.get("AAPL"), 20);

        user.displayPortfolio(market);

        System.out.println("\nTransaction History:");
        for (Transaction t : user.transactions) {
            t.display();
        }
    }
}