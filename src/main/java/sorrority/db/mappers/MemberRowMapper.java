package sorrority.db.mappers;

import static sorrority.db.Columns.stringOrNull;
import static sorrority.db.Columns.uuidOrNull;
import static sorrority.db.Columns.intOrNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NonNull;
import sorrority.db.Columns;
import sorrority.db.MemberRow;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;


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
        .sisterhoodPoints(stringOrNull(results, Columns.SISTERHOOD_POINTS))
        .executive(stringOrNull(results, Columns.EXECUTIVE))
        .phoneNumber(stringOrNull(results, Columns.PHONE_NUMBER))
        .email(stringOrNull(results, Columns.EMAIL))
        .birthday(stringOrNull(results, Columns.BIRTHDAY))
        .duesPaid(intOrNull(results, Columns.DUES_PAID))
        .build();
  }
}