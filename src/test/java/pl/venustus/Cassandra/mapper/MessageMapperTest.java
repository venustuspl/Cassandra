package pl.venustus.Cassandra.mapper;

import com.datastax.driver.core.utils.UUIDs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.venustus.Cassandra.model.Message;
import pl.venustus.Cassandra.model.MessageDto;
import pl.venustus.Cassandra.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

class MessageMapperTest {
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private MessageMapper messageMapper;

    @BeforeEach
    void setup() {
        messageMapper = new MessageMapper(messageRepository);
    }

    @Test
    void mapToMessage() {
        //given
        MessageDto messageDto = getMessageDto();

        //when
        Message message = messageMapper.mapToMessage(messageDto);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("test@test.pl", message.getEmail()),
                () -> Assertions.assertEquals("messageDtoTitle", message.getTitle()),
                () -> Assertions.assertEquals("messageDtoContent", message.getContent()),
                () -> Assertions.assertEquals(101, message.getMagicNumber()),
                () -> Assertions.assertNotNull(message.getId()));
    }

    @Test
    void mapToMessageDto() {
        //given
        Message message = getMessage();

        //when
        MessageDto messageDto = messageMapper.mapToMessageDto(message);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("test@test.pl", messageDto.getEmail()),
                () -> Assertions.assertEquals("messageTitle", messageDto.getTitle()),
                () -> Assertions.assertEquals("messageContent", messageDto.getContent()),
                () -> Assertions.assertEquals(101, messageDto.getMagic_number()));
    }

    @Test
    void mapToMessageDtoList() {
        //given
        List<Message> messages = new ArrayList<>();
        messages.add(getMessage());

        //when
        List<MessageDto> messageDtoList = messageMapper.mapToMessageDtoList(messages);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, messageDtoList.size()),
                () -> Assertions.assertEquals("messageTitle", messageDtoList.get(0).getTitle()),
                () -> Assertions.assertEquals("messageContent", messageDtoList.get(0).getContent()),
                () -> Assertions.assertEquals(101, messageDtoList.get(0).getMagic_number()));
    }

    public Message getMessage() {
        Message message = new Message();
        message.setId(UUIDs.timeBased());
        message.setEmail("test@test.pl");
        message.setContent("messageContent");
        message.setTitle("messageTitle");
        message.setMagicNumber(101);
        return message;
    }

    public MessageDto getMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setEmail("test@test.pl");
        messageDto.setContent("messageDtoContent");
        messageDto.setTitle("messageDtoTitle");
        messageDto.setMagic_number(101);
        return messageDto;
    }
}