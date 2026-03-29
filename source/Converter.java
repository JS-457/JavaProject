
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class Converter {

    private int imageWidth, imageHeight;

    public void convert(String inputFileName, String outputFileName) throws java.io.IOException {

        // Read the input image file
        File inputFile = new File(inputFileName);
        BufferedImage originalImage = ImageIO.read(inputFile);

        // Gathers the width and height of the original image
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new image with <width> and <height>, and specifies the type of the colour value
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        processImage(originalImage, newImage);

        // Save the output image to a file
        File outputFile = new File(outputFileName);
        ImageIO.write(newImage, "PNG", outputFile);
    }

    // Read the documentation of the BufferedImage class at
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/image/BufferedImage.html
    // Specifically, pay attention to the following methods:
    // - getRGB(int x, int y)
    // - setRGB(int x, int y, int rgb)
    // - getWidth()
    // - getHeight()
    // Dedicated for subclasses to process the image
    protected abstract void processImage(java.awt.image.BufferedImage inputImage, java.awt.image.BufferedImage outputImage);

    public int getWidth() {
        return imageWidth;
    }

    public int getHeight() {
        return imageHeight;
    }

    // Read the documentation of the ImageIO class at
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/imageio/package-summary.html
    // Specifically, pay attention to the following methods:
    // - read(File input)
    // - write(RenderedImage im, String formatName, File output)
}
