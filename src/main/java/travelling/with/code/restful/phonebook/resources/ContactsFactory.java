package travelling.with.code.restful.phonebook.resources;

import java.util.Collection;
import java.util.Map;

/**
 * Should be implemented to create a collection of initial contacts that will be used by classes,
 * like {@link InMemoryPhoneBook}, that do not load contact data from a persistent mean,
 * but produce their own contacts and use them, as long as the server runs.
 * <p/>
 * This factory will also handle the indexing of the contacts that are inserted in the phone book,
 * removing this responsibility from the {@link PhoneBook} implementations that will use it.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public interface ContactsFactory {

    /**
     * Should be implemented to create an initial collection of contacts.
     *
     * @return a collection of initial contacts.
     */
    public Collection<IndexedContact> createInitContactsCollection();

    /**
     * Should be implemented to create an initial map of contacts.
     *
     * @return a map of initial contacts.
     */
    public Map<Long, IndexedContact> createInitContactsMap();

    /**
     * Should be implemented to index a {@link Contact} and transform it
     * to an {@link IndexedContact}.
     *
     * @param contact - contact to be indexed.
     * @return an {@link IndexedContact} derived from the contact passed as parameter.
     */
    public IndexedContact createIndexedContact(Contact contact);

}
