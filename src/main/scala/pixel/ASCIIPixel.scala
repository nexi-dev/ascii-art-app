package pixel

/**
 * Class representing the ascii pixel
 * @param value the ascii value
 */
case class ASCIIPixel(value: Char) extends Pixel {
  /**
   * Gets the value of the pixel
   * @return the ascii value in the pixel
   */
  def getPixel: Char = value
}
