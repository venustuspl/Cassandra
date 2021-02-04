package pl.venustus.cassandra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.venustus.cassandra.model.Message;
import pl.venustus.cassandra.service.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MessageController.class)
class MessageControllerTestIT {
    private static final String EMAIL = "jan.kowalski@example.com";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    public static List<Message> getMessagesList() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        return messages;
    }

    @Test
    void sholdReturnEmptyMessagesList() throws Exception {
        //given
        when(messageService.getMessageByEmailValue(EMAIL).stream().collect(Collectors.toList())).thenReturn(getMessagesList());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/messages/" + EMAIL);

        //when
        ResultActions result = mockMvc.perform(request);

        //then
        result
                .andExpect(content().string("[{\"id\":null,\"email\":null,\"title\":null,\"content\":null,\"magicNumber\":null}]"));
    }
}