public class Address {
    private String townName;
    private String street;
//  Constructors
    public Address(String town , String street){
        this.street = street;
        this.townName = town;
    }
    public Address(){
        this("","");
    }

//    Getter And Setter

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getTownName() {
        return townName;
    }
    @Override
    public String toString() {
        return "Address{" +
                "townName='" + townName + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
