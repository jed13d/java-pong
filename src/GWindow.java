import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
/**
 *
 * @author Justin Dowell
 */
public class GWindow extends JFrame {

    // ----- Variables ==========================
    private final GPanel gpanel;
    private Timer timer;

    // ----- Constructors =======================
    private GWindow() {
        gpanel = GPanel.newPanel();
    }// ---

    // ----- Builder ============================
    public static GWindow newWindow() {
        GWindow.setDefaultLookAndFeelDecorated(true);
        GWindow rv = new GWindow();

        rv.setSize(Constants.getWindowDim());
        rv.setDefaultCloseOperation(GWindow.EXIT_ON_CLOSE);
        rv.setLocationRelativeTo(null);
        rv.initMenuBar();
        rv.add(rv.gpanel);
        rv.setTitle("P");
        rv.initTimer();

        return rv;
    }// ---

    // ----- Overrides ==========================

    // ----- Private Methods ====================
    private void initMenuBar() {
        JMenuBar mb = new JMenuBar();
        {
            JMenu fileMenu = new JMenu("File");
            {
                JMenuItem newMI = new JMenuItem("New");
                {
                    newMI.addActionListener((e) -> {
                        newGame();
                    });
                }
                fileMenu.add(newMI);

                JMenuItem quitMI = new JMenuItem("Quit");
                {
                    quitMI.addActionListener((e) -> {
                        System.exit(0);
                    });
                }
                fileMenu.add(quitMI);
            }
            mb.add(fileMenu);

            JMenu setMenu = new JMenu("Settings");
            {
                JMenu aiSubMenu = new JMenu("Toggle AI");
                {
                    JMenuItem p1mi = new JMenuItem("Player 1");
                    {
                        p1mi.addActionListener((e) -> {
                            gpanel.toggleAI(Boolean.TRUE);
                            newGame();
                        });
                    }
                    aiSubMenu.add(p1mi);

                    JMenuItem p2mi = new JMenuItem("Player 2");
                    {
                        p2mi.addActionListener((e) -> {
                            gpanel.toggleAI(Boolean.FALSE);
                            newGame();
                        });
                    }
                    aiSubMenu.add(p2mi);
                }
                setMenu.add(aiSubMenu);
            }
            mb.add(setMenu);
        }
        this.setJMenuBar(mb);
    }// ---

    private void initTimer() {
        // make sure timer doesn't already exist
        if(null == this.timer) {
            this.timer = new Timer(Constants.getFrameTime(), e -> {
               this.update();
            });
        }

        // start timer if not already running
        if(!this.timer.isRunning()) {
            this.timer.start();
        }
    }// ---

    private void killTimer() {
        if(this.timer.isRunning()) {
            this.timer.stop();
        }
        this.timer = null;
    }

    private void newGame() {
        this.killTimer();
        gpanel.newGame();
        this.initTimer();
    }// ---

    private void update() {
        gpanel.update();
        this.repaint();
    }// ---

    // ----- Public Methods =====================

}// =============================================