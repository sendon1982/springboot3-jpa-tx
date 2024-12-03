package org.wemeet.sbjt.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(name = "User.findAll")
    List<User> findAllUsers();

    @Query(name = "User.findByEmail")
    User findUserByEmail(@Param("email") String email);

    @Query(name = "User.findByStatus")
    List<User> findUsersByStatus(@Param("status") String status);
}
