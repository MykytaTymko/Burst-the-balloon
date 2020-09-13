import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RandomBalls extends JFrame {

    final String TITLE_OF_PROGRAM = "Random balls";
    final int WINDOW_WIDTH = 650;
    final int WINDOW_HEIGHT = 650;
    final Color[] COLORS = {Color.red, Color.green, Color.blue};
    Random random;
    ArrayList<Ball> balls;
    final int COUNT_BALLS = 100;
    int showDelay = 1000;
    int counter = 0;
    Canvas canvas = new Canvas();

    public static void main(String[] args) {
        new RandomBalls().game();

    }

    public RandomBalls() {
        balls = new ArrayList<>();
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                deleteBall(e.getX(), e.getY());
                canvas.repaint();
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        random = new Random();
    }

        class Canvas extends JPanel {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                for (Ball ball : balls) {
                    ball.paint(g);
                }
            }
        }

        void addBall() {
            int d = random.nextInt(20) + 20;
            int x = random.nextInt(WINDOW_WIDTH - d);
            int y = random.nextInt(WINDOW_HEIGHT - d);
            Color color = COLORS[random.nextInt(COLORS.length)];
            balls.add(new Ball(x, y, d, color));
        }

        void deleteBall(int x, int y) {
            for (int i = balls.size() - 1; i > -1; i--) {
                double dx = balls.get(i).x + balls.get(i).d / 2 - x;
                double dy = balls.get(i).y + balls.get(i).d / 2 - y;
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d < balls.get(i).d / 2) {
                    balls.remove(i);
                    break;
                }
            }
        }

        void game() {
            while (true) {
                addBall();
                if (balls.size() >= 5) {
                    System.out.println("Game Over: " + counter);
                    break;
                }
                canvas.repaint();
                counter++;
                if (counter % 10 == 0 && showDelay > 100) {
                    showDelay -= 100;
                }
                try {
                    Thread.sleep(showDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    class Ball {
        int x, y, d;
        Color color;

        Ball(int x, int y, int d, Color color) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.color = color;
        }

        void paint(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }
    }
