package clothingapp.objects;

public class Checkout {
    private static String firstName = "";
    private static String lastName = "";
    private static String email = "";
    private static String address = "";
    private static String phone = "";
    private static String city = "";
    private static String province = "";
    private static String country = "";

    public static String getFirstName() { return firstName; }
    public static String getLastName() { return lastName; }
    public static String getEmail() { return email; }
    public static String getAddress() { return address; }
    public static String getPhone() { return phone; }
    public static String getCity() { return city; }
    public static String getProvince() { return province; }
    public static String getCountry() { return country; }

    public static void setFirstName(String firstName) { Checkout.firstName = firstName; }
    public static void setLastName(String lastName) { Checkout.lastName = lastName; }
    public static void setEmail(String email) { Checkout.email = email; }
    public static void setAddress(String address) { Checkout.address = address; }
    public static void setPhone(String phone) { Checkout.phone = phone; }
    public static void setCity(String city) { Checkout.city = city; }
    public static void setProvince(String province) { Checkout.province = province; }
    public static void setCountry(String country) { Checkout.country = country; }

    public static void print(){
        System.out.println(firstName + " " + lastName);
        System.out.println(email);
        System.out.println(address);
        System.out.println(phone);
        System.out.println(city + ", " + province + ", " + country);
    }
}
