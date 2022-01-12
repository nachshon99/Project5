public abstract class User{
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(String firstName, String lastName, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public abstract String startShow();

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    public boolean isValidName(String name){
        boolean valid = false;
        for (int i = 0; i < name.length();i++){
            char currentChar = name.charAt(i);
            if(!Character.isDigit(currentChar)){
                valid = true;
            }else {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String output = "Hello " + this.getFullName();
        return output;
    }

}
