import java.util.Date;

public class Client extends User{
    private boolean isClubMember;
    private int purchases;
    private double costAllPurchases;
    private Date lastPurchases;
    private Cart cart;

    public boolean isClubMember() {
        return isClubMember;
    }

    public void setClubMember(boolean clubMember) {
        isClubMember = clubMember;
    }

    public Client(String firstName, String lastName, String username, String password, boolean isClubMember, int purchases, double costAllPurchases, Cart cart , Date lastPurchases ) {
        super(firstName, lastName, username, password);
        this.isClubMember = isClubMember;
        this.purchases = purchases;
        this.costAllPurchases = costAllPurchases;
        this.lastPurchases = lastPurchases;
        this.cart = cart;

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public double getCostAllPurchases() {
        return costAllPurchases;
    }

    public void setCostAllPurchases(double costAllPurchases) {
        this.costAllPurchases = costAllPurchases;
    }

    public Date getLastPurchases() {
        return lastPurchases;
    }

    public void setLastPurchases(Date lastPurchases) {
        this.lastPurchases = lastPurchases;
    }


    public String startShow() {
        String output = super.toString();
        if(!this.isClubMember) {
            output += "!";
        }else {
            output = output.substring(0,output.length()) + "(VIP)!";
        }
        return  output + "\n";
    }


    public String toString() {
        String output = "Full name: " + this.getFullName()
                + "\nIs club member: " + this.isClubMember
                + "\nAmount purchases: " + this.purchases
                + "\nTotal sum purchase: " + this.costAllPurchases
                +"\nLast purchase date: " + this.lastPurchases;
        return  output;

    }
}
