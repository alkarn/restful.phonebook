package travelling.with.code.restful.phonebook.resources;

/**
 * A class representing a contact in the phone book.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class Contact {

    private Long id;
    private String name;
    private String surname;
    private String phone;

    public Contact(long id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
