package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.PledgeClassResponse;
import sorrority.api.models.PledgeClassesResponse;
import sorrority.service.models.PledgeClass;

public final class PledgeClassResponseMapper {
  private PledgeClassResponseMapper() {}

  public static PledgeClassResponse map(@NonNull final PledgeClass pledgeClass) {
    return PledgeClassResponse.builder()
        .uuid(pledgeClass.getUuid())
        .name(pledgeClass.getName())
        .chapterUuid(pledgeClass.getChapterUuid())
        .sorrorityUuid(pledgeClass.getSorrorityUuid())
        .build();
  }

  public static PledgeClassesResponse map(@NonNull final List<PledgeClass> rows) {
    return new PledgeClassesResponse(unmodifiableList(rows.stream().map(row -> map(row)).collect(toList())));
  }
}
