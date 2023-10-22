package com.pyo.cutalbum.controller;


import com.pyo.cutalbum.entity.Dto.ApiResponse;
import com.pyo.cutalbum.entity.Dto.request.UserAlbumRequest;
import com.pyo.cutalbum.entity.Dto.response.UserAlbumResponse;
import com.pyo.cutalbum.entity.User;
import com.pyo.cutalbum.entity.UserAlbum;
import com.pyo.cutalbum.service.UserAlbumService;
import com.pyo.cutalbum.service.UserPostService;
import com.pyo.cutalbum.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final UserPostService userPostService;
    private final UserAlbumService userAlbumService;
    private final ApiResponse apiResponse;
    private final UserService userService;

    @Operation(summary = "앨범 저장하기", description = "앨범 저장 API 입니다.")
    @PostMapping("/user/album/write")
    public ResponseEntity<?> writePost(UserAlbumRequest userAlbumRequest) throws IOException {
        String inviteCode = getInviteCode();
        if (!StringUtils.hasText(userAlbumRequest.getSubTitle()) || !StringUtils.hasText(userAlbumRequest.getTitle())||
                !StringUtils.hasText(Long.toString(userAlbumRequest.getCoverIndex())))
            return apiResponse.fail("필수값을 입력해주세요.");
        UserAlbum userAlbum = UserAlbum.builder()
                .title(userAlbumRequest.getTitle())
                .subTitle(userAlbumRequest.getSubTitle())
                .coverIndex(userAlbumRequest.getCoverIndex())
                .inviteCode(inviteCode)
                .build();
        User blue = userService.findUser("blue");
        blue.addAlbum(userAlbum);
        userAlbumService.save(userAlbum);
        return apiResponse.success("앨범추가 성공");
    }
    @Operation(summary = "앨범 정보만 불러오기", description = "앨범 정보만 불러오는 API 입니다. 앨범안에있는 사진은 X")
    @GetMapping("/user/albums")
    public ResponseEntity<?> getAlbums()
    {
        List<UserAlbum> userAlbums = userAlbumService.getUserAlbums();
        List<UserAlbumResponse> userAlbumResponses = userAlbums.stream().map(userAlbum -> UserAlbumResponse.builder()
                .id(userAlbum.getId())
                .title(userAlbum.getTitle())
                .subTitle(userAlbum.getSubTitle())
                .coverIndex(userAlbum.getCoverIndex())
                .inviteCode(userAlbum.getInviteCode())
                .createdDate(userAlbum.getCreatedDate())
                .build()).collect(Collectors.toList());
        return apiResponse.success("유저 앨범 불러오기 성공",userAlbumResponses);
    }

    @Operation(summary = "앨범에 있는 사진 모두 불러오기", description = "앨범에 있는 사진 정보 불러오는 API 입니다.")
    @GetMapping("/user/albums/{album_id}")
    public ResponseEntity<?> getAlbumPhotos(@PathVariable Long album_id)
    {
        Optional<UserAlbum> userAlbumById = userAlbumService.getUserAlbumById(album_id);
        if (!userAlbumById.isPresent())
            return apiResponse.fail("잘못된 요청입니다.");
        return apiResponse.success("유저 앨범 불러오기 성공",userAlbumById.get().getUserPosts());
    }
    public String getInviteCode(){
        while (true) {
            int num = (int) ((Math.random() * 10000));
            String s_num = Integer.toString(num);
            String temp = "";
            while (temp.length() + s_num.length() < 4) {
                temp += "0";
            }
            s_num = temp + s_num;
            if (userAlbumService.findByInviteCode(s_num)==false)
                return s_num;
        }
    }

}
