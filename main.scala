//> using scala "3.2"
//> using lib "com.github.scopt::scopt:4.0.1"
//> using lib "com.lihaoyi::os-lib:0.8.0"

package TinyImageFormatGenerator

import scopt.OParser
import java.io.File
import java.text.Normalizer.Form

val ProgramName = "tiny_image_format_generator";
val ProgramVersion = "0.0";

case class Config(
    output: Option[File] = None,
)

val builder = OParser.builder[Config]
val argParser = {
  import builder._
  OParser.sequence(
    programName(ProgramName),
    head(ProgramName, ProgramVersion),
    arg[File]("output")
      .required()
      .action((in, c) => c.copy(output = Some(in)))
      .text("where to place the generatored files"),
  )
}

object Main {
  def main(args: Array[String]): Unit =
    OParser.parse(argParser, args, Config()) match {
      case Some(config) =>
        println { s"$ProgramName $ProgramVersion" };
        // do stuff with config
        val zigBase = WriteZigBase(FormatTable)
        val zigQuery = WriteZigQuery(FormatTable)
        println(zigBase.text)
        println(zigQuery.text)

      case _ =>
        System.exit(1)
    }

}
