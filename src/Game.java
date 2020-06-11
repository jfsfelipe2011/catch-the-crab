import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, MouseListener {
    public static int WIDTH = 480;
    public static int HEIGHT = 480;
    public static List<Crab> crabs;
    public static List<Smoke> smokes;
    public Spawner spawner;
    public static Spritesheet spritesheet;
    public static Rectangle maskBuraco;
    public static int mx, my;
    public static int score = 0;
    public static boolean isPressed = false;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addMouseListener(this);

        spritesheet = new Spritesheet("/spritesheet.png");
        crabs = new ArrayList<>();
        smokes = new ArrayList<>();
        spawner = new Spawner();
        maskBuraco = new Rectangle(WIDTH/2 - 20, HEIGHT/2 - 20, 40, 40);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle("Catch the Crab");
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        new Thread(game).start();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(new Color(255, 229, 102));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(WIDTH/2 - 20, HEIGHT/2 - 20, 40, 40);

        for (int i = 0; i < crabs.size(); i++) {
            crabs.get(i).render(g);
        }

        for (int i = 0; i < smokes.size(); i++) {
            smokes.get(i).render(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.BOLD, 22));
        g.drawString("Score: " + score, 10, 20);

        g.dispose();
        bufferStrategy.show();
    }

    public void update() {
        for (int i = 0; i < crabs.size(); i++) {
            crabs.get(i).update();
        }

        for (int i = 0; i < smokes.size(); i++) {
            smokes.get(i).update();
        }

        spawner.update();
    }

    @Override
    public void run() {
        double fps = 60.0;
        while (true) {
            update();
            render();
            try {
                Thread.sleep((int) (1000/fps));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
