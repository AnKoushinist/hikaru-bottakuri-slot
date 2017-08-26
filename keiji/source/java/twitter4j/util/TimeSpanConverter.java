package twitter4j.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TimeSpanConverter implements Serializable {
    private static final int AN_HOUR_AGO = 4;
    private static final int A_MINUTE_AGO = 2;
    private static final int NOW = 0;
    private static final int N_HOURS_AGO = 5;
    private static final int N_MINUTES_AGO = 3;
    private static final int N_SECONDS_AGO = 1;
    private static final int ONE_DAY_IN_SECONDS = 86400;
    private static final int ONE_HOUR_IN_SECONDS = 3600;
    private static final int ONE_MONTH_IN_SECONDS = 2592000;
    private static final long serialVersionUID = 8665013607650804076L;
    private final SimpleDateFormat dateMonth;
    private final SimpleDateFormat dateMonthYear;
    private final MessageFormat[] formats;

    public TimeSpanConverter() {
        this(Locale.getDefault());
    }

    public TimeSpanConverter(Locale locale) {
        this.formats = new MessageFormat[6];
        String language = locale.getLanguage();
        if ("it".equals(language)) {
            this.formats[NOW] = new MessageFormat("Ora");
            this.formats[N_SECONDS_AGO] = new MessageFormat("{0} secondi fa");
            this.formats[A_MINUTE_AGO] = new MessageFormat("1 minuto fa");
            this.formats[N_MINUTES_AGO] = new MessageFormat("{0} minuti fa");
            this.formats[AN_HOUR_AGO] = new MessageFormat("1 ora fa");
            this.formats[N_HOURS_AGO] = new MessageFormat("{0} ore fa");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
        } else if ("kr".equals(language)) {
            this.formats[NOW] = new MessageFormat("\uc9c0\uae08");
            this.formats[N_SECONDS_AGO] = new MessageFormat("{0}\ucd08 \uc804");
            this.formats[A_MINUTE_AGO] = new MessageFormat("1\ubd84 \uc804");
            this.formats[N_MINUTES_AGO] = new MessageFormat("{0}\ubd84 \uc804");
            this.formats[AN_HOUR_AGO] = new MessageFormat("1\uc2dc\uac04 \uc804");
            this.formats[N_HOURS_AGO] = new MessageFormat("{0} ore fa");
            this.dateMonth = new SimpleDateFormat("M\uc6d4 d\uc77c", locale);
            this.dateMonthYear = new SimpleDateFormat("yy\ub144 M\uc6d4 d\uc77c", locale);
        } else if ("es".equals(language)) {
            this.formats[NOW] = new MessageFormat("Ahora");
            this.formats[N_SECONDS_AGO] = new MessageFormat("hace {0} segundos");
            this.formats[A_MINUTE_AGO] = new MessageFormat("hace 1 munito");
            this.formats[N_MINUTES_AGO] = new MessageFormat("hace {0} munitos");
            this.formats[AN_HOUR_AGO] = new MessageFormat("hace 1 hora");
            this.formats[N_HOURS_AGO] = new MessageFormat("hace {0} horas");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
        } else if ("fr".equals(language)) {
            this.formats[NOW] = new MessageFormat("Maintenant");
            this.formats[N_SECONDS_AGO] = new MessageFormat("Il y a {0} secondes");
            this.formats[A_MINUTE_AGO] = new MessageFormat("Il y a 1 minute");
            this.formats[N_MINUTES_AGO] = new MessageFormat("Il y a {0} minutes");
            this.formats[AN_HOUR_AGO] = new MessageFormat("Il y a 1 heure");
            this.formats[N_HOURS_AGO] = new MessageFormat("Il y a {0} heures");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
        } else if ("de".equals(language)) {
            this.formats[NOW] = new MessageFormat("Jetzt");
            this.formats[N_SECONDS_AGO] = new MessageFormat("vor {0} Sekunden");
            this.formats[A_MINUTE_AGO] = new MessageFormat("vor 1 Minute");
            this.formats[N_MINUTES_AGO] = new MessageFormat("vor {0} Minuten");
            this.formats[AN_HOUR_AGO] = new MessageFormat("vor 1 Stunde");
            this.formats[N_HOURS_AGO] = new MessageFormat("vor {0} Stunden");
            this.dateMonth = new SimpleDateFormat("d MMM", locale);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", locale);
        } else if ("ja".equals(language)) {
            this.formats[NOW] = new MessageFormat("\u4eca");
            this.formats[N_SECONDS_AGO] = new MessageFormat("{0}\u79d2\u524d");
            this.formats[A_MINUTE_AGO] = new MessageFormat("1\u5206\u524d");
            this.formats[N_MINUTES_AGO] = new MessageFormat("{0}\u5206\u524d");
            this.formats[AN_HOUR_AGO] = new MessageFormat("1\u6642\u9593\u524d");
            this.formats[N_HOURS_AGO] = new MessageFormat("{0}\u6642\u9593\u524d");
            this.dateMonth = new SimpleDateFormat("M\u6708d\u65e5", locale);
            this.dateMonthYear = new SimpleDateFormat("yy\u5e74M\u6708d\u65e5", locale);
        } else {
            this.formats[NOW] = new MessageFormat("now");
            this.formats[N_SECONDS_AGO] = new MessageFormat("{0} seconds ago");
            this.formats[A_MINUTE_AGO] = new MessageFormat("1 minute ago");
            this.formats[N_MINUTES_AGO] = new MessageFormat("{0} minutes ago");
            this.formats[AN_HOUR_AGO] = new MessageFormat("1 hour ago");
            this.formats[N_HOURS_AGO] = new MessageFormat("{0} hours ago");
            this.dateMonth = new SimpleDateFormat("d MMM", Locale.ENGLISH);
            this.dateMonthYear = new SimpleDateFormat("d MMM yy", Locale.ENGLISH);
        }
    }

    public String toTimeSpanString(Date date) {
        return toTimeSpanString(date.getTime());
    }

    public String toTimeSpanString(long j) {
        int currentTimeMillis = (int) ((System.currentTimeMillis() - j) / 1000);
        if (currentTimeMillis < ONE_DAY_IN_SECONDS) {
            return toTimeSpanString(currentTimeMillis);
        }
        if (currentTimeMillis >= ONE_MONTH_IN_SECONDS) {
            return this.dateMonthYear.format(new Date(j));
        }
        return this.dateMonth.format(new Date(j));
    }

    private String toTimeSpanString(int i) {
        if (i <= N_SECONDS_AGO) {
            return this.formats[NOW].format(null);
        }
        if (i < 60) {
            MessageFormat messageFormat = this.formats[N_SECONDS_AGO];
            Object obj = new Object[N_SECONDS_AGO];
            obj[NOW] = Integer.valueOf(i);
            return messageFormat.format(obj);
        } else if (i < 2700) {
            r0 = i / 60;
            if (r0 == N_SECONDS_AGO) {
                return this.formats[A_MINUTE_AGO].format(null);
            }
            r1 = this.formats[N_MINUTES_AGO];
            r2 = new Object[N_SECONDS_AGO];
            r2[NOW] = Integer.valueOf(r0);
            return r1.format(r2);
        } else if (i < 6300) {
            return this.formats[AN_HOUR_AGO].format(null);
        } else {
            r0 = (i + 900) / ONE_HOUR_IN_SECONDS;
            r1 = this.formats[N_HOURS_AGO];
            r2 = new Object[N_SECONDS_AGO];
            r2[NOW] = Integer.valueOf(r0);
            return r1.format(r2);
        }
    }
}
