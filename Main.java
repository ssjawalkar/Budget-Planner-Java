import java.util.*;

public class Main {
    enum Category {
        FOOD, CLOTHES, ENTERTAINMENT, OTHER
    }

    static class Purchase {
        String name;
        double price;

        Purchase(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " $" + String.format("%.2f", price);
        }
    }

    private static Map<Category, List<Purchase>> purchases = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        for (Category category : Category.values()) {
            purchases.put(category, new ArrayList<>());
        }

        while (true) {
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("0) Exit");
            System.out.print("> ");
            int action = Integer.parseInt(scanner.nextLine());

            switch (action) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showPurchases();
                    break;
                case 4:
                    showBalance();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }

    private static void addIncome() {
        System.out.println("Enter income:");
        double income = Double.parseDouble(scanner.nextLine());
        System.out.println("Income was added!");
    }

    private static void addPurchase() {
        while (true) {
            System.out.println("Choose the type of purchase");
            System.out.println("1) Food");
            System.out.println("2) Clothes");
            System.out.println("3) Entertainment");
            System.out.println("4) Other");
            System.out.println("5) Back");
            System.out.print("> ");
            int type = Integer.parseInt(scanner.nextLine());

            if (type == 5) {
                return;
            }

            Category category = getCategory(type);
            if (category == null) {
                System.out.println("Invalid category. Please try again.");
                continue;
            }

            System.out.println("Enter purchase name:");
            String name = scanner.nextLine();
            System.out.println("Enter its price:");
            double price = Double.parseDouble(scanner.nextLine());

            purchases.get(category).add(new Purchase(name, price));
            System.out.println("Purchase was added!");
        }
    }

    private static void showPurchases() {
        while (true) {
            System.out.println("Choose the type of purchases");
            System.out.println("1) Food");
            System.out.println("2) Clothes");
            System.out.println("3) Entertainment");
            System.out.println("4) Other");
            System.out.println("5) All");
            System.out.println("6) Back");
            System.out.print("> ");
            int type = Integer.parseInt(scanner.nextLine());

            if (type == 6) {
                return;
            }

            if (type == 5) {
                showAllPurchases();
            } else {
                Category category = getCategory(type);
                if (category == null) {
                    System.out.println("Invalid category. Please try again.");
                    continue;
                }
                showCategoryPurchases(category);
            }
        }
    }

    private static void showCategoryPurchases(Category category) {
        List<Purchase> categoryPurchases = purchases.get(category);
        if (categoryPurchases.isEmpty()) {
            System.out.println(category + ":");
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println(category + ":");
            double total = 0;
            for (Purchase purchase : categoryPurchases) {
                System.out.println(purchase);
                total += purchase.price;
            }
            System.out.println("Total sum: $" + String.format("%.2f", total));
        }
    }

    private static void showAllPurchases() {
        double total = 0;
        boolean isEmpty = true;
        for (Category category : Category.values()) {
            List<Purchase> categoryPurchases = purchases.get(category);
            if (!categoryPurchases.isEmpty()) {
                isEmpty = false;
                System.out.println(category + ":");
                for (Purchase purchase : categoryPurchases) {
                    System.out.println(purchase);
                    total += purchase.price;
                }
            }
        }
        if (isEmpty) {
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("Total sum: $" + String.format("%.2f", total));
        }
    }

    private static void showBalance() {
        double total = 0;
        for (Category category : Category.values()) {
            for (Purchase purchase : purchases.get(category)) {
                total += purchase.price;
            }
        }
        System.out.println("Balance: $" + String.format("%.2f", total));
    }

    private static Category getCategory(int type) {
        switch (type) {
            case 1:
                return Category.FOOD;
            case 2:
                return Category.CLOTHES;
            case 3:
                return Category.ENTERTAINMENT;
            case 4:
                return Category.OTHER;
            default:
                return null;
        }
    }
}