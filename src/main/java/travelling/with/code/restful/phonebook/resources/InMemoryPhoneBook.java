package travelling.with.code.restful.phonebook.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

/**
 * An implementation of {@link PhoneBook}, that creates a {@link HashMap} of contacts, using a {@link ContactsFactory},
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
    private Map<Long, IndexedContact> indexedContacts;

    /**
     * Is used to create contacts for this phone book.
     */
    private ContactsFactory contactsFactory;

    public InMemoryPhoneBook() {
        contactsFactory = new SimpsonsContactsFactory();
    }

    @PostConstruct
    public void init() {
        indexedContacts = contactsFactory.createContactsMap();
    }

    @Override
    public Optional<IndexedContact> findContact(Long id) {
        return Optional.ofNullable(indexedContacts.get(id));
    }

    @Override
    public Collection<IndexedContact> findContacts(Optional<String> name, Optional<String> surname, Optional<String> phone) {
        Stream<IndexedContact> contactStream = indexedContacts.entrySet().stream().map(Entry::getValue);
        if (!name.isPresent() && !surname.isPresent() && !phone.isPresent()) {
            return contactStream.collect(Collectors.toList());
        }
        return contactStream.filter(contact -> (filterByAttribute(contact::getName, name) && filterByAttribute(contact::getSurname, surname) && filterByAttribute(contact::getPhone, phone))).collect(Collectors.toList());
    }

    @Override
    public IndexedContact addContact(Contact contact) {
        IndexedContact indexedContact = contactsFactory.createIndexedContact(contact);
        indexedContacts.put(indexedContact.getId(), indexedContact);
        return indexedContact;
    }

    @Override
    public IndexedContact addContact(IndexedContact contact) {
        indexedContacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public void deleteContact(IndexedContact contact) {
        indexedContacts.remove(contact.getId());
    }

    @Override
    public void deleteContact(Long id) {
        indexedContacts.remove(id);
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
