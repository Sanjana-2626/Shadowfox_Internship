import java.util.ArrayList;
import java.util.Iterator;

public class ContactFunction {

    private String name;
    private String phone;
    private String email;

    private static ArrayList<ContactFunction> contacts = new ArrayList<>();

    // Default Constructor
    public ContactFunction() {}

    // Parameterized Constructor
    public ContactFunction(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ---------------- Validation Helpers ----------------

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}"); // only digits, exactly 10
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // ---------------- Add Contact ----------------

    public boolean add(String name, String phone, String email) {

        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. Must be 10 digits.");
            return false;
        }

        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }

        // Check duplicate phone number
        for (ContactFunction con : contacts) {
            if (con.getPhone().equals(phone)) {
                System.out.println("Phone number already exists.");
                return false;
            }
        }

        contacts.add(new ContactFunction(name, phone, email));
        System.out.println("Contact Added Successfully.");
        return true;
    }

    // ---------------- Delete Contact ----------------

    public boolean delete(String phone) {
        Iterator<ContactFunction> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            ContactFunction con = iterator.next();
            if (con.getPhone().equals(phone)) {
                iterator.remove();
                System.out.println("Contact Deleted Successfully.");
                return true;
            }
        }

        System.out.println("Contact Not Found.");
        return false;
    }

    // ---------------- Update Contact ----------------

    public boolean update(String prevPhone, String name, String phone, String email) {

        if (!isValidPhone(phone)) {
            System.out.println("Invalid new phone number. Must be 10 digits.");
            return false;
        }

        if (!isValidEmail(email)) {
            System.out.println("Invalid new email format.");
            return false;
        }

        for (ContactFunction con : contacts) {
            if (con.getPhone().equals(prevPhone)) {
                con.setName(name);
                con.setPhone(phone);
                con.setEmail(email);
                System.out.println("Contact Updated Successfully.");
                return true;
            }
        }

        System.out.println("Contact Not Found.");
        return false;
    }

    // ---------------- Get All Contacts ----------------

    public ArrayList<ContactFunction> getContacts() {
        return contacts;
    }

    // ---------------- Main ----------------

    public static void main(String[] args) {
        new ContactGUI();
    }
}
