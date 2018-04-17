package lt.itakademija.repository;

import lt.itakademija.model.command.CreateContact;
import lt.itakademija.model.command.CreateMessage;
import lt.itakademija.model.command.UpdateContact;
import lt.itakademija.model.query.Contact;
import lt.itakademija.model.query.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mariusg on 2017.03.19.
 */

public interface MessengerRepository {

    /**
     * Creates a contact and returns its id.
     *
     * @param createContact contact data
     * @return id
     */
    Long createContact(CreateContact createContact);

    /**
     * Deletes a contact.
     *
     * @param contactId contact id
     */
    void deleteContact(Long contactId);

    /**
     * Updates contact.
     *
     * @param contactId contact id
     * @param updateContact contact data
     */
    void updateContact(Long contactId, UpdateContact updateContact);

    /**
     * Returns all contacts.
     *
     * @return contacts
     */
    List<Contact> getContacts();

    /**
     * Returns a contact by id.
     *
     * @param contactId contact id
     * @return contact
     */
    Contact getContact(Long contactId);

    /**
     * Creates a message and returns its id.
     *
     * @param contactId contact id
     * @param createMessage message data
     * @return id
     */
    Long createMessage(Long contactId, CreateMessage createMessage);

    /**
     * Returns all messages sent to a particular contact.
     *
     * @param contactId contact id
     * @return messages
     */
    List<Message> getMessages(Long contactId);

}
