package sorrority.db.mappers;

import static sorrority.db.Columns.intOrNull;
import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;
import static sorrority.db.Columns.booleanOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import sorrority.db.Columns;
import sorrority.db.models.MemberRow;

public final class MemberRowMapper implements RowMapper<MemberRow> {
  @Override
  public MemberRow map(@NonNull ResultSet results, @NonNull StatementContext context)
      throws SQLException {
    return MemberRow.builder()
        .uuid(uuidOrNull(results, Columns.ROW_UUID))
        .name(stringOrNull(results, Columns.NAME))
        .chapterUuid(uuidOrNull(results, Columns.CHAPTER_UUID))
        .pledgeClassUuid(uuidOrNull(results, Columns.PLEDGE_CLASS_UUID))
        .graduatingYear(stringOrNull(results, Columns.GRADUATING_YEAR))
        .big(stringOrNull(results, Columns.BIG))
        .sisterhoodPoints(intOrNull(results, Columns.SISTERHOOD_POINTS))
        .executive(booleanOrNull(results, Columns.EXECUTIVE))
        .phoneNumber(stringOrNull(results, Columns.PHONE_NUMBER))
        .email(stringOrNull(results, Columns.EMAIL))
        .birthday(stringOrNull(results, Columns.BIRTHDAY))
        .duesPaid(intOrNull(results, Columns.DUES_PAID))
        .build();
  }
}
