public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int N = in.readInt();
        double R = in.readDouble();
        return R;

    }

    public static Body[] readBodies(String file) {
        In in = new In(file);
        int N = in.readInt();
        Body[] Bodies = new Body[N];
        double R = in.readDouble();
        for (int i = 0; i < N; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            double v_x = in.readDouble();
            double v_y = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Bodies[i] = new Body(x, y, v_x, v_y, m, img);
        }
        return Bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] Bodies = readBodies(filename);
        double r = readRadius(filename);

        String imgToDraw = "./images/starfield.jpg";
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0, 0, imgToDraw);

        for (Body planets : Bodies) {
            planets.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time;
        double[] xForces = new double[Bodies.length];
        double[] yForces = new double[Bodies.length];
        for (time = 0; time <= T; time = time + dt) {

            for (int j = 0; j < Bodies.length; j++) {
                xForces[j] = Bodies[j].calcNetForceExertedByX(Bodies);
                yForces[j] = Bodies[j].calcNetForceExertedByY(Bodies);
            }
            StdDraw.picture(0, 0, imgToDraw);
            for (int j = 0; j < Bodies.length; j++) {
                Bodies[j].update(dt, xForces[j], yForces[j]);
                Bodies[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(35);
        }
        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                    Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
        }

    }

}
