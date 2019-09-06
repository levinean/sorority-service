package sorrority.api.models;

import java.time.Instant;
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
public final class CommentResponse {
  @NotBlank private UUID uuid;
  @NotBlank private String comment;
  @NotBlank private Instant createdAt;
  @NotBlank private UUID eventUuid;
}
