package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.CommentRequest;
import sorrority.service.models.Comment;

public final class CommentMapper {
  private CommentMapper() {}

  public static Comment map(@NonNull final CommentRequest comment) {
    return Comment.builder()
        .uuid(UUID.randomUUID())
        .comment(comment.getComment())
        .eventUuid(comment.getEventUuid())
        .build();
  }
}
