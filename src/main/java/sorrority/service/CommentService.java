package sorrority.service;

import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.CommentDao;
import sorrority.service.mappers.CommentMapper;
import sorrority.service.mappers.CommentRowMapper;
import sorrority.service.models.Comment;

@Slf4j
public class CommentService {
  private final CommentDao commentDao;

  public CommentService(@NonNull final CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  public void createComment(Comment comment) {
    commentDao.insert(CommentRowMapper.map(comment));
    log.info("Comment was inserted");
  }

  public void deleteComment(UUID commentUuid) {
    if (commentDao.exists(commentUuid)) {
      commentDao.deleteComment(commentUuid);
      log.info("Comment was deleted");
    } else {
      log.info("Comment does not exist");
    }
  }

  public List<Comment> getAll() {
    return CommentMapper.map(commentDao.getAll());
  }
}
