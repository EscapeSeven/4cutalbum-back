package com.pyo.cutalbum.service;

import com.pyo.cutalbum.entity.UserAlbum;
import com.pyo.cutalbum.repository.UserAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAlbumService {
    private final UserAlbumRepository userAlbumRepository;
    public void save(UserAlbum userAlbum)
    {
        userAlbumRepository.save(userAlbum);
    }
    public boolean findByInviteCode(String inviteCode)
    {
        return userAlbumRepository.existsUserAlbumByInviteCode(inviteCode);
    }
    public List<UserAlbum> getUserAlbums()
    {
        return userAlbumRepository.findAllAlbumsFetchJoin();
    }
    public Optional<UserAlbum> getUserAlbumById(Long id)
    {
        return userAlbumRepository.findById(id);
    }

}
