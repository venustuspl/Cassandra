package pl.venustus.cassandra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.venustus.cassandra.mapper.MessageMapper;
import pl.venustus.cassandra.model.Magic;
import pl.venustus.cassandra.model.Message;
import pl.venustus.cassandra.model.MessageDto;
import pl.venustus.cassandra.repository.MessageRepository;

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
            Message message = messageRepository.save(messageMapper.mapToMessage(messageDto));
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/message")
    public ResponseEntity<List<Message>> getAllMessage(@RequestParam(required = false) Integer magic_number) {
        try {

            List<Message> messages = new ArrayList<>();

            if (magic_number == null)
                messages.addAll(messageRepository.findAll());
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


    @GetMapping("/messages/{emailValue}")
    public ResponseEntity<List<Message>> getAllMessagesByEmailValue(@PathVariable(required = true) String emailValue) {
        try {

            List<Message> messages = new ArrayList<>();

            if (emailValue == null)
                messages.addAll(messageRepository.findAll());
            else
                messages = messageRepository.findByEmail(emailValue).stream().collect(Collectors.toList());

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
        Integer magic_number = magic.getMagic_number();
        try {
            List<Message> messages = new ArrayList<>();

            if (magic_number == null)
                messages.addAll(messageRepository.findAll());
            else
                messages = messageRepository.findByMagicNumber(101).stream().collect(Collectors.toList());

            for (Message m : messages) {
                logger.info(m.toString());
            }

            messageRepository.deleteByMagicNumber(magic_number);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}