import biuoop.DrawSurface;


/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private double x;
    private double y;
    private Velocity ballVelocity;
    private int screenWidth;
    private int screenHeight;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Ball.
     *
     * @param center1 the center 1
     * @param r       the r
     * @param color1  the color 1
     */
// constructor
    public Ball(Point center1, int r, java.awt.Color color1) {
        center = center1;
        radius = r;
        color = color1;
        x = center.getX();
        y = center.getY();
        ballVelocity = new Velocity(0, 0, r);
        screenWidth = 1000;
        screenHeight = 1000;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x1     the x 1
     * @param y1     the y 1
     * @param r      the r
     * @param color1 the color 1
     */
    public Ball(double x1, double y1, int r, java.awt.Color color1) {
        x = x1;
        y = y1;
        center = new Point(x, y);
        radius = r;
        color = color1;
        ballVelocity = new Velocity(0, 0, r);
        screenWidth = 400;
        screenHeight = 400;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x1     the x 1
     * @param y1     the y 1
     * @param r      the r
     * @param color1 the color 1
     * @param width  the width
     * @param height the height
     */
    public Ball(double x1, double y1, int r, java.awt.Color color1, int width, int height) {
        x = x1;
        y = y1;
        center = new Point(x, y);
        radius = r;
        color = color1;
        ballVelocity = new Velocity(0, 0, r);
        screenWidth = width;
        screenHeight = height;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x1          the x 1
     * @param y1          the y 1
     * @param r           the r
     * @param color1      the color 1
     * @param width       the width
     * @param height      the height
     * @param environment the environment
     */
    public Ball(double x1, double y1, int r, java.awt.Color color1, int width,
                int height, GameEnvironment environment) {
        x = x1;
        y = y1;
        center = new Point(x, y);
        radius = r;
        color = color1;
        screenWidth = width;
        screenHeight = height;
        gameEnvironment = environment;
        ballVelocity = new Velocity(0, 0, radius);
    }


    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Current ball location point.
     *
     * @return the point
     */
    public Point currentBallLocation() {
        return new Point(center.getX(), center.getY());
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        int x1 = getX();
        int y1 = getY();
        surface.setColor(color);
        surface.fillCircle(x1, y1, radius);
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
     ballVelocity = v;
    }

    /**
     * Set velocity.
     *
     * @param dx the dx speed
     * @param dy the dy speed
     */
    public void setVelocity(double dx, double dy) {
        ballVelocity = new Velocity(dx, dy, radius);
    }

    /**
     * Set velocity.
     *
     * @param dx      the dx speed
     * @param dy      the dy speed
     * @param screenW the screen w
     * @param screenH the screen h
     */
    public void setVelocity(double dx, double dy, int screenW, int screenH) {
        ballVelocity = new Velocity(dx, dy, radius, screenW, screenH);
    }


    /**
     * Set velocity.
     *
     * @param dx        the x speed
     * @param dy        the y speed
     * @param screenW   the screen width
     * @param screenH   the screen height
     * @param xBoundary the x boundary
     * @param yBoundary the y boundary
     */
    public void setVelocity(double dx, double dy, int screenW, int screenH, double xBoundary, double yBoundary) {
        ballVelocity = new Velocity(dx, dy, radius, screenW, screenH, xBoundary, yBoundary);
    }

    /**
     * Sets velocity.
     *
     * @param dx          the dx
     * @param dy          the dy
     * @param screenW     the screen w
     * @param screenH     the screen h
     * @param environment the environment
     */
    public void setVelocity(double dx, double dy, int screenW, int screenH, GameEnvironment environment) {
        ballVelocity = new Velocity(dx, dy, radius, screenW, screenH, environment);
    }

    /**
     * Gets velocity.
     *
     * @param xx the x
     * @param yy the y
     * sets velocity
     */
    public void setCenter(double xx, double yy) {
        center = new Point(xx, yy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return ballVelocity;
    }

    /**
     * Move one step.
     *
     * @return the line
     */
    public Line trajectory() {
        Point startOfTrajectory = new Point(
                Math.floor(center.getX()), Math.floor(center.getY()));
        Point endOfTrajectory = new Point(
                Math.floor(center.getX() + ballVelocity.getHorizontalSpeed()),
                Math.floor(center.getY() + ballVelocity.getVerticalSpeed()));
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        if (gameEnvironment.getClosestCollision(trajectory()) != null) {
            setVelocity(gameEnvironment.getClosestCollision(trajectory()).collisionObject().hit(
                    gameEnvironment.getClosestCollision(trajectory()).collisionPoint(), getVelocity()));
        }
        center = getVelocity().applyToPoint(center);
    }
    /**
     * timePassed.
     */
    public void timePassed() {
        moveOneStep();
        gameEnvironment.stuckHandeler(this);
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
