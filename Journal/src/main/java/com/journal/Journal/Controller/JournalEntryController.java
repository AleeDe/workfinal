package com.journal.Journal.Controller;

import com.journal.Journal.Service.JournalEntryService;
import com.journal.Journal.Service.UserEntryService;
import com.journal.Journal.entity.Journalentry;
import com.journal.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();
        User user = userEntryService.findByUserName(username);
        List<Journalentry> list = user.getJournalentries();
        if (list != null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("{username}/id/{myId}")
//    public ResponseEntity<Journalentry> getAllById(@PathVariable ObjectId myId, @PathVariable String username) {
//        User user = userEntryService.findByUserName(username);
//        List<Journalentry> list = user.getJournalentries();
//
//        // Use stream to find the journal entry with the matching myId
//        Optional<Journalentry> journalentry = list.stream()
//                .filter(entry -> entry.getId().equals(myId))
//                .findFirst();
//
//        if (journalentry.isPresent()) {
//            return new ResponseEntity<>(journalentry.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


    @PostMapping
    public  ResponseEntity<String> createEntry(@RequestBody Journalentry myEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();

        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry,username);

        return new ResponseEntity<>(myEntry.toString(),HttpStatus.CREATED);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<String> DelById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();
        journalEntryService.DelById(myId,username);
        return new ResponseEntity<>("Document not found", HttpStatus.NO_CONTENT);

    }



    @PutMapping("/{myid}")
    public ResponseEntity<?>updateJournalById(
            @PathVariable ObjectId myid,
            @RequestBody Journalentry newEntry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();
        Journalentry old = journalEntryService.findById(myid).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old,username);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
