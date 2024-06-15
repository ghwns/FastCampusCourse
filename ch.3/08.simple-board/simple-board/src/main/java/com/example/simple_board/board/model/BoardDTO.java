package com.example.simple_board.board.model;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDTO {

        private Long id;

        private String boardName;

        private String status;

        private List<PostDTO> postList = List.of();
}
