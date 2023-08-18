package org.example.controller;

import org.example.service.MessageService;
import org.example.model.Message;
import org.example.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageResponse> getMessagesByVendorId(@PathParam("vendorId") String vendorId, @PathParam("accountId") String accountId) {
        return messageService.getMessagesByVendorId(vendorId, accountId);
    }
    @PutMapping
    public void updateMessagesByVendorId(@PathParam("vendorId") String vendorId, @PathParam("accountId") String accountId) {
        messageService.updateNotificationReadStatus(vendorId, accountId);
    }
    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }
}
