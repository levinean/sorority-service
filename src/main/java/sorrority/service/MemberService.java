package sorrority.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.ChapterDao;
import sorrority.db.MembersDao;
import sorrority.db.models.ChapterRow;
import sorrority.db.models.MemberRow;
import sorrority.service.mappers.MemberMapper;
import sorrority.service.mappers.MemberRowMapper;
import sorrority.service.models.Member;

@Slf4j
public class MemberService {
  private final MembersDao membersDao;
  private final ChapterDao chapterDao;

  public MemberService(@NonNull final MembersDao membersDao, @NonNull final ChapterDao chapterDao) {
    this.membersDao = membersDao;
    this.chapterDao = chapterDao;
  }

  public void createMember(Member member) {
    if (!membersDao.exists(member.getName(), member.getEmail(), member.getBirthday())) {
      membersDao.insert(MemberRowMapper.map(member));
      log.info("Member inserted with name " + member.getName());
    } else {
      log.info("Member already exists");
    }
  }

  public Optional<Member> getMember(UUID uuid) {
    Optional<MemberRow> memberRow = membersDao.findBy(uuid);
    if (memberRow.isPresent()) {
      return Optional.of(MemberMapper.map(memberRow.get()));
    } else {
      log.info("Member does not exist");
      return Optional.empty();
    }
  }

  public List<Member> getAllMembersInPledgeClass(UUID pledgeClassUuid) {
    List<MemberRow> memberRows = membersDao.findAllMembersInPledgeClass(pledgeClassUuid);
    List<Member> members = MemberMapper.map(memberRows);
    return members;
  }

  public List<Member> getAllMembersInChapter(UUID chapterUuid) {
    List<MemberRow> memberRows = membersDao.findAllMembersInChapter(chapterUuid);
    List<Member> members = MemberMapper.map(memberRows);
    return members;
  }

  public void deleteMember(UUID memberUuid) {
    if (membersDao.exists(memberUuid)) {
      MemberRow memberRow = membersDao.findBy(memberUuid).get();
      membersDao.deleteMember(memberUuid);
      log.info("Member deleted with name " + memberRow.getName());
    } else {
      log.info("Member doesn't exist");
    }
  }

  public void updateSisterhoodPoints(UUID memberUuid, int newPoints) {
    if (membersDao.exists(memberUuid)) {
      MemberRow memberRow = membersDao.findBy(memberUuid).get();
      membersDao.updateSisterhoodPoints(memberUuid, newPoints + memberRow.getSisterhoodPoints());
      log.info("Member sisterhood points added with name " + memberRow.getName());
    } else {
      log.info("Member doesn't exist");
    }
  }

  public void resetSisterhoodPoints(UUID chapterUuid) {
    if (chapterDao.exists(chapterUuid)) {
      ChapterRow chapterRow = chapterDao.findBy(chapterUuid).get();
      membersDao.resetSisterhoodPoints(chapterUuid);
      log.info("Chapter sisterhood points were reset with name " + chapterRow.getName());
    } else {
      log.info("Chapter does not exist");
    }
  }
}
