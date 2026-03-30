
public class Sepia extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB argb = new ARGB(inputImage.getRGB(x, y));
                // rbg variables
                int R = argb.red;
                int G = argb.green;
                int B = argb.blue;

                // Adds more greens than blues or reds.
                double sepiaRed = (0.39 * R) + (0.77 * G) + (0.19 * B);
                double sepiaGreen = (0.35 * R) + (0.69 * G) + (0.17 * B);
                double sepiaBlue = (0.27 * R) + (0.53 * G) + (0.13 * B);

                // to prevent rgb values from going above 255
                if (sepiaRed > 255) {
                    sepiaRed = 255;
                }
                if (sepiaGreen > 255) {
                    sepiaGreen = 255;
                }
                if (sepiaBlue > 255) {
                    sepiaBlue = 255;
                }

                ARGB sepiaPixel = new ARGB(argb.alpha, (int) (sepiaRed), (int) (sepiaGreen), (int) (sepiaBlue));
                outputImage.setRGB(x, y, sepiaPixel.toInt());
            }
        }
    }
}
