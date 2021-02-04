package pl.venustus.cassandra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.venustus.cassandra.mapper.MessageMapper;
import pl.venustus.cassandra.model.Message;
import pl.venustus.cassandra.model.MessageDto;
import pl.venustus.cassandra.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Transactional
    public Message addMessage(MessageDto messageDto) {
        return messageRepository.save(messageMapper.mapToMessage(messageDto));
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessageByEmailValue(String emailValue) {
        return messageRepository.findByEmail(emailValue).stream().collect(Collectors.toList());
    }

    public List<Message> deleteMessage(Integer magic_number) {
        return messageRepository.deleteByMagicNumber(magic_number);
    }
}
