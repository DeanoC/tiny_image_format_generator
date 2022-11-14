//> using scala "3.2"
//> using lib "com.github.scopt::scopt:4.0.1"
//> using lib "com.lihaoyi::os-lib:0.8.0"

package TinyImageFormatGenerator

import scopt.OParser
import java.io.File
import java.io.PrintWriter
import os.Path
import java.io.FileWriter

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
  def writeTextToFile(path: Path, txt: String) =
    val w = new FileWriter(path.toString)
    w.write(txt)
    w.close()

  def main(args: Array[String]): Unit =
    OParser.parse(argParser, args, Config()) match {
      case Some(config) =>
        println { s"$ProgramName $ProgramVersion" };
        // do stuff with config
        config.output match
          case None =>
            println("Invalid Output specified")
          case Some(out) =>
            val targetPath = Path(config.output.get.getAbsolutePath())
            if !os.exists(targetPath) then os.makeDir.all(targetPath)
            writeTextToFile(targetPath / "tiny_image_format.zig", WriteZigBase(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_query.zig", WriteZigQuery(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_block.zig", WriteZigBlock(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_code.zig", WriteZigCode(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_channel.zig", WriteZigChannel(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_decode.zig", WriteZigDecode(FormatTable).text)
            writeTextToFile(targetPath / "tiny_image_format_encode.zig", WriteZigEncode(FormatTable).text)

      case _ =>
        System.exit(1)
    }

}
