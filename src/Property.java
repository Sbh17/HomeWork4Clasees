public class Property {
//    Symbols & States
    private Address address;
    private int numberOfRooms;
    private int price;
    private String type;
    private boolean isForRent;
    private int houseNumber ;
    private int floorNumber ;
    private String name;
//    Constructors
    public Property(Address Address, int numberOfRooms,int price,String type,boolean isForRent,int houseNumber, int floorNumber,String name){
        this.address = new Address(Address.getTownName(), Address.getStreet());
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.name = name;
    }
    public Property(){
        this(new Address(),0,0,"",false,0,0,"");
    }
//     Getter & Setter
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isForRent() {
        return isForRent;
    }
    public void setForRent(boolean forRent) {
        isForRent = forRent;
    }
    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Property{" +
                "address=" + address +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isForRent=" + isForRent +
                ", houseNumber=" + houseNumber +
                ", floorNumber=" + floorNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
