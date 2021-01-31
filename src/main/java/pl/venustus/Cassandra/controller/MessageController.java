package pl.venustus.Cassandra.controller;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.venustus.Cassandra.model.Magic;
import pl.venustus.Cassandra.model.Message;
import pl.venustus.Cassandra.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message _message = messageRepository.save(new Message(UUIDs.timeBased(), message.getEmail(), message.getTitle(), message.getContent(), message.getMagic_number()));
            return new ResponseEntity<>(_message, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/message")
    public ResponseEntity<List<Message>> getAllMessage(@RequestParam(required = false) Integer magic_number) {
        try {

            List<Message> messages = new ArrayList<Message>();

            if (magic_number == null)
                messageRepository.findAll().forEach(messages::add);
            else
                messages = messageRepository.findByMagic_number(magic_number).stream().collect(Collectors.toList());

            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> deleteMessage(@RequestBody Magic magic) {
        System.out.println(magic.getMagic_number());
        Integer magic_number = magic.getMagic_number();
        try {

//
//            List<Message> messages = new ArrayList<Message>();
//
//            if (magic_number == null)
//                messageRepository.findAll().forEach(messages::add);
//            else
//                 messageRepository.findByMagic_number(magic_number).stream().collect(Collectors.toList());
//
//            for (Message m : messages) {
//                System.out.println(m.toString());
//            }
//
//            messageRepository.deleteByMagic_number(magic_number);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}