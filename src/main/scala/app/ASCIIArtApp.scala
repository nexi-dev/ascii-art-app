package app

import importers.ImageImporter
import filters.mixed.GrayscaleImageMixedFilter
import converters.GrayscaleImageToASCIIImageConverter
import exporters.image.ImageExporter
import pixel.{ASCIIPixel, RGBPixel, GrayscalePixel}
import image.Image
import converters.RGBImageToGrayscaleConverter


/**
 * A class representing the whole ascii art app and wrapping all the models
 * @param importer imports the image
 * @param filter filters to be applied onto the image
 * @param converter gray to ASCII converter with a correct transformation table
 * @param exporter exports image
 */
class ASCIIArtApp(
                   importer: ImageImporter, 
                   filter: GrayscaleImageMixedFilter, 
                   converter: GrayscaleImageToASCIIImageConverter,
                   exporter: ImageExporter[ASCIIPixel]
                 ) extends App {
  override def run(): Unit = {
    val imageRGB: Image[RGBPixel] = importer.input()
    val imageGRAY: Image[GrayscalePixel] = new RGBImageToGrayscaleConverter().convert(imageRGB)
    val imageFiltered: Image[GrayscalePixel] = filter.filter(imageGRAY)
    val imageToExport: Image[ASCIIPixel] = converter.convert(imageFiltered)
    exporter.output(imageToExport)
  }
}
