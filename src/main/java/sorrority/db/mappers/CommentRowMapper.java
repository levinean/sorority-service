package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.CommentRow;

public final class CommentRowMapper implements RowMapper<CommentRow> {
  @Override
  public CommentRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return CommentRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .comment(stringOrNull(results, Columns.COMMENT))
        .eventUuid(uuidOrNull(results, Columns.EVENT_UUID))
        .build();
  }
}
