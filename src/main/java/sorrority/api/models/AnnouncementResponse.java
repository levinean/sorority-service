package sorrority.api.models;

import java.util.UUID;
import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public final class AnnouncementResponse {
    @NotBlank private UUID uuid;
    @NotBlank private String announcement;
    @NotBlank private Instant createdAt;
}