package com.cong.mapper;

import com.cong.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {

    List<Comment> queryCommentList();

    List<Comment> queryCommentListByUserId(Integer userId);

    List<Comment> queryCommentListByActivityId(Integer activityId);

    int addComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(Comment comment);
}
