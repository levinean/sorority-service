package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.SorrorityRequest;
import sorrority.service.models.Sorrority;

public final class SorrorityMapper {
  private SorrorityMapper() {}

  public static Sorrority map(@NonNull final SorrorityRequest sorrority) {
    return Sorrority.builder().uuid(UUID.randomUUID()).name(sorrority.getName()).build();
  }
}
