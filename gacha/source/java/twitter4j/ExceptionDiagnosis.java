package twitter4j;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.Serializable;
import org.cocos2dx.lib.BuildConfig;

final class ExceptionDiagnosis implements Serializable {
    private static final long serialVersionUID = 8501009773274399369L;
    private String hexString;
    private int lineNumberHash;
    private int stackLineHash;

    ExceptionDiagnosis(Throwable th) {
        this(th, new String[0]);
    }

    ExceptionDiagnosis(Throwable th, String[] strArr) {
        this.hexString = BuildConfig.FLAVOR;
        StackTraceElement[] stackTrace = th.getStackTrace();
        this.stackLineHash = 0;
        this.lineNumberHash = 0;
        for (int length = stackTrace.length - 1; length >= 0; length--) {
            StackTraceElement stackTraceElement = stackTrace[length];
            for (String startsWith : strArr) {
                if (stackTraceElement.getClassName().startsWith(startsWith)) {
                    this.stackLineHash = (stackTraceElement.getClassName().hashCode() + stackTraceElement.getMethodName().hashCode()) + (this.stackLineHash * 31);
                    this.lineNumberHash = (this.lineNumberHash * 31) + stackTraceElement.getLineNumber();
                    break;
                }
            }
        }
        this.hexString += toHexString(this.stackLineHash) + Operation.MINUS + toHexString(this.lineNumberHash);
        if (th.getCause() != null) {
            this.hexString += " " + new ExceptionDiagnosis(th.getCause(), strArr).asHexString();
        }
    }

    int getStackLineHash() {
        return this.stackLineHash;
    }

    String getStackLineHashAsHex() {
        return toHexString(this.stackLineHash);
    }

    int getLineNumberHash() {
        return this.lineNumberHash;
    }

    String getLineNumberHashAsHex() {
        return toHexString(this.lineNumberHash);
    }

    String asHexString() {
        return this.hexString;
    }

    private String toHexString(int i) {
        String str = "0000000" + Integer.toHexString(i);
        return str.substring(str.length() - 8, str.length());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExceptionDiagnosis exceptionDiagnosis = (ExceptionDiagnosis) obj;
        if (this.lineNumberHash != exceptionDiagnosis.lineNumberHash) {
            return false;
        }
        if (this.stackLineHash != exceptionDiagnosis.stackLineHash) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.stackLineHash * 31) + this.lineNumberHash;
    }

    public String toString() {
        return "ExceptionDiagnosis{stackLineHash=" + this.stackLineHash + ", lineNumberHash=" + this.lineNumberHash + '}';
    }
}
