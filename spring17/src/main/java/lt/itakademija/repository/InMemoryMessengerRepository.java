package lt.itakademija.repository;

import lt.itakademija.model.command.CreateContact;
import lt.itakademija.model.command.CreateMessage;
import lt.itakademija.model.command.UpdateContact;
import lt.itakademija.model.query.Contact;
import lt.itakademija.model.query.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by mariusg on 2017.03.19.
 */
@Repository
public class InMemoryMessengerRepository implements MessengerRepository {


    private final List<Contact> contacts = new LinkedList<>();

    private final Map<Contact, List<Message>> contactsToMessagesMap = new HashMap<>();

    private final SequenceGenerator sequenceGenerator;

    @Autowired
    public InMemoryMessengerRepository(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public Long createContact(CreateContact createContact) {
        // @formatter:off
        final Long id = sequenceGenerator.getNext();
        final Contact contact = new Contact(id,
                                            createContact.getUsername(),
                                            createContact.getName());contacts.add(contact);
        contactsToMessagesMap.put(contact, new LinkedList<>());
        return id;
        // @formatter:on
    }

    @Override
    public void deleteContact(Long contactId) {
        Contact contact = findContactById(contactId);
        contacts.remove(contact);

        contactsToMessagesMap.remove(contact);
    }

    @Override
    public void updateContact(Long contactId, UpdateContact updateContact) {
        Contact contact = findContactById(contactId);
        contact.setName(updateContact.getName());
    }

    @Override
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    @Override
    public Contact getContact(Long contactId) {
        return findContactById(contactId);
    }

    @Override
    public Long createMessage(Long contactId, CreateMessage createMessage) {
        Contact contact = findContactById(contactId);
        List<Message> messages = contactsToMessagesMap.get(contact);

        final Long id = sequenceGenerator.getNext();
        messages.add(new Message(id, createMessage.getText()));
        return id;
    }

    @Override
    public List<Message> getMessages(Long contactId) {
        Contact contact = findContactById(contactId);
        List<Message> messages = contactsToMessagesMap.get(contact);

        return messages != null ? Collections.unmodifiableList(messages) : Collections.emptyList();
    }

    private Contact findContactById(Long contactId) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(contactId)) {
                return contact;
            }
        }

        throw new RuntimeException("Contact not found by id: " + contactId);
    }


}
