public class User {
    private String userId;
    private String name;
    private String contact;
    private String address;

    public User(String userId, String name, String contact, String address) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Contact: " + contact + ", Address: " + address;
    }
}
