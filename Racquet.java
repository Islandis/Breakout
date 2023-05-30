import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Racquet extends JPanel
{
    int x = 300;
    int xa = 0;
    private static final int y = 800; // 新增三個球拍屬性的final變數,因為已經確定下來了不會再改
    private static final int WIDTH = 120;
    private static final int HEIGHT = 30;

    private Window window;
    
    public Rectangle getBounds() // 返回Rectangle型態的球拍
    {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public Racquet(Window w) { // 建構子
        this.window = w;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void KeyPressed(KeyEvent e) {  // 鍵盤按下時，移動
        if (e.getKeyCode() == KeyEvent.VK_LEFT) // 往左移動
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) // 往右移動
            xa = 2;
    }
    
    public void KeyReleased(KeyEvent e) { // 鍵盤放開時，不移動
        xa = 0;
    }

    public void moveRacquet() {
    	if (x + xa < window.getWidth() - 120 && x + xa > 0) // 移動座標 小於右邊邊界 且 大於左邊邊界
    		x += xa;
    }
    public int getTopY() // 返回球拍所在的水平線
    {
        return y;
    }
    
}