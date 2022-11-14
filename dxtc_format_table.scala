package TinyImageFormatGenerator

object DxtcFormatTable:
  // shortcuts to help keep the table more readable
  def BlockBytes(ts: Int) = ts match
    case 8  => DxtcBlockBytes._8
    case 16 => DxtcBlockBytes._16
    case _  => println(s"Dxtc Block Byte is invalid"); DxtcBlockBytes._8

  def ModeCount(ts: Int) = ts match
    case 1  => DxtcModeCount._1
    case 8  => DxtcModeCount._8
    case 14 => DxtcModeCount._14
    case _  => println(s"Dxtc Mode Count is invalid"); DxtcModeCount._1

  val NoAlpha = DxtcAlpha.None
  val PunchThroughAlpha = DxtcAlpha.PunchThrough
  val BlockAlpha = DxtcAlpha.Block
  val FullAlpha = DxtcAlpha.Full

  val UNorm = DxtcType.UNorm
  val SNorm = DxtcType.SNorm
  val SRGB = DxtcType.SRGB
  val SFloat = DxtcType.SFloat
  val UFloat = DxtcType.UFloat

  def D(name: String, DxtcType: DxtcType, alpha: DxtcAlpha, blockBytes: Int, channelCount: Int, modeCount: Int) =
    (name, GeneratorCode.Dxtc(DxtcType, alpha, BlockBytes(blockBytes), channelCount, ModeCount(modeCount)))

  def Table = Seq(
    D("DXBC1_RGB_UNORM", UNorm, NoAlpha, 8, 3, 1),
    D("DXBC1_RGB_SRGB", SRGB, NoAlpha, 8, 3, 1),
    D("DXBC1_RGBA_UNORM", UNorm, PunchThroughAlpha, 8, 4, 1),
    D("DXBC1_RGBA_SRGB", SRGB, PunchThroughAlpha, 8, 4, 1),
    D("DXBC2_UNORM", UNorm, FullAlpha, 16, 4, 1),
    D("DXBC2_SRGB", SRGB, FullAlpha, 16, 4, 1),
    D("DXBC3_UNORM", UNorm, BlockAlpha, 16, 4, 1),
    D("DXBC3_SRGB", SRGB, BlockAlpha, 16, 4, 1),
    D("DXBC4_UNORM", UNorm, NoAlpha, 8, 1, 1),
    D("DXBC4_SNORM", SNorm, NoAlpha, 8, 1, 1),
    D("DXBC5_UNORM", UNorm, NoAlpha, 16, 2, 1),
    D("DXBC5_SNORM", SNorm, NoAlpha, 16, 2, 1),
    D("DXBC6H_UFLOAT", UFloat, NoAlpha, 16, 3, 14),
    D("DXBC6H_SFLOAT", SFloat, NoAlpha, 16, 3, 14),
    D("DXBC7_UNORM", UNorm, FullAlpha, 16, 4, 8),
    D("DXBC7_SRGB", SRGB, FullAlpha, 16, 4, 8),
  )
