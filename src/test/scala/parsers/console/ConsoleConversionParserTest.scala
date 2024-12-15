package parsers.console

import converters.GrayscaleImageToASCIIImageConverter
import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.GrayscalePixel
import tables.linear.LinearTransformationTable
import tables.nonlinear.NonlinearTransformationTable

class ConsoleConversionParserTest extends AnyFunSuite {
  private def getImage: Image[GrayscalePixel] = {
    Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(47), GrayscalePixel(11)),
        Vector(GrayscalePixel(240), GrayscalePixel(166))
      )
    )
  }

  test("Parse default table when not explicitly stating it") {
    val parser = new ConsoleConversionParser("--table", "bourke", "nonlinear")
    val converter = parser.parse(Array("--image-random", "--export-console"))

    assert(converter.isInstanceOf[GrayscaleImageToASCIIImageConverter])
  }

  test("Parse bourke linear table which should be equal to default") {
    val parser = new ConsoleConversionParser("--table", "bourke", "nonlinear")
    val converter = parser.parse(Array("--image-random", "--export-console"))
    val converterBourke = parser.parse(Array("--image-random", "--export-console", "--table", "bourke"))

    assert(converter.convert(getImage) == converterBourke.convert(getImage))
  }

  test("Parse bourke nonlinear table") {
    val parser = new ConsoleConversionParser("--table", "bourke", "nonlinear")
    val converter = parser.parse(Array("--image-random", "--export-console", "--table", "nonlinear"))

    assert(converter.convert(getImage) == (new GrayscaleImageToASCIIImageConverter(new NonlinearTransformationTable())).convert(getImage))
  }

  test("Parse custom linear table") {
    val parser = new ConsoleConversionParser("--table", "bourke", "nonlinear")
    val converter = parser.parse(Array("--image-random", "--export-console", "--table", "ABCDE"))

    assert(converter.convert(getImage) == (new GrayscaleImageToASCIIImageConverter(new LinearTransformationTable("ABCDE"))).convert(getImage))
  }

  test("Should not accept more than one table") {
    assertThrows[IllegalArgumentException] {
      val parser = new ConsoleConversionParser("--table", "bourke", "nonlinear")
      val converter = parser.parse(Array("--image-random", "--export-console", "--table", "ABCDE", "--table", "bourke"))
    }
  }
}
