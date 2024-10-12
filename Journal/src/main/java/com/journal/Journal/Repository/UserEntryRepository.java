package com.journal.Journal.Repository;

import com.journal.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository <User, ObjectId>{
    User findByuserName(String userName);
}
