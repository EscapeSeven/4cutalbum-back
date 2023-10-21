package com.pyo.cutalbum.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pyo.cutalbum.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<UserAlbum> userPosts=new ArrayList<>();


    public void addAlbum(UserAlbum userAlbum)
    {
        userPosts.add(userAlbum);
        userAlbum.setUser(this);
    }


    @Builder
    public User(Long id, String nickname,String password,Role role) {
        this.id = id;
        this.nickname = nickname;
        this.password=password;
        this.role=role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}