package pl.venustus.cassandra.mapper;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.stereotype.Component;
import pl.venustus.cassandra.model.Message;
import pl.venustus.cassandra.model.MessageDto;
import pl.venustus.cassandra.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {
    private final MessageRepository messageRepository;

    public MessageMapper(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message mapToMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setId(UUIDs.timeBased());
        message.setEmail(messageDto.getEmail());
        message.setContent(messageDto.getContent());
        message.setTitle(messageDto.getTitle());
        message.setMagicNumber(messageDto.getMagic_number());

        return message;
    }

    public MessageDto mapToMessageDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setEmail(message.getEmail());
        messageDto.setContent(message.getContent());
        messageDto.setTitle(message.getTitle());
        messageDto.setMagic_number(message.getMagicNumber());

        return messageDto;
    }

    public List<MessageDto> mapToMessageDtoList(List<Message> messages){
            return messages.stream().map(this::mapToMessageDto).collect(Collectors.toList());
    }
}
