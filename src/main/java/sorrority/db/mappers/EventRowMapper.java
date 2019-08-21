package sorrority.db.mappers;

import static sorrority.db.Columns.intOrNull;
import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.EventRow;

public final class EventRowMapper implements RowMapper<EventRow> {
  @Override
  public EventRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return EventRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .name(stringOrNull(results, Columns.NAME))
        .description(stringOrNull(results, Columns.DESCRIPTION))
        .eventTime(stringOrNull(results, Columns.EVENT_TIME))
        .eventDay(stringOrNull(results, Columns.EVENT_DAY))
        .score(intOrNull(results, Columns.SCORE))
        .build();
  }
}
