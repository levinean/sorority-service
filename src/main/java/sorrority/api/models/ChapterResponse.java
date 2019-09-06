package sorrority.api.models;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public final class ChapterResponse {
  @NotBlank private UUID uuid;
  @NotBlank private String name;
  @NotBlank private UUID sorrorityUuid;
}
