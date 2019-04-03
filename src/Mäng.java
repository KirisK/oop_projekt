import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Mäng extends JPanel implements KeyListener, ActionListener {
    private boolean mängib = false;
    private int skoor = 0;

    private int kokkuRuute = 24;

    private Timer aeg;
    private int viivitus = 8;

    private int mängijaX = 310;

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    private int pallX = randInt(10,690);
    private int pallY = 350;

    private int pallXSuund = -1;
    private int pallYSuund = -2;

    private KastideKaart kaart;

    public Mäng() {
        kaart = new KastideKaart(3, 8);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        aeg = new Timer(viivitus, this);
        aeg.start();
    }

    public void paint(Graphics g) {
        // taust
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //JFrame f= new JFrame();
        //try{
          //  f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Karina Kiris\\Desktop\\oop_projekt2\\77024.jpg")))));

        //}catch (IOException e)
        //{
            //System.out.println("Image doesn't exist");

        //}
        //f.setBounds(10, 10, 700, 600);
        //f.setVisible(true);




        //Kaart
        kaart.joonista((Graphics2D)g);

        //ääred
        g.setColor(Color.orange);
        g.fillRect(0, 0, 4, 592);
        g.fillRect(0, 0, 692, 4);
        g.fillRect(683, 0, 4, 592);

        //skoor
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+skoor, 590, 30);

        //alus
        g.setColor(Color.cyan);
        g.fillRect(mängijaX, 550, 100, 9);

        //pall
        g.setColor(Color.magenta);
        g.fillOval(pallX, pallY, 20, 20);

        if(kokkuRuute <= 0) {
            mängib = false;
            pallXSuund = 0;
            pallYSuund = 0;
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Võitsid!! Sinu skoor: "+skoor, 280, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Vajuta ENTER, et uuesti mängida ", 230, 350);
        }

        if(pallY > 570) {
            mängib = false;
            pallXSuund = 0;
            pallYSuund = 0;
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Mäng on läbi! Sinu skoor: "+skoor, 180, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Vajuta ENTER, et uuesti mängida ", 230, 350);
        }

        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        aeg.start();
        if(mängib) {
            if(new Rectangle(pallX, pallY, 20, 20).intersects(new Rectangle(mängijaX, 550, 100, 8))) {
                pallYSuund = -pallYSuund;
            }

            A: for(int i = 0; i<kaart.getKaart().length; i++) {
                for(int j = 0; j< kaart.getKaart()[0].length; j++) {
                    if(kaart.getKaart()[i][j] > 0) {
                        int kastX = j*kaart.getKastiLaius() + 80;
                        int kastY = i *kaart.getKastiPikkus()+50;
                        int kastiLaius = kaart.getKastiLaius();
                        int kastiPikkus = kaart.getKastiPikkus();

                        Rectangle ristkülik = new Rectangle(kastX, kastY, kastiLaius, kastiPikkus);
                        Rectangle palliÜmbrus = new Rectangle(pallX, pallY, 20, 20);
                        Rectangle kastiRistkülik = ristkülik;

                        if(palliÜmbrus.intersects(kastiRistkülik)) {
                            kaart.kastiVäärtus(0, i, j);
                            kokkuRuute--;
                            skoor += 5;

                            if(pallX +19<= kastiRistkülik.x || pallX +1 >= kastiRistkülik.x + kastiRistkülik.width) {
                                pallXSuund = -pallXSuund;
                            }
                            else {
                                pallYSuund = -pallYSuund;
                            }
                            break A;
                        }
                    }
                }
            }

            pallX += pallXSuund;
            pallY += pallYSuund;
            if(pallX < 0) {
                pallXSuund = -pallXSuund;
            }
            if(pallY < 0) {
                pallYSuund = -pallYSuund;
            }
            if(pallX > 665) {
                pallXSuund = -pallXSuund;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(mängijaX >=600) {
                mängijaX = 600;
            }
            else {
                liiguParemale();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(mängijaX < 10) {
                mängijaX = 10;
            }
            else {
                liiguVasakule();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!mängib) {
                mängib = true;
                pallX = 120;
                pallY = 350;
                pallXSuund = -1;
                pallYSuund = -2;
                mängijaX = 310;
                skoor = 0;
                kokkuRuute = 24;
                kaart = new KastideKaart(3, 8);

                repaint();
            }
        }
    }

    public void liiguParemale() {
        mängib = true;
        mängijaX+=20;
    }

    public void liiguVasakule() {
        mängib = true;
        mängijaX-=20;
    }

}

