package sorrority.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import sorrority.api.mappers.CommentMapper;
import sorrority.api.mappers.CommentResponseMapper;
import sorrority.api.models.CommentRequest;
import sorrority.api.models.CommentsResponse;
import sorrority.service.CommentService;
import sorrority.service.models.Comment;

@Path("/api/v1")
public final class CommentResource {
  private final CommentService commentService;

  public CommentResource(@NonNull final CommentService commentService) {
    this.commentService = commentService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/comments/")
  @Consumes(APPLICATION_JSON)
  public void createComment(@Valid CommentRequest request) {
    final Comment newComment = CommentMapper.map(request);
    commentService.createComment(newComment);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/comments/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllComment() {
    final List<Comment> comments = commentService.getAll();
    final CommentsResponse response = CommentResponseMapper.map(comments);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/comments/{commentUuid}")
  public void deleteComment(@PathParam("commentUuid") UUID uuid) {
    commentService.deleteComment(uuid);
  }
}
