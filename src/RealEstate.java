import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
public class RealEstate {
//    Symbols & States
    private User[] users;
    private Property[] property;
    private Address[] address;
    protected static final int MIN_CHARS_IN_PASSWORD = 8;
    protected static final int NORMAL_USER_PROPERTY_POST = 3;
    protected static final int MEDIATOR_PROPERTY_POSTS = 10;
//        Constructors
    public RealEstate(User[] user, Property[] property ,Address[] address){
        this.users = user;
        this.property = property;
        this.address = address;
    }
    public RealEstate(){
        this(null,null,null);
    }
//    Getter & Setter
    public Address[] getAddress() {
        return address;
    }
    public void setAddress(Address[] address) {
        this.address = address;
    }
    public Property[] getProperty() {
        return property;
    }
    public void setProperty(Property[] property) {
        this.property = property;
    }
    public User[] getUsers() {
        return users;
    }
    public void setUsers(User[] users){
        this.users = users;
    }
//     Printing the object
    @Override
    public String toString() {
        return "RealEstate{" +
                "user=" + Arrays.deepToString(users) +
                ", property=" +  Arrays.deepToString(property) +
                ", address=" +  Arrays.deepToString(address) +
                '}';
    }
    private boolean isValidPhoneNumber(String phoneNum){
      if (phoneNum.charAt(0) != '0' && phoneNum.charAt(1) != '5' && phoneNum.length() != 10)
          return false;
      else{
          for (int i = 1 ; i<=phoneNum.length()-1;i++)
              if (!(phoneNum.charAt(i) >= 48 && phoneNum.charAt(i) <= 57))
                  return false;
      }
      return true;
    }
    private boolean isUserNameExist(String userName){
        boolean flag = false;
        int i = 0;
        while (i< this.users.length) {
            User currentUser = this.users[i];
           if (currentUser.getUserName().equals(userName)) {
               flag = true;
               break;
           }
            i++;
        }
        return flag;
    }
//        Ascii table symbols start from 33 to 47
//        if the method return true that mean it's
//        a strong password else it's not
//        ask the user to enter a new Strong password
    protected boolean passCodeValidation(String passCode){
      boolean strong = false;
      boolean validLength = false;
      boolean hasLetter = false;
      boolean hasDigit = false;
      boolean hasChar = false;
      if (passCode.length() >= MIN_CHARS_IN_PASSWORD){
          validLength = true ;
      }
      for (int i = 0 ; i<passCode.length(); ++i){
          char currentChar = passCode.charAt(i);
          if (Character.isAlphabetic(currentChar)){
              hasLetter = true;
          }else if (Character.isDigit(currentChar)){
              hasDigit = true;
          }else if (passCode.charAt(i) =='$'|| passCode.charAt(i) == '_' || passCode.charAt(i) == '%')
              hasChar = true;
      }
      if (hasDigit && hasLetter && hasChar && validLength) strong = true;
      return strong;
    }
//      Syntax of adding method
    protected void addUserToArray(User user){
        User[] newArray = new User[this.users.length+1];
        for (int i=0;i< newArray.length;i++)
            newArray[i]=this.users[i];
        newArray[this.users.length] = user;
        this.users = newArray;
    }
    private boolean isMediator(String answer){
        return answer.equals("Yes") || answer.equals("yes");
    }
    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        User user = null;
        String userName = null;
        String passCode = null;
        String phoneNum = null;
        String answer = null;
        do {
            System.out.print("Please insert a user name : " );
            userName = scanner.nextLine();
            System.out.print("Please insert a password : " );
            passCode = scanner.nextLine();
            System.out.print("Please insert your phone number : " );
            phoneNum = scanner.nextLine();
            System.out.print("Are you a mediator ?");
            answer = scanner.nextLine();
            boolean flag = false;
            if (answer.equals("Yes") || answer.equals("yes")){
                flag = true;
            }else if (answer.equals("No") || answer.equals("no")){
                flag = false;
            }
            user = new User(userName,passCode,phoneNum,flag);
        }while (!isUserNameExist(userName) && !passCodeValidation(passCode) && !isValidPhoneNumber(phoneNum));
        addUserToArray(user);
    }
    public User logIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter you user name :" );
        String userName = scanner.nextLine();
        System.out.print("Please enter you Password :" );
        String passWord = scanner.nextLine();
//        looking forward the inputs & then save the object & return it to the user ;
        for (User user : this.users) {
            if (user.getUserName().equals(userName) && user.getUserName().equals(passWord)) {
                return user;
            }
        }
        return null;
    }
    private int countUserPosts(String userName){
        int counter = 0;
        for (int i = 0; i< this.property.length ; ++i){
            if (this.property[i].getName().equals(userName))
                counter++;
        }
        return counter;
    }
    public boolean postNewProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int userPostOnPropArr = countUserPosts(user.getUserName());
        if (user.getIsMediator()){
            if (userPostOnPropArr == MEDIATOR_PROPERTY_POSTS)
                return false;
            else {
                Property newPropPosted = new Property();
                if (userPostOnPropArr < MEDIATOR_PROPERTY_POSTS){
                    System.out.println("Please insert your street name and then your city name :");
                    String streetName = scanner.nextLine();
                    String cityName = scanner.nextLine();
                    Address address = new Address(cityName,streetName);
                    newPropPosted.setAddress(address);
                    System.out.println();
                }
            }
        }else if (user.getIsMediator() == false){
            if (userPostOnPropArr == NORMAL_USER_PROPERTY_POST)
                return false;
            else {
                Property newPropPosted = new Property();
                if (userPostOnPropArr < NORMAL_USER_PROPERTY_POST){

                    Address address = new Address();
                    System.out.println("Please insert you town name ");
                    String cityName = scanner.nextLine();
                    System.out.println("Please insert you street name ");
                    String streetName = scanner.nextLine();
                    address.setStreet(streetName);
                    address.setTownName(cityName);

                }
            }
        }
        return true;
    }

