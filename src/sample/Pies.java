package sample;

public class Pies {
    private String id;
    private String name;
    private double price;
    private int amount;
    private double discount;
    private double TotalPrice;

    public Pies() {
    }

    public Pies(String string, String string1, double aDouble, double aDouble1) {
    }

    public Pies(String id, String name, double price, int amount, int discount, double totalPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.discount = discount;
        TotalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return  id + name + price + amount + discount + TotalPrice ;
    }
}
