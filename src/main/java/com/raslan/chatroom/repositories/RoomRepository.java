package com.raslan.chatroom.repositories;


import com.raslan.chatroom.model.Message;
import com.raslan.chatroom.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
