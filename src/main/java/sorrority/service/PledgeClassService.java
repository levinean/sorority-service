package sorrority.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.PledgeClassDao;
import sorrority.db.models.PledgeClassRow;
import sorrority.service.mappers.PledgeClassMapper;
import sorrority.service.mappers.PledgeClassRowMapper;
import sorrority.service.models.PledgeClass;

@Slf4j
public class PledgeClassService {
  private final PledgeClassDao pledgeClassDao;

  public PledgeClassService(@NonNull final PledgeClassDao pledgeClassDao) {
    this.pledgeClassDao = pledgeClassDao;
  }

  public void createPledgeClass(PledgeClass pledgeClass) {
    if (!pledgeClassDao.exists(pledgeClass.getName(), pledgeClass.getChapterUuid())) {
      pledgeClassDao.insert(PledgeClassRowMapper.map(pledgeClass));
      log.info("Pledge Class was inserted with name " + pledgeClass.getName());
    } else {
      log.info("Pledge Class already exists with name" + pledgeClass.getName());
    }
  }

  public void deletePledgeClass(UUID pledgeClassUuid) {
    if (pledgeClassDao.exists(pledgeClassUuid)) {
      PledgeClassRow pledgeClassRow = pledgeClassDao.findBy(pledgeClassUuid).get();
      pledgeClassDao.deletePledgeClass(pledgeClassUuid);
      log.info("Pledge class was deleted with name " + pledgeClassRow.getName());
    } else {
      log.info("Pledge Class does not exist");
    }
  }

  public Optional<PledgeClass> getPledgeClass(UUID pledgeClassUuid) {
    if (pledgeClassDao.exists(pledgeClassUuid)) {
      return Optional.of(PledgeClassMapper.map(pledgeClassDao.findBy(pledgeClassUuid).get()));
    } else {
      return Optional.empty();
    }
  }

  public List<PledgeClass> getAll() {
    return PledgeClassMapper.map(pledgeClassDao.getAll());
  }
}
