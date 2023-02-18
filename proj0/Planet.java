public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the body

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    // gravitational constant G
    private static final double G = 6.67e-11;

    /**
     * 1. This class not need main method;
     * 2. All methods of this class should be non-static;
     * 3. All of the number for this project will be doubles;
     * 4. All instance variables and method will be declared using the public
     * keyword;
     */

    /**
     * @source change the project web address from sp19 to sp18
     */

    /**
     * Calculate the distance betwwen two planets
     * 
     * @param p another Planet
     */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double sdistance = dx * dx + dy * dy;
        double distance = Math.sqrt(sdistance);
        return distance;
    }

    /**
     * Calculate the force exerted on this planet by the given planet.
     * 
     * @param p another Planet
     */
    public double calcForceExertedBy(Planet p) {
        /**
         * F = (G*m1*m2)/r^2,G=6.67*10e-11
         */
        // First invoke calcDistance() to get the distance
        double r = this.calcDistance(p);
        // calculate the force
        // System.out.println(G);
        double force = (G * this.mass * p.mass) / (r * r);
        return force;
    }

    /**
     * Calculate the force exerted in the X direction
     * 
     * @param p another planet
     */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        // double dy = p.yyPos - this.yyPos;
        // double r = Math.sqrt(dx * dx + dy * dy);
        double r = calcDistance(p);
        // invoke calcForceExertedBy() to get the whole exerted force
        double F = calcForceExertedBy(p);
        double F_x = F * dx / r;
        return F_x;
    }

    public double calcForceExertedByY(Planet p) {
        // double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        // double r = Math.sqrt(dx * dx + dy * dy);
        double r = calcDistance(p);
        // invoke calcForceExertedBy() to get the whole exeted force
        double F = calcForceExertedBy(p);
        double F_y = F * dy / r;
        return F_y;
    }

    /**
     * 
     * @param ps Planet array
     * @return the net X force exerted by all planets in that array upon the current
     *         Planet.
     */
    public double calcNetForceExertedByX(Planet[] ps) {
        double netForce = 0.0;
        for (int i = 0; i < ps.length; i++) {
            if (this.equals(ps[i]))
                continue;
            netForce += this.calcForceExertedByX(ps[i]);
        }
        return netForce;
    }

    /**
     * 
     * @param ps Planet array
     * @return the net X force exerted by all planets in that array upon the current
     *         Planet.
     */
    public double calcNetForceExertedByY(Planet[] ps) {
        double netForce = 0.0;
        for (int i = 0; i < ps.length; i++) {
            if (this.equals(ps[i]))
                continue;
            netForce += this.calcForceExertedByY(ps[i]);
        }
        return netForce;
    }

    /**
     * a_x = F_x / m
     */

    public void update(double dt, double fx, double fy) {
        // calculate the acceleration in x and y direction.
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        // update new velocity
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        // update new position
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
