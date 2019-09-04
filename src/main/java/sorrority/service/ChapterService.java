package sorrority.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.ChapterDao;
import sorrority.db.models.ChapterRow;
import sorrority.service.mappers.ChapterMapper;
import sorrority.service.mappers.ChapterRowMapper;
import sorrority.service.models.Chapter;

@Slf4j
public class ChapterService {
  private final ChapterDao ChapterDao;

  public ChapterService(@NonNull final ChapterDao ChapterDao) {
    this.ChapterDao = ChapterDao;
  }

  public void createChapter(Chapter chapter) {
    if (!ChapterDao.exists(chapter.getName(), chapter.getSorrorityUuid())) {
      ChapterDao.insert(ChapterRowMapper.map(chapter));
      log.info("Chapter was inserted with name " + chapter.getName());
    } else {
      log.info("Chapter already exists with name" + chapter.getName());
    }
  }

  public void deleteChapter(UUID ChapterUuid) {
    if (ChapterDao.exists(ChapterUuid)) {
      ChapterRow ChapterRow = ChapterDao.findBy(ChapterUuid).get();
      ChapterDao.deleteChapter(ChapterUuid);
      log.info("Chapter was deleted with name " + ChapterRow.getName());
    } else {
      log.info("Chapter does not exist");
    }
  }

  public Optional<Chapter> getChapter(UUID ChapterUuid) {
    if (ChapterDao.exists(ChapterUuid)) {
      return Optional.of(ChapterMapper.map(ChapterDao.findBy(ChapterUuid).get()));
    } else {
      return Optional.empty();
    }
  }

  public List<Chapter> getAll() {
    return ChapterMapper.map(ChapterDao.getAll());
  }
}
