
public class Invert extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB argb = new ARGB(inputImage.getRGB(x, y));

                // Inverts the colors
                int R = 255 - argb.red;
                int G = 255 - argb.green;
                int B = 255 - argb.blue;

                ARGB sepiaPixel = new ARGB(argb.alpha, R, G, B);
                outputImage.setRGB(x, y, sepiaPixel.toInt());
            }
        }
    }
}
