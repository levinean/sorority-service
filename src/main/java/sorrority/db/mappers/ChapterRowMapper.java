package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.ChapterRow;

public final class ChapterRowMapper implements RowMapper<ChapterRow> {
  @Override
  public ChapterRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return ChapterRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .name(stringOrNull(results, Columns.NAME))
        .sorrorityUuid(uuidOrNull(results, Columns.SORRORITY_UUID))
        .build();
  }
}
