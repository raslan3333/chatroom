package com.raslan.chatroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raslan.chatroom.model.*;
import com.raslan.chatroom.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600, allowCredentials = "true")
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    RoomRepository roomRepository;


    @RequestMapping(
            value = "/newroom",
            method = RequestMethod.POST)
    public void addRoom(@RequestBody Map<String, Object>  roomMap){
        System.out.println(roomMap.get("roomName") + "roooooooooooooooooooooooom");
        final ObjectMapper mapper= new ObjectMapper();
        final Room room = mapper.convertValue(roomMap, Room.class);
        roomRepository.save(room);

    }


    @MessageMapping("/chatroom")
    @SendTo("/topic/messages")
    public Message message( Message msg) throws Exception {
        messageRepository.save(msg);
        return new Message(msg.getContent());
    }


}
