package travelling.with.code.restful.phonebook;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

import travelling.with.code.restful.phonebook.exceptions.ContactNotFoundException;
import travelling.with.code.restful.phonebook.resources.Contact;
import travelling.with.code.restful.phonebook.resources.PhoneBook;

/**
 * A controller that will handle HTTP requests to the server, following Spring’s approach to building RESTful web services.
 * The controller is actually a concept of the Spring MVC framework.
 * <p/>
 * It is a common pattern for a server to include a front controller,
 * which gathers all requests to the server and then delegates responsibility for processing them to other components. A Spring
 * framework typically uses a {@link DispatcherServlet} as a front controller.
 * <p/>
 * The component that takes responsibility to process the request from the front controller is the {@link Controller}, and it
 * applies server's business logic. This class is a controller that will handle (REST) HTTP requests for the project's phonebook.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
@RestController
@RequestMapping("/phonebook/contacts")
public class PhoneBookController {
	
	@Autowired
    private PhoneBook phoneBook;

    /**
     * Exposes the URI "phonebook/contacts" and "listens" for GET reqeusts. If the request does not contain any more parameters, 
     * the method will return all contacts that exist in the phonebook. If some of the optional parameters "name", "surname" or "phone" exist in the HTTP request,
     * it will return the contacts matching these values.
     *
     * @param name - the name to be looked up in phonebook contacts.
     * @param surname - the surname to be looked up in phonebook contacts.
     * @param phone - the phone to be looked up in phonebook contacts.
     * @return a collection of contacts that match with the name, surname and/or phone provided by the request. If no parameters are provided returns all the contacts in the phonebook.
     */
    @RequestMapping(method=RequestMethod.GET)
    public Collection<Contact> getContacts(@RequestParam(value="name", required=false) String name, @RequestParam(value="surname", required=false) String surname,
            @RequestParam(value="phone", required=false) String phone) {
        return phoneBook.findContacts(Optional.ofNullable(name), Optional.ofNullable(surname), Optional.ofNullable(phone));
    }

    /**
     * Searches in phonebook for a contact that maches the GET reqeust's id and if such a contact exists, returns it.
     * 
     * @param id - the contact id to be looked up in the phone book.
     * @return the contact that matches request's id, if such contact exists.
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public Contact getContact(@PathVariable long id) {
        return phoneBook.findContact(id).orElseThrow(() -> new ContactNotFoundException(String.valueOf(id)));
    }
}
