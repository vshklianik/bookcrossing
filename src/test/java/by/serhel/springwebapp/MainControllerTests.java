package by.serhel.springwebapp;

import by.serhel.springwebapp.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry.authenticated;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/initialize-user-before.sql", "/initialize-books-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/initialize-books-before.sql", "/initialize-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTests {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private MainController controller;

    @Test
    public void mainPageTest() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='username']").string("admin"));
    }

//    @Test
//    public void messageListTest() throws Exception {
//        this.mockMvc.perform(get("/books"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id='books-list']/div").nodeCount(6));
//    }

//    @Test
//    public void filterMessageTest() throws Exception {
//        this.mockMvc.perform(get("/main").param("filter", "my-tag"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(2))
//                .andExpect(xpath("//*[@id='message-list']/div[@data-id='1']").exists())
//                .andExpect(xpath("//*[@id='message-list']/div[@data-id='3']").exists());
//    }

//    @Test
//    public void addMessageToListTest() throws Exception {
//        MockHttpServletRequestBuilder multipart = multipart(/mybooks")
//                .file("file", "picture".getBytes())
//                .param("text", "kroko")
//                .param("tag", "FANTASY")
//                .with(csrf());
//
//        this.mockMvc.perform(multipart)
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(6))
//                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']").exists())
//                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/span").string("fifth"))
//                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/i").string("#new one"));
//    }
}