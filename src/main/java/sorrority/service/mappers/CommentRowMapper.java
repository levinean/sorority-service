package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.CommentRow;
import sorrority.service.models.Comment;

public final class CommentRowMapper {
  private CommentRowMapper() {}

  public static CommentRow map(@NonNull final Comment comment) {
    return CommentRow.builder()
        .uuid(comment.getUuid())
        .comment(comment.getComment())
        .eventUuid(comment.getEventUuid())
        .build();
  }
}
