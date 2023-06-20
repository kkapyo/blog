package com.sparta.springproject.dto;

import com.sparta.springproject.entity.Post;

public class PostDto {
    private String title;
    private String author;
    private String content;

    public static PostDto fromEntity(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setAuthor(post.getAuthor());
        postDto.setContent(post.getContent());
        return postDto;
    }

    public Post toEntity() {
        Post post = new Post(null, title, author, content);
        return post;
    }

    // Constructors, getters, and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

