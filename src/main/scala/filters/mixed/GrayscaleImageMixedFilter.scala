package filters.mixed

import image.Image
import pixel.GrayscalePixel
import filters.ImageFilter

/**
 * Filter that applies several filters in a row
 * @param filters sequence of filter to apply
 */
class GrayscaleImageMixedFilter(filters: Seq[ImageFilter[GrayscalePixel]]) extends ImageFilter[GrayscalePixel] {
  /**
   * Applies all the filters given from first to last
   * @param toFilter image to process and filter
   * @return filtered image
   */
  override def filter(toFilter: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    filters.foldLeft(toFilter) { (currentImage, filter) => filter.filter(currentImage) }
  }
}
