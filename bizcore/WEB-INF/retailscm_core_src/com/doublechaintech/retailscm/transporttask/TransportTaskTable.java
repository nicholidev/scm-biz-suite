package com.doublechaintech.retailscm.transporttask;

import com.doublechaintech.retailscm.AccessKey;
import com.doublechaintech.retailscm.RetailscmBaseUtils;
import com.doublechaintech.retailscm.RetailscmUserContext;

import java.util.Map;

public class TransportTaskTable {

  public static AccessKey withId(Object value) {
    AccessKey accessKey = new AccessKey();
    accessKey.setColumnName(COLUMN_ID);
    accessKey.setValue(value);
    return accessKey;
  }
  // Add extra identifiers

  // only this package can use this, so the scope is default, not public, not private either nor
  // protected
  public static final String TABLE_NAME = "transport_task_data";
  static final String COLUMN_ID = "id";
  static final String COLUMN_NAME = "name";
  static final String COLUMN_START = "start";
  static final String COLUMN_BEGIN_TIME = "begin_time";
  static final String COLUMN_END = "end";
  static final String COLUMN_DRIVER = "driver";
  static final String COLUMN_TRUCK = "truck";
  static final String COLUMN_BELONGS_TO = "belongs_to";
  static final String COLUMN_LATITUDE = "latitude";
  static final String COLUMN_LONGITUDE = "longitude";
  static final String COLUMN_VERSION = "version";

  public static final String[] ALL_CLOUMNS = {
    COLUMN_ID,
    COLUMN_NAME,
    COLUMN_START,
    COLUMN_BEGIN_TIME,
    COLUMN_END,
    COLUMN_DRIVER,
    COLUMN_TRUCK,
    COLUMN_BELONGS_TO,
    COLUMN_LATITUDE,
    COLUMN_LONGITUDE,
    COLUMN_VERSION
  };
  public static final String[] NORMAL_CLOUMNS = {
    COLUMN_NAME,
    COLUMN_START,
    COLUMN_BEGIN_TIME,
    COLUMN_END,
    COLUMN_DRIVER,
    COLUMN_TRUCK,
    COLUMN_BELONGS_TO,
    COLUMN_LATITUDE,
    COLUMN_LONGITUDE
  };

  public static void ensureTable(RetailscmUserContext userContext, Map<String, Object> result)
      throws Exception {
    RetailscmBaseUtils.ensureTable(
        userContext,
        result,
        "transport_task_data",
        new String[][] {
          new String[] {"id", "varchar(48)", " not null", "ID", "", ""},
          new String[] {"name", "varchar(16)", "", "??????", "", ""},
          new String[] {"start", "varchar(20)", "", "??????", "", ""},
          new String[] {"begin_time", "date", "", "????????????", "", ""},
          new String[] {"end", "varchar(48)", "", "??????", "retail_store_data", "id"},
          new String[] {"driver", "varchar(48)", "", "??????", "truck_driver_data", "id"},
          new String[] {"truck", "varchar(48)", "", "??????", "transport_truck_data", "id"},
          new String[] {"belongs_to", "varchar(48)", "", "??????", "transport_fleet_data", "id"},
          new String[] {"latitude", "numeric(9,6)", "", "??????", "", ""},
          new String[] {"longitude", "numeric(10,6)", "", "??????", "", ""},
          new String[] {"version", "int", "", "??????", "", ""}
        },
        "????????????",
        new String[] {
          "create unique index idx4id_ver_of_transport_task on transport_task_data (id, version);",
          "create  index idx4begin_time_of_transport_task on transport_task_data (begin_time);",
          "create  index idx4latitude_of_transport_task on transport_task_data (latitude);",
          "create  index idx4longitude_of_transport_task on transport_task_data (longitude);",
          "create  index idx4version_of_transport_task on transport_task_data (version);"
        },
        new String[] {
          "alter table transport_task_data add constraint pk4id_of_transport_task_data primary key (id);",
          "alter table transport_task_data add constraint fk4end_of_transport_task_data foreign key (end) references retail_store_data(id) ON DELETE CASCADE ON UPDATE CASCADE;",
          "alter table transport_task_data add constraint fk4driver_of_transport_task_data foreign key (driver) references truck_driver_data(id) ON DELETE CASCADE ON UPDATE CASCADE;",
          "alter table transport_task_data add constraint fk4truck_of_transport_task_data foreign key (truck) references transport_truck_data(id) ON DELETE CASCADE ON UPDATE CASCADE;",
          "alter table transport_task_data add constraint fk4belongs_to_of_transport_task_data foreign key (belongs_to) references transport_fleet_data(id) ON DELETE CASCADE ON UPDATE CASCADE;",
          ""
        });
  }
}
