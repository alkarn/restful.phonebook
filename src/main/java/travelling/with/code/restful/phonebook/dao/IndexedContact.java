package travelling.with.code.restful.phonebook.dao;

/**
 * A class that extends {@link Contact}, in order to provide the {@link IndexedContact#id} field.
 * <p/>
 * When a new contact is added in the phone book, the {@link PhoneBook} will receive the information
 * given by the user, represented in a {@link Contact} class, and will generate a unique id, to index
 * the contact.
 * <p/>
 * There may be contacts with the same name and surname, since this is something that happens in the real world too,
 * and the {@link IndexedContact#id} will be used to separate them.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class IndexedContact extends Contact {

    private Long id;

    public IndexedContact(long id, String name, String surname, String phone) {
        super(name, surname, phone);
        this.id = id;
    }

    public IndexedContact(long id, Contact contact) {
        super(contact);
        this.id = id;
    }

    public IndexedContact() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
