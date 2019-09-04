package sorrority.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sorrority.db.EventDao;
import sorrority.db.models.EventRow;
import sorrority.service.mappers.EventMapper;
import sorrority.service.mappers.EventRowMapper;
import sorrority.service.models.Event;

@Slf4j
public class EventService {
  private final EventDao eventDao;

  public EventService(@NonNull final EventDao eventDao) {
    this.eventDao = eventDao;
  }

  public void createEvent(Event event) {
    if (!eventDao.existsAtSameTime(event.getEventTime(), event.getEventDay())) {
      eventDao.insert(EventRowMapper.map(event));
      log.info("Event inserted with name " + event.getName());
    } else {
      log.info("event already exists on " + event.getEventDay() + "at " + event.getEventTime());
    }
  }

  public void deleteEvent(UUID eventUuid) {
    if (eventDao.exists(eventUuid)) {
      EventRow eventRow = eventDao.findby(eventUuid);
      eventDao.deleteEvent(eventUuid);
      log.info("Event was deleted with name " + eventRow.getName());
    } else {
      log.info("Event does not exist");
    }
  }

  public Optional<Event> getEvent(UUID eventUuid) {
    if (eventDao.exists(eventUuid)) {
      return Optional.of(EventMapper.map(eventDao.findby(eventUuid)));
    } else {
      return Optional.empty();
    }
  }

  public void addAttender(UUID eventUuid, int score) {
    if (eventDao.exists(eventUuid)) {
      EventRow eventRow = eventDao.findby(eventUuid);
      int currScore = eventRow.getScore();
      int currNumAttended = eventRow.getNumberAttended();
      eventDao.updateNumberAttended(eventUuid, currNumAttended + 1);
      eventDao.updateScore(eventUuid, currScore + score);
      log.info("Event attendance was increase for event with name " + eventRow.getName());
    } else {
      log.info("Event does not exist");
    }
  }

  public void changeTime(UUID eventUuid, String newTime) {
    if (eventDao.exists(eventUuid)) {
      EventRow eventRow = eventDao.findby(eventUuid);
      eventDao.updateTime(eventUuid, newTime);
      log.info("Event time updated for event with name " + eventRow.getName());
    } else {
      log.info("Event does not exist");
    }
  }

  public void changeDay(UUID eventUuid, String newDay) {
    if (eventDao.exists(eventUuid)) {
      EventRow eventRow = eventDao.findby(eventUuid);
      eventDao.updateDay(eventUuid, newDay);
      log.info("Event day updated for event with name " + eventRow.getName());
    } else {
      log.info("Event does not exist");
    }
  }

  public void changeDescription(UUID eventUuid, String newDescription) {
    if (eventDao.exists(eventUuid)) {
      EventRow eventRow = eventDao.findby(eventUuid);
      eventDao.updateDescription(eventUuid, newDescription);
      log.info("Event description updated for event with name " + eventRow.getName());
    } else {
      log.info("Event does not exist");
    }
  }

  public List<Event> getAllEventsOnDay(String day) {
    return EventMapper.map(eventDao.getAllEventsOnDay(day));
  }

  public List<Event> getAllEvents() {
    return EventMapper.map(eventDao.getAll());
  }
}
