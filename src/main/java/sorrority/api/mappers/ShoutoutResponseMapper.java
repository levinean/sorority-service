package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.ShoutoutResponse;
import sorrority.api.models.ShoutoutsResponse;
import sorrority.service.models.Shoutout;

public final class ShoutoutResponseMapper {
  private ShoutoutResponseMapper() {}

  public static ShoutoutResponse map(@NonNull final Shoutout shoutout) {
    return ShoutoutResponse.builder()
        .uuid(shoutout.getUuid())
        .shoutout(shoutout.getShoutout())
        .createdAt(shoutout.getCreatedAt())
        .build();
  }

  public static ShoutoutsResponse map(@NonNull final List<Shoutout> rows) {
    return new ShoutoutsResponse(unmodifiableList(rows.stream().map(row -> map(row)).collect(toList())));
  }
}
