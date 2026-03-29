
public class Grayscale extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        // convert the image to particular ARGB value
    }

    public void setRGB(int x, int y, int rgb) {
        x = getWidth();
        y = getHeight();
        //rgb = toInt();
    }
}
