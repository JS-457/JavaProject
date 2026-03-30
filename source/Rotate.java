
public class Rotate extends Converter {

    @Override
    protected void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int outWidth = outputImage.getWidth();
        int outHeight = outputImage.getHeight();

        // Rotating the image to 90 degrees clockwise
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = inputImage.getRGB(x, y);

                // Rotates the X and Y coordinates by 90 degrees
                int rotX = height - 1 - y;
                int rotY = x;

                // Initially, the image would crop a lot if it wasn't a perfect square
                // So these centerX/Y variables are used to truly  adjust the rotation of the image's width and height.
                int centerX = rotX + (outWidth - height) / 2;
                int centerY = rotY + (outHeight - width) / 2;

                // Output image now has the new X/Y coordinates and pixel values.
                if (centerX >= 0 && centerX < outWidth && centerY >= 0 && centerY < outHeight) {
                    outputImage.setRGB(centerX, centerY, pixel);
                }
            }
        }
    }
}
