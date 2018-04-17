package lt.itakademija.model.query;

/**
 * Created by mariusg on 2017.03.19.
 */
public final class Message {

    private Long id;

    private String text;

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
