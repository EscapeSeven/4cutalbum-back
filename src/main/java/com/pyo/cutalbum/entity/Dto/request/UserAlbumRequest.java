package com.pyo.cutalbum.entity.Dto.request;


import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class UserAlbumRequest {
    private String title;
    private String subTitle;
    private MultipartFile imageFile;


}
