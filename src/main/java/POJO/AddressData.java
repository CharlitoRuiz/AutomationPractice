package POJO;

public class AddressData {
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String phone;
    private String country;
    private String address;
    private String city;
    private String state;
    private String usState;
    private String zip;

    public AddressData(String _firstName, String _lastName,
                       String _companyName, String _email, String _phone, String _country,
                       String _address, String _city, String _state, String _usState, String _zip){

        this.firstName = _firstName;
        this.lastName = _lastName;
        this.companyName = _companyName;
        this.email = _email;
        this.phone = _phone;
        this.country = _country;
        this.address = _address;
        this.city = _city;
        this.state = _state;
        this.usState = _usState;
        this.zip = _zip;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCompanyName() { return companyName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCountry() { return country; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getUSState() { return usState; }
    public String getZip() { return zip; }
}
