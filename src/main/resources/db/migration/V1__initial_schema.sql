CREATE TABLE sorrorities (
  uuid       UUID PRIMARY KEY,
  name       VARCHAR(100) NOT NULL
);

CREATE TABLE chapters (
  uuid           UUID PRIMARY KEY,
  name           VARCHAR(100) NOT NULL,
  sorrority_uuid UUID REFERENCES sorrorities(uuid)
);

CREATE TABLE pledge_classes (
  uuid           UUID PRIMARY KEY,
  name           VARCHAR(100) NOT NULL,
  chapter_uuid   UUID REFERENCES chapters(uuid),
  sorrority_uuid UUID REFERENCES sorrorities(uuid)
);

CREATE TABLE members (
  uuid              UUID PRIMARY KEY,
  name              VARCHAR(64) NOT NULL,
  chapter_uuid      UUID REFERENCES chapters(uuid),
  pledge_class_uuid UUID REFERENCES pledge_classes(uuid),
  graduating_year   VARCHAR(64) NOT NULL,
  big               VARCHAR(64) NOT NULL,
  sisterhood_points INTEGER NOT NULL,
  executive         BOOLEAN NOT NULL,
  phone_number      VARCHAR(64) NOT NULL,
  email             VARCHAR(200) NOT NULL,
  birthday          VARCHAR(64) NOT NULL,
  dues_paid         INTEGER NOT NULL
);

CREATE UNIQUE INDEX member_index
ON members (name, birthday, email);

CREATE TABLE events (
  uuid            UUID PRIMARY KEY,
  name            VARCHAR(200) NOT NULL,
  description     TIMESTAMP NOT NULL,
  score           INTEGER NOT NULL,
  event_time      VARCHAR(64) NOT NULL,
  event_day       VARCHAR(64) NOT NULL,
  number_attended INTEGER NOT NULL
);

CREATE TABLE comments (
  uuid       UUID PRIMARY KEY,
  comment    VARCHAR(1000) NOT NULL,
  event_uuid UUID REFERENCES events(uuid)
);

CREATE TABLE announcements (
  uuid         UUID PRIMARY KEY,
  announcement VARCHAR(1000) NOT NULL,
  created_at   TIMESTAMP
);

CREATE TABLE shoutouts (
  uuid       UUID PRIMARY KEY,
  shoutout   VARCHAR(1000) NOT NULL,
  created_at TIMESTAMP
);
