package com.journal.Journal.Repository;

import com.journal.Journal.entity.Journalentry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository <Journalentry, ObjectId>{
}
