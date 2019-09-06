package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.ChapterResponse;
import sorrority.service.models.Chapter;

public final class ChapterResponseMapper {
  private ChapterResponseMapper() {}

  public static ChapterResponse map(@NonNull final Chapter chapter) {
    return ChapterResponse.builder()
        .uuid(chapter.getUuid())
        .name(chapter.getName())
        .sorrorityUuid(chapter.getSorrorityUuid())
        .build();
  }

  public static List<ChapterResponse> map(@NonNull final List<Chapter> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
