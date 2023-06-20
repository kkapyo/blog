package com.sparta.springproject.controller;

import com.sparta.springproject.entity.Post;
import com.sparta.springproject.dto.PostDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private List<Post> posts = new ArrayList<>();
    private Long postId = 0L;
    @GetMapping
    public List<PostDto> getAllPosts() {
        // 전체 게시글 목록 조회 API 로직
        List<PostDto> postDtos = posts.stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
        return postDtos;

    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        // 게시글 작성 API 로직
        Post post = postDto.toEntity();
        post.setId(++postId);
        posts.add(post);
        return PostDto.fromEntity(post);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        // 선택한 게시글 조회 API 로직
        Post post = posts.stream()
                .filter(p -> p.getId().equals(postId))
                .findFirst()
                .orElse(null);
        return PostDto.fromEntity(post);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        // 선택한 게시글 수정 API 로직
        Post post = posts.stream()
                .filter(p -> p.getId().equals(postId))
                .findFirst()
                .orElse(null);
        if (post != null) {
            post.setTitle(postDto.getTitle());
            post.setAuthor(postDto.getAuthor());
            post.setContent(postDto.getContent());
        }
        return PostDto.fromEntity(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        // 선택한 게시글 삭제 API 로직
        posts.removeIf(p -> p.getId().equals(postId));
    }
}

