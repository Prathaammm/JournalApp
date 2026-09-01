package com.prathm.App.repo;
import com.prathm.App.Entity.JournalEntry;
import com.prathm.App.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);



}