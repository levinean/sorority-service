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
import sorrority.api.mappers.MemberMapper;
import sorrority.api.mappers.MemberResponseMapper;
import sorrority.api.models.MemberRequest;
import sorrority.api.models.MemberResponse;
import sorrority.api.models.MembersResponse;
import sorrority.service.MemberService;
import sorrority.service.models.Member;

@Path("/api/v1")
public final class MemberResource {
  private final MemberService memberService;

  public MemberResource(@NonNull final MemberService memberService) {
    this.memberService = memberService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/members/")
  @Consumes(APPLICATION_JSON)
  public void createMember(@Valid MemberRequest request) {
    final Member newMember = MemberMapper.map(request);
    memberService.createMember(newMember);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/members/{memberUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getMember(@PathParam("memberUuid") UUID uuid) throws NotFoundException {
    final Optional<Member> member = memberService.getMember(uuid);
    if (!member.isPresent()) {
      throw new NotFoundException();
    }
    final MemberResponse response = MemberResponseMapper.map(member.get());
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/members/pledgeclass/{pledgeClassUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getMembersOfPledgeClass(@PathParam("pledgeClassUuid") UUID uuid)
      throws NotFoundException {
    final List<Member> member = memberService.getAllMembersInPledgeClass(uuid);
    final MembersResponse response = MemberResponseMapper.map(member);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/members/chapter/{chapterUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getMembersOfChapter(@PathParam("chapterUuid") UUID uuid)
      throws NotFoundException {
    final List<Member> member = memberService.getAllMembersInChapter(uuid);
    final MembersResponse response = MemberResponseMapper.map(member);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/members/{memberUuid}")
  public void deleteMember(@PathParam("memberUuid") UUID uuid) {
    memberService.deleteMember(uuid);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/members/sisterhood/{memberUuid}/{newPoints}")
  public void updateSisterhoodPoints(
      @PathParam("memberUuid") UUID uuid, @PathParam("newPoints") int newPoints) {
    memberService.updateSisterhoodPoints(uuid, newPoints);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/members/sisterhood/{chapterUuid}")
  public void resetSisterhoodPoints(@PathParam("chapterUuid") UUID uuid) {
    memberService.resetSisterhoodPoints(uuid);
  }
}
