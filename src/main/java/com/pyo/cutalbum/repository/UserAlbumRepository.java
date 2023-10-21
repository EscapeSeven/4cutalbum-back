package com.pyo.cutalbum.repository;

import com.pyo.cutalbum.entity.UserAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAlbumRepository extends JpaRepository<UserAlbum,Long> {
    boolean existsUserAlbumByInviteCode(String inviteCode);

}
