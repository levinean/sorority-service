package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.SorrorityRow;
import sorrority.service.models.Sorrority;

public final class SorrorityRowMapper {
  private SorrorityRowMapper() {}

  public static SorrorityRow map(@NonNull final Sorrority sorrority) {
    return SorrorityRow.builder().uuid(sorrority.getUuid()).name(sorrority.getName()).build();
  }
}
