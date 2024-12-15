package pixel

import org.scalatest.funsuite.AnyFunSuite

class ASCIIPixelTest extends AnyFunSuite{
  test("Compare two ASCII pixels with the same value") {
    val firstPixel = ASCIIPixel('#')
    val secondPixel = ASCIIPixel('#')

    assert(firstPixel == secondPixel)
    assert(firstPixel.getPixel == secondPixel.getPixel)
  }
}
