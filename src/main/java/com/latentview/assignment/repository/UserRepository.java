package com.latentview.assignment.repository;

import com.latentview.assignment.entity.UserData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

    UserData findByName(String name);

    @Query("SELECT u FROM UserData u WHERE u.id = ?1")
    UserData findByUserId(Long id);

    @Query("SELECT u FROM UserData u WHERE u.name = ?1 and u.password = ?2")
    UserData findUserByUserNameAndPassword(String name, String password);

    @Query("SELECT u FROM UserData u WHERE u.name = ?1 and u.password = ?2 and u.email = ?3")
    UserData findUserByUserNameAndPasswordAndEmail(String name, String password, String email);

    @Modifying
    @Transactional
//    DELETE FROM user_data_recommended_show_list WHERE user_data_user_id = :userId
    @Query(value = "DELETE FROM user_data_recommended_show_list WHERE user_data_user_id = :userId", nativeQuery = true)
    void deleteRecommendedShowListByUserId(@Param("userId") Long userId);

}
