package jp.reifrontier.silentlogsdkandroid.data.location;

class Tracker1D {
    private double mPa;
    private double mPb;
    private double mPc;
    private double mPd;
    private final double mQa;
    private final double mQb;
    private final double mQc;
    private final double mQd;
    private double mXa;
    private double mXb;
    private final double mt;
    private final double mt2 = (this.mt * this.mt);
    private final double mt2d2 = (this.mt2 / 2.0d);
    private final double mt3d2 = ((this.mt2 * this.mt) / 2.0d);
    private final double mt4d4 = ((this.mt2 * this.mt2) / 4.0d);

    public Tracker1D(double d, double d2) {
        this.mt = d;
        double d3 = d2 * d2;
        this.mQa = this.mt4d4 * d3;
        this.mQb = this.mt3d2 * d3;
        this.mQc = this.mQb;
        this.mQd = d3 * this.mt2;
        this.mPa = this.mQa;
        this.mPb = this.mQb;
        this.mPc = this.mQc;
        this.mPd = this.mQd;
    }

    public void setState(double d, double d2, double d3) {
        this.mXa = d;
        this.mXb = d2;
        double d4 = d3 * d3;
        this.mPa = this.mt4d4 * d4;
        this.mPb = this.mt3d2 * d4;
        this.mPc = this.mPb;
        this.mPd = d4 * this.mt2;
    }

    public void update(double d, double d2) {
        double d3 = d - this.mXa;
        double d4 = 1.0d / ((d2 * d2) + this.mPa);
        double d5 = this.mPa * d4;
        d4 *= this.mPc;
        this.mXa += d5 * d3;
        this.mXb = (d3 * d4) + this.mXb;
        d3 = this.mPa - (this.mPa * d5);
        d5 = this.mPb - (d5 * this.mPb);
        double d6 = this.mPc - (this.mPa * d4);
        d4 = this.mPd - (d4 * this.mPb);
        this.mPa = d3;
        this.mPb = d5;
        this.mPc = d6;
        this.mPd = d4;
    }

    public void predict(double d) {
        this.mXa = (this.mXa + (this.mXb * this.mt)) + (this.mt2d2 * d);
        this.mXb += this.mt * d;
        double d2 = this.mPd * this.mt;
        double d3 = this.mPb + d2;
        double d4 = this.mPa + (this.mt * (this.mPc + d3));
        d2 += this.mPc;
        double d5 = this.mPd;
        this.mPa = d4 + this.mQa;
        this.mPb = d3 + this.mQb;
        this.mPc = d2 + this.mQc;
        this.mPd = this.mQd + d5;
    }

    public double getPosition() {
        return this.mXa;
    }

    public double getVelocity() {
        return this.mXb;
    }

    public double getAccuracy() {
        return Math.sqrt(this.mPd / this.mt2);
    }
}
