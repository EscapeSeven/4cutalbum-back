package com.pyo.cutalbum.controller;

import com.pyo.cutalbum.entity.Dto.ApiResponse;
import com.pyo.cutalbum.entity.Dto.request.UserPostRequest;
import com.pyo.cutalbum.entity.UserAlbum;
import com.pyo.cutalbum.entity.UserPost;
import com.pyo.cutalbum.infra.GCSService;
import com.pyo.cutalbum.service.UserAlbumService;
import com.pyo.cutalbum.service.UserPostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserPostContoller {
    private final ApiResponse apiResponse;
    private final UserAlbumService albumService;
    private final GCSService gcsService;
    private final UserPostService userPostService;

    @Operation(summary = "앨범에 사진 저장하기", description = "앨범에 사진 저장하는 API 입니다.")
    @PostMapping("/user/album/{id}/write")
    public ResponseEntity<?> writePost(UserPostRequest userPostRequest, @PathVariable Long id) throws IOException {
        Optional<UserAlbum> userAlbumById = albumService.getUserAlbumById(id);
        if (!userAlbumById.isPresent())
            return apiResponse.fail("존재하지 않는 앨범입니다.");
        UserAlbum userAlbum = userAlbumById.get();
        String imageUrl = gcsService.uploadPhoto(userPostRequest.getImageFile());
        UserPost userPost = UserPost.builder()
                .imageUrl(imageUrl)
                .build();
        userAlbum.addPost(userPost);
        userPostService.save(userPost);
        return apiResponse.success("사진추가 성공");
    }
}
