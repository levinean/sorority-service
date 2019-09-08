package sorrority.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import sorrority.api.mappers.ChapterMapper;
import sorrority.api.mappers.ChapterResponseMapper;
import sorrority.api.models.ChapterRequest;
import sorrority.api.models.ChapterResponse;
import sorrority.api.models.ChaptersResponse;
import sorrority.service.ChapterService;
import sorrority.service.models.Chapter;

@Path("/api/v1")
public final class ChapterResource {
  private final ChapterService chapterService;

  public ChapterResource(@NonNull final ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/chapter/")
  @Consumes(APPLICATION_JSON)
  public void createChapter(@Valid ChapterRequest request) {
    final Chapter newChapter = ChapterMapper.map(request);
    chapterService.createChapter(newChapter);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/chapter/{chapterUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getChapter(@PathParam("chapterUuid") UUID uuid) throws NotFoundException {
    final Optional<Chapter> chapter = chapterService.getChapter(uuid);
    if (!chapter.isPresent()) {
      throw new NotFoundException();
    }
    final ChapterResponse response = ChapterResponseMapper.map(chapter.get());
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/chapter/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllChapter() {
    final List<Chapter> chapters = chapterService.getAll();
    final ChaptersResponse response = ChapterResponseMapper.map(chapters);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/chapter/{chapterUuid}")
  public void deleteChapter(@PathParam("chapterUuid") UUID uuid) {
    chapterService.deleteChapter(uuid);
  }
}
