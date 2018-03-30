import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
/**
 *
 * @author Justin Dowell
 */
public class GPanel extends JPanel {

    // ----- Variables ==========================
    private Graphics gBuffer;
    private Image offScreen;
    private Paddle leftPad, rightPad;
    private ArrayList< Ball > balls;
    private Integer activeBalls;
    private Integer leftScore, rightScore;
    private Integer time;
    private Long startTime;

    // ----- Constructors =======================
    private GPanel() {
        leftScore = rightScore = 0;
        leftPad = null;
        rightPad = null;
        balls = new ArrayList<>();
        time = activeBalls = 0;
        startTime = 0L;
    }// ---

    // ----- Builder ============================
    public static GPanel newPanel() {
        GPanel rv = new GPanel();

        rv.setSize(Constants.getPanelDim());
        rv.initMouse();
        rv.newGame();

        return rv;
    }// ---

    // ----- Overrides ==========================
    @Override
    public void paintComponent(Graphics g) {
        // setup buffer
        this.offScreen = createImage(Constants.getPanelWidth(), Constants.getPanelHeight());
        this.gBuffer = offScreen.getGraphics();

        // background
        this.gBuffer.setColor(Constants.getBGColor());
        this.gBuffer.fillRect(0, 0, Constants.getPanelWidth(), Constants.getPanelHeight());

        // Border
        this.gBuffer.setColor(Constants.getBorderColor());
        this.gBuffer.drawLine(0, Constants.getPanelTopBorder(), Constants.getPanelWidth(), Constants.getPanelTopBorder());

        // Score
        this.paintScore(gBuffer);

        /**
         *  for(Ball ball: balls) {
         *      if(ball.isActive()) {
         *          this.gBuffer.fillOval(ball.getX(), ball.getY(), Constants.getBallWidth(), Constants.getBallHeight());
         *      }
         *  }
         */
        // ball
        this.gBuffer.setColor(Constants.getBallColor());
        balls.stream()
                .filter((ball) -> (ball.isActive())).forEachOrdered((ball) -> {
                    this.gBuffer.fillOval(ball.getX(), ball.getY(), Constants.getBallWidth(), Constants.getBallHeight());
                });

        // paddles
        this.gBuffer.setColor(Constants.getPadColor());
        this.gBuffer.fillRect(Constants.getLPadX(), this.leftPad.getPos(), Constants.getPadWidth(), Constants.getPadHeight());
        this.gBuffer.setColor(Constants.getPadColor());
        this.gBuffer.fillRect(Constants.getRPadX(), this.rightPad.getPos(), Constants.getPadWidth(), Constants.getPadHeight());

        g.drawImage(offScreen, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
    }// ---

    // ----- Private Methods ====================
    private void initBalls() {
        balls = new ArrayList<>();
        activeBalls = Constants.getActiveBallCount();
        for(int i = 0; i < activeBalls; i++) {
            balls.add(Ball.newBall());
        }
    }// ---

    private void initMouse() {
        this.addMouseMotionListener(new MouseInputAdapter() {
           @Override
           public void mouseDragged(MouseEvent e) {
               if(leftPad.isHuman()) {
                   leftPad.setPos(e.getY() - (Constants.getPadHeight() / 2));
               } else if (rightPad.isHuman()) {
                   rightPad.setPos(e.getY() - (Constants.getPadHeight() / 2));
               }
           }
        });
    }// ---

    private void initPads() {
        if(null == this.leftPad || !this.leftPad.isHuman()) {
            this.initPad(Boolean.TRUE);
        }
        if(null == this.rightPad || !this.rightPad.isHuman()) {
            this.initPad(Boolean.FALSE);
        }
    }// ---

    private void initPad(Boolean left) {
        SecureRandom sr = new SecureRandom();
        if(left) {
            this.leftPad = Paddle.newPaddle(Boolean.FALSE, sr.nextInt(Constants.getAIPadMaxRoll()) + Constants.getAIPadMinSpeed(), Boolean.TRUE);
        } else {
            this.rightPad = Paddle.newPaddle(Boolean.FALSE, sr.nextInt(Constants.getAIPadMaxRoll()) + Constants.getAIPadMinSpeed(), Boolean.FALSE);
        }
    }// ---

    private void paintScore(Graphics g) {
        g.setFont(Constants.getScoreFont());
        g.setColor(Constants.getScoreFontColor());
        g.drawString(String.format("%03d", this.leftScore), Constants.getScoreLeftX(), Constants.getScoreLeftY());
        g.drawString(String.format("%03d", this.rightScore), Constants.getScoreRightX(), Constants.getScoreRightY());

        g.setFont(Constants.getTimeFont());
        g.setColor(Constants.getTimeFontColor());
        g.drawString(String.format("%02d : %02d", (this.time / 60), (this.time % 60)), Constants.getTimeX(), Constants.getTimeY());
    }// ---

    // ----- Public Methods =====================
    public void newGame() {
        this.leftScore = this.rightScore = 0;
        this.startTime = System.currentTimeMillis();
        this.initBalls();
        this.initPads();
    }// ---

    /**
     * Two AI is okay, Two Human not okay
     * @param lPad false targets rPad
     */
    public void toggleAI(Boolean lPad) {
        if(lPad) {
            if(!this.leftPad.isHuman() && !this.rightPad.isHuman()) {
                this.leftPad = Paddle.newPaddle(Boolean.TRUE, 1000, Boolean.TRUE);
            } else if(this.leftPad.isHuman()) {
                this.initPad(Boolean.TRUE);
            } else if(!this.leftPad.isHuman() && this.rightPad.isHuman()) {
                this.leftPad = Paddle.newPaddle(Boolean.TRUE, 1000, Boolean.TRUE);
                this.initPad(Boolean.FALSE);
            }
        } else {
            if(!this.leftPad.isHuman() && !this.rightPad.isHuman()) {
                this.rightPad = Paddle.newPaddle(Boolean.TRUE, 1000, Boolean.FALSE);
            } else if(this.rightPad.isHuman()) {
                this.initPad(Boolean.FALSE);
            } else if(this.leftPad.isHuman() && !this.rightPad.isHuman()) {
                this.rightPad = Paddle.newPaddle(Boolean.TRUE, 1000, Boolean.FALSE);
                this.initPad(Boolean.TRUE);
            }
        }
    }// ---

    public void update() {
        // update time
        time = (int)((System.currentTimeMillis() - this.startTime) / 1000);

        if(this.activeBalls <= 0) {
            this.initBalls();
            this.initPads();
        }

        balls.stream().filter((ball) -> (ball.isActive())).forEachOrdered((ball) -> {
            if(ball.move(this.leftPad.getPos(), this.rightPad.getPos())) {
                if(!this.leftPad.isHuman()) {
                    this.leftPad.move(this.balls);
                }
                if(!this.rightPad.isHuman()) {
                    this.rightPad.move(this.balls);
                }
            } else {    // if goal, adjust scores and print them
                if(ball.getWinner() < 0) {
                    this.leftScore++;
                } else if(ball.getWinner() > 0) {
                    this.rightScore++;
                }
                ball.deactivate();
                this.activeBalls--;
            }
        }); // move the ball, use return value to determine goal
    }// ---

}
