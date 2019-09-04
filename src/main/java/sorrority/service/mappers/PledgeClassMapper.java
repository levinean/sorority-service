package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.PledgeClassRow;
import sorrority.service.models.PledgeClass;

public final class PledgeClassMapper {
  private PledgeClassMapper() {}

  public static PledgeClass map(@NonNull final PledgeClassRow pledgeClass) {
    return PledgeClass.builder()
        .uuid(pledgeClass.getUuid())
        .name(pledgeClass.getName())
        .chapterUuid(pledgeClass.getChapterUuid())
        .sorrorityUuid(pledgeClass.getSorrorityUuid())
        .build();
  }

  public static List<PledgeClass> map(@NonNull final List<PledgeClassRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
