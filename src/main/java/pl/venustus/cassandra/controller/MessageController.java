package pl.venustus.cassandra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.venustus.cassandra.model.Magic;
import pl.venustus.cassandra.model.Message;
import pl.venustus.cassandra.model.MessageDto;
import pl.venustus.cassandra.service.MessageService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto messageDto) {
        try {
            Message message = messageService.addMessage(messageDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/messages/{emailValue}")
    public ResponseEntity<List<Message>> getAllMessagesByEmailValue(@PathVariable(required = true) String emailValue) {
        try {

            List<Message> messages = new ArrayList<>();

            if (emailValue == null)
                messages.addAll(messageService.getAllMessages());
            else
                messages = messageService.getMessageByEmailValue(emailValue);

            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/send")
    public ResponseEntity<HttpStatus> deleteMessage(@RequestBody Magic magic) {
        Integer magic_number = magic.getMagic_number();
        try {
            List<Message> messages = new ArrayList<>();

            if (magic_number == null)
                messages.addAll(messageService.getAllMessages());
            else
                messages = messageService.deleteMessage(magic_number);
            for (Message m : messages) {
                logger.info(m.toString());
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}