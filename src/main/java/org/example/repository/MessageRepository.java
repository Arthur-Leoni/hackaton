package org.example.repository;

import org.example.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByVendorIdAndAccountId(String vendorId, String accountId);
}

