package TinyImageFormatGenerator

object DxbcFormatTable:
  // shortcuts to help keep the table more readable
  def BlockBytes(ts: Int) = ts match
    case 8  => DxbcBlockBytes._8
    case 16 => DxbcBlockBytes._16
    case _  => println(s"Dxbc Block Byte is invalid"); DxbcBlockBytes._8

  def ModeCount(ts: Int) = ts match
    case 1  => DxbcModeCount._1
    case 8  => DxbcModeCount._8
    case 14 => DxbcModeCount._14
    case _  => println(s"Dxbc Mode Count is invalid"); DxbcModeCount._1

  val NoAlpha = DxbcAlpha.None
  val PunchThroughAlpha = DxbcAlpha.PunchThrough
  val BlockAlpha = DxbcAlpha.Block
  val FullAlpha = DxbcAlpha.Full

  val UNorm = DxbcType.UNorm
  val SNorm = DxbcType.SNorm
  val SRGB = DxbcType.SRGB
  val SFloat = DxbcType.SFloat
  val UFloat = DxbcType.UFloat

  def D(name: String, dxbcType: DxbcType, alpha: DxbcAlpha, blockBytes: Int, channelCount: Int, modeCount: Int) =
    (name, GeneratorCode.Dxbc(dxbcType, alpha, BlockBytes(blockBytes), channelCount, ModeCount(modeCount)))

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
