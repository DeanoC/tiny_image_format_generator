package TinyImageFormatGenerator

import collection.mutable.StringBuilder

extension (x: Double)
  def ~=(y: Double, precision: Double = 0.0000001) = {
    if ((x - y).abs < precision) true else false
  }

case class WriteZigChannel(table: Seq[(String, GeneratorCode)]):
  val text = {
    var sb = StringBuilder()
    sb ++= "// Do Not Edit Autogenerated by tiny_image_format_generator\n\n"
    sb ++= "const TinyImageFormat = @import(\"tiny_image_format.zig\").Format;\n"
    sb ++= "const PhysicalChannel = @import(\"tiny_image_format.zig\").PhysicalChannel;\n"
    sb ++= "const LogicalChannel = @import(\"tiny_image_format.zig\").LogicalChannel;\n"
    sb ++= "\n"

    genU32QueryFunc(sb, "Count", 4, { _.ChannelCount })

    genPerChannelQueryFunc(sb, "BitWidthAtPhysicalChannel", 0, { (fmt, c) => fmt.PerChannelBitWidth(c) })
    genPerChannelDoubleQueryFunc(sb, "MinimumAtPhysicalChannel", 0.0, { (fmt, c) => fmt.PerChannelMinimum(c) })
    genPerChannelDoubleQueryFunc(sb, "MaximumAtPhysicalChannel", 1.0, { (fmt, c) => fmt.PerChannelMaximum(c) })

    genLogicalToPhysicalThunkFunc(sb, "BitWidth", "u8", "BitWidthAtPhysicalChannel");
    genLogicalToPhysicalThunkFunc(sb, "Minimum", "f64", "MinimumAtPhysicalChannel");
    genLogicalToPhysicalThunkFunc(sb, "Maximum", "f64", "MaximumAtPhysicalChannel");

    genPhysicalToLogical(sb)
    genLogicalToPhysical(sb)

    sb.result()

  }

  def genLogicalToPhysicalThunkFunc(sb: StringBuilder, name: String, ret: String, funcName: String) =
    sb ++= s"pub fn $name(fmt: TinyImageFormat, channel: LogicalChannel) $ret {\n"
    sb ++= s"    return $funcName(fmt, LogicalChannelToPhysical(fmt, channel));\n"
    sb ++= "}\n\n"

  def genU32QueryFunc(sb: StringBuilder, name: String, default: Int, func: (GeneratorCode) => Int) =
    sb ++= s"pub fn $name(fmt: TinyImageFormat) u8 {\n"
    sb ++= "    switch(fmt) {\n"
    table.foreach(fmt => if func(fmt._2) != default then sb ++= f"        .${fmt._1} => return ${func(fmt._2)},\n")
    sb ++= s"        else => return $default,\n"
    sb ++= "    }\n}\n\n"

  def genPerChannelQueryFunc(sb: StringBuilder, name: String, default: Int, func: (GeneratorCode, Int) => Int) =
    sb ++= s"pub fn $name(fmt: TinyImageFormat, channel: u8) u8 {\n"
    for (chan <- 0 until 4)
      sb ++= s"    ${if chan == 0 then "" else "else "}if(channel == $chan) {\n"
      sb ++= "         switch(fmt) {\n"
      table.foreach(fmt =>
        if func(fmt._2, chan) != default then sb ++= f"            .${fmt._1} => return ${func(fmt._2, chan)},\n",
      )
      sb ++= s"         else => return $default,\n"
      sb ++= "        }\n"
      sb ++= "    }\n"
    sb ++= "    return 0;\n\n"
    sb ++= "}\n\n"

  def genPerChannelDoubleQueryFunc(
      sb: StringBuilder,
      name: String,
      default: Double,
      func: (GeneratorCode, Int) => Double,
  ) =
    sb ++= s"pub fn $name(fmt: TinyImageFormat, channel: u8) f64 {\n"
    for (chan <- 0 until 4)
      sb ++= s"    ${if chan == 0 then "" else "else "}if(channel == $chan) {\n"
      sb ++= "         switch(fmt) {\n"
      table.foreach(fmt =>
        if !(func(fmt._2, chan) ~= default) then sb ++= f"            .${fmt._1} => return ${func(fmt._2, chan)},\n",
      )
      sb ++= s"         else => return $default,\n"
      sb ++= "        }\n"
      sb ++= "    }\n"
    sb ++= "    return 0.0;\n\n"
    sb ++= "}\n\n"

  def genPhysicalToLogical(
      sb: StringBuilder,
  ) =
    sb ++= s"pub fn PhysicalChannelToLogical(fmt: TinyImageFormat, channel: PhysicalChannel) LogicalChannel {\n"
    sb ++= s"    if(@enumToInt(channel) >= Count(channel)) return .Const0\n"
    sb ++= s"    else switch(channel) {\n"

    sb ++= s"        ._0 => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.PhysicalToLogical(PhysicalChannel._0) != LogicalChannel.Red &&
        fmt._2.ChannelCount > 0
      then sb ++= f"            .${fmt._1} => return .${fmt._2.PhysicalToLogical(PhysicalChannel._0)},\n",
    )
    sb ++= s"            else => return .Red\n"
    sb ++= s"        },\n"

    sb ++= s"        ._1 => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.PhysicalToLogical(PhysicalChannel._1) != LogicalChannel.Green &&
        fmt._2.ChannelCount > 1
      then sb ++= f"            .${fmt._1} => return .${fmt._2.PhysicalToLogical(PhysicalChannel._1)},\n",
    )
    sb ++= s"            else => return .Green\n"
    sb ++= s"        },\n"

    sb ++= s"        ._2 => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.PhysicalToLogical(PhysicalChannel._2) != LogicalChannel.Blue &&
        fmt._2.ChannelCount > 2
      then sb ++= f"            .${fmt._1} => return .${fmt._2.PhysicalToLogical(PhysicalChannel._2)},\n",
    )
    sb ++= s"            else => return .Blue\n"
    sb ++= s"        },\n"

    sb ++= s"        ._3 => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.PhysicalToLogical(PhysicalChannel._3) != LogicalChannel.Alpha &&
        fmt._2.ChannelCount > 3
      then sb ++= f"            .${fmt._1} => return .${fmt._2.PhysicalToLogical(PhysicalChannel._3)},\n",
    )
    sb ++= s"            else => return .Alpha\n"
    sb ++= s"        },\n"

    sb ++= s"        .Const0 => return .Const0,\n"
    sb ++= s"        .Const1 => return .Const1,\n"
    sb ++= "    }\n"
    sb ++= "}\n\n"

  def genLogicalToPhysical(
      sb: StringBuilder,
  ) =
    sb ++= s"pub fn LogicalChannelToPhysical(fmt: TinyImageFormat, channel: LogicalChannel) PhysicalChannel {\n"
    sb ++= s"    switch(channel) {\n"

    sb ++= s"        .Red => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Red) != PhysicalChannel._0 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Red)},\n",
    )
    sb ++= s"            else => return ._0\n"
    sb ++= s"        },\n"

    sb ++= s"        .Green => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Green) != PhysicalChannel._1 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Green)},\n",
    )
    sb ++= s"            else => return ._1\n"
    sb ++= s"        },\n"

    sb ++= s"        .Blue => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Blue) != PhysicalChannel._2 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Blue)},\n",
    )
    sb ++= s"            else => return ._2\n"
    sb ++= s"        },\n"

    sb ++= s"        .Alpha => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Alpha) != PhysicalChannel._3 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Alpha)},\n",
    )
    sb ++= s"            else => return ._3\n"
    sb ++= s"        },\n"

    sb ++= s"        .Depth => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Depth) != PhysicalChannel.Const0 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Depth)},\n",
    )
    sb ++= s"            else => return .Const0\n"
    sb ++= s"        },\n"

    sb ++= s"        .Stencil => switch(fmt) {\n"
    table.foreach(fmt =>
      if fmt._2.LogicalToPhysical(LogicalChannel.Stencil) != PhysicalChannel.Const0 then
        sb ++= f"            .${fmt._1} => return .${fmt._2.LogicalToPhysical(LogicalChannel.Stencil)},\n",
    )
    sb ++= s"            else => return .Const0\n"
    sb ++= s"        },\n"

    sb ++= s"        .Const0 => return .Const0,\n"
    sb ++= s"        .Const1 => return .Const1,\n"
    sb ++= "    }\n"
    sb ++= "}\n\n"
