package sorrority.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.SorrorityDao;
import sorrority.db.models.SorrorityRow;
import sorrority.service.mappers.SorrorityMapper;
import sorrority.service.mappers.SorrorityRowMapper;
import sorrority.service.models.Sorrority;

@Slf4j
public class SorrorityService {
  private final SorrorityDao sorrorityDao;

  public SorrorityService(@NonNull final SorrorityDao sorrorityDao) {
    this.sorrorityDao = sorrorityDao;
  }

  public void createSorrority(Sorrority sorrority) {
    if (!sorrorityDao.exists(sorrority.getName())) {
      sorrorityDao.insert(SorrorityRowMapper.map(sorrority));
      log.info("Sorrority was inserted with name " + sorrority.getName());
    } else {
      log.info("Sorrority already exists with name" + sorrority.getName());
    }
  }

  public void deleteSorrority(UUID SorrorityUuid) {
    if (sorrorityDao.exists(SorrorityUuid)) {
      SorrorityRow SorrorityRow = sorrorityDao.findBy(SorrorityUuid).get();
      sorrorityDao.deleteSorrority(SorrorityUuid);
      log.info("Sorrority was deleted with name " + SorrorityRow.getName());
    } else {
      log.info("Sorrority does not exist");
    }
  }

  public Optional<Sorrority> getSorrority(UUID SorrorityUuid) {
    if (sorrorityDao.exists(SorrorityUuid)) {
      return Optional.of(SorrorityMapper.map(sorrorityDao.findBy(SorrorityUuid).get()));
    } else {
      return Optional.empty();
    }
  }

  public List<Sorrority> getAll() {
    return SorrorityMapper.map(sorrorityDao.getAll());
  }
}
