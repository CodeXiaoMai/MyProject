package com.xiaomai.myproject.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by XiaoMai on 2016/9/7.
 */
public final class TableColumns {

    public static final String AUTHORITY = "com.xiaomai";

    public static final class JsonColumns implements BaseColumns {

        private JsonColumns() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/json_cache");

        public static final String KEY = "key";

        public static final String DATA = "data";

        public static final String ADD_TIME = "add_time";

        public static final String CACHE_SPAN = "cache_span";

    }

    public static final class DraftColumns implements BaseColumns {

        private DraftColumns() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/draft_cache");

        public static final String KEY = "key";

        public static final String VALUE = "value";

    }

    public static final class PushColumns implements BaseColumns {

        private PushColumns() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/push_cache");

        public static final String KEY = _ID;

        public static final String TITLE = "title";

        public static final String CONTENT = "content";

        public static final String CUSTOM_CONTENT = "custom_content";

        public static final String DATE = "date";

    }
}
