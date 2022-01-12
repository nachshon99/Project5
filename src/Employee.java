import java.util.Date;

public class Employee extends Client {
    private String rank;


    public static final String REGULAR_RANK = "Regular employee";
    public static final String MANAGER_RANK = "Manager";
    public static final String MANAGEMENT_TEAM_RANK = "Management team";

    public Employee(String firstName, String lastName, String username, String password, boolean isClubMember, int purchases, double costAllPurchases, Date lastPurchases,Cart cart, String rank){
        super(firstName, lastName, username,password,isClubMember,purchases,costAllPurchases, cart, lastPurchases);
        this.rank = rank;

    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String startShow(){
        String output = super.startShow().substring(0,super.startShow().indexOf('!')) + "(" + this.rank + ")!\n";
        return output;
    }

    public String toString() {
        return super.toString();
    }
}
