
public class Emboss extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // For embossment
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get current pixel
                int current = inputImage.getRGB(x, y);
                ARGB currentPixel = new ARGB(current);

                // Get next neighbour pixel on the top-left
                ARGB neighborPixel;
                if (x > 0 && y > 0) {
                    int next = inputImage.getRGB(x - 1, y - 1);
                    neighborPixel = new ARGB(next);
                } else {
                    // For edge pixels, it gives black or same pixel
                    neighborPixel = new ARGB(0, 128, 128, 128);
                }

                // Computes the embossed color for each RB color
                int embR = currentPixel.red - neighborPixel.red + 128;
                int embG = currentPixel.green - neighborPixel.green + 128;
                int embB = currentPixel.blue - neighborPixel.blue + 128;

                // Makes sure that each value is in between 0 and 255, inclusive
                if (embR > 255) {
                    embR = 255;
                }
                if (embR < 0) {
                    embR = 0;
                }
                if (embG > 255) {
                    embG = 255;
                }
                if (embG < 0) {
                    embG = 0;
                }
                if (embB > 255) {
                    embB = 255;
                }
                if (embB < 0) {
                    embB = 0;
                }

                int A = currentPixel.alpha;

                ARGB newPixel = new ARGB(A, embR, embG, embB);
                outputImage.setRGB(x, y, newPixel.toInt());

            }
        }
    }
}
