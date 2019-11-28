package com.jacken.messagesms.dao;

import com.jacken.messagesms.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("select * from user")
    List<User> findAllUser();
}
