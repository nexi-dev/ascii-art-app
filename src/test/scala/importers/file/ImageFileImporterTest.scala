package importers.file

import image.Image
import org.scalatest.funsuite.AnyFunSuite
import pixel.RGBPixel

import java.io.File

class ImageFileImporterTest extends AnyFunSuite{
  test("Cannot import non existent file") {
    val importer = new ImageFileImporter(new File("something.jpeg"))
    assertThrows[IllegalArgumentException] {
      val image = importer.input()
    }
  }

  test("Can import existing JPG file as Image[RGBPixel]") {
    val importer = new ImageFileImporter(new File("src/test/resources/scala.jpg"))
    val image = importer.input()

    assert(image.getHeight == 50)
    assert(image.getWidth == 50)
    assert(image.isInstanceOf[Image[RGBPixel]])
  }
}
