package TinyImageFormatGenerator

object ClutFormatTable:

  // shortcuts to help keep the table more readable
  def BlockSize(ts: Int) = ts match
    case 1 => ClutBlockSize._1
    case 2 => ClutBlockSize._2
    case 4 => ClutBlockSize._4
    case 8 => ClutBlockSize._8
    case _ => println(s"Clut Block is invalid"); ClutBlockSize._1

  def BitCount(ts: Int) = ts match
    case 0 => ClutBits._0
    case 1 => ClutBits._1
    case 2 => ClutBits._2
    case 4 => ClutBits._4
    case 8 => ClutBits._8
    case _ => println(s"Clut Bits is invalid"); ClutBits._0

  val RGB = ClutType.RGB
  val Single = ClutType.Single
  val ExplicitAlpha = ClutType.ExplicitAlpha

  val NoChannel = ClutChannel(ClutType.None, BitCount(0))

  def C(clutType: ClutType, bitCount: Int) = ClutChannel(clutType, BitCount(bitCount))

  def D1(name: String, blockSize: Int, c0: ClutChannel) =
    (name, GeneratorCode.Clut(BlockSize(blockSize), c0, NoChannel))
  def D2(name: String, blockSize: Int, c0: ClutChannel, c1: ClutChannel) =
    (name, GeneratorCode.Clut(BlockSize(blockSize), c0, c1))

  def Table = Seq(
    D1("CLUT_P4", 2, C(RGB, 4)),
    D2("CLUT_P4A4", 1, C(RGB, 4), C(ExplicitAlpha, 4)),
    D1("CLUT_P8", 1, C(RGB, 8)),
    D2("CLUT_P8A8", 1, C(RGB, 8), C(ExplicitAlpha, 8)),
  )
