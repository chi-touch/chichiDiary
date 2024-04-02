package africa.semicolon.chichidiary.data.repository;

import africa.semicolon.chichidiary.data.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, String> {

    List<Entry> findByAuthor(String userName);
}
