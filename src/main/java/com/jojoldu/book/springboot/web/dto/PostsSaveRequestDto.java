package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* Entity는 데이터베이스와 맞닿은 핵심 클래스이므로 Entity클래스를 resonse와 request클래스로 사용하면 안된다.
* 수많은 서비스 클래스나 비즈니스 로직들이 Entity클래스를 기준으로 동작한다. 때문에 Entity클래스가 변경되면 여러 클래스에 영향을 끼친다.
* 화면 변경이 자주 발생하는 Request와 Response를 따로 만드는 것도 이 때문이다.
*
* DB레이어와 View 레이어의 역할 분리를 철저하게 하는게 좋다.
*
* */

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
