import java.util.Scanner;

public class Shop {
    private User[]users;
    private Product[]products;
    public static final int EMPLOYEE_CHOOSE = 1;
    public static final int CLIENT_CHOOSE = 2;
    public static final int ADD_TO_LENGTH_ARRAY = 1;
    public static final String REGULAR_EMPLOYEE = "Regular Employee";
    public static final String MANAGER = "Manager";
    public static final String MANAGEMENT_TEAM = "Management Team";
    public static final int INT_REGULAR_EMPLOYEE = 1;
    public static final int INT_MANAGER = 2;
    public static final int INT_MANAGEMENT_TEAM = 3;
    public static final int MIN_LENGTH_PASSWORD = 6;


    public Shop(){
        this.users = new User[0];
        this.products= new Product[0];
    }
    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        int typeOfUser;
        do {
            System.out.println("Which type of user do you want?");
            System.out.println("Press 1: for Employee");
            System.out.println("Press 2: for Client");
            typeOfUser = scanner.nextInt();
            scanner.nextLine();
        }while (typeOfUser < 1 || typeOfUser > 2);
        switch (typeOfUser){
            case EMPLOYEE_CHOOSE:{
                String firstName = getFirstName();
                String lastName = getLastName();
                String username = getUsername();
                String password = getPassword();
                String rank = getRank();
                AddEmployeeToArray(firstName,lastName,username,password,rank);
                printArray(this.users);
                break;

            }
            case CLIENT_CHOOSE:{
                String firstName = getFirstName();
                String lastName = getLastName();
                String username = getUsername();
                String password = getPassword();
                System.out.println("Are you a club member? (True/ False)");
                boolean clubMember = scanner.nextBoolean();
                AddClientToArray(firstName,lastName,username,password,clubMember);
                break;
            }
        }

    }
    //פונקציות של העובד
    public void printAllClients(){
        System.out.println("This is all the clients:");
        for (int i = 0; i < this.users.length; i++) {
            System.out.println(i+1 +":" +this.users[i].toString());
            System.out.println();
        }
    }
    public void printClubMembers(){
        System.out.println("This is all club members");
        for (int i = 0, j = 1; i < this.users.length; i++) {
            if(((Client) users[i]).isClubMember()){
                System.out.println(j +": " +users[i].toString());
                System.out.println();
                j++;
            }
        }
    }
    public void printClientsMadePurchase(){
        System.out.println("This is all clients that made a purchase");
        for (int i = 0,j = 0; i < this.users.length; i++) {
            if(users[i] instanceof Client){
                if(((Client) users[i]).getPurchases() > 0){
                    System.out.println(j+": " +users[i].toString());
                    System.out.println();
                    j++;
                }
            }
        }
    }
    public void printClientThatSumPurchaseIsHigher(){
        String clientName = null;
        double higherPurchase;
        higherPurchase = ((Client)users[0]).getCostAllPurchases();
        clientName = users[0].toString();
        System.out.println("This is the client that the sum of purchase is higher");
        for (int i = 1; i < this.users.length; i++) {
            if(((Client) users[i]).getCostAllPurchases() > higherPurchase){
                higherPurchase = ((Client) users[i]).getCostAllPurchases();
                clientName = users[i].toString();
            }
        }
        System.out.println(clientName);
    }
    public void changeProductStatus(Product product){
        product.setExist(!(product.isExist()));
    }


    public User login(){
        Scanner scanner = new Scanner(System.in);
        User user = null;

        int choose;
        do {
            System.out.println("Choose type account: (1 - Employee , 2 - Client)");
            choose = scanner.nextInt();
            scanner.nextLine();
        }while (choose < EMPLOYEE_CHOOSE || choose > CLIENT_CHOOSE);

        switch (choose){
            case EMPLOYEE_CHOOSE:{
                user = isExistAccount(this.users,true);
                break;
            }

            case CLIENT_CHOOSE:{
                user = isExistAccount(this.users,false);
                break;
            }
        }
        return user;

    } //login

    private String getFirstName(){
        Scanner scanner = new Scanner(System.in);
        String firstName = null;
        do {
            System.out.println("Enter your first name: ");
            firstName = scanner.nextLine();
        } while (!isValidName(firstName));
        return firstName;
    }
    private String getLastName(){
        Scanner scanner = new Scanner(System.in);
        String lastName = null;
        do {
            System.out.println("Enter your last name: ");
            lastName = scanner.nextLine();
        }while (!isValidName(lastName));
        return lastName;
    }
    private String getUsername(){
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean checkUsername;
        do {
            System.out.println("Enter your username: ");
            username = scanner.nextLine();
            checkUsername = isUsernameExist(username);
            if(checkUsername){
                System.out.println("The username is busy!");
            }
        }while (checkUsername);
        return username;
    }
    private String getPassword() {
        Scanner scanner = new Scanner(System.in);
        String password = null;
        boolean strongPassword;
        do {
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            strongPassword = isStrongPassword(password);
            if(!strongPassword){
                System.out.println("The password is not strong!");
            }
        }while (!strongPassword);
        return password;
    }
    private String getRank(){
        Scanner scanner = new Scanner(System.in);
        int choose;
        String rank = null;
        do {
            System.out.println("Choose your rank");
            System.out.println("1 - Regular \n2 - Manager\n3 - Management team");
            choose = scanner.nextInt();
        }while (choose < 1 || choose > 3);
        switch (choose){
            case INT_REGULAR_EMPLOYEE:{
                rank = REGULAR_EMPLOYEE;
                break;
            }
            case INT_MANAGER:{
                rank = MANAGER;
                break;
            }
            case INT_MANAGEMENT_TEAM:{
                rank = MANAGEMENT_TEAM;
                break;
            }
        }
        return rank;

    }

    private String inputUsername(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        return username;
    }
    private String inputPassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return password;
    }

    public void addProductToArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name ");
        String productName = scanner.nextLine();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter club member discount percentage: ");
        int discountPercent = scanner.nextInt();
        scanner.nextLine();
        Product newProduct = new Product(productName,price,discountPercent,true);
        Product[] products= new Product[this.products.length +1];
        for (int i = 0; i < this.products.length; i++){
            products[i] = this.products[i];
        }
        products[products.length-1] = newProduct;
        this.products = products;
        printProducts();
    }

    public void printProducts(){
        System.out.println("Choose product number to change product status: ");
        for (int i = 0, j = 1; i < products.length;i++){
            System.out.println("(Press " + i +")" +"\n" +j +") " + this.products[i]);
            j++;
        }
    }

    //לקוח
    public int printProductsInStuckAndReturnLengthArray(){
        int lengthProductsArray = 0;
        if(products.length > 0) {
            for (int i = 0, j = 1; i < products.length; i++) {
                if(products[i].isExist()){
                    System.out.println(j+":" +"(Press " + i + " to add to cart)\n" + products[i]);
                    lengthProductsArray = j;
                    j++;
                }
            }
        }
        return lengthProductsArray;

    }



    private User isExistAccount(User[]users, boolean isEmployee){
        User user = null;
        String inputUsername = inputUsername();
        String inputPassword = inputPassword();
        for (int i = 0; i < this.users.length; i++) {
            if(this.users[i] instanceof Employee && isEmployee){
                if(this.users[i].getUsername().equals(inputUsername)){
                    if(this.users[i].getPassword().equals(inputPassword)){
                        user = users[i];
                        break;
                    }

                }
            }else if(this.users[i] instanceof Client && !isEmployee){
                if(this.users[i].getUsername().equals(inputUsername)){
                    if(this.users[i].getPassword().equals(inputPassword)){
                        user = users[i];
                        break;
                    }
                }
            }
        }
        return user;
    }

    private void printArray(User[]users){
        for (int i = 0; i < users.length; i++){
            System.out.print(users[i]);
            System.out.println();
        }
    }

    private void AddClientToArray(String firstName, String lastName, String username, String password, boolean isClubMember) {
        User[] newArray = new User[this.users.length + ADD_TO_LENGTH_ARRAY];
        for (int i = 0; i < this.users.length;i++){
            newArray[i] = this.users[i];
        }
        User userToAdd = new Client(firstName,lastName,username,password, isClubMember,0,0.0,null,null);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;
    }

    private void AddEmployeeToArray(String firstName, String lastName, String username, String password,String rank) {
        User[] newArray = new User[this.users.length + ADD_TO_LENGTH_ARRAY];
        for (int i = 0; i < this.users.length;i++){
            newArray[i] = this.users[i];
        }
        User userToAdd = new Employee(firstName,lastName,username,password,false,0,0.0,null,null, rank);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;
    }

    private boolean isStrongPassword(String password){
        boolean strong = false;
        if(password.length() >= MIN_LENGTH_PASSWORD){
            strong = true;
        }
        return strong;
    }

    private boolean isValidName(String name){
        boolean valid = false;
        for (int i = 0; i < name.length();i++){
            char currentChar = name.charAt(i);
            if(Character.isAlphabetic(currentChar)){
                valid = true;
            }else {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private boolean isUsernameExist(String usernameToCheck){
        boolean exist = false;
        if(usernameToCheck.length() != 0) { // בודק אם הכנסנו לפחות תו אחד
            if (this.users.length != 0){ //בודק אם אורך המערך גדול מ0
                for (int i = 0; i < this.users.length; i++) {
                    User currentUser = this.users[i];
                    if (currentUser.getUsername().equals(usernameToCheck)) {
                        exist = true;
                        break;
                    }
                }
            }

        }
        else {
            System.out.println("You must to insert correct username!");
        }
        return exist;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
