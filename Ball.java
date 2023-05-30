
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends JPanel {
    private static final int ballsize = 30;
    int x = 300; // 小球的預設位置
    int y = 200;
    int incx = 1; // 這是小球要移動的單位
    int incy = 1;
    private Window window;

    public Rectangle getBounds() // 返回Rectangle型態的小球
    {
        return new Rectangle(x, y, ballsize, ballsize);
    }
    public Rectangle getBoundsr() // 返回Rectangle型態的小球
    {
        return new Rectangle(x, y+30, ballsize, ballsize);
    }

    public Ball(Window w) { // 建構子
        this.window = w;
    }

    private boolean collision() {
        return window.racquet.getBounds().intersects(getBounds()); // 用intersects方法判斷小球是否和球拍相交
    }

    private int BrickCollision() {
        for (int i = 0; i < 13; i++) {
            if (window.brick.getBounds(i).intersects(getBounds())) {
                return i;
            }
            if (window.brick.getBoundsr(i).intersects(getBoundsr())) {
                return i;
            }
        }
        return -1; // 用intersects方法判斷小球是否和brick相交
    }

    void moveBall() // 這個方法就是不斷更新小球的位置
    {
        int number_of_brick = BrickCollision();
        if (collision()) {
            incy = -1; // 改變y軸的移動方向
            y = window.racquet.getTopY() - ballsize; // 這個是矯正球的位置

        }
        if (number_of_brick != -1) {
            window.score++;

            if (y < window.brick.getTopY(number_of_brick)
                    && y + incy > window.brick.getTopY(number_of_brick) - ballsize) {
                incy = -1; // 改變y軸的移動方向
                y = window.brick.getTopY(number_of_brick) - ballsize; // 向上反彈
            }
           
            
            else if (y < window.brick.getButtonY(number_of_brick) && y + incy < window.brick.getButtonY(number_of_brick)) {
                incy = 1; // 改變y軸的移動方向
                y = window.brick.getButtonY(number_of_brick) + ballsize;// 向下反彈
            }
            if (x > window.brick.getRightX(number_of_brick) && x + incx < window.brick.getRightX(number_of_brick)) {
                incx = 1;// 向右反彈
                x = window.brick.getRightX(number_of_brick) + ballsize;
            }
            else if (x < window.brick.getLeftX(number_of_brick)
                    && x + incx > window.brick.getLeftX(number_of_brick) - ballsize) {
                incx = -1;// 向左反彈
                x = window.brick.getLeftX(number_of_brick) - ballsize;
            }/*123*/
            
            

            window.brick.HIT(number_of_brick);
        }
        if (x + incx > window.getWidth() - ballsize) // 如果小球移動後的位置超出視窗範圍的話,移動方向就是-1; 再扣掉球的大小
            incx = -1;
        if (x + incx < 0) // 同理
            incx = 1;
        if (y + incy > window.getHeight() - ballsize)
            incy = -1;
        if (y + incy < 0)
            incy = 1;
        x += incx;
        y += incy;

    }

    boolean gameover() {
        boolean gameover = false;
        if (y > window.racquet.getTopY()) {
            gameover = true;
        }
        return gameover;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.darkGray); // 設定顏色
        g.fillOval(x, y, ballsize, ballsize); // (x軸, y軸, 球的寬度, 球的高度)
    }
}