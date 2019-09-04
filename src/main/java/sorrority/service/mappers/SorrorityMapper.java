package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.SorrorityRow;
import sorrority.service.models.Sorrority;

public final class SorrorityMapper {
  private SorrorityMapper() {}

  public static Sorrority map(@NonNull final SorrorityRow sorrority) {
    return Sorrority.builder().uuid(sorrority.getUuid()).name(sorrority.getName()).build();
  }

  public static List<Sorrority> map(@NonNull final List<SorrorityRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
