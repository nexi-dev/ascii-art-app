package pixel

/**
 * Class representing the grayscale pixel
 * @param value of intensity of the pixel
 */
case class GrayscalePixel(value: Int) extends Pixel {
  if (!isValid(value)) {
    throw new IllegalArgumentException(s"RGBPixel channels must be 0-255: $value")
  }

  /**
   * Gets the intensity of the pixel
   * @return the intensity of pixel
   */
  def getPixel: Int = value

  /**
   * Checks if intensity is in bound of 0 - 255
   * @param channel the intesnity of controlled channel
   * @return true if in bounds otherwise false
   */
  private def isValid(channel: Int): Boolean = channel >= 0 && channel <= 255
}
