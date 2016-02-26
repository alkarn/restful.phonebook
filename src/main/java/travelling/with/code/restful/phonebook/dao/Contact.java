package travelling.with.code.restful.phonebook.dao;

/**
 * A class containing all the useful information of a contact in the phone book.
 * <p/>
 * When a phone book user wants to insert a new contact in the book, he will only be
 * concerned about the information included in this class.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class Contact {

    private String name;
    private String surname;
    private String phone;

    public Contact(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Contact(Contact contact) {
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.phone = contact.getPhone();
    }

    public Contact() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
