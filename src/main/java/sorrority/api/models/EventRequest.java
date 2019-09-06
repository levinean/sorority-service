package sorrority.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@EqualsAndHashCode
@ToString
@Getter
public final class EventRequest {
    @NotBlank private String name;
    @NotBlank private String description;
    @NotBlank private String eventTime;
    @NotBlank private String eventDay;
}