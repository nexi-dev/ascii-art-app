package parsers.console

import exporters.image.*
import pixel.ASCIIPixel

import java.io.File

/**
 * Parses console arguments regarding exports and builds the desired export
 * @param fileExportKeyword keyword corresponding to export to file
 * @param consoleExportKeyword keyword corresponding to export to console
 */
class ConsoleExportParser(fileExportKeyword: String, consoleExportKeyword: String) extends ConsolePartialParser[ImageExporter[ASCIIPixel]] {
  /**
   * Parses console arguments regarding exports and builds the desired export
   * @param args the array of string from std input
   * @return the exporter
   */
  override def parse(args: Array[String]): ImageExporter[ASCIIPixel] = {
    val matches = findMatches(args, Array(fileExportKeyword, consoleExportKeyword), 1, Int.MaxValue)
    val exporters: Seq[StreamASCIIImageExporter] = matches.map { case (exportType, index) =>
      exportType match {
        case `fileExportKeyword` =>
          if (index + 1 >= args.length) {
            throw new IllegalArgumentException(s"Missing path for export: $fileExportKeyword")
          }
          val filePath = args(index + 1)
          new FileOutputASCIIImageExporter(new File(filePath))
        case `consoleExportKeyword` =>
          new StdOutputASCIIImageExporter()
      }
    }
    new MixedStreamASCIIImageExporter(exporters)
  }
}
