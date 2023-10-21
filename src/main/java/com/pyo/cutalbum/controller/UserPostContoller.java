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
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/user/album/{album_id}/write")
    public ResponseEntity<?> writePost(UserPostRequest userPostRequest, @PathVariable Long album_id) throws IOException {
        Optional<UserAlbum> userAlbumById = albumService.getUserAlbumById(album_id);
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

    @Operation(summary = "앨범에 저장된 사진 수정하기", description = "앨범에 저장된 사진 수정하는 API 입니다.")
    @PostMapping("/user/album/{post_id}/edit")
    public ResponseEntity<?> writePost(MultipartFile imageFile,@PathVariable Long post_id) throws IOException {
        Optional<UserPost> userPost = userPostService.findById(post_id);
        if (!userPost.isPresent())
            return apiResponse.fail("잘못된 요청입니다.");
        UserPost userPost1 = userPost.get();
        String imageUrl = gcsService.uploadPhoto(imageFile);
        userPost1.update(imageUrl);
        userPostService.save(userPost1);
        return apiResponse.success("사진수정 성공");
    }

    @Operation(summary = "좋아요 버튼", description = "좋아요 기능 API 입니다. 증가된 좋아요 개수가 return 됩니다.")
    @PostMapping("/user/album/{post_id}/like")
    public ResponseEntity<?> UserPostLike(@PathVariable Long post_id) {
        Optional<UserPost> userPost = userPostService.findById(post_id);
        if (!userPost.isPresent())
            return apiResponse.fail("잘못된 요청입니다.");
        userPostService.like(post_id);
        return apiResponse.success(userPost.get().getLikes()+1);

    }
}
