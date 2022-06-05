/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double horizontalSpeed;
    private double verticalSpeed;
    private double screenWidth;
    private double screenHeight;
    private int radius;
    private double xBound;
    private double yBound;
    private GameEnvironment stuffOfTheGame;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx speed
     * @param dy the dy speed
     */
// constructor
    public Velocity(double dx, double dy) {
    horizontalSpeed = dx;
    verticalSpeed = dy;
    screenHeight = 400;
    screenWidth = 400;
    radius = 40;
    xBound = 0;
    yBound = 0;
    stuffOfTheGame = null;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx speed
     * @param dy the dy speed
     * @param r  the radius
     */
    public Velocity(double dx, double dy, int r) {
        horizontalSpeed = dx;
        verticalSpeed = dy;
        screenHeight = 400;
        screenWidth = 400;
        radius = r;
        xBound = 0;
        yBound = 0;
        stuffOfTheGame = null;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param dx     the dx speed
     * @param dy     the dy speed
     * @param r      the r radius
     * @param width  the width
     * @param height the height
     */
    public Velocity(double dx, double dy, int r, int width, int height) {
        horizontalSpeed = dx;
        verticalSpeed = dy;
        screenHeight = height;
        screenWidth = width;
        radius = r;
        xBound = 0;
        yBound = 0;
        stuffOfTheGame = null;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param vel the velocity given to the constructor
     */
    public Velocity(Velocity vel) {
        horizontalSpeed = vel.getHorizontalSpeed();
        verticalSpeed = vel.getVerticalSpeed();
        screenHeight = 0;
        screenWidth = 0;
        radius = 0;
        xBound = 0;
        yBound = 0;
        stuffOfTheGame = null;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param dx        the dx speed
     * @param dy        the dy speed
     * @param r         the r radius
     * @param width     the width
     * @param height    the height
     * @param xBoundary the x boundary
     * @param yBoundary the y boundary
     */
    public Velocity(double dx, double dy, int r, int width, int height, double xBoundary, double yBoundary) {
        horizontalSpeed = dx;
        verticalSpeed = dy;
        screenHeight = height;
        screenWidth = width;
        radius = r;
        xBound = xBoundary;
        yBound = yBoundary;
        stuffOfTheGame = null;
    }

    /**
     * Instantiates a new Velocity.
     *
     * @param dx          the dx
     * @param dy          the dy
     * @param r           the r
     * @param width       the width
     * @param height      the height
     * @param environment the environment
     */
    public Velocity(double dx, double dy, int r, int width, int height, GameEnvironment environment) {
        horizontalSpeed = dx;
        verticalSpeed = dy;
        screenHeight = height;
        screenWidth = width;
        radius = r;
        xBound = 0;
        yBound = 0;
        stuffOfTheGame = environment;
    }

    /**
     * Gets horizontal speed.
     *
     * @return the horizontal speed
     */
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * Gets vertical speed.
     *
     * @return the vertical speed
     */
    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this;
    }

    /**
     * Apply to point point.
     *
     * @param p the point
     * @return the new point location
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
   public Point applyToPoint(Point p) {
        double newX = p.getX() + horizontalSpeed;
        double newY = p.getY() + verticalSpeed;
        return new Point(newX, newY);
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity in x and y speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle - 90.0);
        double dx = Math.cos(angleRad) * speed;
        double dy = Math.sin(angleRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(horizontalSpeed * horizontalSpeed + verticalSpeed * verticalSpeed);
    }

}
