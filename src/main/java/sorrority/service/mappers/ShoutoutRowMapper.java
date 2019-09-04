package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.ShoutoutRow;
import sorrority.service.models.Shoutout;

public final class ShoutRowoutMapper {
  private ShoutRowoutMapper() {}

  public static ShoutRowout map(@NonNull final Shoutout shoutout) {
    return Shoutout.builder()
        .uuid(shoutout.getUuid())
        .shoutout(shoutout.getShoutout())
        .createdAt(shoutout.getCreatedAt())
        .build();
  }
}
