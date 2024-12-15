package importers.file

import java.io.File
import javax.imageio.ImageIO
import image.Image
import importers.ImageImporter
import pixel.RGBPixel

/**
 * Imports RGB pixel image from file
 * @param source the file to import from
 */
class ImageFileImporter(val source: File) extends ImageImporter {
  /**
   * Imports the RGB pixel image
   * @return the imported image
   */
  override def input(): Image[RGBPixel] = {
    if (!source.exists() || !source.isFile) {
      throw new IllegalArgumentException(s"Invalid file path: $source")
    }

    val image = ImageIO.read(source)
    if (image == null) {
      throw new IllegalArgumentException(s"Unsupported or corrupted image file: $source")
    }

    val width = image.getWidth
    val height = image.getHeight
    val pixels = Array.ofDim[RGBPixel](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val rgb = image.getRGB(x, y)
        val red = (rgb >> 16) & 0xFF
        val green = (rgb >> 8) & 0xFF
        val blue = rgb & 0xFF

        pixels(x)(y) = RGBPixel((red, green, blue))
      }
    }

    new Image[RGBPixel](pixels.map(row => row.toVector).toVector)
  }

  /**
   * Checks the extension
   * @param file the file to be checked
   * @param extension the extension that is correct
   * @return true if the extension matches in file, otherwise false
   */
  protected def isValidExtension(file: File, extension: String): Boolean = {
    val name = file.getName.toLowerCase
    name.endsWith(s".$extension")
  }
}
