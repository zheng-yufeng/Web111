package com.example.demo.UserInfoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserInfo;

//                                                        <要操作的数据库名称，主key的数据类型>
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	UserInfo findByUserName(String userName);
}
