package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.ChapterRow;
import sorrority.service.models.Chapter;

public final class ChapterRowMapper {
  private ChapterRowMapper() {}

  public static ChapterRow map(@NonNull final Chapter chapter) {
    return ChapterRow.builder()
        .uuid(chapter.getUuid())
        .name(chapter.getName())
        .sorrorityUuid(chapter.getSorrorityUuid())
        .build();
  }
}
