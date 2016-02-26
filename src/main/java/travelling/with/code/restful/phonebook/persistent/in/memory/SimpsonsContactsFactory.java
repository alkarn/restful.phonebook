package travelling.with.code.restful.phonebook.persistent.in.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import travelling.with.code.restful.phonebook.dao.Contact;
import travelling.with.code.restful.phonebook.dao.IndexedContact;

/**
 * An implementation of the {@link ContactsFactory} interface, that creates a collection
 * of the main Simpsons characters.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class SimpsonsContactsFactory implements ContactsFactory {

    private long identifier = 0;

    @Override
    public Collection<IndexedContact> createInitContactsCollection() {
        Collection<IndexedContact> simpsons = new ArrayList<IndexedContact>();
        simpsons.add(new IndexedContact(newUniqueIdentifier(), "Hommer", "Simpson", "+1-877-555-2501"));
        simpsons.add(new IndexedContact(newUniqueIdentifier(), "Marge", "Simpson", "+1-877-555-2502"));
        simpsons.add(new IndexedContact(newUniqueIdentifier(), "Bart", "Simpson", "+1-877-555-2503"));
        simpsons.add(new IndexedContact(newUniqueIdentifier(), "Lisa", "Simpson", "+1-877-555-2504"));
        return simpsons;
    }

    @Override
    public HashMap<Long, IndexedContact> createInitContactsMap() {
        HashMap<Long, IndexedContact> simpsons = new HashMap<>();

        Long currentId = newUniqueIdentifier();
        simpsons.put(currentId, new IndexedContact(currentId, "Hommer", "Simpson", "+1-877-555-2501"));

        currentId = newUniqueIdentifier();
        simpsons.put(currentId, new IndexedContact(currentId, "Marge", "Simpson", "+1-877-555-2502"));

        currentId = newUniqueIdentifier();
        simpsons.put(currentId, new IndexedContact(currentId, "Bart", "Simpson", "+1-877-555-2503"));

        currentId = newUniqueIdentifier();
        simpsons.put(currentId, new IndexedContact(currentId, "Lisa", "Simpson", "+1-877-555-2504"));

        return simpsons;
    }

    @Override
    public IndexedContact createIndexedContact(Contact contact) {
        return new IndexedContact(newUniqueIdentifier(), contact);
    }

    private long newUniqueIdentifier() {
        return identifier++;
    }

}
