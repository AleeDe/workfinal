package com.journal.Journal.Service;

import com.journal.Journal.Repository.JournalEntryRepository;
import com.journal.Journal.entity.Journalentry;
import com.journal.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;



    @Transactional
    public boolean saveEntry(Journalentry journalentry,String username){
        User user = userEntryService.findByUserName(username);
        Journalentry saved = journalEntryRepository.save(journalentry);
        user.getJournalentries().add(saved);
        userEntryService.saveEntry(user);

        return true;
    }



    public List<Journalentry> GetAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<Journalentry> GetAllById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }

    public boolean DelById(ObjectId myId,String username){
        User user = userEntryService.findByUserName(username);
        user.getJournalentries().removeIf (x -> x.getId().equals(myId));
        userEntryService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
        return true;
    }

    public boolean existsById(ObjectId myId) {
        return journalEntryRepository.findById(myId).isPresent();
    }

    public Optional<Journalentry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
}
