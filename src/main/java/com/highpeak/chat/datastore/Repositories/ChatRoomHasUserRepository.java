package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatRoomHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ChatRoomHasUserRepository extends JpaRepository<ChatRoomHasUser, Long> {

    /**
     * fetching active chat room ids by userId
     *
     * @param userId user id
     * @return chat room ids
     */
    @Query(value = "select cru_cr_id from chat_room_has_user cru where cru.cru_cu_id=:userId and cru.cru_is_active=1", nativeQuery = true)
    List<BigInteger> findByChatUserIdAndIsActiveTrue(@Param("userId") BigInteger userId);

    @Modifying
    @Query(value = "UPDATE ChatRoomHasUser c SET c.isActive=false where c.chatRoomId=:chatRoomId and c.chatUserId=:userId ")
    Integer leaveGroup(@Param("chatRoomId") BigInteger chatRoomId,@Param("userId") BigInteger userId);
}
