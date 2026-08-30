package com.prathm.App.controller;

import com.prathm.App.Entity.JournalEntry;
import com.prathm.App.repo.JournalEntryRepo;
import com.prathm.App.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/Journal")
public class JournalEntryController_v2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    } catch(Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
    }

    @GetMapping("id/{Myid}")
    public  ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId Myid){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(Myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{Myid}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId Myid){
        journalEntryService.deleteById(Myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus. OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}