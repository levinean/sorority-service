package sorrority.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.ShoutoutDao;
import sorrority.service.mappers.ShoutoutMapper;
import sorrority.service.mappers.ShoutoutRowMapper;
import sorrority.service.models.Shoutout;

@Slf4j
public class ShoutoutService {
  private final ShoutoutDao shoutoutDao;

  public ShoutoutService(@NonNull final ShoutoutDao shoutoutDao) {
    this.shoutoutDao = shoutoutDao;
  }

  public void createShoutout(Shoutout shoutout) {
    shoutoutDao.insert(ShoutoutRowMapper.map(shoutout));
    log.info("Shoutout was inserted");
  }

  public void deleteShoutout(UUID shoutoutUuid) {
    if (shoutoutDao.exists(shoutoutUuid)) {
      shoutoutDao.deleteShoutout(shoutoutUuid);
      log.info("Shoutout was deleted");
    } else {
      log.info("Shoutout does not exist");
    }
  }

  public void cleanUpShoutouts(Instant time) {
    shoutoutDao.cleanUpShoutouts(time);
    log.info("Shoutouts deleted from before " + time.toString());
  }

  public List<Shoutout> getAll() {
    return ShoutoutMapper.map(shoutoutDao.getAll());
  }
}
