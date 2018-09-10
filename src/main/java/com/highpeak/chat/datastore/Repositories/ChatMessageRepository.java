package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, Long> {

    /**
     * counting chat messages by chat room id
     *
     * @param chatRoomId id
     * @return chat messages count
     */
    @Query(value = "select count(*) from chat_message cm where cm.cm_cr_id=:chatRoomId and cm.cm_is_deleted=0", nativeQuery = true)
    long countByChatRoomId(@Param("chatRoomId") BigInteger chatRoomId);

    /**
     * fetching chat messages by chat room id
     *
     * @param chatRoomId id
     * @param offset offset
     * @param count count
     * @return list of chat messages
     */
    @Query(value = "select * from chat_message cm where cm.cm_cr_id=:chatRoomId and cm.cm_is_deleted=0 order by cm.cm_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatMessageModel> findByChatRoomId(@Param("chatRoomId") BigInteger chatRoomId, @Param("offset") int offset, @Param("count") int count);
}
