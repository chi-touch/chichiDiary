package africa.semicolon.chichidiary.service;

import africa.semicolon.chichidiary.data.model.Entry;
import africa.semicolon.chichidiary.data.repository.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntryServiceImpl implements EntryService{

    private final EntryRepository entryRepository;

    @Override
    public void save(Entry entry) {
        entryRepository.save(entry);

    }

    @Override
    public void deleteEntryById(String id) {
        entryRepository.deleteById(id);

    }

    @Override
    public List<Entry> findEntriesByUserName(String userName) {

        return entryRepository.findByAuthor(userName);
    }
}
