
public class Rotate extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Rotating the image to 90 degrees clockwise
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = inputImage.getRGB(x, y);

                // Rotates the X and Y coordinates by 90 degrees
                int rotX = height - 1 - x;
                int rotY = x;

                // Output image now has the new X/Y coordinates and pixel values.
                outputImage.setRGB(rotX, rotY, pixel);
            }
        }
    }
}
