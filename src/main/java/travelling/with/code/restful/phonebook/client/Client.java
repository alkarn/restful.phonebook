package travelling.with.code.restful.phonebook.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import travelling.with.code.restful.phonebook.dao.IndexedContact;

public class Client {

    private RestTemplate restTemplate;
    private String targetUrl = "http://localhost:8080/phonebook/contacts";

    public Client() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public IndexedContact getContact(long id) {
        return restTemplate.getForObject(targetUrl + "/" + id, IndexedContact.class);
    }

    public Collection<IndexedContact> getContacts(Optional<String> name, Optional<String> surname, Optional<String> phone) {
        Map<String, String> requestParameters = new HashMap<>();
        if (name.isPresent()) {
            requestParameters.put("name", name.get());
        }
        if (surname.isPresent()) {
            requestParameters.put("surname", surname.get());
        }
        if (phone.isPresent()) {
            requestParameters.put("phone", phone.get());
        }
        IndexedContact[] contactsArray = restTemplate.getForObject(targetUrl, IndexedContact[].class, requestParameters);
        return Arrays.asList(contactsArray);
    }

//    public static void main(String[] args) {
//        Client client = new Client();
//        System.out.println("Will try to get Bart...");
//        System.out.println(client.getContact(3).getName());
//
//        System.out.println("Will try to get Simpsons...");
//        Collection<Contact> simpsons = client.getContacts(Optional.empty(), Optional.of("Simpsons"), Optional.empty());
//        simpsons.forEach(simpson -> {System.out.println(simpson.getName());});
//    }

}
