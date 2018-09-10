package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomModel, Long> {

    /**
     * count chat rooms by type and ids
     *
     * @param type type
     * @param ids ids
     * @return count
     */
    @Query(value = "select count(*) from chat_room cr where cr.cr_type=:type and cr.cr_id in :ids and cr.cr_is_active=1", nativeQuery = true)
    long countByTypeAndIdInIsActiveTrue(@Param("type") String type,@Param("ids") List<BigInteger> ids);

    /**
     * fetching chat rooms by type and id
     *
     * @param type type
     * @param ids ids
     * @return chat room model
     */
    @Query(value = "select * from chat_room cr where cr.cr_type=:type and cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatRoomModel> findByTypeAndIdInIsActiveTrue(@Param("type") String type,@Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

    /**
     * count chat rooms by type, name and ids
     *
     * @param type type
     * @param name name
     * @param ids ids
     * @return count
     */
    @Query(value = "select count(*) from chat_room cr where cr.cr_type=:type and cr.cr_name=:name and cr.cr_id in :ids and cr.cr_is_active=1", nativeQuery = true)
    long countByTypeAndNameAndIdInIsActiveTrue(@Param("type") String type, @Param("name") String name, @Param("ids") List<BigInteger> ids);

    /**
     * fetching chat rooms by type, name and id
     *
     * @param type type
     * @param name name
     * @param ids ids
     * @return chat room model
     */
    @Query(value = "select * from chat_room cr where cr.cr_type=:type and cr.cr_name=:name and cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatRoomModel> findByTypeAndNameAndIdInIsActiveTrue(@Param("type") String type, @Param("name") String name, @Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

    /**
     * counting active chat rooms by ids
     *
     * @return count
     */
    long countByIdInAndIsActiveTrue(@Param("ids") List<BigInteger> ids);

    /**
     * fetching active chat rooms
     *
     * @param offset offset
     * @param count count
     * @return list of chat rooms
     */
    @Query(value = "select * from chat_room cr where cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count",nativeQuery = true)
    List<ChatRoomModel> findByIdsAndIsActiveTrue(@Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

}
