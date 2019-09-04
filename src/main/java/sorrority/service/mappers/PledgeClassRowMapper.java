package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.PledgeClassRow;
import sorrority.service.models.PledgeClass;

public final class PledgeClassRowMapper {
  private PledgeClassRowMapper() {}

  public static PledgeClassRow map(@NonNull final PledgeClass pledgeClass) {
    return PledgeClassRow.builder()
        .uuid(pledgeClass.getUuid())
        .name(pledgeClass.getName())
        .chapterUuid(pledgeClass.getChapterUuid())
        .sorrorityUuid(pledgeClass.getSorrorityUuid())
        .build();
  }
}
