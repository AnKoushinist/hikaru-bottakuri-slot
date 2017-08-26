package com.raizlabs.android.dbflow.annotation;

import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public enum ConflictAction {
    NONE,
    ROLLBACK,
    ABORT,
    FAIL,
    IGNORE,
    REPLACE;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction = null;

        static {
            $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction = new int[ConflictAction.values().length];
            try {
                $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[ConflictAction.ROLLBACK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[ConflictAction.ABORT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[ConflictAction.FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[ConflictAction.IGNORE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[ConflictAction.REPLACE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static int getSQLiteDatabaseAlgorithmInt(ConflictAction conflictAction) {
        switch (AnonymousClass1.$SwitchMap$com$raizlabs$android$dbflow$annotation$ConflictAction[conflictAction.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return 1;
            case TwitterResponse.READ_WRITE /*2*/:
                return 2;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return 3;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return 4;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                return 5;
            default:
                return 0;
        }
    }
}
