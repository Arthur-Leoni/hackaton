package org.example.service;

import org.example.model.Message;
import org.example.model.MessageResponse;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        message.setRead(false);
        return messageRepository.save(message);
    }

    public List<MessageResponse> getMessagesByVendorId(String vendorId, String accountId) {
        List<MessageResponse> messageList = new ArrayList<>();

        messageRepository.findByVendorIdAndAccountId(vendorId, accountId).forEach(message -> {
            MessageResponse messageResponse = new MessageResponse();

            if(StringUtils.isEmpty(message.getRead())){
                message.setRead(Boolean.TRUE);
            }
            messageResponse.setImage(message.getImage());
            messageResponse.setMessage(message.getMessage());
            messageResponse.setStatus(message.getRead());
            messageList.add(messageResponse);
        });
        return messageList;
    }

    public void updateNotificationReadStatus(String vendorId, String accountId) {
        List<Message> messagesToUpdate = messageRepository.findByVendorIdAndAccountId(vendorId, accountId);
        messagesToUpdate.stream().forEach(message -> {
            message.setRead(Boolean.TRUE);
        });
        messageRepository.saveAll(messagesToUpdate);
    }
}
