package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.SorrorityRow;

public final class SorrorityRowMapper implements RowMapper<SorrorityRow> {
  @Override
  public SorrorityRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return SorrorityRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .name(stringOrNull(results, Columns.NAME))
        .build();
  }
}
