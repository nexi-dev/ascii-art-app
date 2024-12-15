package tables.nonlinear

import converters.GrayscaleImageToASCIIImageConverter
import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.{ASCIIPixel, GrayscalePixel}
import tables.linear.LinearTransformationTable
import tables.nonlinear.NonlinearTransformationTable

class NonlinearTransformationTableTest extends AnyFunSuite {
  test("Test nonlinear transformation with empty character ramp") {
    assertThrows[IllegalArgumentException] {
      val table = NonlinearTransformationTable("")
    }
  }

  test("Test nonlinear transformation with character ramp of 1 character") {
    val table = NonlinearTransformationTable("X")

    for (i <- 0 to 255) {
      assert(table.transform(GrayscalePixel(i)) == ASCIIPixel('X'))
    }
  }

  test("Test nonlinear transformation with linear part of ramp len being divisor of 128") {
    val characterRamp = "1234X"
    val table = NonlinearTransformationTable(characterRamp)

    for (i <- 0 to 127) {
      assert(table.transform(GrayscalePixel(i)) == ASCIIPixel(characterRamp.charAt(i / 32)))
    }

    for (i <- 128 to 255) {
      assert(table.transform(GrayscalePixel(i)) == ASCIIPixel('X'))
    }
  }

  test("Test nonlinear transformation with linear part of ramp len NOT being divisor of 128 ") {
    val characterRamp = "123X"
    val table = NonlinearTransformationTable(characterRamp)

    assert(table.transform(GrayscalePixel(0)) == ASCIIPixel('1'))
    assert(table.transform(GrayscalePixel(42)) == ASCIIPixel('1'))
    assert(table.transform(GrayscalePixel(43)) == ASCIIPixel('2'))
    assert(table.transform(GrayscalePixel(85)) == ASCIIPixel('2'))
    assert(table.transform(GrayscalePixel(86)) == ASCIIPixel('3'))
    assert(table.transform(GrayscalePixel(127)) == ASCIIPixel('3'))
    assert(table.transform(GrayscalePixel(128)) == ASCIIPixel('X'))
  }

  test("Test nonlinear transformation with edge intensities like 0 and 255") {
    val table = NonlinearTransformationTable("0" + ("X" * 254) + "1")
    assert(table.transform(GrayscalePixel(0)) == ASCIIPixel('0'))
    assert(table.transform(GrayscalePixel(255)) == ASCIIPixel('1'))
  }

  test("Test nonlinear transformation with ramp exceeding the len of 256") {
    assertThrows[IllegalArgumentException] {
      val table = NonlinearTransformationTable("X" * 257)
    }
  }
}