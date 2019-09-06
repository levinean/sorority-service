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
public final class MemberResponse {
  @NotBlank private UUID uuid;
  @NotBlank private String name;
  @NotBlank private UUID chapterUuid;
  @NotBlank private UUID pledgeClassUuid;
  @NotBlank private String graduatingYear;
  @NotBlank private String big;
  @NotBlank private int sisterhoodPoints;
  @NotBlank private Boolean executive;
  @NotBlank private String phoneNumber;
  @NotBlank private String email;
  @NotBlank private String birthday;
  @NotBlank private int duesPaid;
}
