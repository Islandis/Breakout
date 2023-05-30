import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.*;
import javax.swing.JFrame;
import java.awt.Font;

public class Brick extends JFrame {
    Random rand = new Random();
    private static int n = (int) (Math.random() * 8) + 2;

    private static int[] WIDTH = { 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70 , 70};
    private static int[] HEIGHT = { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 ,30 };

    /*int[] x = { 1000, 700, 150, 450, 570, 1530, 1280, 766, 100, 1150, 300, 250 };
    int[] y = { 150, 250, 175, 200, 117, 30, 380, 120, 330, 295, 50, 390 };*/
    /*int[] x= {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300};
    int[] y= {500,435,370,305,240,185,120,185,240,305,370,435,500};*/
    int[] x= {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300};
    int[] y= {500,450,400,350,300,250,200,250,300,350,400,450,500};
    

    int[] lifebar = { 3, 3, 3, 3, 3, 3, 3,3 , 3, 3, 3, 3 , 3 };

    private Window window;

    public Rectangle getBounds(int n) // ��^Rectangle���A���j�Y
    {
        return new Rectangle(x[n], y[n], WIDTH[n], HEIGHT[n]);

    }
    public Rectangle getBoundsr(int n) // ��^Rectangle���A���j�Y
    {
        return new Rectangle(x[n], y[n]+30, WIDTH[n], HEIGHT[n]);

    }
    

    public Brick(Window w) { // �غc�l
        this.window = w;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        for (int i = 0; i < 13; i++) {
            g.drawString(String.valueOf(lifebar[i]), x[i], y[i]);
        }
        for (int i = 0; i < 13; i++) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x[i], y[i], WIDTH[i], HEIGHT[i]);
        }

    }

    void HIT(int n) {
        if (lifebar[n] == 1) {
            WIDTH[n] = 0;
            HEIGHT[n] = 0;
            x[n] = 0;
            y[n] = 0;

        } else {
            lifebar[n]--;
        }

    }

    public int getButtonY(int n) // ��^brick�����Ҧb�������u
    {
        return y[n] + 30;
    }
    public int getButtonYr(int n) // ��^brick�����Ҧb�������u
    {
        return y[n] - 30;
    }

    public int getTopY(int n) // ��^brick�����Ҧb�������u
    {
        return y[n];
    }
    
    public int getRightX(int n) // ��^brick����Ҧb�������u
    {
        return x[n];
    }
    public int getLeftX(int n) // ��^brick�k��Ҧb�������u
    {
        return x[n]+100;
    }
}