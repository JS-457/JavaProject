public class Blur extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Start recursion from row 0
        blurRow(inputImage, outputImage, 0, width, height);
    }

    /**
     * Recursively processes the image one row at a time.
     * Each row is fully blurred before moving to the next.
     *
     * @param inputImage  original image to read from
     * @param outputImage output image to write blurred pixels to
     * @param y           current row being processed
     * @param width       total image width
     * @param height      total image height
     */
    private void blurRow(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage,
                         int y, int width, int height) {

        // Base case: all rows done, stop
        if (y >= height) return;

        // Process every pixel in this row
        for (int x = 0; x < width; x++) {
            int totalAlpha = 0, totalRed = 0, totalGreen = 0, totalBlue = 0;
            int count = 0;

            // Average the 3x3 neighbourhood around pixel (x, y)
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    int nx = x + dx;
                    int ny = y + dy;

                    if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                        ARGB neighbour = new ARGB(inputImage.getRGB(nx, ny));
                        totalAlpha += neighbour.alpha;
                        totalRed   += neighbour.red;
                        totalGreen += neighbour.green;
                        totalBlue  += neighbour.blue;
                        count++;
                    }
                }
            }

            ARGB blurred = new ARGB(
                totalAlpha / count,
                totalRed   / count,
                totalGreen / count,
                totalBlue  / count
            );
            outputImage.setRGB(x, y, blurred.toInt());
        }

        // Recurse to the next row
        blurRow(inputImage, outputImage, y + 1, width, height);
    }
}
