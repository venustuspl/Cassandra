package pl.venustus.cassandra.model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
public class Message {
    @Id
    @PrimaryKey
    @CqlName("id")
    private UUID id;
    private String email;
    private String title;
    private String content;
    private Integer magicNumber;

    public Message() {

    }

    public Message(UUID id, String email, String title, String content, Integer magicNumber) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.content = content;
        this.magicNumber = magicNumber;
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

    public Integer getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public String toString() {
        return "Message{" +
                " email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' + "}";
    }
}