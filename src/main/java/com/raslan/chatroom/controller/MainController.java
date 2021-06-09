package com.raslan.chatroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raslan.chatroom.model.*;
import com.raslan.chatroom.repositories.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600, allowCredentials = "false")
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
    @GetMapping("/getRooms")
    public ArrayList<Room> getRooms(){
       ArrayList rooms= (ArrayList) roomRepository.findAll();
       return rooms;
    }
    @GetMapping("/getMessages/{id}")
    public ArrayList<Message> getMessages(@PathVariable long id){
        ArrayList<Message> messages= (ArrayList) messageRepository.findAllByRoomId(id);



        return messages;
    }

/*    @RequestMapping(
            value = "/newmessage",
            method = RequestMethod.POST)
    public void addMessage(@RequestBody Message message){
        System.out.println("messssssssssage");
        messageRepository.save(message);

    }*/


    @MessageMapping("/chatroom")
    @SendTo("/topic/messages")
    public Message message( Payload payload) throws Exception {
        Message msg = new Message();
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(payload, msg);
        UserEntity userEntity = userRepository.findById(payload.getUserId()).get();
        UserEntity returnedUser = new UserEntity();
        BeanUtils.copyProperties(userEntity, userRest);
        BeanUtils.copyProperties(userRest, returnedUser);
        msg.setUserEntity(returnedUser);
        messageRepository.save(msg);
        return msg;
    }


}
