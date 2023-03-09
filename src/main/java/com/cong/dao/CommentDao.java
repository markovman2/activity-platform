package com.cong.dao;

import com.cong.mapper.CommentMapper;
import com.cong.pojo.Comment;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;

@Repository
public class CommentDao {

    @Resource
    private CommentMapper commentMapper;

    public void save(Comment comment) {
        commentMapper.addComment(comment);
    }

    public Collection<Comment> getAll() {
        return commentMapper.queryCommentList();
    }

    public Collection<Comment> getCommentByUserId(Integer userId) {
        return commentMapper.queryCommentListByUserId(userId);
    }

    public Collection<Comment> getCommentByActivityId(Integer activityId) {
        return commentMapper.queryCommentListByActivityId(activityId);
    }


    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        commentMapper.deleteComment(comment);
    }
}
