package exporters.image

import exporters.Exporter
import image.Image
import pixel.Pixel

/**
 * Exports image of some pixels
 * @tparam T the type of pixels
 */
trait ImageExporter[-T <: Pixel] extends Exporter[Image[T]] {
}
