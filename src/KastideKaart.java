import java.awt.*;

public class KastideKaart {
    private int kaart[][];
    private int kastiLaius;
    private int kastiPikkus;

    public int[][] getKaart() {
        return kaart;
    }

    public int getKastiLaius() {
        return kastiLaius;
    }

    public int getKastiPikkus() {
        return kastiPikkus;
    }


    //teeb kastide maatriksi
    public KastideKaart(int rida, int veerg) {
        kaart = new int[rida][veerg];
        for (int i= 0; i< kaart.length; i++) {
            for(int j=0; j<kaart[0].length; j++) {
                kaart[i][j] = 1;
            }
        }

        kastiLaius = 540/veerg;
        kastiPikkus = 150/rida;
    }

    //Joonistab kastid taustale
    public void joonista(Graphics2D g) {
        for (int i= 0; i< kaart.length; i++) {
            for (int j = 0; j < kaart[0].length; j++) {
                if (kaart[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * kastiLaius + 80, i * kastiPikkus + 50, kastiLaius, kastiPikkus);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * kastiLaius + 80, i * kastiPikkus + 50, kastiLaius, kastiPikkus);
                }
            }
        }
    }

    //Kas on kast või ei
    public void kastiVäärtus(int väärtus, int rida, int veerg) {
        kaart[rida][veerg] = väärtus;
    }

}
