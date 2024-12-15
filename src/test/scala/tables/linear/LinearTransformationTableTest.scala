package tables.linear

import org.scalatest.funsuite.AnyFunSuite
import pixel.{ASCIIPixel, GrayscalePixel}

class LinearTransformationTableTest extends AnyFunSuite {
  test("Test linear transformation table with a ramp of 0 character") {
    assertThrows[IllegalArgumentException] {
      val table = LinearTransformationTable("")
    }
  }

  test("Test linear transformation table with a ramp of 1 character") {
    val table = LinearTransformationTable("X")

    for (i <- 0 to 255) {
      assert(table.transform(GrayscalePixel(i)) == ASCIIPixel('X'))
    }
  }

  test("Test linear transformation table with a ramp len being a divisor of 256") {
    val characterRamp = "ASDFGHJKLQWERTYU" // 16
    val table = LinearTransformationTable(characterRamp)

    for (i <- 0 to 255) {
      assert(table.transform(GrayscalePixel(i)) == ASCIIPixel(characterRamp.charAt(i / 16)))
    }
  }

  test("Test linear transformation table with a ramp len not being a divisor of 256") {
    val table = LinearTransformationTable("123")

    assert(table.transform(GrayscalePixel(0)) == ASCIIPixel('1'))
    assert(table.transform(GrayscalePixel(85)) == ASCIIPixel('1'))
    assert(table.transform(GrayscalePixel(86)) == ASCIIPixel('2'))
    assert(table.transform(GrayscalePixel(170)) == ASCIIPixel('2'))
    assert(table.transform(GrayscalePixel(171)) == ASCIIPixel('3'))
    assert(table.transform(GrayscalePixel(255)) == ASCIIPixel('3'))
  }

  test("Test linear transformation of table on the 0 and 255 intensity pixels") {
    val table = LinearTransformationTable("0" + ("X" * 254) + "1")
    assert(table.transform(GrayscalePixel(0)) == ASCIIPixel('0'))
    assert(table.transform(GrayscalePixel(255)) == ASCIIPixel('1'))
  }

  test("Test linear transformation of table with longer ramp than 256") {
    assertThrows[IllegalArgumentException] {
      val table = LinearTransformationTable("X" * 257)
    }
  }
}
