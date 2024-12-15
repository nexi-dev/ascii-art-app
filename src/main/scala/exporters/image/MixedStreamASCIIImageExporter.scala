package exporters.image

import image.Image
import pixel.ASCIIPixel

/**
 * Exports ascii pixel image into multiple streams
 * @param exporters sequence of exporters to use
 */
class MixedStreamASCIIImageExporter(exporters: Seq[StreamASCIIImageExporter]) extends ImageExporter[ASCIIPixel] {
  /**
   * Exports the image to all streams in exporters
   * @param toExport ascii pixel image to export
   */
  override def output(toExport: Image[ASCIIPixel]): Unit = {
    exporters.foreach{ exporter =>
      exporter.output(toExport)
    }
  }
}
