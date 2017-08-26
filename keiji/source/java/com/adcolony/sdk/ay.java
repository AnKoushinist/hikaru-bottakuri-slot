package com.adcolony.sdk;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.opengl.GLSurfaceView.Renderer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

class ay implements Renderer {
    static Options a = new Options();
    static ByteBuffer b;
    ax c;
    String d;
    int e;
    int f;
    boolean g;
    boolean h;
    ArrayList<o> i = new ArrayList();
    al j;
    int k;
    int l;
    ArrayList<a> m = new ArrayList();
    HashMap<Integer, a> n = new HashMap();

    static class a {
        static int a = 1;
        int b;
        String c;
        boolean d = true;
        int e;
        int f;
        int g;
        int h;
        int i;

        a(int i, String str, int i2, int i3) {
            this.b = i;
            this.e = this.e;
            this.c = str;
            this.f = i2;
            this.g = i3;
            this.h = 1;
            while (this.h < i2) {
                this.h <<= 1;
            }
            this.i = 1;
            while (this.i < i3) {
                this.i <<= 1;
            }
        }
    }

    ay(ax axVar, boolean z, String str) {
        System.out.println("AdColony new ADCGLViewRenderer");
        this.c = axVar;
        this.g = z;
        this.d = str;
        this.j = (al) n.a().h().b().get(str);
        this.e = axVar.b;
        this.f = this.j.b();
        this.j.l().add(n.a("RenderView.create_image", new q(this) {
            final /* synthetic */ ay a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(oVar);
            }
        }, true));
        this.j.l().add(n.a("RenderView.load_texture", new q(this) {
            final /* synthetic */ ay a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.b(oVar);
            }
        }, true));
        this.j.m().add("RenderView.create_image");
        this.j.m().add("RenderView.load_texture");
    }

    synchronized void a() {
        if (!this.h) {
            this.h = true;
            synchronized (ADCNative.a) {
                ADCNative.nativeDeleteSceneController(this.f, this.e);
            }
        }
    }

    protected void finalize() {
        a();
    }

    public synchronized void onDrawFrame(GL10 gl10) {
        n.f();
        synchronized (ADCNative.a) {
            if (this.h) {
            } else {
                b();
                ADCNative.nativeRender(this.f, this.e, this.k, this.l);
            }
        }
    }

    public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (ADCNative.a) {
            if (this.h) {
            } else {
                ADCNative.nativeCreateSceneController(this.f, this.e);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.k = i;
        this.l = i2;
    }

    void a(o oVar) {
    }

    synchronized void b(o oVar) {
        this.i.add(oVar);
    }

    synchronized void b() {
        for (int i = 0; i < this.i.size(); i++) {
            o oVar = (o) this.i.get(i);
            JSONObject b = oVar.b();
            a aVar = null;
            if (b.has("pixels")) {
                String a = bb.a(b, "pixels");
                int[] iArr = new int[(a.length() / 4)];
                int length = a.length() - 4;
                int length2 = iArr.length;
                while (length >= 0) {
                    char charAt = a.charAt(length);
                    char charAt2 = a.charAt(length + 1);
                    char charAt3 = a.charAt(length + 2);
                    length += 4;
                    length2--;
                    iArr[length2] = (((charAt << 24) | (charAt2 << 16)) | (charAt3 << 8)) | a.charAt(length + 3);
                }
                int b2 = bb.b(b, "width");
                int b3 = bb.b(b, "height");
                if (b2 * b3 == iArr.length) {
                    aVar = a(bb.b(b, "texture_id"), bb.a(b, "filepath"), iArr, b2, b3);
                }
            } else if (b.has("bytes")) {
                String a2 = bb.a(b, "bytes");
                byte[] bArr = new byte[a2.length()];
                int length3 = a2.length();
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        break;
                    }
                    bArr[length3] = (byte) a2.charAt(length3);
                }
                aVar = a(bb.b(b, "texture_id"), bb.a(b, "filepath"), bArr);
            } else if (b.has("filepath")) {
                aVar = a(bb.b(b, "texture_id"), bb.a(b, "filepath"));
            }
            if (aVar == null) {
                bb.a(b, "success", false);
                break;
            }
            bb.a(b, "success", aVar.d);
            bb.b(b, "image_width", aVar.f);
            bb.b(b, "image_height", aVar.g);
            bb.b(b, "texture_width", aVar.h);
            bb.b(b, "texture_height", aVar.i);
            oVar.a(b).a();
        }
        this.i.clear();
    }

    a a(int i, String str) {
        Bitmap decodeStream;
        a.inScaled = false;
        String str2 = "file:///android_asset/";
        if (str.startsWith(str2)) {
            try {
                decodeStream = BitmapFactory.decodeStream(n.c().getAssets().open(str.substring(str2.length())), null, a);
            } catch (IOException e) {
                bd.f.b(e.toString());
                decodeStream = null;
            }
        } else {
            decodeStream = BitmapFactory.decodeFile(str, a);
        }
        if (decodeStream != null) {
            return a(i, str, decodeStream);
        }
        bd.f.a("Failed to load ").a(str).b((Object) " - using white 16x16 as placeholder.");
        decodeStream = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        decodeStream.eraseColor(-1);
        a a = a(i, str, decodeStream);
        a.d = false;
        return a;
    }

    a a(int i, String str, byte[] bArr) {
        a.inScaled = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            return a(i, str, decodeByteArray);
        }
        bd.f.a("Failed to load ").a(str).b((Object) " bytes - using white 16x16 as placeholder.");
        decodeByteArray = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        decodeByteArray.eraseColor(-1);
        a a = a(i, str, decodeByteArray);
        a.d = false;
        return a;
    }

    a a(int i, String str, int[] iArr, int i2, int i3) {
        return a(i, str, Bitmap.createBitmap(iArr, i2, i3, Config.ARGB_8888));
    }

    a a(int i, String str, Bitmap bitmap) {
        int i2 = 4194304;
        a aVar = new a(i, str, bitmap.getWidth(), bitmap.getHeight());
        int i3 = (aVar.h * aVar.i) * 4;
        if (b == null || b.capacity() < i3) {
            if (i3 >= 4194304) {
                i2 = i3;
            }
            b = ByteBuffer.allocateDirect(i2);
            b.order(ByteOrder.nativeOrder());
        }
        b.rewind();
        bitmap.copyPixelsToBuffer(b);
        this.m.add(aVar);
        this.n.put(Integer.valueOf(i), aVar);
        synchronized (ADCNative.a) {
            ADCNative.nativeCreateTexture(this.f, this.e, i, str, b, aVar.f, aVar.g, aVar.h, aVar.i);
        }
        return aVar;
    }
}
