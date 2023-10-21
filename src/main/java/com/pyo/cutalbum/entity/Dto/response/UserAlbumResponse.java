package com.pyo.cutalbum.entity.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserAlbumResponse {
    private String id;
    private String title;
    private String subTitle;
    private String inviteCode;
    private String coverIndex;
    private String createdDate;
}
