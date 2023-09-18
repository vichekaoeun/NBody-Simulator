public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;

    }

    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return r;
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        double F = (G * this.mass * b.mass) / Math.pow(r, 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - this.xxPos;
        double r = calcDistance(b);
        double F = calcForceExertedBy(b);
        double Fx = (F * dx) / r;
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - this.yyPos;
        double r = calcDistance(b);
        double F = calcForceExertedBy(b);
        double Fy = (F * dy) / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double Fnetx = 0;
        for (Body bodies : allBodys) {
            if (this.equals(bodies)) {
                continue;
            } else {
                Fnetx += this.calcForceExertedByX(bodies);
            }
        }
        return Fnetx;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double Fnety = 0;
        for (Body bodies : allBodys) {
            if (bodies.equals(bodies)) {
                continue;
            } else {
                Fnety += this.calcForceExertedByY(bodies);
            }
        }
        return Fnety;
    }

    public void update(double dt, double fX, double fY) {
        double anetx = fX / this.mass;
        double anety = fY / this.mass;

        double vnewx = this.xxVel + dt * anetx;
        double vnewy = this.yyVel + dt * anety;

        double pnewx = this.xxPos + dt * vnewx;
        double pnewy = this.yyPos + dt * vnewy;

        this.xxVel = vnewx;
        this.yyVel = vnewy;
        this.xxPos = pnewx;
        this.yyPos = pnewy;
    }

    public void draw() {
        String imgToDraw = "./images/" + this.imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToDraw);

    }

}