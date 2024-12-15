package exporters.image

import java.io.OutputStream
import pixel.ASCIIPixel
import image.Image

/**
 * Exports ascii art image into output stream
 * @param outputStream stream to export to
 */
class StreamASCIIImageExporter(outputStream: OutputStream) extends ImageExporter[ASCIIPixel] {
  private var closed = false

  /**
   * Exports the ascii pixel image into the output stream
   * @param toExport the image to export
   */
  override def output(toExport: Image[ASCIIPixel]): Unit = {
    if (closed)
      throw new Exception("The stream has been already closed")

    val stringBuilder = new StringBuilder()

    for (y <- 0 until toExport.getHeight) {
      for (x <- 0 until toExport.getWidth) {
        stringBuilder.append(toExport.getPixel(x, y).getPixel)
      }
      stringBuilder.append("\n")
    }

    outputStream.write(stringBuilder.toString().getBytes("UTF-8"))
    outputStream.flush()
  }

  /**
   * Closes the output stream
   */
  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }
}
