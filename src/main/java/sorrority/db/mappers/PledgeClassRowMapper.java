package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.PledgeClassRow;

public final class PledgeClassRowMapper implements RowMapper<PledgeClassRow> {
  @Override
  public PledgeClassRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return PledgeClassRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .name(stringOrNull(results, Columns.NAME))
        .sorrorityUuid(uuidOrNull(results, Columns.SORRORITY_UUID))
        .chapterUuid(uuidOrNull(results, Columns.CHAPTER_UUID))
        .build();
  }
}
