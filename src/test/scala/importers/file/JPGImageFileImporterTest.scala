package importers.file

import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class JPGImageFileImporterTest extends AnyFunSuite{
  test("Should not accept .gif") {
    assertThrows[IllegalArgumentException] {
      val importer = new JPGImageFileImporter(new File("something.gif"))
    }
  }

  test("Should not accept .png") {
    assertThrows[IllegalArgumentException] {
      val importer = new JPGImageFileImporter(new File("something.png"))
    }
  }

  test("Should accept .jpg") {
    val importer = new JPGImageFileImporter(new File("something.jpg"))
  }

  test("Should accept .jpeg") {
      val importer = new JPGImageFileImporter(new File("something.jpeg"))
  }
}