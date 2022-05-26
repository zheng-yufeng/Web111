package com.example.demo.UserInfoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BlogInfo;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {
	BlogInfo findByBlogId(Long blogId);
}
