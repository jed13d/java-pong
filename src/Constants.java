import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.security.SecureRandom;

/**
 *
 * @author Justin Dowell
 */
public class Constants {

    // ----- Variables ==========================
    // Window Settings
    private static final Integer WINDOW_HEIGHT = 557;
    private static final Integer WINDOW_WIDTH = 711;
    private static final Dimension WINDOW_DIM = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

    // Panel Settings
    private static final Integer PANEL_HEIGHT = 500;
    private static final Integer PANEL_WIDTH = 700;
    private static final Integer PANEL_TOP_BORDER = 50;
    private static final Dimension PANEL_DIM = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);

    private static final Color BG_COLOR = new Color(0, 0, 0);
    private static final Color BORDER_COLOR = new Color(240, 240, 0);

    // Mob Settings
    private static final Integer PAD_WIDTH = 10;
    private static final Integer PAD_HEIGHT = 70;
    private static final Integer L_PAD_X = 30;
    private static final Integer R_PAD_X = PANEL_WIDTH - (L_PAD_X + PAD_WIDTH);
    private static final Integer PAD_SPAWN_Y = ((PANEL_HEIGHT + PANEL_TOP_BORDER) / 2) - (PAD_HEIGHT / 2);

    private static final Integer AI_PAD_MAX_SPEED = 50;
    private static final Integer AI_PAD_MIN_SPEED = 15;
    private static final Integer AI_PAD_MAX_ROLL = AI_PAD_MAX_SPEED - AI_PAD_MIN_SPEED;

    private static final Integer ACTIVE_BALL_COUNT = 10;

    private static final Integer BALL_HEIGHT = 10;
    private static final Integer BALL_WIDTH = 10;
    private static final Integer BALL_SPAWN_X = (PANEL_WIDTH / 2) - (BALL_WIDTH / 2);
    private static final Integer BALL_SPAWN_Y = (PANEL_HEIGHT / 2) - (BALL_HEIGHT / 2);
    private static final Integer BALL_DEFAULT_DX = 5;

    private static final Color PAD_COLOR = new Color(255, 255, 255);
    private static final Color BALL_COLOR = new Color(255, 255, 255);

    // Score Field Settings
    private static final Font SCORE_FONT = new Font("Comic Sans", Font.BOLD, 25);
    private static final Color SCORE_FONT_COLOR = new Color(128, 128 ,0);
    private static final Integer SCORE_LEFT_X = ((PANEL_WIDTH / 6) * 2) - 25;
    private static final Integer SCORE_LEFT_Y = ((PANEL_TOP_BORDER / 10) * 8);
    private static final Integer SCORE_RIGHT_X = ((PANEL_WIDTH / 6) * 4);
    private static final Integer SCORE_RIGHT_Y = ((PANEL_TOP_BORDER / 10) * 8);

    // Time Settings
    private static final Font TIME_FONT = new Font("Comic Sans", Font.BOLD, 35);
    private static final Color TIME_FONT_COLOR = new Color(128, 0, 128);
    private static final Integer TIME_X = ((PANEL_WIDTH / 2) * 1) - 50;
    private static final Integer TIME_Y = ((PANEL_TOP_BORDER / 10) * 8);

    // Timer settings
    private static final Integer FPS = 50;
    private static final Integer FRAME_TIME = 1000 / FPS;

    // ----- Constructors =======================
    private Constants() {}// ---

    // ----- Methods ============================
    public static Integer getWindowHeight() {
        return WINDOW_HEIGHT;
    }// ---

    public static Integer getWindowWidth() {
        return WINDOW_WIDTH;
    }// ---

    public static Dimension getWindowDim() {
        return WINDOW_DIM;
    }// ---

    public static Integer getPanelHeight() {
        return PANEL_HEIGHT;
    }// ---

    public static Integer getPanelWidth() {
        return PANEL_WIDTH;
    }// ---

    public static Integer getPanelTopBorder() {
        return PANEL_TOP_BORDER;
    }// ---

    public static Dimension getPanelDim() {
        return PANEL_DIM;
    }// ---

    public static Color getBGColor() {
        return BG_COLOR;
    }// ---

    public static Color getBorderColor() {
        return BORDER_COLOR;
    }// ---

    public static Integer getPadWidth() {
        return PAD_WIDTH;
    }// ---

    public static Integer getPadHeight() {
        return PAD_HEIGHT;
    }// ---

    public static Integer getLPadX() {
        return L_PAD_X;
    }// ---

    public static Integer getRPadX() {
        return R_PAD_X;
    }// ---

    public static Integer getPadSpawnY() {
        return PAD_SPAWN_Y;
    }// ---

    public static Integer getAIPadMaxSpeed() {
        return AI_PAD_MAX_SPEED;
    }// ---

    public static Integer getAIPadMinSpeed() {
        return AI_PAD_MIN_SPEED;
    }// ---

    public static Integer getAIPadMaxRoll() {
        return AI_PAD_MAX_ROLL;
    }// ---

    public static Integer getActiveBallCount() {
        return ACTIVE_BALL_COUNT;
    }// ---

    public static Integer getBallHeight() {
        return BALL_HEIGHT;
    }// ---

    public static Integer getBallWidth() {
        return BALL_WIDTH;
    }// ---

    public static Integer getBallSpawnX() {
        return BALL_SPAWN_X;
    }// ---

    public static Integer getBallSpawnY() {
        return BALL_SPAWN_Y;
    }// ---

    public static Integer getBallDefaultDX() {
        return BALL_DEFAULT_DX;
    }

    // (new Color(sr.nextInt(255), sr.nextInt(255), sr.nextInt(255))); // <-----<< all colors
    public static Color getPadColor() {
        SecureRandom sr = new SecureRandom();
        return PAD_COLOR;
//        return(new Color(sr.nextInt(100) + 55, sr.nextInt(100) + 55, sr.nextInt(100) + 55));
    }// ---

    public static Color getBallColor() {
        SecureRandom sr = new SecureRandom();
        return BALL_COLOR;
//        return(new Color(0, 0, sr.nextInt(100) + 155));
    }// ---

    public static Font getScoreFont() {
        return SCORE_FONT;
    }// ---

    public static Color getScoreFontColor() {
        return SCORE_FONT_COLOR;
    }// ---

    public static Integer getScoreLeftX() {
        return SCORE_LEFT_X;
    }// ---

    public static Integer getScoreLeftY() {
        return SCORE_LEFT_Y;
    }// ---

    public static Integer getScoreRightX() {
        return SCORE_RIGHT_X;
    }// ---

    public static Integer getScoreRightY() {
        return SCORE_RIGHT_Y;
    }// ---

    public static Font getTimeFont() {
        return TIME_FONT;
    }// ---

    public static Color getTimeFontColor() {
        return TIME_FONT_COLOR;
    }// ---

    public static Integer getTimeX() {
        return TIME_X;
    }// ---

    public static Integer getTimeY() {
        return TIME_Y;
    }// ---

    public static Integer getFrameTime() {
        return FRAME_TIME;
    }// ---

}// =========================================================