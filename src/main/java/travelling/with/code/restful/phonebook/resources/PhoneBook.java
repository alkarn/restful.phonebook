package travelling.with.code.restful.phonebook.resources;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface that should be implemented to interact with the data layer that contains the contacts.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public interface PhoneBook {

    /**
     * If all the optional parameters are empty, will return a collection with all the phonebook's contacts.
     * <p/>
     * If there are non-empty parameters, will return a collection with the contacts that match these parameters.
     *
     * @param name - optional parameter that when not empty will be used to filter contacts with this specific name.
     * @param surname - optional parameter that when not empty will be used to filter contacts with this specific surname.
     * @param phone - optional parameter that when not empty will be used to filter contacts with this specific phone.
     * @return all contacts if all parameters are empty, or the contacts that match the non empty parameters.
     */
    public Collection<Contact> findContacts(Optional<String> name, Optional<String> surname, Optional<String> phone);

    /**
     * Will return a contact with the specific id, or an empty optional if no match could be made.
     *
     * @param id - the id of the contact that should be returned.
     * @return a contact with the specific id, or an empty optional if no match could be made.
     */
    public Optional<Contact> findContact(Long id);

}
