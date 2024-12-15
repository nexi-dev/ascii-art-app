package pixel

import org.scalatest.funsuite.AnyFunSuite

class RGBPixelTest extends AnyFunSuite {
  test("Compare two RGB pixels with the same value") {
    val firstPixel = RGBPixel(1, 128, 6)
    val secondPixel = RGBPixel(1, 128, 6)

    assert(firstPixel == secondPixel)
    assert(firstPixel.getPixel == secondPixel.getPixel)
  }

  test("Create RGB pixels with negative values") {
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((-1, 0, 0))
    }
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((-1, -1, 0))
    }
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((-1, -1, -1))
    }
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((0, -180, -4000))
    }
  }

  test("Create RGB pixels with values exceeding 255") {
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((0, 180, 4000))
    }
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((0, 256, 40))
    }
    assertThrows[IllegalArgumentException] {
      val pixel = RGBPixel((90000, 180, 4000))
    }
  }
}
