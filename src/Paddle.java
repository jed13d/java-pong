import java.util.ArrayList;

/**
 *
 * @author Justin Dowell
 */
public class Paddle {

    // ----- Variables ==========================
    private Integer yPos;
    private final Boolean H;
    private final Boolean L;
    private final Integer MAX_SPEED;

    // ----- Constructors =======================
    private Paddle(Boolean human, Integer speed, Boolean left) {
        this.yPos = Constants.getPadSpawnY();
        this.H = human;
        this.L = left;
        this.MAX_SPEED = speed;
    }// ---

    // ----- Builder ============================
    public static Paddle newPaddle(Boolean human, Integer speed, Boolean left) {
        Paddle rv = new Paddle(human, speed, left);
        return rv;
    }// ---

    // ----- Overrides ==========================

    // ----- Private Methods ====================

    // ----- Public Methods =====================
    public Integer getPos() {
        return this.yPos;
    }// ---

    public Boolean isHuman() {
        return this.H;
    }

    public void move(ArrayList< Ball > balls) {
        ArrayList< Ball > targets = new ArrayList<>();
        Ball target = null;
        Integer leastDistance = Constants.getPanelWidth() + 1000;
        Integer dist = 0;

        /**
         *  for(Ball b: balls) {
         *      if(b.isActive() && ((b.getDX() > 0 && !L) || (b.getDX() < 0 && L))) {
         *          targets.add(b);
         *      }
         *  }
         */
        // if ball is active and moving towards pad, put in list of targets
        balls.stream()
                .filter((b) -> (b.isActive() && ((b.getDX() > 0 && !L) || (b.getDX() < 0 && L))))
                .forEachOrdered((b) -> {
                    targets.add(b);
                });

        // of targets, find closest and track
        for(Ball b: targets) {
            if(L) {
                dist = Math.abs(Constants.getLPadX() - b.getX());
            } else {
                dist = Math.abs(Constants.getRPadX() - b.getX());
            }

            if(dist < leastDistance) {
                leastDistance = dist;
                target = b;
            }
        }

        // finally, no targets, reset position
        if(null == target) {
            this.setPos(Constants.getPadSpawnY());
        } else {
            this.setPos((target.getY() + (Constants.getBallHeight() / 2)) - (Constants.getPadHeight() / 2));
        }
    }// ---

    public void setPos(Integer y) {
        // limit the speed at which the paddle moves if ai
        if(!this.isHuman()) {
            if(Math.abs(this.yPos - y) > this.MAX_SPEED) {
                if(y < this.yPos) {
                    this.yPos -= this.MAX_SPEED;
                } else {
                    this.yPos += this.MAX_SPEED;
                }
            } else {
                this.yPos += (y - this.yPos);
            }
        } else { // human, reaction is limit (for now)
            this.yPos += (y - this.yPos);
        }

        // stop the paddle at the boundaries
        if(this.yPos > Constants.getPanelHeight() - Constants.getPadHeight()) {
            this.yPos = Constants.getPanelHeight() - Constants.getPadHeight();
        } else if(this.yPos < Constants.getPanelTopBorder()) {
            this.yPos = Constants.getPanelTopBorder();
        }
    }// ---

}
