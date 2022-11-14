package TinyImageFormatGenerator

import collection.mutable.StringBuilder

case class WriteZigBase(table: Seq[(String, GeneratorCode)]):
  val text = {
    var sb = StringBuilder()
    sb ++= "// Do Not Edit Autogenerated by tiny_image_format_generator\n\n"
    sb ++= """// Physical Channel (Where something is actually located in the format) 
    |pub const PhysicalChannel = enum(i3) {
    |   _0 = 0,
    |   _1 = 1,
    |   _2 = 2,
    |   _3 = 3,
    |   Const0 = -1,
    |   Const1 = -2,
    |};
    |
    |""".stripMargin
    sb ++= """// Logical Channels (Channel usage regardless of actual position)
    |pub const LogicalChannel = enum(i3) {
    |   Red = 0,
    |   Green = 1,
    |   Blue = 2,
    |   Alpha = 3,
    |   Depth = 4,
    |   Stencil = 5,
    |   Const0 = -1,
    |   Const1 = -2,
    |};
    |
    |""".stripMargin

    val mainSize = if table.length < 256 then "u8" else "u16"
    sb ++= s"pub const Count = ${table.length};\n\n"

    sb ++= s"""// The main enumeration
    |pub const Format = enum($mainSize) {
    |""".stripMargin
    table.zipWithIndex.foreach((fmt, i) => sb ++= s"    ${fmt._1} = $i,\n")
    sb ++= "};\n\n"

    sb.result();
  }
