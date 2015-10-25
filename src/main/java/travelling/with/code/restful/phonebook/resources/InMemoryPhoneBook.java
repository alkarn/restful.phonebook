package travelling.with.code.restful.phonebook.resources;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

/**
 * An implementation of {@link PhoneBook}, that creates a {@link Collection} of contacts, using a {@link ContactsFactory},
 * and maintains it in memory as long as the server runs.
 * <p/>
 * By default, the {@link ContactsFactory} that is used is {@link SimpsonsContactsFactory}, an implementation that
 * fills the contacts collection with the main characters of the Simpsons TV show: Hommer, Margie, Bart and Lisa.
 * In order to use this class with different phonebook contacts you can use
 * {@link InMemoryPhoneBook#setContactsFactory(ContactsFactory)} to set your own contact factory.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
@Repository
public class InMemoryPhoneBook implements PhoneBook {

    /**
     * Holds the contacts that will be available in this phone book.
     */
    private Collection<Contact> contacts;

    /**
     * Is used to create contacts for this phone book.
     */
    private ContactsFactory contactsFactory;

    public InMemoryPhoneBook() {
        contactsFactory = new SimpsonsContactsFactory();
    }

    @PostConstruct
    public void init() {
        contacts = contactsFactory.createContacts();
    }

    @Override
    public Optional<Contact> findContact(Long id) {
        return contacts.stream().filter(contact -> id == contact.getId()).findAny();
    }

    @Override
    public Collection<Contact> findContacts(Optional<String> name, Optional<String> surname, Optional<String> phone) {
        if (!name.isPresent() && !surname.isPresent() && !phone.isPresent()) {
            return contacts;
        }
        return contacts.stream().filter(contact -> (filterByAttribute(contact::getName, name) && filterByAttribute(contact::getSurname, surname) && filterByAttribute(contact::getPhone, phone))).collect(Collectors.toList());
    }

    /**
     * Compares a contact's String attribute with another String value, referred as <code>comparingAttribute</code> parameter.
     * For example, if the attribute you want to compare is name, the method will compare contact's name with the
     * String contained in <code>comparingAttribute</code>.
     * <p/>
     * If comparingAttribute, which is Optional, contains no value, true will be returned.
     *
     * @param getContactAttribute - the getter that will return contact's proper attribute.
     * @param comparingAttribute - the value that will be checked against contact's attribute.
     * @return - false if comparingAttribute has a value and is not equal to the contact's attribute, true otherwise.
     */
    private boolean filterByAttribute(Supplier<String> getContactAttribute, Optional<String> comparingAttribute) {
        if (comparingAttribute.isPresent()) {
            if (!comparingAttribute.get().equals(getContactAttribute.get())) {
                return false;
            };
        }
        return true;
    }

    public void setContactsFactory(ContactsFactory contactsFactory) {
        this.contactsFactory = contactsFactory;
    }

    public class DuplicateContactException extends Exception {
        private static final long serialVersionUID = 1L;

        public DuplicateContactException(String message) {
            super(message);
        }
    }

}
