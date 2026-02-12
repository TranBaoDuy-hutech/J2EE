package com.travel3d.vietlutravel.controller;

import com.travel3d.vietlutravel.model.Messages;
import com.travel3d.vietlutravel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/private-message")
    public void receive(Messages msg) {

        // 1) Lưu DB
        messageService.save(msg);

        // 2) Xác định người nhận ĐÚNG
        int receiverId;

        if (msg.getIsFromAdmin()) {
            // STAFF gửi -> USER nhận
            receiverId = msg.getCustomerID();
        } else {
            // USER gửi -> STAFF nhận
            receiverId = msg.getUserID();
        }

        // 3) Gửi realtime đúng queue người nhận
        messagingTemplate.convertAndSend(
                "/queue/user-" + receiverId,
                msg
        );
    }
}
