package sorrority.db.mappers;

import static sorrority.db.Columns.timestampOrNull;
import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.AnnouncementRow;

public final class AnnouncementRowMapper implements RowMapper<AnnouncementRow> {
  @Override
  public AnnouncementRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return AnnouncementRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .announcement(stringOrNull(results, Columns.ANNOUNCEMENT))
        .createdAt(timestampOrNull(results, Columns.CREATED_AT))
        .build();
  }
}
