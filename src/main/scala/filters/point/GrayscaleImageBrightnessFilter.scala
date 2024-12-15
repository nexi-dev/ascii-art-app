package filters.point

import pixel.GrayscalePixel

/**
 * A filter that adjusts the brightness of grayscale pixels
 * @param brightness the amount to adjust the brightness (can be positive or negative)
 */
class GrayscaleImageBrightnessFilter(brightness: Int) extends GrayscaleImagePointFilter {
  /**
   * Adjusts the brightness of a given grayscale pixel by adding brightness to it
   * @param pixel the grayscale pixel to be processed
   * @return the brightness-adjusted grayscale pixel
   */
  override def pointOperation(pixel: GrayscalePixel): GrayscalePixel = {
    val intensity: Int = math.max(0, math.min(255, pixel.getPixel + brightness))
    GrayscalePixel(intensity)
  }
}
