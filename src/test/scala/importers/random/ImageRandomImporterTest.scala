package importers.random

import org.scalatest.funsuite.AnyFunSuite
import pixel.RGBPixel

import scala.util.Random

class ImageRandomImporterTest extends AnyFunSuite {
  test("The lengths of an random image must be positive") {
    assertThrows[IllegalArgumentException] {
      val importer = new ImageRandomImporter(new Random(), 0, 0)
    }
    assertThrows[IllegalArgumentException] {
      val importer = new ImageRandomImporter(new Random(), -50, 0)
    }
  }

  test("Max length must be greater to min length") {
    assertThrows[IllegalArgumentException] {
      val importer = new ImageRandomImporter(new Random(), 1, 0)
    }
    val importerValid = new ImageRandomImporter(new Random(), 1, 5)
  }

  test("With a given seed the random importer is deterministic") {
    val importer = new ImageRandomImporter(new Random(seed = 4), 1, 2)
    val image = importer.input()

    assert(image.getPixel(0, 0) == RGBPixel((235, 207, 235)))
  }
}
