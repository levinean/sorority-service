package sorrority.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.AnnouncementDao;
import sorrority.service.mappers.AnnouncementMapper;
import sorrority.service.mappers.AnnouncementRowMapper;
import sorrority.service.models.Announcement;

@Slf4j
public class AnnouncementService {
  private final AnnouncementDao announcementDao;

  public AnnouncementService(@NonNull final AnnouncementDao announcementDao) {
    this.announcementDao = announcementDao;
  }

  public void createAnnouncement(Announcement announcement) {
    announcementDao.insert(AnnouncementRowMapper.map(announcement));
    log.info("Announcement was inserted");
  }

  public void deleteAnnouncement(UUID announcementUuid) {
    if (announcementDao.exists(announcementUuid)) {
      announcementDao.deleteAnnouncement(announcementUuid);
      log.info("Announcement was deleted");
    } else {
      log.info("Announcement does not exist");
    }
  }

  public void cleanUpAnnouncements(Instant time) {
    announcementDao.cleanUpAnnouncements(time);
    log.info("Announcements deleted from before " + time.toString());
  }

  public List<Announcement> getAll() {
    return AnnouncementMapper.map(announcementDao.getAll());
  }
}
