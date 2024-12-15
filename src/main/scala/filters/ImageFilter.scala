package filters

import image.Image
import pixel.Pixel

/**
 * Filters an image of pixels
 * @tparam T type of pixels
 */
trait ImageFilter[T <: Pixel] extends Filter[Image[T]] {
}
