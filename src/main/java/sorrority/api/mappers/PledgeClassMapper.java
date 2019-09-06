package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.PledgeClassRequest;
import sorrority.service.models.PledgeClass;

public final class PledgeClassMapper {
  private PledgeClassMapper() {}

  public static PledgeClass map(@NonNull final PledgeClassRequest pledgeClass) {
    return PledgeClass.builder()
        .uuid(UUID.randomUUID())
        .name(pledgeClass.getName())
        .chapterUuid(pledgeClass.getChapterUuid())
        .sorrorityUuid(pledgeClass.getSorrorityUuid())
        .build();
  }
}
