package filters.point

import image.Image
import pixel.GrayscalePixel
import filters.ImageFilter

/**
 * Filters grayscale image with a point operation
 */
trait GrayscaleImagePointFilter extends ImageFilter[GrayscalePixel] {
  /**
   * Filters the image through the specific point operation
   * @param toFilter image to filter
   * @return filtered image
   */
  override def filter(toFilter: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    val newPixels: Array[Array[GrayscalePixel]] = Array.ofDim[GrayscalePixel](toFilter.getWidth, toFilter.getHeight)

    for (x <- 0 until toFilter.getWidth) {
      for (y <- 0 until toFilter.getHeight) {
        newPixels(x)(y) = pointOperation(toFilter.getPixel(x, y))
      }
    }

    new Image[GrayscalePixel](newPixels.map(row => row.toVector).toVector)
  }

  /**
   * Operation to be applied to each pixel individually
   * @param point the pixel to filter
   * @return filtered pixel
   */
  def pointOperation(point: GrayscalePixel): GrayscalePixel
}
