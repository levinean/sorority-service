package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.CommentRow;
import sorrority.service.models.Comment;

public final class CommentMapper {
  private CommentMapper() {}

  public static Comment map(@NonNull final CommentRow comment) {
    return Comment.builder()
        .uuid(comment.getUuid())
        .comment(comment.getComment())
        .eventUuid(comment.getEventUuid())
        .build();
  }

  public static List<Comment> map(@NonNull final List<CommentRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
