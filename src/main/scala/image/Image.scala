package image

import pixel.Pixel

/**
 * Represents the image
 * @param pixels 2d vector of pixels representing the image
 * @tparam T the type of pixel the image is made of
 */
case class Image[+T <: Pixel](pixels: Vector[Vector[T]]) {
  if (pixels.isEmpty || pixels.exists(_.isEmpty)) {
    throw new IllegalArgumentException("Image cannot be empty")
  }

  if (!pixels.forall(_.length == this.getHeight)) {
    throw new IllegalArgumentException("All columns in the image must have the same length")
  }

  /**
   * Gets width of the image
   * @return image width
   */
  def getWidth: Int = pixels.length

  /**
   * Gets height of the image
   * @return image height
   */
  def getHeight: Int = if (pixels.isEmpty) 0 else pixels.head.length

  /**
   * Gets a pixel in a specific space in the image
   * @param x index of column
   * @param y index of row
   * @return the pixel at position x, y
   */
  def getPixel(x: Int, y: Int): T = {
    if (x < 0 || y < 0 || x >= getWidth || y >= getHeight) {
      throw new IllegalArgumentException(s"Coordinates are out of bounds: ($x, $y)")
    }
    pixels(x)(y)
  }
}