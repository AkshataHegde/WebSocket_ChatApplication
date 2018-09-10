package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * counting active users
     *
     * @return count
     */
    @Query(value = "select count(*) from user usr where usr.u_is_active=1", nativeQuery = true)
    long countAll();

    /**
     * fetching users for chat
     *
     * @param offset offset
     * @param count  count
     * @return list of users
     */
    @Query(value = "select * from user usr where usr.u_is_active=1 order by usr.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findAll(@Param("offset") int offset, @Param("count") int count);

    /**
     * count users by search values
     *
     * @param name     name
     * @param userName userName
     * @param email    email
     * @return count
     */
    @Query(value = "select count(*) from user usr where (usr.u_name like concat('%',:name,'%') or usr.u_username like concat('%',:userName,'%') or usr.u_email like concat('%',:email,'%')) and usr.u_is_active=1", nativeQuery = true)
    long countBySearchValue(@Param("name") String name, @Param("userName") String userName, @Param("email") String email);

    /**
     * fetching users by search value
     *
     * @param name     name
     * @param userName username
     * @param email    email
     * @param offset   offset
     * @param count    count
     * @return list of users
     */
    @Query(value = "select * from user usr where (usr.u_name like concat('%',:name,'%') or usr.u_username like concat('%',:userName,'%') or usr.u_email like concat('%',:email,'%')) and usr.u_is_active=1 order by usr.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findBySearchValue(@Param("name") String name, @Param("userName") String userName, @Param("email") String email, @Param("offset") int offset, @Param("count") int count);

}
