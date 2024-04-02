package africa.semicolon.chichidiary.service;

import africa.semicolon.chichidiary.dto.request.CreateEntryRequest;
import africa.semicolon.chichidiary.dto.request.LoginRequest;
import africa.semicolon.chichidiary.dto.request.RegisterRequest;
import africa.semicolon.chichidiary.dto.response.RegisterResponse;
import africa.semicolon.chichidiary.exceptions.UserNameAlreadyExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DiaryServiceImplTest {
    @Autowired
    DiaryService diaryService;


    RegisterRequest request;
    RegisterRequest request1;
    LoginRequest logins;

    @BeforeEach
    public void setUp() {

        request = new RegisterRequest();
        request1 = new RegisterRequest();
        logins = new LoginRequest();
    }



    @AfterEach
    void tearDown() {
        diaryService.deleteAll();
    }

    @Test
    public void testThatWeCanRegisterAUser(){

        request.setUserName("ChiChi");
        request.setPassWord("12345");
        RegisterResponse response = diaryService.register(request);
        assertThat(response).isNotNull();
        assertEquals(1,diaryService.getNumberOfUsers());

    }
    @Test
    public void testWeCanRegisterNewUser(){
        request.setUserName("newUser");
        request.setPassWord("09876");
        RegisterResponse response = diaryService.register(request);
        RegisterResponse response2 = diaryService.register(request1);
        assertThat(response).isNotNull();
        assertEquals(2, diaryService.getNumberOfUsers());

    }

    @Test
    public void testToRegisterTheSameUser(){

        try {

            request.setUserName("ChiChi");
            request.setPassWord("12345");
            diaryService.register(request);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertThrows(UserNameAlreadyExistException.class,()-> diaryService.register(request));

    }

    @Test
    public void testToLoginUser(){
        diaryService.register(request);

        logins.setUsername("newUser");
        logins.setPassword("09876");
        diaryService.login(logins);

        assertEquals(1,diaryService.getNumberOfUsers());


    }

    @Test
    public void testToCreateEntry(){
        diaryService.register(request);

        logins.setUsername("newUser");
        logins.setPassword("09876");
        diaryService.login(logins);

        CreateEntryRequest createRequest = new CreateEntryRequest();
        createRequest.setAuthor("Chichi");
        createRequest.setTitle("this life");
        createRequest.setBody("light is the source of joy");
        diaryService.createEntry(createRequest);

        assertEquals(1,diaryService.getNumberOfUsers());

    }

}