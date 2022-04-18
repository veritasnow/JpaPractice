package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
* @Entity : 테이블과 링크될 클래스임을 나타낸다.
* - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭함
* - ex) SalesManager.java -> sales_manager table
*
*
* @Id
* - 해당 테이블의 PK 필드를 나타냅니다.
*
*
* @GeneratedValue
* - PK의 생성 규칙을 나타냅니다.
* - 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
*
*
* @Column
* - 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
* - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함
* - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT를 변경하고 싶거나(ex : content)등의 경우에 사용됩니다.
*/


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    /*
    * update기능에 데이터베이스에 쿼리를 날리는 부분이 없다. 그럼에도 DB에 업데이트가 가능한 이유는
    * JPA의 영속성 컨텍스트 때문이다.
    * - 영속성 컨테스트란 엔티티를 영구 저장하는 환경이다.
    * - JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    * - JPA의 엔터티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    * - 이 상태에서 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경부늘 ㅂ나영한다.
    * - ENTITY 객체의 값만 변경하면 별도로 Update쿼리를 날릴 필요가 없다.
    * */


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
