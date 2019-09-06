package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.SorrorityResponse;
import sorrority.service.models.Sorrority;

public final class SorrorityResponseMapper {
  private SorrorityResponseMapper() {}

  public static SorrorityResponse map(@NonNull final Sorrority sorrority) {
    return SorrorityResponse.builder().uuid(sorrority.getUuid()).name(sorrority.getName()).build();
  }

  public static List<SorrorityResponse> map(@NonNull final List<Sorrority> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
