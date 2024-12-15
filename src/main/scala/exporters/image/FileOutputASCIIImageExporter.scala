package exporters.image

import java.io.{File, FileOutputStream}

/**
 * Exports an ascii pixel image into a file
 * @param file the file to export to
 */
class FileOutputASCIIImageExporter(file: File) extends StreamASCIIImageExporter(new FileOutputStream(file)) {
}
