package filters.point

import pixel.GrayscalePixel

/**
 * A filter that inverts the intensity of grayscale pixels
 * @param white The maximum intensity value (typically 255)
 */
class GrayscaleImageInvertFilter(white: Int) extends GrayscaleImagePointFilter {
  /**
   * Inverts the intensity of a given grayscale pixel
   * @param pixel the grayscale pixel to be processed
   * @return the inverted grayscale pixel
   */
  def pointOperation(pixel: GrayscalePixel): GrayscalePixel = {
    val intensity: Int = math.max(0, math.min(255, white - pixel.getPixel))
    GrayscalePixel(intensity)
  }
}
