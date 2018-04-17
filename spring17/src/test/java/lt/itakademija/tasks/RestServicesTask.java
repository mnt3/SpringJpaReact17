package lt.itakademija.tasks;

import lt.itakademija.grader.Grader;
import lt.itakademija.model.command.CreateContact;
import lt.itakademija.model.command.CreateMessage;
import lt.itakademija.model.command.UpdateContact;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.RequestEntity.method;

/**
 * Created by mariusg on 2016.12.19.
 */
public class RestServicesTask {

    private static final Logger logger = LoggerFactory.getLogger(RestServicesTask.class);

    private static TestRestTemplate restTemplate;

    private static String uri = "http://localhost:9092/spring-exam/webapi/messenger";

    @BeforeClass
    public static void setUp() {
        restTemplate = new TestRestTemplate(new RestTemplate());
    }

    @Test
    public void canCreateContacts() {
        Grader.graded(1, () -> {
            final CreateContact createContact = createContact("testas-for-creation", "Testas Testaitis-Creation");
            final Identifier id = createContactWithAssert(createContact);

            final TestContact retrievedContact = retrieveContactWithAssert(id);
            assertThat(retrievedContact, is(new TestContact(id.getId(), createContact.getUsername(), createContact.getName())));
        });
    }

    private static CreateContact createContact(String username, String name) {
        CreateContact createContact = new CreateContact();
        createContact.setUsername(username);
        createContact.setName(name);
        return createContact;
    }

    private static Identifier createContactWithAssert(CreateContact createContact) {
        logger.info("Creating contact: {}", createContact);

        ResponseEntity<Identifier> responseEntity = request(POST, uri + "/contacts", createContact, Identifier.class);
        assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.CREATED));

        final Identifier id = responseEntity.getBody();
        logger.info("Contact created. Id: {}", id);

        return id;
    }

    @Test
    public void canRetrieveContacts() {
        Grader.graded(1, () -> {
            createContactWithAssert(createContact("testas-for-retrieval", "Testas Testaitis-Retrieval"));

            logger.info("Retrieving contacts");

            ResponseEntity<List> responseEntity = request(GET, uri + "/contacts", null, List.class);
            assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.OK));
        });
    }

    @Test
    public void canDeleteContacts() {
        Grader.graded(1, () -> {
            Identifier id = createContactWithAssert(createContact("testas-for-deletion", "Testas Testaitis-Deletion"));

            logger.info("Deleting contact: id={}", id);

            ResponseEntity<Void> responseEntity = request(DELETE, uri + "/contacts/" + id.getId(), null, Void.class);
            assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.OK));
        });
    }

    @Test
    public void canRetrieveContact() {
        Grader.graded(1, () -> {
            final CreateContact createContact = createContact("testas-for-retrieval-single", "Testas Testaitis-Retrieval-Single");
            Identifier id = createContactWithAssert(createContact);

            final TestContact retrievedContact = retrieveContactWithAssert(id);
            assertThat(retrievedContact, is(new TestContact(id.getId(), createContact.getUsername(), createContact.getName())));
        });
    }

    private static TestContact retrieveContactWithAssert(Identifier id) {
        logger.info("Retrieving contact: id={}", id);

        ResponseEntity<TestContact> responseEntity = request(GET, uri + "/contacts/" + id.getId(), null, TestContact.class);
        assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.OK));

        return responseEntity.getBody();
    }

    @Test
    public void canUpdateContacts() {
        Grader.graded(1, () -> {
            final CreateContact createContact = createContact("testas-for-update", "Testas Testaitis-Update");
            Identifier id = createContactWithAssert(createContact);

            logger.info("Updating contact: {}={}", id, createContact);

            UpdateContact updateContact = new UpdateContact();
            updateContact.setName("Testas Testaitis Modifikuotas");

            ResponseEntity<Void> responseEntity = request(PUT, uri + "/contacts/" + id.getId(), updateContact, Void.class);
            assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.OK));

            final TestContact retrievedContact = retrieveContactWithAssert(id);
            assertThat(retrievedContact, is(new TestContact(id.getId(), createContact.getUsername(), updateContact.getName())));
        });
    }

    @Test
    public void canCreateMessages() {
        Grader.graded(1, () -> {
            Identifier id = createContactWithAssert(createContact("testas-for-messaging", "Testas Testaitis-Messaging"));
            createMessageWithAssert(id, createMessage("Lorem ipsum dolor sit amet"));
        });
    }

    @Test
    public void canRetrieveMessages() {
        Grader.graded(1, () -> {
            Identifier id = createContactWithAssert(createContact("testas-for-message-retrieval", "Testas Testaitis-Message-Retrieval"));
            createMessageWithAssert(id, createMessage("Lorem ipsum dolor sit amet 1"));

            ResponseEntity<List> responseEntity = request(GET, uri + "/messages/" + id.getId(), null, List.class);
            assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.OK));
        });
    }

    private static Identifier createMessageWithAssert(Identifier contactId, CreateMessage createMessage) {
        logger.info("Creating message: {}", createMessage);

        ResponseEntity<Identifier> responseEntity = request(POST, uri + "/messages/" + contactId.getId(), createMessage, Identifier.class);
        assertThat("Wrong http status", responseEntity.getStatusCode(), is(HttpStatus.CREATED));

        final Identifier id = responseEntity.getBody();
        logger.info("Message created. Id: {}", id);

        return id;
    }

    private static CreateMessage createMessage(String text) {
        CreateMessage createMessage = new CreateMessage();
        createMessage.setText(text);
        return createMessage;
    }

    private static <T> ResponseEntity<T> request(HttpMethod httpMethod, String url, Object body, Class<T> returnType) {
        try {
            final RequestEntity<Object> requestEntity = method(httpMethod, new URI(url)).accept(MediaType.APPLICATION_JSON).body(body);
            return restTemplate.exchange(requestEntity, returnType);
        } catch (URISyntaxException e) {
            throw new RuntimeException("post request failed", e);
        }
    }

    private static final class TestContact {
        private Long id;

        private String username;

        private String name;

        public TestContact() {}

        public TestContact(Long id, String username, String name) {
            this.id = id;
            this.username = username;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestContact that = (TestContact) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(username, that.username) &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, username, name);
        }
    }

    private static final class Identifier {

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Identifier{");
            sb.append("id=").append(id);
            sb.append('}');
            return sb.toString();
        }
    }

}
