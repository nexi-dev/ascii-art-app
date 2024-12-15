package pixel

import org.scalatest.funsuite.AnyFunSuite

class GrayscalePixelTest extends AnyFunSuite{
  test("Compare two Grayscale pixels with the same value") {
    val firstPixel = GrayscalePixel(41)
    val secondPixel = GrayscalePixel(41)

    assert(firstPixel == secondPixel)
    assert(firstPixel.getPixel == secondPixel.getPixel)
  }

  test("Create Grayscale pixels with negative intensity") {
    assertThrows[IllegalArgumentException] {
      val pixel = GrayscalePixel(-1)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = GrayscalePixel(-156)
    }
  }

  test("Create Grayscale pixels with values exceeding 255") {
    assertThrows[IllegalArgumentException] {
      val pixel = GrayscalePixel(256)
    }
    assertThrows[IllegalArgumentException] {
      val pixel = GrayscalePixel(9814)
    }
  }
}
