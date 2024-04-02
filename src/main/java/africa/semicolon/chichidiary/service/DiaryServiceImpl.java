package africa.semicolon.chichidiary.service;

import africa.semicolon.chichidiary.data.model.Diary;
import africa.semicolon.chichidiary.data.model.Entry;
import africa.semicolon.chichidiary.data.repository.DiaryRepository;
import africa.semicolon.chichidiary.data.repository.EntryRepository;
import africa.semicolon.chichidiary.dto.request.CreateEntryRequest;
import africa.semicolon.chichidiary.dto.request.LoginRequest;
import africa.semicolon.chichidiary.dto.request.RegisterRequest;
import africa.semicolon.chichidiary.dto.response.CreatEntryResponse;
import africa.semicolon.chichidiary.dto.response.LoginResponse;
import africa.semicolon.chichidiary.dto.response.RegisterResponse;
import africa.semicolon.chichidiary.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final EntryService entryService;
    private final EntryRepository entryRepository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userAlreadyExists(registerRequest.getUserName())) {
            throw new UserNameAlreadyExistException("This username already exists");
        }

        Diary diary = new Diary();
        diary.setUserName(registerRequest.getUserName());
        diary.setPassWord(registerRequest.getPassWord());
        diary.setLocked(false);

        diaryRepository.save(diary);
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Registration Successful");
        return response;
    }

    @Override
    public Optional<Diary> findDiaryByUserName(String username) {
        return diaryRepository.findByUserName(username);
    }

    private boolean userAlreadyExists(String username) {
        return diaryRepository.findByUserName(username).isPresent();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();



      boolean isAuthenticated = authenticate(username, password);


        if (!isAuthenticated) {
            throw new InvalidPasswordException("Invalid username or password");
        }

        LoginResponse response = new LoginResponse();
        response.setMessage("Login was successful");
        return response;
    }

    private boolean authenticate(String username, String password) {
        Optional<Diary> diaryOptional = diaryRepository.findByUserName(username);
        return diaryOptional.isPresent() && diaryOptional.get().getPassWord().equals(password);
    }

    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public CreatEntryResponse createEntry(CreateEntryRequest createEntryRequest) {
        String entryUser = createEntryRequest.getAuthor();
        Optional<Diary> diaryOptional = findDiaryByUserName(entryUser);
        if (diaryOptional.isEmpty()) {
            throw new InvalidDiaryException("Diary does not exist");
        }

        validateEntry(createEntryRequest);

        Entry entry = new Entry();
        entry.setAuthor(createEntryRequest.getAuthor());
        entry.setBody(createEntryRequest.getBody());
        entry.setTitle(createEntryRequest.getTitle());
        entryService.save(entry);

        CreatEntryResponse createResponse = new CreatEntryResponse();
        createResponse.setMessage("You have successfully created an entry");
        return createResponse;
    }

    @Override
    public void deleteAll() {
        diaryRepository.deleteAll();
    }

    private void validateEntry(CreateEntryRequest createEntryRequest) {
        List<Entry> myEntries = entryService.findEntriesByUserName(createEntryRequest.getAuthor());
        for (Entry entry : myEntries) {
            if (entry.getTitle().equalsIgnoreCase(createEntryRequest.getTitle())) {
                throw new EntryAlreadyExitException("This entry already exists");
            }
        }

}


    private void isLocked(Diary diary) {
        if (diary.isLocked()) {
            throw new TitleAlreadyExistException("this title already exist");
        }


//    public void logOut(LoginOutRequest loginOutRequest){
//        Diary diary  = new Diary();
//        diary.
//
//    }


    }
}
