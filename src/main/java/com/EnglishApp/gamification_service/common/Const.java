package com.EnglishApp.gamification_service.common;

import java.time.ZoneId;

public final class Const {
    private Const() {
    }

    public static final class QUEST_STATUS {
        public static final Byte IN_PROGRESS = 0;
        public static final Byte COMPLETED = 1;
        public static final Byte CLAIMED = 2;

        private QUEST_STATUS() {
        }
    }

    public static final class ACTIVE_STATUS {
        public static final Byte INACTIVE = 0;
        public static final Byte ACTIVE = 1;

        private ACTIVE_STATUS() {
        }
    }

    public static final class LEADERBOARD_PERIOD_STATUS {
        public static final Byte UPCOMING = 0;
        public static final Byte ACTIVE = 1;
        public static final Byte ENDED = 2;

        private LEADERBOARD_PERIOD_STATUS() {
        }
    }

    public static final ZoneId GAMIFICATION_TIME_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
}
