package b.a.a.a.a.c;

/* compiled from: Priority */
public enum e {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int a(i iVar, Y y) {
        e b;
        if (y instanceof i) {
            b = ((i) y).b();
        } else {
            b = NORMAL;
        }
        return b.ordinal() - iVar.b().ordinal();
    }
}
