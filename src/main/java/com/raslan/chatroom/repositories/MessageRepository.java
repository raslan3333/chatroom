package com.raslan.chatroom.repositories;

import com.raslan.chatroom.model.*;
import org.springframework.data.jpa.repository.*;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    Iterable<Message> findAllByRoomId(long id);
}