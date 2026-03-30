public class Blur extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Start recursion from pixel (0, 0)
        blurPixel(inputImage, outputImage, 0, 0, width, height);
    }

    /**
     * Recursively processes every pixel in the image row by row.
     * When we reach the end of a row, we move to the next row.
     * When we reach the end of all rows, we stop.
     *
     * @param inputImage  the original image to read pixels from
     * @param outputImage the output image to write blurred pixels to
     * @param x           current column
     * @param y           current row
     * @param width       total image width
     * @param height      total image height
     */
    private void blurPixel(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage,
                           int x, int y, int width, int height) {

        // Base case: we've processed all rows, stop
        if (y >= height) return;

        // Move to next row when we reach the end of current row
        if (x >= width) {
            blurPixel(inputImage, outputImage, 0, y + 1, width, height);
            return;
        }

        // --- Blur logic for pixel (x, y) ---
        // Average the colour of all surrounding pixels (3x3 neighbourhood)
        int totalAlpha = 0, totalRed = 0, totalGreen = 0, totalBlue = 0;
        int count = 0;

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int nx = x + dx;
                int ny = y + dy;

                // Only include neighbours that are inside the image
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

        // Write the averaged colour to the output image
        ARGB blurred = new ARGB(
            totalAlpha / count,
            totalRed   / count,
            totalGreen / count,
            totalBlue  / count
        );
        outputImage.setRGB(x, y, blurred.toInt());

        // Recurse to the next pixel in this row
        blurPixel(inputImage, outputImage, x + 1, y, width, height);
    }
}
```

---

**How the recursion works:** // how the recursion works
```
blurPixel(0,0) → blurPixel(1,0) → blurPixel(2,0) → ...
→ blurPixel(width,0) → blurPixel(0,1) → blurPixel(1,1) → ...
→ blurPixel(0, height) → STOP
