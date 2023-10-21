package com.pyo.cutalbum.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAlbum extends BaseTimeEntity{
    @Id
    private Long id;
    private String title;
    private String subtitle;
    private String inviteCode;
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}