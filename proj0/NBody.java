public class NBody {
    /**
     * 
     * @param path file path
     * @return radius
     */
    static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        double radius = in.readDouble();
        in.close();

        return radius;
    }

    /**
     * 
     * @param path
     * @return
     */
    static Planet[] readPlanets(String path) {
        /**
         * standard iput stream,get file content
         */
        In in = new In(path);
        /**
         * get numbers of planet as the length of return array
         */
        Planet[] planets = new Planet[in.readInt()];
        double radius = in.readDouble();
        System.out.println(planets);
        for (int i = 0; i < planets.length; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imString = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imString);
            planets[i] = planet;
        }
        in.close();
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        // StdDraw.setScale(-10,10);
        // StdDraw.clear();
        // StdDraw.picture(0, 0, "images/starfield.jpg");
        // for (int i = 0; i < planets.length; i++) {
        // planets[i].draw();
        // }

        StdDraw.enableDoubleBuffering();
        for (double time = 0; time <= T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int j = 0; j < planets.length; j++) {
                planets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}