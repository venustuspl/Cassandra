package pl.venustus.Cassandra.model;


import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Message {
    @PrimaryKey
    private UUID id;

    private String email;
    private String title;
    private String content;
    private Integer magic_number;

    public Message() {

    }

    public Message(UUID id, String email, String title, String content, Integer magic_number) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.magic_number = magic_number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        return "Message{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", magicnumber=" + magic_number +
                '}';
    }
}