
package com.doublechaintech.retailscm.userapp;
import com.doublechaintech.retailscm.AccessKey;
import com.doublechaintech.retailscm.RetailscmBaseUtils;
import com.doublechaintech.retailscm.RetailscmUserContext;

import java.util.Map;

public class UserAppTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="user_app_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_TITLE = "title";
	static final String COLUMN_SEC_USER = "sec_user";
	static final String COLUMN_APP_ICON = "app_icon";
	static final String COLUMN_FULL_ACCESS = "full_access";
	static final String COLUMN_PERMISSION = "permission";
	static final String COLUMN_OBJECT_TYPE = "object_type";
	static final String COLUMN_OBJECT_ID = "object_id";
	static final String COLUMN_LOCATION = "location";
	static final String COLUMN_VERSION = "version";

	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_TITLE,COLUMN_SEC_USER,COLUMN_APP_ICON,COLUMN_FULL_ACCESS,COLUMN_PERMISSION,COLUMN_OBJECT_TYPE,COLUMN_OBJECT_ID,COLUMN_LOCATION,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_TITLE,COLUMN_SEC_USER,COLUMN_APP_ICON,COLUMN_FULL_ACCESS,COLUMN_PERMISSION,COLUMN_OBJECT_TYPE,COLUMN_OBJECT_ID,COLUMN_LOCATION};

	  public static void ensureTable(RetailscmUserContext userContext, Map<String, Object> result) throws Exception {
        RetailscmBaseUtils.ensureTable(userContext, result, "user_app_data", new String[][]{
                new String[]{"id","varchar(48)"," not null","ID","",""},
                new String[]{"title","varchar(300)","","头衔","",""},
                new String[]{"sec_user","varchar(48)","","安全用户","sec_user_data","id"},
                new String[]{"app_icon","varchar(36)","","应用程序图标","",""},
                new String[]{"full_access","tinyint","","完全访问","",""},
                new String[]{"permission","varchar(16)","","许可","",""},
                new String[]{"object_type","varchar(100)","","对象类型","",""},
                new String[]{"object_id","varchar(40)","","对象ID","",""},
                new String[]{"location","varchar(48)","","位置","",""},
                new String[]{"version","int","","版本","",""}
            }, "用户应用程序", new String[]{
                "create unique index idx4id_ver_of_user_app on user_app_data (id, version);",
                "create  index idx4object_id_of_user_app on user_app_data (object_id);"
         }, new String[]{
                "alter table user_app_data add constraint pk4id_of_user_app_data primary key (id);",
                "alter table user_app_data add constraint fk4sec_user_of_user_app_data foreign key (sec_user) references sec_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;",
                ""
         });
  }


}


