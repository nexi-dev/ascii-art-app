package importers.file

import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class GIFImageFileImporterTest extends AnyFunSuite {
  test("Should accept .gif") {
    val importer = new GIFImageFileImporter(new File("something.gif"))
  }

  test("Should not accept .png") {
    assertThrows[IllegalArgumentException] {
      val importer = new GIFImageFileImporter(new File("something.png"))
    }
  }

  test("Should not accept .jpg") {
    assertThrows[IllegalArgumentException] {
      val importer = new GIFImageFileImporter(new File("something.jpg"))
    }
  }
}
