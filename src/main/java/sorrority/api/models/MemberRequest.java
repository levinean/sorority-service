package sorrority.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@EqualsAndHashCode
@ToString
@Getter
public final class MemberRequest {
    @NotBlank String name;
    @NotBlank private UUID chapterUuid;
    @NotBlank private UUID pledgeClassUuid;
    @NotBlank private String graduatingYear;
    @NotBlank private Boolean executive;
    @NotBlank private String phoneNumber;
    @NotBlank private String email;
    @NotBlank private String birthday;
}