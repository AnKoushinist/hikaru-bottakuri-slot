package b.a.a.a.a.b;

/* compiled from: AdvertisingInfo */
class b {
    public final String a;
    public final boolean b;

    b(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.b != bVar.b) {
            return false;
        }
        if (this.a != null) {
            if (this.a.equals(bVar.a)) {
                return true;
            }
        } else if (bVar.a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.b) {
            i = 1;
        }
        return hashCode + i;
    }
}
