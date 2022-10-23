package com.muller.community.dao;

import com.muller.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    // offset是起始行，limit是每一页的贴子数。
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId,@Param("offset") int offset,@Param("limit") int limit);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数，并且在<if>里使用,则必须加别名。
    int selectDiscussPostRows(@Param("userId") int userId);

}
