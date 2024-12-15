package parsers.console

import importers.ImageImporter
import importers.file.{PNGImageFileImporter, JPGImageFileImporter, GIFImageFileImporter}
import importers.random.ImageRandomImporter
import java.io.File
import scala.util.Random

/**
 * Parses console arguments regarding import and builds the desired importer
 * @param fileImportKeyword keyword corresponding to import from file
 * @param randomImportKeyword keyword corresponding to import from random
 */
class ConsoleImportParser(fileImportKeyword: String, randomImportKeyword: String) extends ConsolePartialParser[ImageImporter] {
  /**
   * Parses args and gets desired importer based on them
   * @param args array of strings from std input
   * @return the image importer
   */
  override def parse(args: Array[String]): ImageImporter = {
    val matches = findMatches(args, Array(fileImportKeyword, randomImportKeyword), 1, 1)
    val (importType, index) = matches.head

    importType match {
      case `fileImportKeyword` =>
        if (index + 1 >= args.length) {
          throw new IllegalArgumentException(s"Missing path for import: $fileImportKeyword")
        }

        val filePath = args(index + 1)
        val file = new File(filePath)

        filePath.toLowerCase match {
          case path if path.endsWith(".png") => new PNGImageFileImporter(file)
          case path if path.endsWith(".jpg") || path.endsWith(".jpeg") => new JPGImageFileImporter(file)
          case path if path.endsWith(".gif") => new GIFImageFileImporter(file)
          case _ => throw new IllegalArgumentException(s"Unsupported file format for import: $filePath")
        }
      case `randomImportKeyword` =>
        new ImageRandomImporter(Random())
    }
  }
}
