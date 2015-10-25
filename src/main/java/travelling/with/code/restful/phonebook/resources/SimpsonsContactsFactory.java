package travelling.with.code.restful.phonebook.resources;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An implementation of the {@link ContactsFactory} interface, that creates a collection
 * of the main Simpsons characters.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class SimpsonsContactsFactory implements ContactsFactory {

    @Override
    public Collection<Contact> createContacts() {
        int identifier = 0;
        Collection<Contact> simpsons = new ArrayList<Contact>();
        simpsons.add(new Contact(identifier++, "Hommer", "Simpson", "+1-877-555-2501"));
        simpsons.add(new Contact(identifier++, "Marge", "Simpson", "+1-877-555-2502"));
        simpsons.add(new Contact(identifier++, "Bart", "Simpson", "+1-877-555-2503"));
        simpsons.add(new Contact(identifier++, "Lisa", "Simpson", "+1-877-555-2504"));
        return simpsons;
    }

}
