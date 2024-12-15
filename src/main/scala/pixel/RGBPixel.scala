package pixel

/**
 * Class corresponding to rgb pixels
 * @param value is a tuple or red, green and blue
 */
case class RGBPixel(value: (Int, Int, Int)) extends Pixel {
  private val (red, green, blue) = value

  if (!isValid(red) || !isValid(green) || !isValid(blue)) {
    throw new IllegalArgumentException(s"RGBPixel channels must be 0-255: $value")
  }

  /**
   * Gets the value of the pixel
   * @return tuple of (red, green, blue) integers
   */
  def getPixel: (Int, Int, Int) = value

  /**
   * Checks if given channel is in bounds of 0 - 255
   * @param channel channel to check
   * @return true if in bounds otherwise false
   */
  private def isValid(channel: Int): Boolean = channel >= 0 && channel <= 255
}
