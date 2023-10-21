package com.pyo.cutalbum.controller;


import com.pyo.cutalbum.entity.Dto.ApiResponse;
import com.pyo.cutalbum.entity.Dto.request.UserAlbumRequest;
import com.pyo.cutalbum.entity.User;
import com.pyo.cutalbum.entity.UserAlbum;
import com.pyo.cutalbum.infra.GCSService;
import com.pyo.cutalbum.service.UserAlbumService;
import com.pyo.cutalbum.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final GCSService gcsService;
    private final UserAlbumService userAlbumService;
    private final ApiResponse apiResponse;
    private final UserService userService;

    @Operation(summary = "앨범 저장하기", description = "앨범 저장 API 입니다.")
    @PostMapping("/user/album/write")
    public ResponseEntity<?> writePost(UserAlbumRequest userAlbumRequest) throws IOException {
        String imageUrl = gcsService.uploadPhoto(userAlbumRequest.getImageFile());
        String inviteCode = getInviteCode();
        UserAlbum userAlbum = UserAlbum.builder()
                .title(userAlbumRequest.getTitle())
                .subTitle(userAlbumRequest.getSubTitle())
                .imageUrl(imageUrl)
                .inviteCode(inviteCode)
                .build();
        User blue = userService.findUser("blue");
        blue.addAlbum(userAlbum);
        userAlbumService.save(userAlbum);
        return apiResponse.success("앨범추가 성공");

    }
    @Operation(summary = "앨범 불러오기", description = "앨범 불러오기 API 입니다.")
    @GetMapping("/user/albums")
    public ResponseEntity<?> getAlbums()
    {
        List<UserAlbum> userAlbums = userAlbumService.getUserAlbums();
        return apiResponse.success("유저 앨범 불러오기 성공",userAlbums);
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
