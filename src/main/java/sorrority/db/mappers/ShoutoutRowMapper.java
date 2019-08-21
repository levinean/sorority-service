package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.timestampOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.ShoutoutRow;

public final class ShoutoutRowMapper implements RowMapper<ShoutoutRow> {
  @Override
  public ShoutoutRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return ShoutoutRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .shoutout(stringOrNull(results, Columns.SHOUTOUT))
        .createdAt(timestampOrNull(results, Columns.CREATED_AT))
        .build();
  }
}
