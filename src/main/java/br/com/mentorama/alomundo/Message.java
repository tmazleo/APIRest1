package br.com.mentorama.alomundo;

public class Message {

    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
