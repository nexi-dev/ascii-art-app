package converters

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.{ASCIIPixel, GrayscalePixel}
import tables.linear.LinearTransformationTable
import tables.nonlinear.NonlinearTransformationTable

class GrayscaleImageToASCIIImageConverterTest extends AnyFunSuite {
  private def testConversion(
                              imageToConvert: Image[GrayscalePixel],
                              converter: GrayscaleImageToASCIIImageConverter,
                              expectedWidth: Int,
                              expectedHeight: Int,
                              expectedPixels: Map[(Int, Int), ASCIIPixel]): Unit = {
    val imageConverted = converter.convert(imageToConvert)

    assert(imageConverted.getWidth == expectedWidth)
    assert(imageConverted.getHeight == expectedHeight)

    expectedPixels.foreach {
      case ((i, j), expectedPixel) =>
        assert(imageConverted.getPixel(i, j) == expectedPixel)
    }
  }

  test("Convert image of grayscale 0 and 255 intensity pixels to ASCII image with linear table") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(255)),
        Vector(GrayscalePixel(255), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(255)),
      )
    )
    val tableLinear = new LinearTransformationTable("12345")
    val converterLinear = new GrayscaleImageToASCIIImageConverter(tableLinear)

    testConversion(grayImage, converterLinear, 3, 2, Map(
      (0, 0) -> ASCIIPixel('1'),
      (0, 1) -> ASCIIPixel('5'),
      (1, 0) -> ASCIIPixel('5'),
      (1, 1) -> ASCIIPixel('1'),
      (2, 0) -> ASCIIPixel('1'),
      (2, 1) -> ASCIIPixel('5')
    ))
  }

  test("Convert image of grayscale 0 and 255 intensity pixels to ASCII image with linear table with ramp only 1 char") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(255), GrayscalePixel(0)),
        Vector(GrayscalePixel(0), GrayscalePixel(255)),
      )
    )
    val tableLinear = new LinearTransformationTable("X")
    val converterLinear = new GrayscaleImageToASCIIImageConverter(tableLinear)

    testConversion(grayImage, converterLinear, 2, 2, Map(
      (0, 0) -> ASCIIPixel('X'),
      (0, 1) -> ASCIIPixel('X'),
      (1, 0) -> ASCIIPixel('X'),
      (1, 1) -> ASCIIPixel('X'),
    ))
  }

  test("Convert image of normal value grayscale pixels to ASCII image with linear table with ramp len being divisor of 256") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(63)),
        Vector(GrayscalePixel(64), GrayscalePixel(127)),
        Vector(GrayscalePixel(128), GrayscalePixel(191)),
        Vector(GrayscalePixel(192), GrayscalePixel(255)),
      )
    )
    val tableLinear = new LinearTransformationTable("1234")
    val converterLinear = new GrayscaleImageToASCIIImageConverter(tableLinear)

    testConversion(grayImage, converterLinear, 4, 2, Map(
      (0, 0) -> ASCIIPixel('1'),
      (0, 1) -> ASCIIPixel('1'),
      (1, 0) -> ASCIIPixel('2'),
      (1, 1) -> ASCIIPixel('2'),
      (2, 0) -> ASCIIPixel('3'),
      (2, 1) -> ASCIIPixel('3'),
      (3, 0) -> ASCIIPixel('4'),
      (3, 1) -> ASCIIPixel('4'),
    ))
  }

  test("Convert image of normal value grayscale pixels to ASCII image with linear table with ramp len NOT being divisor of 256") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(85)),
        Vector(GrayscalePixel(86), GrayscalePixel(170)),
        Vector(GrayscalePixel(171), GrayscalePixel(255)),
      )
    )
    val tableLinear = new LinearTransformationTable("123")
    val converterLinear = new GrayscaleImageToASCIIImageConverter(tableLinear)

    testConversion(grayImage, converterLinear, 3, 2, Map(
      (0, 0) -> ASCIIPixel('1'),
      (0, 1) -> ASCIIPixel('1'),
      (1, 0) -> ASCIIPixel('2'),
      (1, 1) -> ASCIIPixel('2'),
      (2, 0) -> ASCIIPixel('3'),
      (2, 1) -> ASCIIPixel('3'),
    ))
  }

  test("Convert grayscale image with intensities > 128 while using nonlinear table with longest possible character ramp") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(128), GrayscalePixel(200)),
        Vector(GrayscalePixel(128), GrayscalePixel(170)),
        Vector(GrayscalePixel(171), GrayscalePixel(255)),
      )
    )
    val table = new NonlinearTransformationTable(("X * 255") + "O")
    val converter = new GrayscaleImageToASCIIImageConverter(table)

    testConversion(grayImage, converter, 3, 2, Map(
      (0, 0) -> ASCIIPixel('O'),
      (0, 1) -> ASCIIPixel('O'),
      (1, 0) -> ASCIIPixel('O'),
      (1, 1) -> ASCIIPixel('O'),
      (2, 0) -> ASCIIPixel('O'),
      (2, 1) -> ASCIIPixel('O'),
    ))
  }

  test("Convert grayscale image with 0 and 255 and nonlinear 2 character ramp transformation") {
    val grayImage: Image[GrayscalePixel] = Image[GrayscalePixel](
      Vector(
        Vector(GrayscalePixel(0), GrayscalePixel(255)),
        Vector(GrayscalePixel(128), GrayscalePixel(0)),
      )
    )
    val table = new NonlinearTransformationTable("AB")
    val converter = new GrayscaleImageToASCIIImageConverter(table)

    testConversion(grayImage, converter, 2, 2, Map(
      (0, 0) -> ASCIIPixel('A'),
      (0, 1) -> ASCIIPixel('B'),
      (1, 0) -> ASCIIPixel('B'),
      (1, 1) -> ASCIIPixel('A'),
    ))
  }
}
