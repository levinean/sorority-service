package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.CommentResponse;
import sorrority.service.models.Comment;

public final class CommentResponseMapper {
  private CommentResponseMapper() {}

  public static CommentResponse map(@NonNull final Comment comment) {
    return CommentResponse.builder()
        .uuid(comment.getUuid())
        .comment(comment.getComment())
        .eventUuid(comment.getEventUuid())
        .build();
  }

  public static List<CommentResponse> map(@NonNull final List<Comment> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
