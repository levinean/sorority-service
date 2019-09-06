package sorrority.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@EqualsAndHashCode
@ToString
@Getter
public final class PledgeClassRequest {
  @NotBlank String name;
  @NotBlank private UUID chapterUuid;
  @NotBlank private UUID sorrorityUuid;
}
