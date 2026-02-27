import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void updatePrice() {
        double change = (Math.random() - 0.5) * 10; // random fluctuation
        price += change;
        if (price < 10) price = 10; // minimum price
    }
}

class User {
    String username;
    double balance;
    HashMap<String, Integer> portfolio = new HashMap<>();

    User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    void buyStock(Stock stock, int quantity) {
        double totalCost = stock.price * quantity;

        if (balance >= totalCost) {
            balance -= totalCost;
            portfolio.put(stock.name,
                    portfolio.getOrDefault(stock.name, 0) + quantity);
            System.out.println("✅ Bought " + quantity + " shares of " + stock.name);
        } else {
            System.out.println("❌ Not enough balance!");
        }
    }

    void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.name, 0);

        if (owned >= quantity) {
            balance += stock.price * quantity;
            portfolio.put(stock.name, owned - quantity);
            System.out.println("✅ Sold " + quantity + " shares of " + stock.name);
        } else {
            System.out.println("❌ Not enough shares to sell!");
        }
    }

    void showPortfolio(List<Stock> market) {
        System.out.println("\n----- Portfolio -----");
        double totalValue = balance;

        for (Stock stock : market) {
            int qty = portfolio.getOrDefault(stock.name, 0);
            if (qty > 0) {
                double value = qty * stock.price;
                totalValue += value;
                System.out.println(stock.name + " | Shares: " + qty + " | Value: " + value);
            }
        }

        System.out.println("Available Balance: " + balance);
        System.out.println("Total Portfolio Value: " + totalValue);
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create Market Stocks
        List<Stock> market = new ArrayList<>();
        market.add(new Stock("TATA", 100));
        market.add(new Stock("RELIANCE", 200));
        market.add(new Stock("INFOSYS", 150));

        // Create User
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        User user = new User(name, 10000); // Starting balance

        int choice;

        do {
            // Update stock prices randomly
            for (Stock stock : market) {
                stock.updatePrice();
            }

            System.out.println("\n====== STOCK TRADING PLATFORM ======");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (int i = 0; i < market.size(); i++) {
                        Stock s = market.get(i);
                        System.out.println((i + 1) + ". " + s.name + " - ₹" + String.format("%.2f", s.price));
                    }
                    break;

                case 2:
                    System.out.print("Select stock number: ");
                    int buyIndex = sc.nextInt() - 1;
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    user.buyStock(market.get(buyIndex), buyQty);
                    break;

                case 3:
                    System.out.print("Select stock number: ");
                    int sellIndex = sc.nextInt() - 1;
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    user.sellStock(market.get(sellIndex), sellQty);
                    break;

                case 4:
                    user.showPortfolio(market);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}