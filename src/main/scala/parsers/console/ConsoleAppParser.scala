package parsers.console

import app.ASCIIArtApp

/**
 * Parses all the console arguments in partial parsers and builds and app
 * @param fileImportKeyword keyword for import from file
 * @param randomImportKeyword keyword for random import
 * @param rotateKeyword keyword for rotation filter
 * @param invertKeyword keyword for invert filter
 * @param brightnessKeyword keyword for brightness filter
 * @param tableKeyword keyword for transformation table
 * @param bourkeOption keyword for the implicit bourke option
 * @param nonlinearOption keyword for the nonlinear option
 * @param fileExportKeyword keyword for the file export
 * @param consoleExportKeyword keyword for the console export
 */
class ConsoleAppParser(
                      fileImportKeyword: String,
                      randomImportKeyword: String,
                      rotateKeyword: String,
                      invertKeyword: String,
                      brightnessKeyword: String,
                      tableKeyword: String,
                      bourkeOption: String,
                      nonlinearOption: String,
                      fileExportKeyword: String,
                      consoleExportKeyword: String
                      ) extends ConsoleParser[ASCIIArtApp] {
  /**
   * Parses all args from console in partial parsers and builds the whole ascii art app from the built components
   * @param args the array of string from std input
   * @return the ascii art app
   */
  override def parse(args: Array[String]): ASCIIArtApp = {
    val importer = new ConsoleImportParser(fileImportKeyword, randomImportKeyword).parse(args)
    val filter = new ConsoleFilterParser(rotateKeyword, invertKeyword, brightnessKeyword).parse(args)
    val converter = new ConsoleConversionParser(tableKeyword, bourkeOption, nonlinearOption).parse(args)
    val exporter = new ConsoleExportParser(fileExportKeyword, consoleExportKeyword).parse(args)

    new ASCIIArtApp(importer, filter, converter, exporter)
  }
}
