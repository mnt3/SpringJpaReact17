package lt.itakademija.model.command;

/**
 * Created by mariusg on 2017.03.19.
 */
public final class CreateMessage {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateMessage{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
