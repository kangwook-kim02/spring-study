package com.example.fistproject.entity;

import com.example.fistproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // primary key

    @ManyToOne // comment 엔터티와 article 엔터리를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 설정, Article 엔터티의 primary key와 매핑
    private Article article; // 해당 댓글의 부모 게시글 (참조할 엔터티)

    @Column
    private String nickname; // 댓글을 단 사람

    @Column
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        // 엔터티 생성 및 반환
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if(dto.getNickname()!=null) // nickname이 수정되었을 경우
            this.nickname = dto.getNickname();
        if(dto.getBody()!=null) // body가 수정되었을 경우
            this.body = dto.getBody();
    }
}
