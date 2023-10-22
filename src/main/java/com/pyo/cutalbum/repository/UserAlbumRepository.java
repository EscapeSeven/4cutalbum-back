package com.pyo.cutalbum.repository;

import com.pyo.cutalbum.entity.UserAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAlbumRepository extends JpaRepository<UserAlbum,Long> {
    boolean existsUserAlbumByInviteCode(String inviteCode);

    @Query(value = "select u from UserAlbum u left join fetch u.userPosts" )
    List<UserAlbum> findAllAlbumsFetchJoin();

}
