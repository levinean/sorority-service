package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.ChapterRequest;
import sorrority.service.models.Chapter;

public final class ChapterMapper {
  private ChapterMapper() {}

  public static Chapter map(@NonNull final ChapterRequest chapter) {
    return Chapter.builder()
        .uuid(UUID.randomUUID())
        .name(chapter.getName())
        .sorrorityUuid(chapter.getSorrorityUuid())
        .build();
  }
}
