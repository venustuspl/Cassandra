package pl.venustus.cassandra.model;

public class MessageDto {
    private String email;
    private String title;
    private String content;
    private Integer magic_number;

    public MessageDto() {

    }

    public MessageDto( String email, String title, String content, Integer magic_number) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.magic_number = magic_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMagic_number() {
        return magic_number;
    }

    public void setMagic_number(Integer magic_number) {
        this.magic_number = magic_number;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", magicnumber=" + magic_number +
                '}';
    }
}
