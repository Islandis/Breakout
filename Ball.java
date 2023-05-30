
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends JPanel {
    private static final int ballsize = 30;
    int x = 300; // �p�y���w�]��m
    int y = 200;
    int incx = 1; // �o�O�p�y�n���ʪ����
    int incy = 1;
    private Window window;

    public Rectangle getBounds() // ��^Rectangle���A���p�y
    {
        return new Rectangle(x, y, ballsize, ballsize);
    }
    public Rectangle getBoundsr() // ��^Rectangle���A���p�y
    {
        return new Rectangle(x, y+30, ballsize, ballsize);
    }

    public Ball(Window w) { // �غc�l
        this.window = w;
    }

    private boolean collision() {
        return window.racquet.getBounds().intersects(getBounds()); // ��intersects��k�P�_�p�y�O�_�M�y��ۥ�
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
        return -1; // ��intersects��k�P�_�p�y�O�_�Mbrick�ۥ�
    }

    void moveBall() // �o�Ӥ�k�N�O���_��s�p�y����m
    {
        int number_of_brick = BrickCollision();
        if (collision()) {
            incy = -1; // ����y�b�����ʤ�V
            y = window.racquet.getTopY() - ballsize; // �o�ӬO�B���y����m

        }
        if (number_of_brick != -1) {
            window.score++;

            if (y < window.brick.getTopY(number_of_brick)
                    && y + incy > window.brick.getTopY(number_of_brick) - ballsize) {
                incy = -1; // ����y�b�����ʤ�V
                y = window.brick.getTopY(number_of_brick) - ballsize; // �V�W�ϼu
            }
           
            
            else if (y < window.brick.getButtonY(number_of_brick) && y + incy < window.brick.getButtonY(number_of_brick)) {
                incy = 1; // ����y�b�����ʤ�V
                y = window.brick.getButtonY(number_of_brick) + ballsize;// �V�U�ϼu
            }
            if (x > window.brick.getRightX(number_of_brick) && x + incx < window.brick.getRightX(number_of_brick)) {
                incx = 1;// �V�k�ϼu
                x = window.brick.getRightX(number_of_brick) + ballsize;
            }
            else if (x < window.brick.getLeftX(number_of_brick)
                    && x + incx > window.brick.getLeftX(number_of_brick) - ballsize) {
                incx = -1;// �V���ϼu
                x = window.brick.getLeftX(number_of_brick) - ballsize;
            }/*123*/
            
            

            window.brick.HIT(number_of_brick);
        }
        if (x + incx > window.getWidth() - ballsize) // �p�G�p�y���ʫ᪺��m�W�X�����d�򪺸�,���ʤ�V�N�O-1; �A�����y���j�p
            incx = -1;
        if (x + incx < 0) // �P�z
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
        g.setColor(Color.darkGray); // �]�w�C��
        g.fillOval(x, y, ballsize, ballsize); // (x�b, y�b, �y���e��, �y������)
    }
}