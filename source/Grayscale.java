
public class Grayscale extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB argb = new ARGB(inputImage.getRGB(x, y));

                // Average the three channels to produce a neutral gray
                int gray = (argb.red + argb.green + argb.blue) / 3;

                ARGB grayPixel = new ARGB(argb.alpha, gray, gray, gray);
                outputImage.setRGB(x, y, grayPixel.toInt());
            }
        }
    }
}
