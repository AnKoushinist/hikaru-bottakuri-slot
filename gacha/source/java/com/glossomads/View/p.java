package com.glossomads.View;

import android.graphics.Bitmap;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.glossomads.j;
import com.glossomads.s;

public class p extends ImageView {
    private a a;
    private String b;
    private boolean c = false;

    public interface a {
        void a();

        void a(boolean z);
    }

    public p(String str) {
        super(s.e());
        this.b = str;
        setBackgroundColor(-16777216);
        setOnClickListener(new q(this));
    }

    public void a(boolean z) {
        Bitmap a = j.a().a(this.b, z);
        if (a != null) {
            setImageBitmap(a);
            setLayoutParams(new LayoutParams(a.getWidth(), a.getHeight(), 17));
        }
    }

    public boolean a() {
        this.c = j.a().b(this.b);
        return this.c;
    }

    public void b() {
        this.c = j.a().c(this.b);
        if (this.a != null) {
            this.a.a(this.c);
        }
    }

    public void c() {
        this.a = null;
        setImageBitmap(null);
    }

    public boolean d() {
        return this.c;
    }

    public void setSugarThumbnailViewListener(a aVar) {
        this.a = aVar;
    }
}
