package africa.semicolon.chichidiary.service;

import africa.semicolon.chichidiary.data.model.Diary;
import africa.semicolon.chichidiary.dto.request.CreateEntryRequest;
import africa.semicolon.chichidiary.dto.request.LoginRequest;
import africa.semicolon.chichidiary.dto.request.RegisterRequest;
import africa.semicolon.chichidiary.dto.response.CreatEntryResponse;
import africa.semicolon.chichidiary.dto.response.LoginResponse;
import africa.semicolon.chichidiary.dto.response.RegisterResponse;

import java.util.Optional;

public interface DiaryService {

    RegisterResponse register(RegisterRequest registerRequest);
    Optional<Diary> findDiaryByUserName(String username);

    LoginResponse login(LoginRequest loginRequest);

    long getNumberOfUsers();

    CreatEntryResponse createEntry(CreateEntryRequest createEntryRequest);


    void deleteAll();
}
