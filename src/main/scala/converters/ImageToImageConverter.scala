package converters

import pixel.Pixel
import image.Image

/**
 * Converts image of S to image of T
 * @tparam S source is a type of pixels in the source image
 * @tparam T target is a type of pixels in the target image
 */
trait ImageToImageConverter[S <: Pixel, T <: Pixel] extends Converter[Image[S], Image[T]] {
  /**
   * Converts the actual image of S to the image of T
   * @param source image of S to be converted
   * @return the converted source to target
   */
  override def convert(source: Image[S]): Image[T] = {
    val width = source.getWidth
    val height = source.getHeight

    val pixels: Vector[Vector[T]] =
      (0 until width).map { x =>
        (0 until height).map { y =>
          convertPixel(source.getPixel(x, y))
        }.toVector
      }.toVector
    new Image[T](pixels)
  }

  /**
   * Converts only one pixel, is supposed to be implemented in children
   * @param pixel to be converted
   * @return the pixel converted to T
   */
  protected def convertPixel(pixel: S): T
}
