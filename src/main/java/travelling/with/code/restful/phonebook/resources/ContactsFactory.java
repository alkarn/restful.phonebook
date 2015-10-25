package travelling.with.code.restful.phonebook.resources;

import java.util.Collection;

/**
 * Should be implemented to create a collection of contacts. Should be used by classes,
 * like {@link InMemoryPhoneBook}, that do not load contact data from a persistent mean,
 * but produce their own contacts and use them, as long as the server runs.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public interface ContactsFactory {

    public Collection<Contact> createContacts();

}
