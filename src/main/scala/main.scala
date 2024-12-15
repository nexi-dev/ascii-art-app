import app.ASCIIArtApp
import parsers.console.ConsoleAppParser

def main(args: Array[String]): Unit = {
  try {
    val appParser = new ConsoleAppParser(
      fileImportKeyword = "--image-file",
      randomImportKeyword = "--image-random",
      rotateKeyword = "--rotate",
      invertKeyword = "--invert",
      brightnessKeyword = "--brightness",
      tableKeyword = "--table",
      bourkeOption = "bourke",
      nonlinearOption = "nonlinear",
      fileExportKeyword = "--export-file",
      consoleExportKeyword = "--export-console"
    )
    val app: ASCIIArtApp = appParser.parse(args)
    app.run()
  } catch {
    case exception: Throwable => println(exception.getMessage)
  }
}