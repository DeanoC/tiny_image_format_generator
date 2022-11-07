package TinyImageFormatGenerator

enum LogicalChannel(value: Int):
  case Red extends LogicalChannel(0)
  case Green extends LogicalChannel(1)
  case Blue extends LogicalChannel(2)
  case Alpha extends LogicalChannel(3)
  case Depth extends LogicalChannel(0)
  case Stencil extends LogicalChannel(1)
  case Const0 extends LogicalChannel(-1)
  case Const1 extends LogicalChannel(-2)

enum PhysicalChannel(value: Int):
  case _0 extends PhysicalChannel(0)
  case _1 extends PhysicalChannel(1)
  case _2 extends PhysicalChannel(2)
  case _3 extends PhysicalChannel(3)
  case Const0 extends PhysicalChannel(-1)
  case Const1 extends PhysicalChannel(-2)

enum Namespace:
  case Pack
  case DepthStencil
  case Dxtc
  case Pvrtc
  case Etc
  case Astc
  case Clut

enum PackSpecial:
  case None
  case Pack
  case Multi2
  case Multi4
  case Multi8

enum PackSwizzle:
  case Red
  case Green
  case Blue
  case Alpha
  case Const0
  case Const1

enum PackType:
  case None
  case UNorm
  case SNorm
  case UInt
  case SInt
  case UFloat
  case SFloat
  case SRGB
  case SBFloat
case class PackColour(swizzle: PackSwizzle, bitCount: Int, packType: PackType)

enum DepthStencilTotalSize:
  case _8
  case _16
  case _32
  case _64

enum DepthStencilBitCount:
  case _0
  case _8
  case _16
  case _24
  case _32

enum DepthStencilSwizzle:
  case Depth
  case Stencil
  case Const0

enum DepthStencilType:
  case None
  case UNorm
  case UInt
  case SFloat
case class DepthStencilChannel(swizzle: DepthStencilSwizzle, bitCount: DepthStencilBitCount, dsType: DepthStencilType)

enum DxbcAlpha:
  case None
  case PunchThrough
  case Block
  case Full

enum DxbcType:
  case UNorm
  case SNorm
  case SRGB
  case SFloat
  case UFloat

enum DxbcBlockBytes:
  case _8
  case _16

enum DxbcModeCount:
  case _1
  case _8
  case _14

enum PvrtcType:
  case UNorm
  case SRGB

enum PvrtcVersion:
  case V1
  case V2

enum PvrtcBits:
  case _2
  case _4

enum EtcType:
  case UNorm
  case SNorm
  case SRGB

enum EtcBits:
  case _8
  case _11

enum EtcAlpha:
  case None
  case PunchThrough
  case Block

enum AstcType:
  case UNorm
  case SRGB

enum AstcSize:
  case _1
  case _4
  case _5
  case _6
  case _8
  case _10
  case _12

enum ClutBlockSize:
  case _1
  case _2
  case _4
  case _8

enum ClutType:
  case None
  case RGB
  case Single
  case ExplicitAlpha

enum ClutBits:
  case _0
  case _1
  case _2
  case _4
  case _8
case class ClutChannel(clutType: ClutType, bits: ClutBits)

enum GeneratorCode(val code: BigInt):
  case Pack(
      special: PackSpecial,
      red: PackColour,
      green: PackColour,
      blue: PackColour,
      alpha: PackColour,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForPack(special, red, green, blue, alpha))

  case DepthStencil(
      totalSize: DepthStencilTotalSize,
      depth: DepthStencilChannel,
      stencil: DepthStencilChannel,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForDepthStencil(totalSize, depth, stencil))
  case Dxbc(
      dxbcType: DxbcType,
      alpha: DxbcAlpha,
      blockBytes: DxbcBlockBytes,
      channelCount: Int,
      modeCount: DxbcModeCount,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForDxbc(dxbcType, alpha, blockBytes, channelCount, modeCount))
  case Pvrtc(
      pvrtcType: PvrtcType,
      version: PvrtcVersion,
      bits: PvrtcBits,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForPvrtc(pvrtcType, version, bits))
  case Etc(
      etcType: EtcType,
      bits: EtcBits,
      alpha: EtcAlpha,
      channelCount: Int,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForEtc(etcType, bits, alpha, channelCount))
  case Astc(
      astcType: AstcType,
      blockWidth: AstcSize,
      blockHeight: AstcSize,
      blockDepth: AstcSize,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForAstc(astcType, blockWidth, blockHeight, blockDepth))
  case Clut(
      blockSize: ClutBlockSize,
      chan0: ClutChannel,
      chan1: ClutChannel,
  ) extends GeneratorCode(GeneratorCode.ComputeCodeForClut(blockSize, chan0, chan1))

