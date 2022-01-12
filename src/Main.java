import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static final int CREATE_ACCOUNT = 1;
    public static final int CONNECT_TO_ACCOUNT = 2;
    public static final int EXIT = 3;
    public static final int PRINT_ALL_CLIENTS = 1;
    public static final int PRINT_CLUB_MEMBERS = 2;
    public static final int PRINT_PURCHASE_CLIENTS = 3;
    public static final int PRINT_PURCHASE_HIGHEST = 4;
    public static final int ADD_PRODUCT = 5;
    public static final int CHANGE_PRODUCT_STATUS = 6;
    public static final int BUY = 7;
    public static final int LOG_OUT = 8;
    public static final int MINIMUM_PRODUCTS_INDEX = 0;
    public static final int END_PURCHASE = -1;


    public static void main(String[] args) {

        //System.out.println(nowDate);


        /*System.out.println(client);
        System.out.println(employee);*/
        Shop shop = new Shop();


        //למחוק
        User[]users = new User[3];
        users[0] = new Client("Nachshon", "Kedar", "Nachshon99", "a123b456",true,0,2.1,null,null);
        users[1] = new Employee("Yossi", "Shitrit", "Yossi10", "a1b2c3d4",true,1,10.5,null,null,"Manager");
        users[2] = new Client("Daniel", "aaaaa", "Dani10", "a123b456",false,0,11.3,null,null);
        Product[] product = new Product[2];
        product[0] = new Product("Milk", 5.5, 10,true);
        product[1] = new Product("Bread", 7, 15,true);
        shop.setProducts(product);

        shop.setUsers(users);

        //shop.printProducts();


        /*shop.printAllClients();
        System.out.println("-----");
        shop.printClubMembers();
        System.out.println("-----");
        shop.printClientsMadePurchase();
        System.out.println("-----");
        shop.printClientThatSumPurchaseIsHigher();
        System.out.println("-----");
        shop.printProductsInStuck();*/


        ////למחוק

        Scanner scanner = new Scanner(System.in);

        User user;
        int option;
        do {
            do {
                printMenu();
                System.out.println("What do you want to do? ");
                option = scanner.nextInt();
            }while (option < 1 || option > 3);
            switch (option){
                case CREATE_ACCOUNT:{
                    shop.createUser();
                    break;
                }
                case CONNECT_TO_ACCOUNT:{
                    Cart cart = new Cart();
                    do {
                        user = shop.login();
                        if(user == null){
                            System.out.println("No exist account!");
                        }
                    }while (user == null);

                    System.out.println(user.startShow());
                    if (user instanceof Employee){
                        int select;
                        do {
                            do {
                                printNewMenu();
                                select = scanner.nextInt();
                                scanner.nextLine();
                                switch (select) {
                                    case PRINT_ALL_CLIENTS: {
                                        shop.printAllClients();
                                        break;
                                    }
                                    case PRINT_CLUB_MEMBERS: {
                                        shop.printClubMembers();
                                        break;
                                    }
                                    case PRINT_PURCHASE_CLIENTS: {
                                        shop.printClientsMadePurchase();
                                        break;
                                    }
                                    case PRINT_PURCHASE_HIGHEST: {
                                        shop.printClientThatSumPurchaseIsHigher();
                                        break;
                                    }
                                    case ADD_PRODUCT: {
                                        System.out.println();
                                        shop.addProductToArray();
                                        break;
                                    }
                                    case CHANGE_PRODUCT_STATUS:{
                                        int changeStatus;
                                        if(cart.getProducts().length != 0) {
                                            do {
                                                shop.printProducts();
                                                changeStatus = scanner.nextInt();
                                                scanner.nextLine();
                                            } while (changeStatus < MINIMUM_PRODUCTS_INDEX || changeStatus > cart.getProducts().length);
                                            shop.changeProductStatus(cart.getProducts()[changeStatus]);
                                            System.out.println("Status change succeeded");
                                        }else {
                                            System.out.println("No products in the list!");
                                        }

                                    }
                                }
                                if(select < PRINT_ALL_CLIENTS || select > LOG_OUT){
                                    System.out.println("Please select a valid option!");
                                }
                            } while (select < PRINT_ALL_CLIENTS || select > LOG_OUT);
                        }while (select != LOG_OUT);

                    }
                    else { //לקוח
                        int selectProduct;
                        int lengthArray;
                        int countOfThisProduct;
                        do {
                            do {
                                lengthArray = shop.printProductsInStuckAndReturnLengthArray();
                                selectProduct = scanner.nextInt();
                                scanner.nextLine();
                                if((selectProduct < MINIMUM_PRODUCTS_INDEX || selectProduct > lengthArray-1) && selectProduct != END_PURCHASE){
                                    System.out.println("The index not exist!");
                                }
                                if(selectProduct == END_PURCHASE){
                                    break;
                                }
                            }while ((selectProduct < MINIMUM_PRODUCTS_INDEX || selectProduct > lengthArray-1));
                            if(selectProduct == END_PURCHASE){
                                System.out.println("The purchase end");
                                break;
                            }

                            do {
                                System.out.println("How many units from this product? ");
                                countOfThisProduct = scanner.nextInt();
                                scanner.nextLine();
                            }while (countOfThisProduct < 0);
                            //פונקציה של קניה
                            for (int i = 0; i < countOfThisProduct; i++) {
                                cart.setProducts(cart.addToCart(shop.getProducts()[selectProduct]));
                            }
                            System.out.println("--------------");
                            System.out.println("Shopping list:\n");
                            for (int i = 0; i < cart.getProducts().length; i++) {
                                System.out.println(cart.getProducts()[i]);

                            }
                            System.out.println("End list");
                            System.out.println("--------------");

                        }while (selectProduct != END_PURCHASE);
                        ((Client)user).getCart().setProducts(cart.getProducts());
                        System.out.println("The price is: " + cart.sumPrices(user));
                        ((Client) user).setCostAllPurchases(((Client) user).getCostAllPurchases() +cart.sumPrices(user));
                        ((Client) user).setPurchases(((Client) user).getPurchases() + 1);
                        Calendar calendar = GregorianCalendar.getInstance();
                        Date nowDate = Calendar.getInstance().getTime();
                        ((Client) user).setLastPurchases(nowDate);

                    }
                    break;
                }
            case EXIT:{
                break;
            }
        }


        }while (option != EXIT);



    }


    private static void printMenu(){
        System.out.println("Press 1 - Create user.");
        System.out.println("Press 2 - Connect to an existing account.");
        System.out.println("Press 3 - Exit.");
    }
    private static void printNewMenu(){
        System.out.println("Press 1 - to print all client list.");
        System.out.println("Press 2 - to print club members.");
        System.out.println("Press 3 - to print clients that purchased at least ones");
        System.out.println("Press 4 - to print the client with the highest purchase rate.");
        System.out.println("Press 5 - to add product.");
        System.out.println("Press 6 - to change product status");
        System.out.println("Press 7 - to make purchase");
        System.out.println("Press 8 - log out");

    }



}