import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Racquet extends JPanel
{
    int x = 300;
    int xa = 0;
    private static final int y = 800; // �s�W�T�Ӳy���ݩʪ�final�ܼ�,�]���w�g�T�w�U�ӤF���|�A��
    private static final int WIDTH = 120;
    private static final int HEIGHT = 30;

    private Window window;
    
    public Rectangle getBounds() // ��^Rectangle���A���y��
    {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public Racquet(Window w) { // �غc�l
        this.window = w;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void KeyPressed(KeyEvent e) {  // ��L���U�ɡA����
        if (e.getKeyCode() == KeyEvent.VK_LEFT) // ��������
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) // ���k����
            xa = 2;
    }
    
    public void KeyReleased(KeyEvent e) { // ��L��}�ɡA������
        xa = 0;
    }

    public void moveRacquet() {
    	if (x + xa < window.getWidth() - 120 && x + xa > 0) // ���ʮy�� �p��k����� �B �j�������
    		x += xa;
    }
    public int getTopY() // ��^�y��Ҧb�������u
    {
        return y;
    }
    
}