//    Adding new object to the property array ;
    private void addPropertyToArray(Property property){
        Property[] newArray = new Property[this.property.length+1];
        for (int i = this.property.length - 1; i >= 0; --i){
            newArray[i] = this.property[i];
        }
        newArray[this.property.length] = property;
        this.property = newArray;
    }
//  Print Properties Array
    public void printAllProperties(){
        for (int i=0;i<this.property.length;i++)
            System.out.println(this.property[i].toString());
    }
    public void printUserProperties(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert your user name :");
        String userName = scanner.nextLine();
        if (isUserNameExist(userName)){
            for (int i=0; i<this.property.length;i++){
                if (this.property[i].getName().equals(userName))
                    System.out.println(this.property[i].toString() +" ");
            }
        }
    }
    public Property[] search(){
        Property[] properties = new Property[10];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is for rent or sell ?");
        String Answer = scanner.nextLine();
        System.out.println();
        System.out.print("which type you want penthouse,apartment,basic house ...? ");
        String type = scanner.nextLine();
        System.out.println();
        System.out.print("how much rooms you want ? ");
        int roomsNumber = scanner.nextInt();
        System.out.println("insert a range of prises ? , :");
        System.out.print("The minimum price ?");
        int minPrice = scanner.nextInt();
        System.out.println();
        System.out.print("The maximum price ?");
        int maxPrice = scanner.nextInt();
        if (type.equals(-999) || roomsNumber == -999)
            return null;
        else {
            for (int i = 0; i < this.property.length; ++i) {
                if (this.property[i].getType().equals(type) && this.property[i].isForRent() && this.property[i].getNumberOfRooms() == roomsNumber && this.property[i].getPrice() >=minPrice &&
                        this.property[i].getPrice() <= maxPrice)
                    properties[i] = this.property[i];
            }
            return properties;
        }
    }
//    helping methods
    private String[] getCitiesWithoutDuplicates(){
        int len = this.address.length;
        boolean[] isSame = new boolean[len];
        int isSameCount = 0;
        for (int i=0; i<len; i++){
            for (int j=i+1; i<len; i++){
                if (this.address[i].getTownName().equals(this.address[j].getTownName())){
                    isSame[i] = true;
                    isSameCount ++;
                }
            }
        }
        String[] res = new String[len-isSameCount];
        int counter = 0;
        for (int i=0; i<len ; i++){
            if (!isSame[i]){
                res[counter] = this.address[i].getTownName();
                counter++;
            }
        }
        return res;
    }
   public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int userPosts = countUserPosts(user.getUserName());
        if (userPosts == 0)
            System.out.println("Sorry you don't have a properties to delete 😑");
        else {
            if (user.getIsMediator() == true){
                Property[] userProps = getUserProps(user.getUserName(), MEDIATOR_PROPERTY_POSTS);
                printUserProperties(user.getUserName());
                System.out.println("now choose which property to delete by index on list");
                int index = scanner.nextInt();
                userProps[index] = null;

            }else if (user.getIsMediator() == false){
                Property[] props = getUserProps(user.getUserName(), NORMAL_USER_PROPERTY_POST);
                printUserProperties(user.getUserName());
                System.out.println("now choose which property to delete by index on list");
                int index = scanner.nextInt();
                props[index] = null;
            }
        }
   }
//  helping methods
   private Property[] getUserProps(String userName,int len){
        Property[] newList = new Property[len];
        for (int i=0; i< this.property.length; i++)
            if (this.property[i].getName().equals(userName))
                newList[i] = this.property[i];
        return newList;
   }
    private void printUserProperties(String userName){
        System.out.println("Please insert your user name :");
        if (isUserNameExist(userName)){
            for (int i=0; i<this.property.length;i++){
                if (this.property[i].getName().equals(userName))
                    System.out.println(this.property[i].toString() +" ");
            }
        }
    }
    private String[] printTowns(){
        String[] towns = new String[10];
        towns[0] = this.address[0].getTownName();
        for (int i=0;i<this.address.length;i++){
            for (int j=i+1; j<this.address.length;j++){
                if (!(towns[i] == this.address[j].getTownName()))
                    towns[j] = this.address[j].getTownName();
            }
        }
        return towns;
    }
    private String[] withOutDuplicates(){
        boolean isFound = false;
        String[] temp = new String[address.length];
       for (int i =0;i<this.address.length;i++){
           temp[i] = this.address[i].getTownName();
       }
       String[] nonDuplicates = new String[address.length];
       for (int j=0;j< temp.length; j++){
           String current = temp[j];
           for (int i =j+1;i<temp.length;i++){
               if (this.address[i].getTownName() != current)
                  nonDuplicates[j] =current;
           }
       }
       return nonDuplicates;
    }
    /*
   אם המשתמש לא פרסם אף נכס עד כה, תודפס הודעה מתאימה והמתודה תסתיים.
אם המשתמש פרסם לפחות נכס אחד, תוצג למשתמש רשימה ממוספרת של הנכסים שהוא עצמו פרסם
(אין להדפיס למשתמש נכס שלא פורסם על ידו)
 והמשתמש יתבקש לבחור את המספר של הנכס שאת פרסומו הוא מעוניין להסיר.
במידה ופעולת המחיקה התבצעה בהצלחה, יש להדפיס הודעה מתאימה

    */

}
