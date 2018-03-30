import java.security.SecureRandom;
/**
 *
 * @author Justin Dowell
 */
public class Ball {

    // ----- Variables ==========================
    private Integer x, y, dx, dy;
    private Integer winner;
    private Boolean active;

    // ----- Constructors =======================
    private Ball() {
        x = Constants.getBallSpawnX();
        y = Constants.getBallSpawnY();
        winner = 0;
        active = Boolean.TRUE;

        // random velocity
        SecureRandom sr = new SecureRandom();
        Boolean neg = Boolean.FALSE;

        neg = sr.nextBoolean();
        dx = sr.nextInt(10) + 5;
        if(neg) {
            dx = -dx;
        }

        neg = sr.nextBoolean();
        dy = sr.nextInt(10) + 5;
        if(neg) {
            dy = -dy;
        }
    }// ---

    // ----- Builder ============================
    public static Ball newBall() {
        Ball rv = new Ball();

        return rv;
    }// ---

    // ----- Overrides ==========================

    // ----- Private Methods ====================
    private void addRandVelo() {
        // random velocity
        SecureRandom sr = new SecureRandom();

        this.dx += sr.nextInt(Math.abs(this.dx)) - Math.abs(this.dx / 2);
        if(this.dx > 0 && this.dx < 5) {
            this.dx += 5;
        } else if(this.dx < 0 && this.dx > -5) {
            this.dx -= 5;
        }

        this.dy += sr.nextInt(Math.abs(this.dy)) - Math.abs(this.dy / 2);
        if(this.dy > 0 && this.dy < 5) {
            this.dy += 5;
        } else if(this.dy < 0 && this.dy > -5) {
            this.dy -= 5;
        }
    }

    // ----- Public Methods =====================
    public void deactivate() {
        this.active = Boolean.FALSE;
    }// ---

    public Integer getDX() {
        return this.dx;
    }// ---

    /**
     * @return -1 for left, 1 for right. 0 for none
     */
    public Integer getWinner() {
        Integer rv = this.winner;
        return rv;
    }// ---

    public Integer getX() {
        return this.x;
    }// ---

    public Integer getY() {
        return this.y;
    }// ---

    public Boolean isActive() {
        return this.active;
    }// ---

    public Boolean move(Integer LPAD_Y, Integer RPAD_Y) {
        Boolean success = Boolean.TRUE;                                 // FALSE means goal.
        // vertical movement
        if(this.dy > 0 && (this.y + this.dy + Constants.getBallHeight()) > Constants.getPanelHeight()) {            // bottom boundaries
            this.y += (((Constants.getPanelHeight() - (this.y + Constants.getBallHeight())) * 2) - this.dy);
            this.dy = -this.dy;
        } else if(this.dy < 0 && (this.y + this.dy) < Constants.getPanelTopBorder()) {                                                          // top boundaries
            this.y += ((Constants.getPanelTopBorder() - this.y) * 2) - this.dy;
            this.dy = -this.dy;
        } else {                                                                                                    // between boundaries
            this.y += this.dy;
        }

        // horizontal movement
        if(this.dx > 0                                                                                              // rightward velocity
                && (this.x + this.dx + Constants.getBallWidth()) >= Constants.getRPadX()                             // will pass right pad
                && this.x < Constants.getRPadX()                                                                    // not already passed pad
                && (this.y + (Constants.getBallHeight() / 2)) > RPAD_Y                                              //
                && (this.y + (Constants.getBallHeight() / 2)) < (RPAD_Y + Constants.getPadHeight())) {              // between bounds of pad
            this.x += (((Constants.getRPadX() - (this.x + Constants.getBallWidth())) * 2) - this.dx);
            this.dx = -this.dx;
            addRandVelo();
        } else if(this.dx < 0                                                                                       // leftward velocity
                && (this.x + this.dx) <= (Constants.getLPadX() + Constants.getPadWidth())                            // will pass left pad
                && this.x > (Constants.getLPadX() + Constants.getPadWidth())                                        // not already passed pad
                && (this.y + (Constants.getBallHeight() / 2)) > LPAD_Y                                              //
                && (this.y + (Constants.getBallHeight() / 2)) < (LPAD_Y + Constants.getPadHeight())) {              // between bounds of pad
            this.x += ((((Constants.getLPadX() + Constants.getPadWidth()) - this.x) * 2) - this.dx);
            this.dx = -this.dx;
            addRandVelo();
        } else {
            this.x += this.dx;
        }

        // check for goal
        if(this.dx > 0 && this.x > Constants.getPanelWidth()) {
            success = Boolean.FALSE;
            winner = -1;
        } else if (this.dx < 0 && this.x + Constants.getBallWidth() < 0) {
            success = Boolean.FALSE;
            winner = 1;
        }

        return success;
    }// ---

}
