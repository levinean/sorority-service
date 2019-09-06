package sorrority.api.models;

import java.util.List;
import lombok.NonNull;
import lombok.Value;

@Value
public class ChaptersResponse {
  @NonNull List<ChapterResponse> chapters;
}