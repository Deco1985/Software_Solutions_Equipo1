import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame implements ActionListener, KeyListener {
    private static final int TILE_SIZE = 20;
    private static final int GRID_SIZE = 20;

    private enum Direction {UP, DOWN, LEFT, RIGHT}

    private Direction direction = Direction.RIGHT;
    private Timer timer;
    private LinkedList<Point> snake;
    private Point apple;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(TILE_SIZE * GRID_SIZE, TILE_SIZE * GRID_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        snake = new LinkedList<>();
        snake.add(new Point(5, 5));

        spawnApple();

        timer = new Timer(150, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void spawnApple() {
        Random rand = new Random();
        int x = rand.nextInt(GRID_SIZE);
        int y = rand.nextInt(GRID_SIZE);
        apple = new Point(x, y);

        // Ensure the apple doesn't spawn on the snake
        while (snake.contains(apple)) {
            x = rand.nextInt(GRID_SIZE);
            y = rand.nextInt(GRID_SIZE);
            apple.setLocation(x, y);
        }
    }

    public void move() {
        Point head = snake.getFirst();
        Point newHead = new Point(head);

        switch (direction) {
            case UP:
                newHead.translate(0, -1);
                break;
            case DOWN:
                newHead.translate(0, 1);
                break;
            case LEFT:
                newHead.translate(-1, 0);
                break;
            case RIGHT:
                newHead.translate(1, 0);
                break;
        }

        if (newHead.equals(apple)) {
            snake.addFirst(newHead);
            spawnApple();
        } else {
            snake.addFirst(newHead);
            snake.removeLast();
        }

        checkCollision();
        repaint();
    }

    public void checkCollision() {
        Point head = snake.getFirst();

        // Check collision with walls
        if (head.x < 0 || head.x >= GRID_SIZE || head.y < 0 || head.y >= GRID_SIZE) {
            gameOver();
        }

        // Check collision with itself
        if (snake.size() > 1 && snake.subList(1, snake.size()).contains(head)) {
            gameOver();
        }
    }

    public void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
   public void paint(Graphics g) {
    super.paint(g);

    // Establecer el color de fondo a negro
    getContentPane().setBackground(Color.BLACK);
    
    // Dibujar la serpiente
    g.setColor(Color.GREEN);
    for (Point point : snake) {
        g.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    // Dibujar la manzana
    g.setColor(Color.RED);
    g.fillRect(apple.x * TILE_SIZE, apple.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
