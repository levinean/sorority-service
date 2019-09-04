package sorrority.service.mappers;

import lombok.NonNull;
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
