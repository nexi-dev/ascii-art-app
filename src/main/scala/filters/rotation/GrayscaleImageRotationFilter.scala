package filters.rotation

import image.Image
import pixel.GrayscalePixel
import filters.ImageFilter

/**
 * Filter rotates a grayscale image in the multiples of 90 degrees
 * @param degrees the rotation in degrees, negative is anticlockwise, positive is clockwise
 */
class GrayscaleImageRotationFilter(degrees: Int) extends ImageFilter[GrayscalePixel] {
  if (degrees % 90 != 0) {
    throw new IllegalArgumentException(s"Rotation filter allows 90 degrees multiples rotation: $degrees")
  }

  /**
   * Rotates the image for degrees
   * @param toFilter the image to be rotated
   * @return rotated image
   */
  override def filter(toFilter: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    val numberOfRotations = (((degrees / 90) % 4) + 4) % 4

    var rotatedImage = toFilter
    for (_ <- 0 until numberOfRotations)
      rotatedImage = rotate90(rotatedImage)

    rotatedImage
  }

  /**
   * Does exactly 90 degrees rotation
   * @param toRotate the image to rotate
   * @return image rotated by 90 degrees
   */
  private def rotate90(toRotate: Image[GrayscalePixel]): Image[GrayscalePixel]= {
    val newPixels = Array.ofDim[GrayscalePixel](toRotate.getHeight, toRotate.getWidth)

    for (x <- 0 until toRotate.getWidth)
      for (y <- 0 until toRotate.getHeight)
        newPixels(y)(toRotate.getWidth - 1 - x) = toRotate.getPixel(x, y)

    new Image[GrayscalePixel](newPixels.map(_.toVector).toVector)
  }
}
