package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.ChapterRow;
import sorrority.service.models.Chapter;

public final class ChapterMapper {
  private ChapterMapper() {}

  public static Chapter map(@NonNull final ChapterRow chapter) {
    return Chapter.builder()
        .uuid(chapter.getUuid())
        .name(chapter.getName())
        .sorrorityUuid(chapter.getSorrorityUuid())
        .build();
  }

  public static List<Chapter> map(@NonNull final List<ChapterRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
