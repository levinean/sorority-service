package sorrority.api.mappers;

import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.ShoutoutRequest;
import sorrority.service.models.Shoutout;

public final class ShoutoutMapper {
  private ShoutoutMapper() {}

  public static Shoutout map(@NonNull final ShoutoutRequest shoutout) {
    return Shoutout.builder()
        .uuid(UUID.randomUUID())
        .shoutout(shoutout.getShoutout())
        .createdAt(Instant.now())
        .build();
  }
}
