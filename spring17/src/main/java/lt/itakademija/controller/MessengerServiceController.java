package lt.itakademija.controller;

import lt.itakademija.model.Id;
import lt.itakademija.model.command.CreateContact;
import lt.itakademija.model.command.CreateMessage;
import lt.itakademija.model.command.UpdateContact;
import lt.itakademija.model.query.Contact;
import lt.itakademija.model.query.Message;
import lt.itakademija.repository.MessengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mariusg on 2017.03.19.
 */

@RestController
public class MessengerServiceController {

    @Autowired
    private final MessengerRepository repository;


    public MessengerServiceController(MessengerRepository repository) {
        this.repository = repository;
    }

   @RequestMapping(value = "/webapi/messenger/contacts",method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
    public Id createContact(@RequestBody final CreateContact createContact) {
       Id id=new Id(repository.createContact(createContact));

           return id;
       // throw new UnsupportedOperationException("not implemented");
    }

    @RequestMapping(value = "/webapi/messenger/contacts",method = RequestMethod.GET)
    public List<Contact> getContacts() {

        return repository.getContacts();
       // throw new UnsupportedOperationException("not implemented");
    }

    @RequestMapping(value = "/webapi/messenger/contacts/{id}",method = RequestMethod.GET)
    public Contact getContact(@PathVariable("id") final Long contactId) {
        return repository.getContact(contactId);

        //throw new UnsupportedOperationException("not implemented");
    }
    @RequestMapping(value = "/webapi/messenger/contacts/{id}",method = RequestMethod.PUT)
    public void updateContact(@PathVariable("id") final Long contactId,
                              @RequestBody final
                              UpdateContact updateContact) {

       repository.updateContact(contactId,updateContact);
       // throw new UnsupportedOperationException("not implemented");
    }
    @RequestMapping(value = "/webapi/messenger/contacts/{id}",method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable("id") final Long contactId) {

    repository.deleteContact(contactId);
       // throw new UnsupportedOperationException("not implemented");
    }
    @RequestMapping(value="/webapi/messenger/messages/{id}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Id createMessage(@PathVariable("id") final Long contactId,@RequestBody final
                            CreateMessage createMessage) {
        Id id = new Id(repository.createMessage(contactId,createMessage));
        return id;
       //throw new UnsupportedOperationException("not implemented");
    }

    @RequestMapping(value="/webapi/messenger/messages/{id}",method = RequestMethod.GET)
    public List<Message> getMessages(@PathVariable("id") final Long contactId) {
        return repository.getMessages(contactId);
       // throw new UnsupportedOperationException("not implemented");
    }

}
