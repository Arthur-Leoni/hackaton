package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Data
@AllArgsConstructor
public class Message {
    @Id
    private ObjectId id;
    private String vendorId;
    private String image;
    private String accountId;
    private String message;
    private Boolean read;

}
