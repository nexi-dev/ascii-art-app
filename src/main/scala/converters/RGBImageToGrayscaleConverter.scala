package converters

import pixel.{RGBPixel, GrayscalePixel}

/**
 * Convert RGB image to grayscale image
 */
class RGBImageToGrayscaleConverter extends ImageToImageConverter[RGBPixel, GrayscalePixel] {
  /**
   * Converts one RGB pixel into grayscale pixel based on the following formula
   * greyscale value = ((0.3 * Red) + (0.59 * Green) + (0.11 * Blue))
   * @param pixel to be converted
   * @return the pixel converted to T
   */
  override protected def convertPixel(pixel: RGBPixel): GrayscalePixel = {
    val (r, g, b) = pixel.getPixel
    GrayscalePixel(((30 * r) + (59 * g) + (11 * b)) / 100)
  }
}
