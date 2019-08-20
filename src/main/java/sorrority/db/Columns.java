/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorrority.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class Columns {
  private Columns() {}

  // Member column names

  // uuid              UUID PRIMARY KEY,
  // name              VARCHAR(64) NOT NULL,
  // chapter_uuid      UUID REFERENCES chapters(uuid),
  // pledge_class_uuid UUID REFERENCES pledge_classes(uuid),
  // graduating_year   VARCHAR(64) NOT NULL,
  // big               VARCHAR(64) NOT NULL,
  // sisterhood_points INTEGER NOT NULL,
  // executive         BOOLEAN NOT NULL,
  // phone_number      VARCHAR(64) NOT NULL,
  // email             VARCHAR(200) NOT NULL,
  // birthday          VARCHAR(64) NOT NULL,
  // dues_paid         INTEGER NOT NULL

  public static final String ROW_UUID = "uuid";
  public static final String NAME = "name";
  public static final String CHAPTER_UUID = "chapter_uuid";
  public static final String PLEDGE_CLASS_UUID = "pledge_class_uuid";
  public static final String GRADUATING_YEAR = "graduating_year";
  public static final String BIG = "big";
  public static final String SISTERHOOD_POINTS = "sisterhood_points";
  public static final String EXECUTIVE = "executive";
  public static final String PHONE_NUMBER = "phone_number";
  public static final String EMAIL = "email";
  public static final String BIRTHDAY = "birthday";
  public static final String DUES_PAID = "dues_paid";

  public static UUID uuidOrNull(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      return null;
    }
    return results.getObject(column, UUID.class);
  }

  public static UUID uuidOrThrow(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      throw new IllegalArgumentException();
    }
    return results.getObject(column, UUID.class);
  }

  public static Instant timestampOrNull(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      return null;
    }
    return results.getTimestamp(column).toInstant();
  }

  public static Instant timestampOrThrow(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      throw new IllegalArgumentException();
    }
    return results.getTimestamp(column).toInstant();
  }

  public static String stringOrNull(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      return null;
    }
    return results.getString(column);
  }

  public static Integer intOrNull(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      return null;
    }
    return results.getInt(column);
  }

  public static Boolean booleanOrNull(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      return null;
    }
    return results.getBoolean(column);
  }

  public static String stringOrThrow(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      throw new IllegalArgumentException();
    }
    return results.getString(column);
  }

  public static List<String> arrayOrThrow(ResultSet results, String column) throws SQLException {
    if (results.getObject(column) == null) {
      throw new IllegalArgumentException();
    }
    return Arrays.asList((String[]) results.getArray(column).getArray());
  }
}
