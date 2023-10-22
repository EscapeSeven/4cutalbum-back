package com.pyo.cutalbum.entity.Dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAlbumRequest {
    private String title;
    private String subTitle;
    private Long coverIndex;


}
