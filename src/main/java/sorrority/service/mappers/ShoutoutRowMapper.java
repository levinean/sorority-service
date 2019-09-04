package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.ShoutoutRow;
import sorrority.service.models.Shoutout;

public final class ShoutoutRowMapper {
  private ShoutoutRowMapper() {}

  public static ShoutoutRow map(@NonNull final Shoutout shoutout) {
    return ShoutoutRow.builder()
        .uuid(shoutout.getUuid())
        .shoutout(shoutout.getShoutout())
        .createdAt(shoutout.getCreatedAt())
        .build();
  }
}