object GeneratorCode:
  def log2(x: Int) = math.ceil(scala.math.log(x) / scala.math.log(2)).toInt

  // namespace definitions
  val NameSpaceShift = 0
  val NameSpaceSizeInBits = log2(Namespace.values.length)

  // pack type definitions
  val PackNumChannels = 4
  val PackSpecialBits = log2(PackSpecial.values.length)
  val PackBitCountBits = log2(32)
  val PackSwizzleBits = log2(PackSwizzle.values.length)
  val PackTypeBits = log2(PackType.values.length)

  val PackSpecialBeginShift = NameSpaceShift
  val PackSpecialEndShift = PackSpecialBeginShift + PackSpecialBits
  val PackBitsBeginShift = PackSpecialEndShift
  val PackBitsEndShift = PackBitsBeginShift + NameSpaceSizeInBits + PackBitCountBits * PackNumChannels
  val PackSwizzleBeginShift = PackBitsEndShift
  val PackSwizzleEndShift = PackSwizzleBeginShift + PackSwizzleBits * PackNumChannels
  val PackTypeBeginShift = PackSwizzleEndShift
  val PackTypeEndShift = PackTypeBeginShift + PackTypeBits * PackNumChannels
  assert(PackTypeEndShift <= 64);

  // depth stencil definitions
  val DSNumChannels = 2
  val DSTotalSizeBits = log2(DepthStencilTotalSize.values.length)
  val DSBitCountBits = log2(DepthStencilBitCount.values.length)
  val DSSwizzleBits = log2(DepthStencilSwizzle.values.length)
  val DSTypeBits = log2(DepthStencilType.values.length)

  val DSTotalSizeBeginShift = NameSpaceShift
  val DSTotalSizeEndShift = DSTotalSizeBeginShift + DSTotalSizeBits
  val DSBitCountBeginShift = DSTotalSizeEndShift
  val DSBitCountEndShift = DSTotalSizeEndShift + DSBitCountBits * DSNumChannels
  val DSSwizzleBeginShift = DSBitCountEndShift
  val DSSwizzleEndShift = DSTotalSizeEndShift + DSSwizzleBits * DSNumChannels
  val DSTypeBeginShift = DSSwizzleEndShift
  val DSTypeEndShift = DSTotalSizeEndShift + DSTypeBits * DSNumChannels

  // dxtc definitions
  val DxbcTypeBits = log2(DxbcType.values.length)
  val DxbcAlphaBits = log2(DxbcAlpha.values.length)
  val DxbcBlockBytesBits = log2(DxbcBlockBytes.values.length)
  val DxbcChannelCountBits = log2(4)
  val DxbcModeCountBits = log2(DxbcModeCount.values.length)

  val DxbcTypeBeginShift = NameSpaceSizeInBits
  val DxbcTypeEndShift = DxbcTypeBeginShift + DxbcTypeBits
  val DxbcAlphaBeginShift = DxbcTypeEndShift
  val DxbcAlphaEndShift = DxbcAlphaBeginShift + DxbcAlphaBits
  val DxbcBlockBytesBeginShift = DxbcAlphaEndShift
  val DxbcBlockBytesEndShift = DxbcBlockBytesBeginShift + DxbcBlockBytesBits
  val DxbcChannelCountBitsBeginShift = DxbcBlockBytesEndShift
  val DxbcChannelCountBitsEndShift = DxbcChannelCountBitsBeginShift + DxbcChannelCountBits
  val DxbcModeCountBitsBeginShift = DxbcChannelCountBitsEndShift
  val DxbcModeCountBitsEndShift = DxbcModeCountBitsBeginShift + DxbcModeCountBits

  // pvtc
  val PvrtcTypeBits = log2(PvrtcType.values.length)
  val PvrtcVersionBits = log2(PvrtcVersion.values.length)
  val PvrtcBitsBits = log2(PvrtcBits.values.length)

  val PvrtcTypeBeginShift = NameSpaceSizeInBits
  val PvrtcTypeEndShift = PvrtcTypeBeginShift + PvrtcTypeBits
  val PvrtcVersionBeginShift = PvrtcTypeEndShift
  val PvrtcVersionEndShift = PvrtcVersionBeginShift + PvrtcVersionBits
  val PvrtcBitsBeginShift = PvrtcVersionEndShift
  val PvrtcBitsEndShift = PvrtcBitsBeginShift + PvrtcBitsBits

  // etc
  val EtcTypeBits = log2(EtcType.values.length)
  val EtcBitsBits = log2(EtcBits.values.length)
  val EtcAlphaBits = log2(EtcAlpha.values.length)
  val EtcChannelCountBits = log2(4)

  val EtcTypeBeginShift = NameSpaceSizeInBits
  val EtcTypeEndShift = EtcTypeBeginShift + EtcTypeBits
  val EtcBitsBeginShift = EtcTypeEndShift
  val EtcBitsEndShift = EtcBitsBeginShift + EtcBitsBits
  val EtcAlphaBeginShift = EtcBitsEndShift
  val EtcAlphaEndShift = EtcAlphaBeginShift + EtcAlphaBits
  val EtcChannelCountBeginShift = EtcAlphaEndShift
  val EtcChannelCountEndShift = EtcChannelCountBeginShift + EtcChannelCountBits

  // astc
  val AstcTypeBits = log2(AstcType.values.length)
  val AstcSizeBits = log2(AstcSize.values.length)

  val AstcTypeBeginShift = NameSpaceSizeInBits
  val AstcTypeEndShift = AstcTypeBeginShift + AstcTypeBits
  val AstcBlockWidthBeginShift = AstcTypeEndShift
  val AstcBlockWidthEndShift = AstcBlockWidthBeginShift + AstcSizeBits
  val AstcBlockHeightBeginShift = AstcBlockWidthEndShift
  val AstcBlockHeightEndShift = AstcBlockHeightBeginShift + AstcSizeBits
  val AstcBlockDepthBeginShift = AstcBlockHeightEndShift
  val AstcBlockDepthEndShift = AstcBlockDepthBeginShift + AstcSizeBits

  // clut
  val ClutNumChannels = 2
  val ClutBlockSizeBits = log2(ClutBlockSize.values.length)
  val ClutTypeBits = log2(ClutType.values.length)
  val ClutBitsBits = log2(ClutBits.values.length)

  val ClutBlockSizeBeginShift = NameSpaceSizeInBits
  val ClutBlockSizeEndShift = ClutBlockSizeBeginShift + ClutBlockSizeBits
  val ClutTypeBeginShift = ClutBlockSizeEndShift
  val ClutTypeEndShift = ClutTypeBeginShift + ClutNumChannels * ClutTypeBits
  val ClutBitsBeginShift = ClutTypeEndShift
  val ClutBitsEndShift = ClutBitsBeginShift + ClutNumChannels * ClutBitsBits

  def BitCountToMask(bitCount: Int): BigInt = (BigInt(1) << bitCount) - 1

  def ToPosition(v: Int, shift: Int, bitCount: Int): BigInt = (v & BitCountToMask(bitCount)) << shift

  def ComputeCodeForPack(
      special: PackSpecial,
      red: PackColour,
      green: PackColour,
      blue: PackColour,
      alpha: PackColour,
  ) =
    val rc = PhysicalChannel._0.ordinal
    val gc = PhysicalChannel._1.ordinal
    val bc = PhysicalChannel._2.ordinal
    val ac = PhysicalChannel._3.ordinal
    Namespace.Pack.ordinal |
      ToPosition(special.ordinal, PackSpecialBeginShift, PackSpecialBits) |
      ToPosition(red.bitCount, PackBitsBeginShift + rc * PackBitCountBits, PackBitCountBits) |
      ToPosition(green.bitCount, PackBitsBeginShift + gc * PackBitCountBits, PackBitCountBits) |
      ToPosition(blue.bitCount, PackBitsBeginShift + bc * PackBitCountBits, PackBitCountBits) |
      ToPosition(alpha.bitCount, PackBitsBeginShift + ac * PackBitCountBits, PackBitCountBits) |
      ToPosition(red.swizzle.ordinal, PackSwizzleBeginShift + rc * PackSwizzleBits, PackSwizzleBits) |
      ToPosition(green.swizzle.ordinal, PackSwizzleBeginShift + gc * PackSwizzleBits, PackSwizzleBits) |
      ToPosition(blue.swizzle.ordinal, PackSwizzleBeginShift + bc * PackSwizzleBits, PackSwizzleBits) |
      ToPosition(alpha.swizzle.ordinal, PackSwizzleBeginShift + ac * PackSwizzleBits, PackSwizzleBits) |
      ToPosition(red.packType.ordinal, PackTypeBeginShift + rc * PackTypeBits, PackTypeBits) |
      ToPosition(green.packType.ordinal, PackTypeBeginShift + gc * PackTypeBits, PackTypeBits) |
      ToPosition(blue.packType.ordinal, PackTypeBeginShift + bc * PackTypeBits, PackTypeBits) |
      ToPosition(alpha.packType.ordinal, PackTypeBeginShift + ac * PackTypeBits, PackTypeBits)

  def ComputeCodeForDepthStencil(
      totalSize: DepthStencilTotalSize,
      depth: DepthStencilChannel,
      stencil: DepthStencilChannel,
  ) =
    val dc = PhysicalChannel._0.ordinal
    val sc = PhysicalChannel._0.ordinal
    Namespace.DepthStencil.ordinal |
      ToPosition(totalSize.ordinal, DSTotalSizeBeginShift, DSTotalSizeBits) |
      ToPosition(depth.bitCount.ordinal, DSBitCountBeginShift + dc * DSBitCountBits, DSBitCountBits) |
      ToPosition(stencil.bitCount.ordinal, DSBitCountBeginShift + sc * DSBitCountBits, DSBitCountBits) |
      ToPosition(depth.swizzle.ordinal, DSSwizzleBeginShift + dc * DSSwizzleBits, DSSwizzleBits) |
      ToPosition(stencil.swizzle.ordinal, DSSwizzleBeginShift + sc * DSSwizzleBits, DSSwizzleBits) |
      ToPosition(depth.dsType.ordinal, DSTypeBeginShift + dc * DSTypeBits, DSTypeBits) |
      ToPosition(stencil.dsType.ordinal, DSTypeBeginShift + sc * DSTypeBits, DSTypeBits)

  def ComputeCodeForDxbc(
      dxbcType: DxbcType,
      alpha: DxbcAlpha,
      blockBytes: DxbcBlockBytes,
      channelCount: Int,
      modeCount: DxbcModeCount,
  ) =
    Namespace.Dxtc.ordinal |
      ToPosition(dxbcType.ordinal, DxbcTypeBeginShift, DxbcTypeBits) |
      ToPosition(alpha.ordinal, DxbcAlphaBeginShift, DxbcAlphaBits) |
      ToPosition(blockBytes.ordinal, DxbcBlockBytesBeginShift, DxbcBlockBytesBits) |
      ToPosition(channelCount, DxbcChannelCountBitsBeginShift, DxbcChannelCountBits) |
      ToPosition(modeCount.ordinal, DxbcModeCountBitsBeginShift, DxbcModeCountBits)

  def ComputeCodeForPvrtc(
      pvrtcType: PvrtcType,
      version: PvrtcVersion,
      bits: PvrtcBits,
  ) =
    Namespace.Pvrtc.ordinal |
      ToPosition(pvrtcType.ordinal, PvrtcTypeBeginShift, PvrtcTypeBits) |
      ToPosition(version.ordinal, PvrtcVersionBeginShift, PvrtcVersionBits) |
      ToPosition(bits.ordinal, PvrtcBitsBeginShift, PvrtcBitsBits)

  def ComputeCodeForEtc(
      etcType: EtcType,
      bits: EtcBits,
      alpha: EtcAlpha,
      channelCount: Int,
  ) =
    Namespace.Etc.ordinal |
      ToPosition(etcType.ordinal, EtcTypeBeginShift, EtcTypeBits) |
      ToPosition(bits.ordinal, EtcBitsBeginShift, EtcBitsBits) |
      ToPosition(alpha.ordinal, EtcAlphaBeginShift, EtcAlphaBits) |
      ToPosition(channelCount, EtcChannelCountBeginShift, EtcChannelCountBits)

  def ComputeCodeForAstc(
      astcType: AstcType,
      blockWidth: AstcSize,
      blockHeight: AstcSize,
      blockDepth: AstcSize,
  ) =
    Namespace.Astc.ordinal |
      ToPosition(astcType.ordinal, AstcTypeBeginShift, AstcTypeBits) |
      ToPosition(blockWidth.ordinal, AstcBlockWidthBeginShift, AstcSizeBits) |
      ToPosition(blockHeight.ordinal, AstcBlockHeightBeginShift, AstcSizeBits) |
      ToPosition(blockDepth.ordinal, AstcBlockDepthBeginShift, AstcSizeBits)

  def ComputeCodeForClut(
      blockSize: ClutBlockSize,
      chan0: ClutChannel,
      chan1: ClutChannel,
  ) =
    val c0 = PhysicalChannel._0.ordinal
    val c1 = PhysicalChannel._1.ordinal
    Namespace.Clut.ordinal |
      ToPosition(blockSize.ordinal, ClutBlockSizeBeginShift, ClutBlockSizeBits) |
      ToPosition(chan0.clutType.ordinal, ClutTypeBeginShift + c0 * ClutTypeBits, ClutTypeBits) |
      ToPosition(chan1.clutType.ordinal, ClutTypeBeginShift + c1 * ClutTypeBits, ClutTypeBits) |
      ToPosition(chan0.bits.ordinal, ClutBitsBeginShift + c0 * ClutBitsBits, ClutBitsBits) |
      ToPosition(chan1.bits.ordinal, ClutBitsBeginShift + c1 * ClutBitsBits, ClutBitsBits)
