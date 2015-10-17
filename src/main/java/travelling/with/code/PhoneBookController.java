package travelling.with.code;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phonebook/contacts")
public class PhoneBookController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getContacts() {
		return "Phonebook is getting ready to show the hottest contacts you can find...";
	}

}
