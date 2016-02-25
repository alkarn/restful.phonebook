package travelling.with.code.restful.phonebook.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import travelling.with.code.restful.phonebook.resources.Contact;
import travelling.with.code.restful.phonebook.resources.ContactsFactory;
import travelling.with.code.restful.phonebook.resources.IndexedContact;

/**
 * Creates a collection of contacts using the src/test/resources/contacts.properties file.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class ResourcesContactsFactory implements ContactsFactory {

    /**
     * A properties file which contains test contacts in the form of:
     * <p/>
     * <pre>{id}=name={name},surname={surname},phone={phone}</pre>
     * <p/>
     * Any variable can be null, except the id.
     */
    private static final ResourceBundle resourceContacts = ResourceBundle.getBundle("contacts");

    /**
     * A pattern that matches a contact from the {@link ServerSideTests#resourceContacts} and uses grouping to fetch name, surname and phone.
     * <p/>
     * <ul>
     *   <li>group(1): name</li>
     *   <li>group(2): surname</li>
     *   <li>group(3): phone</li>
     * </ul>
     */
    private static final Pattern contactPattern = Pattern.compile("(?m)^name=(.*?),surname=(.*?),phone=(.*)$");

    @Override
    public Collection<IndexedContact> createContactsCollection() {
        Collection<IndexedContact> contacts = new ArrayList<IndexedContact>();
        for (String id : resourceContacts.keySet()) {
            String contact = resourceContacts.getString(id);
            Matcher contactMatcher = contactPattern.matcher(contact);
            if (contactMatcher.matches()) {
                String name = contactMatcher.group(1);
                String surname = contactMatcher.group(2);
                String phone = contactMatcher.group(3);
                contacts.add(new IndexedContact(Long.valueOf(id), name, surname, phone));
            }
        }
        return contacts;
    }

    @Override
    public IndexedContact createIndexedContact(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Long, IndexedContact> createContactsMap() {
        return createContactsCollection().stream().collect(Collectors.toMap(IndexedContact::getId, contact -> contact));
    }

}
