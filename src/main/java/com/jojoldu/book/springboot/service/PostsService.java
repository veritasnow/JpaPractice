package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
 *
 * @RequiredArgsConstructor
 * - final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 RequiredArgsConstructor가 대신 생성해준다.
 * - 이 방식은 생성자로 주입받는 방식이다.
 * - 생성자를 직접쓰지 않는 방식을 사용하면 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결해준다.
 *
 * */
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // db update
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        // 먼저 등록된 게시글이 있는지 파악
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        /*
         *
         *
         * */


        // 게시글 업데이트
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }





    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        /*
         * .map(PostsListResponseDto::new)는
         * .map(Posts -> new PostsListResponseDto(posts))와 같다.
         * */
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
   }
}
