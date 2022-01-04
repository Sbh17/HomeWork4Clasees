public class User {
//    המייצגת משתמש במערכת. שדות: שם משתמש, סיסמה, מספר טלפון, האם מתווך או משתמש רגיל.
//    Symbols
    private String userName;
    private String passCode;
    private String phoneNumber;
    private boolean isMediator;
//    Constructors
    public User(String userName, String passCode, String phoneNumber, boolean isMediator){
        this.userName = userName ;
        this.passCode = passCode ;
        this.phoneNumber = phoneNumber ;
        this.isMediator = isMediator ;
    }
    public User(){
        this("","","",false);
    }
//    Getter And Setter
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassCode(){
        return passCode;
    }
    public void setPassCode(String passCode){
        this.passCode = passCode;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public boolean getIsMediator() {
        return isMediator;
    }
    public void setMediator(boolean mediator) {
        this.isMediator = mediator;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passCode='" + passCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isValidMediator=" + isMediator +
                '}';
    }
}
