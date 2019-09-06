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
public final class EventResponse {
    @NotBlank private UUID uuid;
    @NotBlank private String name;
    @NotBlank private String description;
    @NotBlank private String eventTime;
    @NotBlank private String eventDay;
    @NotBlank private int score;
    @NotBlank private int numberAttended;
}