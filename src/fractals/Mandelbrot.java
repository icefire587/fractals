package fractals;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * <p>Title: The fractals class</p>
 * <p>Description:  </p>
 *
 * @author  Gang Liu N00914466
 *
 */
public class Mandelbrot extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int maxIteration;
    private int bound = 2; // 2
    private double scale; // scale
    private int width; // width
    private int height; // height
    BufferedImage buffImage;


    /**
     * <p>Name: Mandelbrot</p>
     * <p>Description: default constructor
     */
    public Mandelbrot(){
        super("Mandelbrot Set"); // title
        width = 800; // width
        height = 800; // height
        scale = 200.0; // scale
        maxIteration = 100; // max time of iteration
        buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setBounds(0, 0 ,width, height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * <p>Method Name: escape</p>
     * <p>Description: </p>
     * @param double rec hold real parts of a complex number, double imc hold imaginary part
     * @return int,
     */
    public int escape(double rec, double imc) {
        double rez = rec;
        double imz = imc;
        double tempRez, tempImz;
        int iter = 0;

        for (int i = 0; i < maxIteration; ++i) {

            tempRez = (rez * rez) - (imz * imz) + rec; //temp real part
            tempImz = 2 * rez * imz + imc; //temp imaginary part
            rez = tempRez;
            imz = tempImz;

            iter++;
            if ( rez * rez + imz * imz > (bound * bound)) {
                return iter;
            }
        }
        return iter;
    }

    /**
     * <p>Method Name: paint</p>
     * <p>Description: draw the picture</p>
     * @param Graphics g,
     */
    public void paint(Graphics g) {
        for (int row = 0; row < height; ++row) { // loop all pixels, height
            for(int col = 0; col < width; ++col) { // width
                double zRe = (col - width/2.0)/ scale; //col to real part
                double zIm = (row - height/2.0)/ scale; //row to imaginary part
                int iteration = escape(zRe, zIm);

                if ( iteration == maxIteration) {
                    buffImage.setRGB(col, row, 0x000000);
                } else {
                    buffImage.setRGB(col, row, 0xFFFFFF);
                }

            }
        }



        g.drawImage(buffImage, 0, 0, null);

    }

}