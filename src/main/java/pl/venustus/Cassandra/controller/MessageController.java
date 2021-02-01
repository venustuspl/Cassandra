package pl.venustus.Cassandra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.venustus.Cassandra.mapper.MessageMapper;
import pl.venustus.Cassandra.model.Magic;
import pl.venustus.Cassandra.model.Message;
import pl.venustus.Cassandra.model.MessageDto;
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
    @Autowired
    MessageMapper messageMapper;
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto messageDto) {
        try {
            Message _message = messageRepository.save(messageMapper.mapToMessage(messageDto));
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
                messages = messageRepository.findByMagicNumber(magic_number).stream().collect(Collectors.toList());

            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/messages/{email}")
    public ResponseEntity<List<Message>> getAllMessagesByEmaila(@PathVariable(required = true) String email) {
        try {

            List<Message> messages = new ArrayList<>();

            if (email == null)
                messageRepository.findAll().forEach(messages::add);
            else
                messages = messageRepository.findByEmail(email).stream().collect(Collectors.toList());

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
        System.out.println(magic.getMagic_number());
        Integer magic_number = magic.getMagic_number();
        try {
            logger.info(String.valueOf(messageRepository.findByMagicNumber(101).stream().collect(Collectors.toList()).size()));

            List<Message> messages = new ArrayList<>();

            if (magic_number == null)
                messageRepository.findAll().forEach(messages::add);
            else
                 messages = messageRepository.findByMagicNumber(101).stream().collect(Collectors.toList());

            for (Message m : messages) {
                System.out.println(m.toString());
            }

            messageRepository.deleteByMagicNumber(magic_number);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}