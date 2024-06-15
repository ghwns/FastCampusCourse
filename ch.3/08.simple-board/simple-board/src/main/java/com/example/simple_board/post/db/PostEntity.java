package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
            mappedBy = "post"
    )
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default
    private List<ReplyEntity> replyList = List.of();
}
