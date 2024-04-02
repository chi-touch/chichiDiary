package africa.semicolon.chichidiary.data.repository;

import africa.semicolon.chichidiary.data.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiaryRepository extends MongoRepository<Diary, String> {

    Optional<Diary> findByUserName(String userName);
}
