package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.ShoutoutRow;
import sorrority.service.models.Shoutout;

public final class ShoutoutMapper {
  private ShoutoutMapper() {}

  public static Shoutout map(@NonNull final ShoutoutRow shoutout) {
    return Shoutout.builder()
        .uuid(shoutout.getUuid())
        .shoutout(shoutout.getShoutout())
        .createdAt(shoutout.getCreatedAt())
        .build();
  }

  public static List<Shoutout> map(@NonNull final List<ShoutoutRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
