/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package photoshopa;

/**
 *
 * @author Edyta
 */
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImagingUtility {
        
        public static final int IMAGE_TYPE = BufferedImage.TYPE_3BYTE_BGR;
        
        /**
         * Clone an image by redrawing it
         * @param img
         */
        public static BufferedImage copyImage(BufferedImage img) throws OutOfMemoryError {
                int w = img.getWidth();
                int h = img.getHeight();
                BufferedImage image = new BufferedImage(w, h, IMAGE_TYPE);
                image.createGraphics().drawImage(
                        img, new AffineTransformOp(
                                        new AffineTransform(),
                                        AffineTransformOp.TYPE_NEAREST_NEIGHBOR
                        ), 0, 0
                );
                return image;
        }
        
        /**
         * Get the rgb from image. The {@code col} and {@code row}
         * could be out of border, this function will return nearest
         * color in border
         * @param image
         * @param row
         * @param col
         * @return
         */
        public static int getRGBExtended(BufferedImage image, int row, int col) {
                int width = image.getWidth();
                int height = image.getHeight();
                row = Math.max(0, Math.min(height - 1, row));
                col = Math.max(0, Math.min(width - 1, col));
                return image.getRGB(col, row);
        }
        
        
//      this function is removed because it is too slow
//      try using getRGBExtended() instead
//      public static BufferedImage extendImage(BufferedImage image, int radius) {
//              
//              int width = image.getWidth();
//              int height = image.getHeight();
//              
//              BufferedImage img = new BufferedImage(
//                      width + 2 * radius, height + 2 * radius, IMAGE_TYPE
//              );
//              Graphics2D g2d = img.createGraphics();
//              g2d.drawImage(image, radius, radius, null);
//              
//              // corners
//              int c = image.getRGB(0, 0);
//              for (int row = 0; row < radius; row++) {
//                      for (int col = 0; col < radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              c = image.getRGB(width - 1, 0);
//              for (int row = 0; row < radius; row++) {
//                      for (int col = width + radius; col < width + 2 * radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              c = image.getRGB(0, height - 1);
//              for (int row = height + radius; row < height + 2 * radius; row++) {
//                      for (int col = 0; col < radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              c = image.getRGB(width - 1, height - 1);
//              for (int row = height + radius; row < height + 2 * radius; row++) {
//                      for (int col = width + radius; col < width + 2 * radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              // extend borders
//              for (int row = radius; row < height + radius; row++) {
//                      c = image.getRGB(0, row - radius);
//                      for (int col = 0; col < radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//                      c = image.getRGB(width - 1, row - radius);
//                      for (int col = width + radius; col < width + 2 * radius; col++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              for (int col = radius; col < width + radius; col++) {
//                      c = image.getRGB(col - radius, 0);
//                      for (int row = 0; row < radius; row++) {
//                              img.setRGB(col, row, c);
//                      }
//                      c = image.getRGB(col - radius, height - 1);
//                      for (int row = height + radius; row < height + 2 * radius; row++) {
//                              img.setRGB(col, row, c);
//                      }
//              }
//              
//              return img;
//      }
        
        /**
         * Gets the intensity of a given rgb
         * @param rgb
         * @return intensity in [0, 255]
         */
        public static int getColorIntensity(int rgb) {
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                return (r * 7471 + g * 38470 + b * 19595) >> 16;
        }
        
}

