package com.pyo.cutalbum.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPost extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    @ColumnDefault("0")
    private int likes;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private UserAlbum userAlbum;

    public void update(String imageUrl) {
        this.imageUrl=imageUrl;
    }

}
