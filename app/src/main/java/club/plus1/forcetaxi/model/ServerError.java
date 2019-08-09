package club.plus1.forcetaxi.model;

public class ServerError {
    private int id;
    private String text;

    ServerError(int id, String text) {
        this.setId(id);
        this.setText(text);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
