package com.example.fistproject.dto;

import com.example.fistproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 롬복을 통해 constructor를 한 줄로 대체 가능
@ToString // 롬복을 통해 ToString을 한 줄로 대체 가능
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
