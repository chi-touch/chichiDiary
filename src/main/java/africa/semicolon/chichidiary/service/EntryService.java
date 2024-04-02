package africa.semicolon.chichidiary.service;

import africa.semicolon.chichidiary.data.model.Entry;

import java.util.List;

public interface EntryService {
    void  save(Entry entry);
    void deleteEntryById(String id);
    List<Entry> findEntriesByUserName(String userName);

}
