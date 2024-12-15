package importers.file

import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class PNGImageFileImporterTest extends AnyFunSuite{
  test("Should not accept .gif") {
    assertThrows[IllegalArgumentException] {
    val importer = new PNGImageFileImporter(new File("something.gif"))
    }
  }

  test("Should accept .png") {
      val importer = new PNGImageFileImporter(new File("something.png"))
  }

  test("Should not accept .jpg") {
    assertThrows[IllegalArgumentException] {
      val importer = new PNGImageFileImporter(new File("something.jpg"))
    }
  }
}