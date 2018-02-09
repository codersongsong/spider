package com.spider.song.spidercommon.statement;

/**
 * =====================================
 * Created by IntelliJ IDEA.
 * Description: (常量类管理)
 * auther: songsong.
 * Date: 2018-02-06.
 * Time: 23:56.
 * =====================================
 */
public final class Constants {


    public static final class EMAIL_FLAG{

        public static final String SMTP_PORT = "465";

        public static final String SEND_SUCCESS = "010";
        public static final String SEND_FAILURE = "020";

        public static final String MY_EMAIL_ACCOUNT = "myEmailAccount";
        public static final String MY_EMAIL_PASSWORD = "myEmailPassword";
        public static final String MY_EMAIL_SMTP_HOST = "myEmailSMTPHost";
        public static final String SENDER_NICKNAME = "senderNickName";
        public static final String TO_EMAIL_ACCOUNT = "toEmailAccount";
        public static final String TO_EMAIL_ACCOUNT_CC = "toEmailAccountCC";
    }

    public static final class JEDIS_FLAG{
        //可用连接实例的最大数目，默认值为8；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        public static int MAX_ACTIVE = 20;

        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        public static int MAX_IDLE = 10;

        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        public static int MAX_WAIT = 10000;

        public static int TIMEOUT = 10000;

        //在获取连接的时候检查有效性, 默认false
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        public static boolean TEST_ON_BORROW = true;
        public static String JEDIS_HOST = "jedis.host";
        public static String JEDIS_PORT = "jedis.port";
        public static String JEDIS_AUTH_PASSWORD = "jedis.auth.password";
    }

    public static final class WECHAT_FLAG {

        public static final String MESSAGE_TEXT = "text";
        public static final String MESSAGE_IMAGE = "image";
        public static final String MESSAGE_VOICE = "voice";
        public static final String MESSAGE_VIDEO = "video";
        public static final String MESSAGE_LINK = "link";
        public static final String MESSAGE_LOCATION = "location";
        public static final String MESSAGE_EVENT = "event";
        public static final String MESSAGE_SUBSCRIBE = "subscribe";
        public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
        public static final String MESSAGE_CLICK = "CLICK";
        public static final String MESSAGE_VIEW = "VIEW";
        public static final String MESSAGE_NEWS = "news";

    }


}
