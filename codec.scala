package TinyImageFormatGenerator

enum LogicalChannel(value: Int):
  case Red extends LogicalChannel(0)
  case Green extends LogicalChannel(1)
  case Blue extends LogicalChannel(2)
  case Alpha extends LogicalChannel(3)
  case Depth extends LogicalChannel(4)
  case Stencil extends LogicalChannel(5)
  case Const0 extends LogicalChannel(-1)
  case Const1 extends LogicalChannel(-2)
  override def toString = this match
    case LogicalChannel.Red     => "red"
    case LogicalChannel.Green   => "green"
    case LogicalChannel.Blue    => "blue"
    case LogicalChannel.Alpha   => "alpha"
    case LogicalChannel.Depth   => "depth"
    case LogicalChannel.Stencil => "stencil"
    case LogicalChannel.Const0  => "const0"
    case LogicalChannel.Const1  => "const1"
  def toZig = this match
    case LogicalChannel.Red     => ".Red"
    case LogicalChannel.Green   => ".Green"
    case LogicalChannel.Blue    => ".Blue"
    case LogicalChannel.Alpha   => ".Alpha"
    case LogicalChannel.Depth   => ".Depth"
    case LogicalChannel.Stencil => ".Stencil"
    case LogicalChannel.Const0  => ".Const0"
    case LogicalChannel.Const1  => ".Const1"

enum PhysicalChannel(value: Int):
  case _0 extends PhysicalChannel(0)
  case _1 extends PhysicalChannel(1)
  case _2 extends PhysicalChannel(2)
  case _3 extends PhysicalChannel(3)
  case Const0 extends PhysicalChannel(-1)
  case Const1 extends PhysicalChannel(-2)
  override def toString = this match
    case PhysicalChannel._0     => "position0"
    case PhysicalChannel._1     => "position1"
    case PhysicalChannel._2     => "position2"
    case PhysicalChannel._3     => "positionp3"
    case PhysicalChannel.Const0 => "const0"
    case PhysicalChannel.Const1 => "const1"
  def toZig = this match
    case PhysicalChannel._0     => "._0"
    case PhysicalChannel._1     => "._1"
    case PhysicalChannel._2     => "._2"
    case PhysicalChannel._3     => "._3"
    case PhysicalChannel.Const0 => ".Const0"
    case PhysicalChannel.Const1 => ".Const1"

enum BlockDim:
  case Width
  case Height
  case Depth

enum Namespace:
  case Undefined
  case Pack
  case DepthStencil
  case Dxtc
  case Pvrtc
  case Etc
  case Astc
  case Clut

enum PackBlockSize:
  case _1
  case _2
  case _4
  case _8
  def toInt = this match
    case PackBlockSize._1 => 1
    case PackBlockSize._2 => 2
    case PackBlockSize._4 => 4
    case PackBlockSize._8 => 8

enum PackSwizzle:
  case Red
  case Green
  case Blue
  case Alpha
  case Const0
  case Const1
  def toLogicalChannel = this.match
    case PackSwizzle.Red    => LogicalChannel.Red
    case PackSwizzle.Green  => LogicalChannel.Green
    case PackSwizzle.Blue   => LogicalChannel.Blue
    case PackSwizzle.Alpha  => LogicalChannel.Alpha
    case PackSwizzle.Const0 => LogicalChannel.Const0
    case PackSwizzle.Const1 => LogicalChannel.Const1

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
  def toInt = this match
    case DepthStencilTotalSize._8  => 8
    case DepthStencilTotalSize._16 => 16
    case DepthStencilTotalSize._32 => 32
    case DepthStencilTotalSize._64 => 64

enum DepthStencilBitCount:
  case _0
  case _8
  case _16
  case _24
  case _32
  def toInt = this match
    case DepthStencilBitCount._0  => 0
    case DepthStencilBitCount._8  => 8
    case DepthStencilBitCount._16 => 16
    case DepthStencilBitCount._24 => 24
    case DepthStencilBitCount._32 => 32

enum DepthStencilSwizzle:
  case Depth
  case Stencil
  case Const0
  def toLogicalChannel = this.match
    case DepthStencilSwizzle.Depth   => LogicalChannel.Depth
    case DepthStencilSwizzle.Stencil => LogicalChannel.Stencil
    case DepthStencilSwizzle.Const0  => LogicalChannel.Const0

enum DepthStencilType:
  case None
  case UNorm
  case UInt
  case SFloat
case class DepthStencilChannel(swizzle: DepthStencilSwizzle, bitCount: DepthStencilBitCount, dsType: DepthStencilType)

enum DxtcAlpha:
  case None
  case PunchThrough
  case Block
  case Full

enum DxtcType:
  case UNorm
  case SNorm
  case SRGB
  case SFloat
  case UFloat

enum DxtcBlockBytes:
  case _8
  case _16
  def toInt = this match
    case DxtcBlockBytes._8  => 8
    case DxtcBlockBytes._16 => 16

enum DxtcModeCount:
  case _1
  case _8
  case _14
  def toInt = this match
    case DxtcModeCount._1  => 1
    case DxtcModeCount._8  => 8
    case DxtcModeCount._14 => 14

enum PvrtcType:
  case UNorm
  case SRGB

enum PvrtcVersion:
  case V1
  case V2

enum PvrtcBits:
  case _2
  case _4
  def toInt = this match
    case PvrtcBits._2 => 2
    case PvrtcBits._4 => 4

enum EtcType:
  case UNorm
  case SNorm
  case SRGB

enum EtcBits:
  case _8
  case _11
  def toInt = this match
    case EtcBits._8  => 8
    case EtcBits._11 => 11

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
  def toInt = this match
    case AstcSize._1  => 1
    case AstcSize._4  => 4
    case AstcSize._5  => 5
    case AstcSize._6  => 6
    case AstcSize._8  => 8
    case AstcSize._10 => 10
    case AstcSize._12 => 12

enum ClutBlockSize:
  case _1
  case _2
  case _4
  case _8
  def toInt = this match
    case ClutBlockSize._1 => 1
    case ClutBlockSize._2 => 2
    case ClutBlockSize._4 => 4
    case ClutBlockSize._8 => 8

enum ClutType:
  case None
  case RGB
  case Single
  case ExplicitAlpha

enum ClutBitCount:
  case _0
  case _1
  case _2
  case _4
  case _8
  def toInt = this match
    case ClutBitCount._0 => 0
    case ClutBitCount._1 => 1
    case ClutBitCount._2 => 2
    case ClutBitCount._4 => 4
    case ClutBitCount._8 => 8

case class ClutChannel(clutType: ClutType, bitCount: ClutBitCount)

// namespace definitions
val NameSpaceSizeInBits = 4 // nibble makes it easy to see in debug

// pack type definitions
val PackNumChannels = 4
val PackSpecialBits = log2(PackBlockSize.values.length)
val PackBitCountBits = log2(32)
val PackSwizzleBits = log2(PackSwizzle.values.length)
val PackTypeBits = log2(PackType.values.length)

val PackSpecialBeginShift = NameSpaceSizeInBits
val PackSpecialEndShift = PackSpecialBeginShift + PackSpecialBits
val PackBitsBeginShift = PackSpecialEndShift
val PackBitsEndShift = PackBitsBeginShift + (PackBitCountBits * PackNumChannels)
val PackSwizzleBeginShift = PackBitsEndShift
val PackSwizzleEndShift = PackSwizzleBeginShift + (PackSwizzleBits * PackNumChannels)
val PackTypeBeginShift = PackSwizzleEndShift
val PackTypeEndShift = PackTypeBeginShift + (PackTypeBits * PackNumChannels)

// depth stencil definitions
val DSNumChannels = 2
val DSTotalSizeBits = log2(DepthStencilTotalSize.values.length)
val DSBitCountBits = log2(DepthStencilBitCount.values.length)
val DSSwizzleBits = log2(DepthStencilSwizzle.values.length)
val DSTypeBits = log2(DepthStencilType.values.length)

val DSTotalSizeBeginShift = NameSpaceSizeInBits
val DSTotalSizeEndShift = DSTotalSizeBeginShift + DSTotalSizeBits
val DSBitCountBeginShift = DSTotalSizeEndShift
val DSBitCountEndShift = DSTotalSizeEndShift + DSBitCountBits * DSNumChannels
val DSSwizzleBeginShift = DSBitCountEndShift
val DSSwizzleEndShift = DSTotalSizeEndShift + DSSwizzleBits * DSNumChannels
val DSTypeBeginShift = DSSwizzleEndShift
val DSTypeEndShift = DSTotalSizeEndShift + DSTypeBits * DSNumChannels

// dxtc definitions
val DxtcTypeBits = log2(DxtcType.values.length)
val DxtcAlphaBits = log2(DxtcAlpha.values.length)
val DxtcBlockBytesBits = log2(DxtcBlockBytes.values.length)
val DxtcChannelCountBits = log2(4)
val DxtcModeCountBits = log2(DxtcModeCount.values.length)

val DxtcTypeBeginShift = NameSpaceSizeInBits
val DxtcTypeEndShift = DxtcTypeBeginShift + DxtcTypeBits
val DxtcAlphaBeginShift = DxtcTypeEndShift
val DxtcAlphaEndShift = DxtcAlphaBeginShift + DxtcAlphaBits
val DxtcBlockBytesBeginShift = DxtcAlphaEndShift
val DxtcBlockBytesEndShift = DxtcBlockBytesBeginShift + DxtcBlockBytesBits
val DxtcChannelCountBitsBeginShift = DxtcBlockBytesEndShift
val DxtcChannelCountBitsEndShift = DxtcChannelCountBitsBeginShift + DxtcChannelCountBits
val DxtcModeCountBitsBeginShift = DxtcChannelCountBitsEndShift
val DxtcModeCountBitsEndShift = DxtcModeCountBitsBeginShift + DxtcModeCountBits

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
val ClutBitsBits = log2(ClutBitCount.values.length)

val ClutBlockSizeBeginShift = NameSpaceSizeInBits
val ClutBlockSizeEndShift = ClutBlockSizeBeginShift + ClutBlockSizeBits
val ClutTypeBeginShift = ClutBlockSizeEndShift
val ClutTypeEndShift = ClutTypeBeginShift + ClutNumChannels * ClutTypeBits
val ClutBitsBeginShift = ClutTypeEndShift
val ClutBitsEndShift = ClutBitsBeginShift + ClutNumChannels * ClutBitsBits

def log2(x: Int) = math.ceil(scala.math.log(x) / scala.math.log(2)).toInt
def BitCountToMask(bitCount: Int): BigInt = (BigInt(1) << bitCount) - 1
def ToPosition(v: Int, shift: Int, bitCount: Int): BigInt = (v & BitCountToMask(bitCount)) << shift

extension (b: Boolean) def toInt: Int = if b then 1 else 0

enum GeneratorCode(val code: BigInt):
  def IsInPackNamespace = namespace == Namespace.Pack
  def IsInDepthStencilNamespace = namespace == Namespace.DepthStencil
  def IsInDxtcNamespace = namespace == Namespace.Dxtc
  def IsInPvrtcNamespace = namespace == Namespace.Pvrtc
  def IsInEtcNamespace = namespace == Namespace.Etc
  def IsInAstcNamespace = namespace == Namespace.Astc
  def IsInClutNamespace = namespace == Namespace.Clut

  def namespace = Namespace.fromOrdinal((code & BitCountToMask(NameSpaceSizeInBits)).toInt)

  def IsUFloat(c: PackColour) =
    if c.bitCount == 0 then false
    else c.packType == PackType.UFloat
  def IsSBFloat(c: PackColour) =
    if c.bitCount == 0 then false
    else c.packType == PackType.SBFloat

  def IsFloat(c: PackColour) =
    if c.bitCount == 0 then false
    else c.packType == PackType.SFloat || c.packType == PackType.UFloat || c.packType == PackType.SBFloat
  def IsNormalised(c: PackColour) =
    if c.bitCount == 0 then false
    else c.packType == PackType.UNorm || c.packType == PackType.SNorm || c.packType == PackType.SRGB
  def IsSigned(c: PackColour) =
    if c.bitCount == 0 then false
    else
      c.packType == PackType.SNorm || c.packType == PackType.SInt || c.packType == PackType.SFloat || c.packType == PackType.SBFloat
  def IsSRGB(c: PackColour) =
    if c.bitCount == 0 then false
    else c.packType == PackType.SRGB
  def GetBlockDim(p: Pack, blockDim: BlockDim) = if blockDim == BlockDim.Width then p.blockSize.toInt else 1
  def GetChannelCount(p: Pack) = Math.min(p.channel0.bitCount.toInt, 1) + Math.min(p.channel1.bitCount.toInt, 1) +
    Math.min(p.channel2.bitCount.toInt, 1) + Math.min(p.channel3.bitCount.toInt, 1)
  def GetPerChannelBitWidth(p: Pack, channelIndex: Int) = channelIndex match
    case 0 => p.channel0.bitCount
    case 1 => p.channel1.bitCount
    case 2 => p.channel2.bitCount
    case 3 => p.channel3.bitCount
    case _ => 0
  def GetMinimumAtChannel(c: PackColour): Double = c.packType match
    case PackType.None   => 0.0
    case PackType.UNorm  => 0.0
    case PackType.SNorm  => -1.0
    case PackType.SRGB   => 0.0
    case PackType.UInt   => 0.0
    case PackType.SInt   => -math.pow(2, c.bitCount - 1)
    case PackType.UFloat => 0.0
    case PackType.SFloat =>
      c.bitCount match
        case 16 => -65504.0
        case 32 => -Float.MaxValue
        case 64 => -Double.MaxValue
        case _  => 0.0
    case PackType.SBFloat => -Float.MaxValue
  def GetMaximumAtChannel(c: PackColour): Double = c.packType match
    case PackType.None  => 1.0
    case PackType.UNorm => 1.0
    case PackType.SNorm => 1.0
    case PackType.SRGB  => 1.0
    case PackType.UInt  => math.pow(2, c.bitCount) - 1.0
    case PackType.SInt  => math.pow(2, c.bitCount - 1) - 1.0
    case PackType.UFloat =>
      c.bitCount match
        case 16 => 65504.0
        case 32 => Float.MaxValue
        case 64 => Double.MaxValue
        case _  => 0.0
    case PackType.SFloat =>
      c.bitCount match
        case 16 => 65504.0
        case 32 => Float.MaxValue
        case 64 => Double.MaxValue
        case _  => 0.0
    case PackType.SBFloat => Float.MaxValue
  def GetPerChannelMinimum(p: Pack, channelIndex: Int): Double =
    channelIndex match
      case 0 => GetMinimumAtChannel(p.channel0)
      case 1 => GetMinimumAtChannel(p.channel1)
      case 2 => GetMinimumAtChannel(p.channel2)
      case 3 => GetMinimumAtChannel(p.channel3)
      case _ => 0.0
  def GetPerChannelMaximum(p: Pack, channelIndex: Int): Double =
    channelIndex match
      case 0 => GetMaximumAtChannel(p.channel0)
      case 1 => GetMaximumAtChannel(p.channel1)
      case 2 => GetMaximumAtChannel(p.channel2)
      case 3 => GetMaximumAtChannel(p.channel3)
      case _ => 1.0
  def GetByteSizeOfBlock(p: Pack) =
    val baseSize = p.channel0.bitCount + p.channel1.bitCount + p.channel2.bitCount + p.channel3.bitCount
    p.blockSize match
      case PackBlockSize._1 => (baseSize * 1) / 8
      case PackBlockSize._2 => (baseSize * 2) / 8
      case PackBlockSize._4 => (baseSize * 4) / 8
      case PackBlockSize._8 => (baseSize * 8) / 8
  def IsHomogenous(p: Pack): Boolean =
    val chk0 = p.channel0.packType != PackType.None
    val chk1 = p.channel1.packType != PackType.None
    val chk2 = p.channel2.packType != PackType.None
    val chk3 = p.channel3.packType != PackType.None
    val chkCount = GetChannelCount(p)
    val fmt: PackType =
      if chk0 then p.channel0.packType
      else if chk1 then p.channel1.packType
      else if chk2 then p.channel2.packType
      else if chk3 then p.channel3.packType
      else { return false }
    val bits: Int =
      if chk0 then p.channel0.bitCount
      else if chk1 then p.channel1.bitCount
      else if chk2 then p.channel2.bitCount
      else if chk3 then p.channel3.bitCount
      else { return false }

    val packCount = chkCount ==
      (chk0 && p.channel0.packType == fmt).toInt + (chk1 && p.channel1.packType == fmt).toInt +
      (chk2 && p.channel2.packType == fmt).toInt + (chk3 && p.channel3.packType == fmt).toInt
    val bitCount =
      chkCount == (chk0 && p.channel0.bitCount == bits).toInt + (chk1 && p.channel1.bitCount == bits).toInt +
        (chk2 && p.channel2.bitCount == bits).toInt + (chk3 && p.channel3.bitCount == bits).toInt
    packCount && bitCount
  def GetPhysicalToLogical(p: Pack, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => p.channel0.swizzle.toLogicalChannel
      case PhysicalChannel._1     => p.channel1.swizzle.toLogicalChannel
      case PhysicalChannel._2     => p.channel2.swizzle.toLogicalChannel
      case PhysicalChannel._3     => p.channel3.swizzle.toLogicalChannel
      case PhysicalChannel.Const0 => LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
  def FindLogicalChannel(p: Pack, channel: LogicalChannel) =
    if p.channel0.swizzle.toLogicalChannel == channel then PhysicalChannel._0
    else if p.channel1.swizzle.toLogicalChannel == channel then PhysicalChannel._1
    else if p.channel2.swizzle.toLogicalChannel == channel then PhysicalChannel._2
    else if p.channel3.swizzle.toLogicalChannel == channel then PhysicalChannel._3
    else if channel == LogicalChannel.Alpha then PhysicalChannel.Const1
    else PhysicalChannel.Const0

  def GetLogicalToPhysical(p: Pack, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0 => PhysicalChannel.Const0
      case LogicalChannel.Const1 => PhysicalChannel.Const1
      case _                     => FindLogicalChannel(p, channel)
  def GetChannelFromPhysicalChannel(p: Pack, channel: PhysicalChannel): (Option[PackColour], Int) =
    channel match
      case PhysicalChannel._0     => (Some(p.channel0), 0)
      case PhysicalChannel._1     => (Some(p.channel1), 0)
      case PhysicalChannel._2     => (Some(p.channel2), 0)
      case PhysicalChannel._3     => (Some(p.channel3), 0)
      case PhysicalChannel.Const0 => (None, 0)
      case PhysicalChannel.Const1 => (None, 1)

  def GetDataTypeForPhysicalChannel(p: Pack, channel: PhysicalChannel): String =
    val c = channel match
      case PhysicalChannel._0     => p.channel0
      case PhysicalChannel._1     => p.channel1
      case PhysicalChannel._2     => p.channel2
      case PhysicalChannel._3     => p.channel3
      case PhysicalChannel.Const0 => return ""
      case PhysicalChannel.Const1 => return ""
    val dataType = c.packType match
      case PackType.None                                      => ""
      case PackType.UNorm | PackType.UInt                     => "u"
      case PackType.SInt | PackType.SNorm                     => "i"
      case PackType.SFloat                                    => "f"
      case PackType.SBFloat | PackType.UFloat | PackType.SRGB => "u" // TODO Special cases
    s"${dataType}${c.bitCount}"

  def GetChannelFromLogicalChannel(p: Pack, channel: LogicalChannel): (Option[PackColour], Int) =
    GetChannelFromPhysicalChannel(p, GetLogicalToPhysical(p, channel))

  def GetDataTypeForLogicalChannel(p: Pack, channel: LogicalChannel): String =
    GetDataTypeForPhysicalChannel(p, GetLogicalToPhysical(p, channel))

  def GetEncodeMultiplierForLogicalChannel(p: Pack, channel: LogicalChannel): String =
    val chan = GetChannelFromLogicalChannel(p, channel)
    chan._1 match
      case None => "1.0"
      case Some(c) =>
        c.packType match
          case PackType.UNorm | PackType.UInt => s"${Math.pow(2.0, c.bitCount) - 1}"
          case PackType.SInt | PackType.SNorm => s"${Math.pow(2.0, c.bitCount - 1) - 0.5}"
          case PackType.SFloat                => "1.0"
          case PackType.UFloat                => "0.0"
          case PackType.SRGB                  => "0.0"
          case PackType.SBFloat               => "0.0"
          case _                              => "0.0"

  def GetDecodeMultiplierForLogicalChannel(p: Pack, channel: LogicalChannel): String =
    val chan = GetChannelFromLogicalChannel(p, channel)
    chan._1 match
      case None => "1.0"
      case Some(c) =>
        c.packType match
          case PackType.UNorm => s"${1.0 / (Math.pow(2.0, c.bitCount) - 1)}"
          case PackType.SNorm => s"${1.0 / (Math.pow(2.0, c.bitCount - 1) - 0.5)}"
          case _              => "1.0"

  def GetNeedDecodeMultiplier(p: Pack): Boolean =
    GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Red) != "1.0" ||
      GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Green) != "1.0" ||
      GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Blue) != "1.0" ||
      GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Alpha) != "1.0"
  def GetNeedEncodeMultiplier(p: Pack): Boolean =
    GetEncodeMultiplierForLogicalChannel(p, LogicalChannel.Red) != "1.0" ||
      GetEncodeMultiplierForLogicalChannel(p, LogicalChannel.Green) != "1.0" ||
      GetEncodeMultiplierForLogicalChannel(p, LogicalChannel.Blue) != "1.0" ||
      GetEncodeMultiplierForLogicalChannel(p, LogicalChannel.Alpha) != "1.0"

  def FetchPixelF(p: Pack, outputBitWidth: Int, spaces: String): String =
    var comma = ""
    val structChannelFunc = (c: PackColour) =>
      c.swizzle match
        case PackSwizzle.Const0 | PackSwizzle.Const1 => ""
        case swiz =>
          var ret = String()
          for i <- 0 until p.blockSize.toInt do
            val s =
              s"$comma ${c.swizzle.toString.toLowerCase}$i: ${GetDataTypeForLogicalChannel(p, swiz.toLogicalChannel)}"
            comma = ","
            ret = ret + s
          ret

    val zigCastFunc = (p: Pack, channel: LogicalChannel, index: Int, floatSize: Int) =>
      val (ch, constant) = GetChannelFromLogicalChannel(p, channel)
      ch match
        case None => s"${constant}.0"
        case Some(c) =>
          val chan = c.swizzle.toLogicalChannel.toString.toLowerCase
          c.packType match
            case PackType.None => "0.0"
            case PackType.UNorm | PackType.SNorm | PackType.UInt | PackType.SInt =>
              s"@intToFloat(f$floatSize, s.$chan$index)"
            case PackType.SRGB => s"sRGBTable[s.$chan$index]"
            case PackType.UFloat =>
              c.bitCount match
                case _ => s"@intToFloat(f$floatSize, s.$chan$index)" // TODO
            case PackType.SBFloat =>
              if floatSize == 32 then s"@bitCast(f32, (@as(u32, s.$chan$index) << 16) | 0)"
              else "NOT SUPPORTED"
            case PackType.SFloat =>
              c.bitCount match
                case 16 | 32 | 64 | 80 | 128 =>
                  if c.bitCount == floatSize then s"s.$chan$index" else s"@floatCast(f$floatSize, s.$chan$index)"
                case _ => "NOT SUPPORTED"
    var sb = StringBuilder()
    sb ++= s"${spaces}const format = packed struct {"
    sb ++= structChannelFunc(p.channel0)
    sb ++= structChannelFunc(p.channel1)
    sb ++= structChannelFunc(p.channel2)
    sb ++= structChannelFunc(p.channel3)
    sb ++= s" };\n"

    sb ++= s"${spaces}const src = @ptrCast([*]const format, @alignCast(@alignOf(format), input.plane0))[0..(input.plane0.len / @sizeOf(format))];\n"
    sb ++= s"${spaces}std.debug.assert(src.len != 0);\n"
    sb ++= s"${spaces}for (src) |s, i| {\n"

    val numOuts = p.blockSize.toInt
    for i <- 0 until numOuts do
      sb ++= s"${spaces}    output[(i * $numOuts) + $i] = @Vector(4, f32){ "
      sb ++= s"${zigCastFunc(p, LogicalChannel.Red, i, 32)}, "
      sb ++= s"${zigCastFunc(p, LogicalChannel.Green, i, 32)}, "
      sb ++= s"${zigCastFunc(p, LogicalChannel.Blue, i, 32)}, "
      sb ++= s"${zigCastFunc(p, LogicalChannel.Alpha, i, 32)}"
      sb ++= s" }"
      if GetNeedDecodeMultiplier(p) then
        val rm = GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Red)
        val gm = GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Green)
        val bm = GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Blue)
        val am = GetDecodeMultiplierForLogicalChannel(p, LogicalChannel.Alpha)
        sb ++= s" * @Vector(4, f32){ $rm, $gm, $bm, $am }"

      sb ++= ";\n"

    sb ++= s"${spaces}}\n"
    sb.result()

  def PutPixelF(p: Pack, outputBitWidth: Int, spaces: String): String =
    var sb = StringBuilder()
    var comma = ""
    val structChannelFunc = (c: PackColour) =>
      c.swizzle match
        case PackSwizzle.Const0 | PackSwizzle.Const1 => ""
        case swiz =>
          var ret = String()
          for i <- 0 until p.blockSize.toInt do
            val s =
              s"$comma ${c.swizzle.toString.toLowerCase}$i: ${GetDataTypeForLogicalChannel(p, swiz.toLogicalChannel)}"
            comma = ","
            ret = ret + s
          ret

    val zigCastFunc = (p: Pack, channel: LogicalChannel, index: Int, floatSize: Int) =>
      val (ch, constant) = GetChannelFromLogicalChannel(p, channel)
      ch match
        case None => ""
        case Some(c) =>
          val chan = c.swizzle.toLogicalChannel.toString.toLowerCase
          val mult = GetEncodeMultiplierForLogicalChannel(p, channel)

          c.packType match
            case PackType.None => ""
            case PackType.UNorm | PackType.SNorm =>
              s".$chan$index = @floatToInt(${GetDataTypeForLogicalChannel(p, channel)}, input[si + $index][${channel.ordinal}] * $mult), "
            case PackType.UInt | PackType.SInt =>
              s".$chan$index = @floatToInt(${GetDataTypeForLogicalChannel(p, channel)}, input[si + $index][${channel.ordinal}]), "
            case PackType.SRGB => s".$chan$index = f32ToSrgb(@floatCast(f32, input[si + $index][${channel.ordinal}])), "
            case PackType.UFloat =>
              c.bitCount match
                case _ => s".@intToFloat(f$floatSize, s.$chan$index)" // TODO
            case PackType.SBFloat =>
              if floatSize == 32 then
                s".$chan$index = @truncate(u16, (@bitCast(u32, input[si + $index][${channel.ordinal}]) >> 16)), "
              else "NOT SUPPORTED"
            case PackType.SFloat =>
              c.bitCount match
                case 16 | 32 | 64 | 80 | 128 =>
                  if c.bitCount == floatSize then s".$chan$index = input[si + $index][${channel.ordinal}], "
                  else s".$chan$index = @floatCast(f${c.bitCount}, input[si + $index][${channel.ordinal}]), "
                case _ => "NOT SUPPORTED"

    sb ++= s"${spaces}const format = packed struct {"
    sb ++= structChannelFunc(p.channel0)
    sb ++= structChannelFunc(p.channel1)
    sb ++= structChannelFunc(p.channel2)
    sb ++= structChannelFunc(p.channel3)
    sb ++= s" };\n"

    sb ++= s"${spaces}const dest = @ptrCast([*] format, @alignCast(@alignOf(format), output.plane0))[0..(output.plane0.len/${p.blockSize.toInt})];\n"
    sb ++= s"${spaces}std.debug.assert(dest.len != 0);\n"

    sb ++= s"${spaces}var si : usize = 0;\n"
    sb ++= s"${spaces}var di : usize = 0;\n"
    sb ++= s"${spaces}while(si < input.len) : ({si += ${p.blockSize.toInt}; di +=1;}) {\n"
    sb ++= s"${spaces}   dest[di] = format{ "
    for i <- 0 until p.blockSize.toInt do
      sb ++= s"${zigCastFunc(p, LogicalChannel.Red, i, 32)}"
      sb ++= s"${zigCastFunc(p, LogicalChannel.Green, i, 32)}"
      sb ++= s"${zigCastFunc(p, LogicalChannel.Blue, i, 32)}"
      sb ++= s"${zigCastFunc(p, LogicalChannel.Alpha, i, 32)}"
    sb ++= "};\n"

    sb ++= s"${spaces}}\n"
    sb.result()

  def FetchPixel(p: Pack, isOutputFloat: Boolean, outputBitWidth: Int, spaces: String): Option[String] =
    if p.channel0.bitCount > outputBitWidth ||
      p.channel1.bitCount > outputBitWidth ||
      p.channel2.bitCount > outputBitWidth ||
      p.channel3.bitCount > outputBitWidth
    then return None

    // TODO decode some of the odder formats
    val hasUFloat = IsUFloat(p.channel0) || IsUFloat(p.channel1) || IsUFloat(p.channel2) || IsUFloat(p.channel3)
    if hasUFloat then return None

    // if has float and we aren't output float so no
    val hasFloat = IsFloat(p.channel0) || IsFloat(p.channel1) || IsFloat(p.channel2) || IsFloat(p.channel3)
    if hasFloat && !isOutputFloat then return None
    if isOutputFloat then Some(FetchPixelF(p, outputBitWidth, spaces))
    else return None // TODO fetching to non float buffers

  def PutPixel(p: Pack, outputBitWidth: Int, spaces: String): Option[String] =
    if p.channel0.bitCount > outputBitWidth ||
      p.channel1.bitCount > outputBitWidth ||
      p.channel2.bitCount > outputBitWidth ||
      p.channel3.bitCount > outputBitWidth
    then return None

    // TODO decode some of the odder formats
    val hasUFloat = IsUFloat(p.channel0) || IsUFloat(p.channel1) || IsUFloat(p.channel2) || IsUFloat(p.channel3)
    if hasUFloat then return None

    Some(PutPixelF(p, outputBitWidth, spaces))

  // depth stencil query functions
  def IsFloat(c: DepthStencilChannel) =
    if c.bitCount == DepthStencilBitCount._0 then false
    else c.dsType == DepthStencilType.SFloat
  def IsNormalised(c: DepthStencilChannel) =
    if c.bitCount == DepthStencilBitCount._0 then false
    else c.dsType == DepthStencilType.UNorm
  def IsSigned(c: DepthStencilChannel) =
    if c.bitCount == DepthStencilBitCount._0 then false
    else c.dsType == DepthStencilType.SFloat
  def IsSRGB(c: DepthStencilChannel) = false
  def GetBlockDim(ds: DepthStencil, blockDim: BlockDim) = 1
  def GetChannelCount(ds: DepthStencil) =
    Math.min(ds.channel0.bitCount.toInt, 1) + Math.min(ds.channel1.bitCount.toInt, 1)
  def GetPerChannelBitWidth(ds: DepthStencil, channelIndex: Int) = channelIndex match
    case 0 => ds.channel0.bitCount.toInt
    case 1 => ds.channel1.bitCount.toInt
    case _ => 0
  def GetMinimumAtChannel(c: DepthStencilChannel): Double = c.dsType match
    case DepthStencilType.None  => 0.0
    case DepthStencilType.UNorm => 0.0
    case DepthStencilType.UInt  => 0.0
    case DepthStencilType.SFloat =>
      c.bitCount match
        case DepthStencilBitCount._0  => 0.0
        case DepthStencilBitCount._8  => 0.0 // not supported
        case DepthStencilBitCount._16 => -65404
        case DepthStencilBitCount._24 => 0.0 // not supported
        case DepthStencilBitCount._32 => -Float.MaxValue
  def GetMaximumAtChannel(c: DepthStencilChannel): Double = c.dsType match
    case DepthStencilType.None  => 1.0
    case DepthStencilType.UNorm => 1.0
    case DepthStencilType.UInt  => math.pow(2, c.bitCount.toInt) - 1.0
    case DepthStencilType.SFloat =>
      c.bitCount match
        case DepthStencilBitCount._0  => 1.0
        case DepthStencilBitCount._8  => 1.0 // not supported
        case DepthStencilBitCount._16 => 65404
        case DepthStencilBitCount._24 => 1.0 // not supported
        case DepthStencilBitCount._32 => Float.MaxValue
  def GetPerChannelMinimum(c: DepthStencil, channelIndex: Int): Double =
    channelIndex match
      case 0 => GetMinimumAtChannel(c.channel0)
      case 1 => GetMinimumAtChannel(c.channel1)
      case _ => 0.0
  def GetPerChannelMaximum(c: DepthStencil, channelIndex: Int): Double =
    channelIndex match
      case 0 => GetMaximumAtChannel(c.channel0)
      case 1 => GetMaximumAtChannel(c.channel1)
      case _ => 1.0
  def GetByteSizeOfBlock(ds: DepthStencil) = ds.totalSize.toInt / 8
  def IsHomogenous(ds: DepthStencil): Boolean = GetChannelCount(ds) == 1

  def GetPhysicalToLogical(ds: DepthStencil, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => ds.channel0.swizzle.toLogicalChannel
      case PhysicalChannel._1     => ds.channel1.swizzle.toLogicalChannel
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def FindLogicalChannel(ds: DepthStencil, channel: LogicalChannel) =
    if ds.channel0.swizzle.toLogicalChannel == channel then PhysicalChannel._0
    else if ds.channel1.swizzle.toLogicalChannel == channel then PhysicalChannel._1
    else PhysicalChannel.Const0
  def GetLogicalToPhysical(ds: DepthStencil, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0 => PhysicalChannel.Const0
      case LogicalChannel.Const1 => PhysicalChannel.Const1
      case _                     => FindLogicalChannel(ds, channel)

  // dxtc query functions
  def IsFloat(d: Dxtc) = d.dxtcType == DxtcType.SFloat || d.dxtcType == DxtcType.UFloat
  def IsNormalised(d: Dxtc) =
    d.dxtcType == DxtcType.UNorm || d.dxtcType == DxtcType.SNorm || d.dxtcType == DxtcType.SRGB
  def IsSigned(d: Dxtc) = false
  def IsSRGB(d: Dxtc) = d.dxtcType == DxtcType.SRGB
  def GetBlockDim(d: Dxtc, blockDim: BlockDim) = blockDim match
    case BlockDim.Width  => 4
    case BlockDim.Height => 4
    case BlockDim.Depth  => 1
  def GetChannelCount(d: Dxtc) = d.channelCount
  // compressed formats bit width per channel can be hard so these values are crude and possible wrong!
  def GetPerChannelBitWidth(d: Dxtc, channelIndex: Int) =
    if GetChannelCount(d) < 3 then 8 // BC 4 and 5 are 8 bit
    else if channelIndex != 3 && (d.dxtcType == DxtcType.UFloat || d.dxtcType == DxtcType.SFloat) then
      9 // BC 6 3 channel XR
    else if channelIndex != 3 && d.modeCount != DxtcModeCount._1 then 8 // BC 7 is effectively 8 bit
    else
      channelIndex match
        case 0 => 5
        case 1 => 6
        case 2 => 5 // BC 1 2 3 are 565 bit colour channels
        case _ =>
          d.alpha match
            case TinyImageFormatGenerator.DxtcAlpha.None => 0
            case DxtcAlpha.PunchThrough                  => 1
            case DxtcAlpha.Block                         => 4
            case DxtcAlpha.Full                          => 8
  def GetPerChannelMinimum(d: Dxtc, channelIndex: Int): Double =
    d.dxtcType match
      case DxtcType.UNorm  => 0.0
      case DxtcType.SNorm  => -1.0
      case DxtcType.SRGB   => 0.0
      case DxtcType.SFloat => -Float.MaxValue
      case DxtcType.UFloat => 0.0
  def GetPerChannelMaximum(d: Dxtc, channelIndex: Int): Double =
    d.dxtcType match
      case DxtcType.UNorm  => 1.0
      case DxtcType.SNorm  => 1.0
      case DxtcType.SRGB   => 1.0
      case DxtcType.SFloat => Float.MaxValue
      case DxtcType.UFloat => Float.MaxValue
  def GetByteSizeOfBlock(d: Dxtc) = d.blockBytes match
    case DxtcBlockBytes._8  => 8
    case DxtcBlockBytes._16 => 16
  def IsHomogenous(d: Dxtc): Boolean = false
  def GetPhysicalToLogical(d: Dxtc, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => LogicalChannel.Red
      case PhysicalChannel.Const0 => LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def GetLogicalToPhysical(d: Dxtc, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0  => PhysicalChannel.Const0
      case LogicalChannel.Const1  => PhysicalChannel.Const1
      case LogicalChannel.Depth   => PhysicalChannel.Const0
      case LogicalChannel.Stencil => PhysicalChannel.Const0
      case _                      => PhysicalChannel._0

  // pvrtc query functions
  def IsFloat(p: Pvrtc) = false
  def IsNormalised(p: Pvrtc) = p.pvrtcType == PvrtcType.UNorm || p.pvrtcType == PvrtcType.SRGB
  def IsSigned(p: Pvrtc) = false
  def IsSRGB(p: Pvrtc) = p.pvrtcType == PvrtcType.SRGB
  def GetBlockDim(p: Pvrtc, blockDim: BlockDim) = blockDim match
    case BlockDim.Width  => p.bits.toInt
    case BlockDim.Height => 4
    case BlockDim.Depth  => 1
  def GetChannelCount(p: Pvrtc) = 4
  def GetPerChannelBitWidth(p: Pvrtc, channelIndex: Int) = 5 // pvrtc is 3 - 5 bits per block
  def GetPerChannelMinimum(p: Pvrtc, channelIndex: Int): Double = 0.0
  def GetPerChannelMaximum(p: Pvrtc, channelIndex: Int): Double = 1.0
  def GetByteSizeOfBlock(p: Pvrtc) = 64 / 8
  def IsHomogenous(p: Pvrtc): Boolean = false
  def GetPhysicalToLogical(p: Pvrtc, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => LogicalChannel.Red
      case PhysicalChannel.Const0 => LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def GetLogicalToPhysical(p: Pvrtc, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0  => PhysicalChannel.Const0
      case LogicalChannel.Const1  => PhysicalChannel.Const1
      case LogicalChannel.Depth   => PhysicalChannel.Const0
      case LogicalChannel.Stencil => PhysicalChannel.Const0
      case _                      => PhysicalChannel._0

  // etc query functions
  def IsFloat(e: Etc) = false
  def IsNormalised(e: Etc) = e.etcType == EtcType.UNorm || e.etcType == EtcType.SNorm || e.etcType == EtcType.SRGB
  def IsSigned(e: Etc) = e.etcType == EtcType.SNorm
  def IsSRGB(e: Etc) = e.etcType == EtcType.SRGB
  def GetBlockDim(e: Etc, blockDim: BlockDim) = blockDim match
    case BlockDim.Width  => 4
    case BlockDim.Height => 4
    case BlockDim.Depth  => 1
  def GetChannelCount(e: Etc) = e.channelCount
  def GetPerChannelBitWidth(e: Etc, channelIndex: Int) = e.bits match
    case EtcBits._8 =>
      if channelIndex == 3 then
        e.alpha match
          case TinyImageFormatGenerator.EtcAlpha.None => 0
          case EtcAlpha.PunchThrough                  => 1
          case EtcAlpha.Block                         => 3
      else 5 // unsure what the put here on Etc
    case EtcBits._11 => if channelIndex < 2 then 11 else 0
  def GetPerChannelMinimum(e: Etc, channelIndex: Int): Double =
    e.etcType match
      case EtcType.UNorm => 0.0
      case EtcType.SNorm => -1.0
      case EtcType.SRGB  => 0.0
  def GetPerChannelMaximum(e: Etc, channelIndex: Int): Double = 1.0
  def GetByteSizeOfBlock(e: Etc) = 64 / 8
  def IsHomogenous(e: Etc): Boolean = false
  def GetPhysicalToLogical(e: Etc, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => LogicalChannel.Red
      case PhysicalChannel.Const0 => LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def GetLogicalToPhysical(e: Etc, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0  => PhysicalChannel.Const0
      case LogicalChannel.Const1  => PhysicalChannel.Const1
      case LogicalChannel.Depth   => PhysicalChannel.Const0
      case LogicalChannel.Stencil => PhysicalChannel.Const0
      case _                      => PhysicalChannel._0

  // astc query functions
  def IsFloat(a: Astc) = false
  def IsNormalised(a: Astc) = a.astcType == AstcType.UNorm || a.astcType == AstcType.SRGB
  def IsSigned(a: Astc) = false
  def IsSRGB(a: Astc) = a.astcType == AstcType.SRGB
  def GetBlockDim(a: Astc, blockDim: BlockDim) =
    val size = blockDim match
      case BlockDim.Width  => a.blockWidth
      case BlockDim.Height => a.blockHeight
      case BlockDim.Depth  => a.blockDepth
    size.toInt
  def GetChannelCount(a: Astc) = 4
  def GetPerChannelBitWidth(a: Astc, channelIndex: Int) = 8 // TODO
  def GetPerChannelMinimum(a: Astc, channelIndex: Int): Double = 0.0
  def GetPerChannelMaximum(a: Astc, channelIndex: Int): Double = 1.0
  def GetByteSizeOfBlock(a: Astc) = 128 / 8
  def IsHomogenous(a: Astc): Boolean = false
  def GetPhysicalToLogical(a: Astc, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0     => LogicalChannel.Red
      case PhysicalChannel.Const0 => LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def GetLogicalToPhysical(a: Astc, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Const0  => PhysicalChannel.Const0
      case LogicalChannel.Const1  => PhysicalChannel.Const1
      case LogicalChannel.Depth   => PhysicalChannel.Const0
      case LogicalChannel.Stencil => PhysicalChannel.Const0
      case _                      => PhysicalChannel._0

  // clut query functions
  def IsFloat(c: Clut) = false
  def IsNormalised(c: Clut) = true
  def IsSigned(c: Clut) = false
  def IsSRGB(c: Clut) = true
  def GetBlockDim(c: Clut, blockDim: BlockDim) = if blockDim == BlockDim.Width then c.blockSize.toInt else 1
  def GetChannelCount(c: Clut) = Math.min(c.channel0.bitCount.toInt, 1) + Math.min(c.channel1.bitCount.toInt, 1)
  def GetPerChannelBitWidth(c: Clut, channelIndex: Int) =
    channelIndex match
      case 0 => c.channel0.bitCount.toInt
      case 1 => c.channel1.bitCount.toInt
      case _ => 0
  def GetPerChannelMinimum(c: Clut, channelIndex: Int): Double = 0.0
  def GetPerChannelMaximum(c: Clut, channelIndex: Int): Double = 1.0
  def GetByteSizeOfBlock(c: Clut) = (c.channel0.bitCount.toInt + c.channel1.bitCount.toInt * c.blockSize.toInt) / 8
  def IsHomogenous(c: Clut): Boolean = false
  def GetPhysicalToLogical(c: Clut, channel: PhysicalChannel) =
    channel match
      case PhysicalChannel._0 =>
        if c.channel0.clutType == ClutType.RGB || c.channel0.clutType == ClutType.Single then LogicalChannel.Red
        else LogicalChannel.Const0
      case PhysicalChannel._1 =>
        if c.channel1.clutType == ClutType.ExplicitAlpha then LogicalChannel.Alpha else LogicalChannel.Const0
      case PhysicalChannel.Const1 => LogicalChannel.Const1
      case _                      => LogicalChannel.Const0
  def GetLogicalToPhysical(c: Clut, channel: LogicalChannel) =
    channel match
      case LogicalChannel.Red | LogicalChannel.Green | LogicalChannel.Blue => PhysicalChannel._0
      case LogicalChannel.Alpha =>
        if c.channel1.clutType == ClutType.ExplicitAlpha then PhysicalChannel._1 else PhysicalChannel.Const0
      case LogicalChannel.Const1 => PhysicalChannel.Const1
      case _                     => PhysicalChannel.Const0

  def IsCompressed = namespace match
    case Namespace.Undefined    => false
    case Namespace.Pack         => false
    case Namespace.DepthStencil => false
    case Namespace.Dxtc         => true
    case Namespace.Pvrtc        => true
    case Namespace.Etc          => true
    case Namespace.Astc         => true
    case Namespace.Clut         => false // ? arguable clut is a form or compression...

  def HasFloat =
    namespace match
      case Namespace.Pack =>
        val p = asInstanceOf[Pack]
        IsFloat(p.channel0) || IsFloat(p.channel1) || IsFloat(p.channel2) || IsFloat(p.channel3)
      case Namespace.DepthStencil =>
        val ds = asInstanceOf[DepthStencil]
        IsFloat(ds.channel0) || IsFloat(ds.channel1)
      case Namespace.Dxtc => IsFloat(asInstanceOf[Dxtc])
      case _              => false

  def HasNormalised =
    namespace match
      case Namespace.Undefined => false
      case Namespace.Pack =>
        val p = asInstanceOf[Pack]
        IsNormalised(p.channel0) || IsNormalised(p.channel1) || IsNormalised(p.channel2) || IsNormalised(p.channel3)
      case Namespace.DepthStencil =>
        val ds = asInstanceOf[DepthStencil]
        IsNormalised(ds.channel0) || IsNormalised(ds.channel1)
      case Namespace.Dxtc  => IsNormalised(asInstanceOf[Dxtc])
      case Namespace.Pvrtc => IsNormalised(asInstanceOf[Pvrtc])
      case Namespace.Etc   => IsNormalised(asInstanceOf[Etc])
      case Namespace.Astc  => IsNormalised(asInstanceOf[Astc])
      case Namespace.Clut  => IsNormalised(asInstanceOf[Clut])

  def HasSigned =
    namespace match
      case Namespace.Undefined => false
      case Namespace.Pack =>
        val p = asInstanceOf[Pack]
        IsSigned(p.channel0) || IsSigned(p.channel1) || IsSigned(p.channel2) || IsSigned(p.channel3)
      case Namespace.DepthStencil =>
        val ds = asInstanceOf[DepthStencil]
        IsSigned(ds.channel0) || IsSigned(ds.channel1)
      case Namespace.Dxtc  => IsSigned(asInstanceOf[Dxtc])
      case Namespace.Pvrtc => IsSigned(asInstanceOf[Pvrtc])
      case Namespace.Etc   => IsSigned(asInstanceOf[Etc])
      case Namespace.Astc  => IsSigned(asInstanceOf[Astc])
      case Namespace.Clut  => IsSigned(asInstanceOf[Clut])

  def HasSRGB =
    namespace match
      case Namespace.Undefined => false
      case Namespace.Pack =>
        val p = asInstanceOf[Pack]
        IsSRGB(p.channel0) || IsSRGB(p.channel1) || IsSRGB(p.channel2) || IsSRGB(p.channel3)
      case Namespace.DepthStencil =>
        val ds = asInstanceOf[DepthStencil]
        IsSRGB(ds.channel0) || IsSRGB(ds.channel1)
      case Namespace.Dxtc  => IsSRGB(asInstanceOf[Dxtc])
      case Namespace.Pvrtc => IsSRGB(asInstanceOf[Pvrtc])
      case Namespace.Etc   => IsSRGB(asInstanceOf[Etc])
      case Namespace.Astc  => IsSRGB(asInstanceOf[Astc])
      case Namespace.Clut  => IsSRGB(asInstanceOf[Clut])

  def HasDepth(ds: DepthStencil) =
    (ds.channel0.swizzle == DepthStencilSwizzle.Depth && ds.channel0.bitCount != DepthStencilBitCount._0) ||
      (ds.channel1.swizzle == DepthStencilSwizzle.Depth && ds.channel1.bitCount != DepthStencilBitCount._0)

  def HasStencil(ds: DepthStencil) =
    (ds.channel0.swizzle == DepthStencilSwizzle.Stencil && ds.channel0.bitCount != DepthStencilBitCount._0) ||
      (ds.channel1.swizzle == DepthStencilSwizzle.Stencil && ds.channel1.bitCount != DepthStencilBitCount._0)

  def HasDepthOnly =
    if IsInDepthStencilNamespace then
      val ds = asInstanceOf[DepthStencil]
      HasDepth(ds) && !HasStencil(ds)
    else false

  def HasStencilOnly: Boolean =
    if IsInDepthStencilNamespace then
      val ds = asInstanceOf[DepthStencil]
      !HasDepth(ds) && HasStencil(ds)
    else false

  def HasDepthAndStencil: Boolean =
    if IsInDepthStencilNamespace then
      val ds = asInstanceOf[DepthStencil]
      HasDepth(ds) && HasStencil(ds)
    else false

  def SizeAtBlockDim(blockDim: BlockDim) = namespace match
    case Namespace.Undefined    => 1
    case Namespace.Pack         => GetBlockDim(asInstanceOf[Pack], blockDim)
    case Namespace.DepthStencil => GetBlockDim(asInstanceOf[DepthStencil], blockDim)
    case Namespace.Dxtc         => GetBlockDim(asInstanceOf[Dxtc], blockDim)
    case Namespace.Pvrtc        => GetBlockDim(asInstanceOf[Pvrtc], blockDim)
    case Namespace.Etc          => GetBlockDim(asInstanceOf[Etc], blockDim)
    case Namespace.Astc         => GetBlockDim(asInstanceOf[Astc], blockDim)
    case Namespace.Clut         => GetBlockDim(asInstanceOf[Clut], blockDim)

  def ChannelCount = namespace match
    case Namespace.Undefined    => 1
    case Namespace.Pack         => GetChannelCount(asInstanceOf[Pack])
    case Namespace.DepthStencil => GetChannelCount(asInstanceOf[DepthStencil])
    case Namespace.Dxtc         => GetChannelCount(asInstanceOf[Dxtc])
    case Namespace.Pvrtc        => GetChannelCount(asInstanceOf[Pvrtc])
    case Namespace.Etc          => GetChannelCount(asInstanceOf[Etc])
    case Namespace.Astc         => GetChannelCount(asInstanceOf[Astc])
    case Namespace.Clut         => GetChannelCount(asInstanceOf[Clut])

  def PerChannelBitWidth(channel: Int) = namespace match
    case Namespace.Undefined    => 0
    case Namespace.Pack         => GetPerChannelBitWidth(asInstanceOf[Pack], channel)
    case Namespace.DepthStencil => GetPerChannelBitWidth(asInstanceOf[DepthStencil], channel)
    case Namespace.Dxtc         => GetPerChannelBitWidth(asInstanceOf[Dxtc], channel)
    case Namespace.Pvrtc        => GetPerChannelBitWidth(asInstanceOf[Pvrtc], channel)
    case Namespace.Etc          => GetPerChannelBitWidth(asInstanceOf[Etc], channel)
    case Namespace.Astc         => GetPerChannelBitWidth(asInstanceOf[Astc], channel)
    case Namespace.Clut         => GetPerChannelBitWidth(asInstanceOf[Clut], channel)

  def PerChannelMinimum(channel: Int) = namespace match
    case Namespace.Undefined    => 0.0
    case Namespace.Pack         => GetPerChannelMinimum(asInstanceOf[Pack], channel)
    case Namespace.DepthStencil => GetPerChannelMinimum(asInstanceOf[DepthStencil], channel)
    case Namespace.Dxtc         => GetPerChannelMinimum(asInstanceOf[Dxtc], channel)
    case Namespace.Pvrtc        => GetPerChannelMinimum(asInstanceOf[Pvrtc], channel)
    case Namespace.Etc          => GetPerChannelMinimum(asInstanceOf[Etc], channel)
    case Namespace.Astc         => GetPerChannelMinimum(asInstanceOf[Astc], channel)
    case Namespace.Clut         => GetPerChannelMinimum(asInstanceOf[Clut], channel)

  def PerChannelMaximum(channel: Int) = namespace match
    case Namespace.Undefined    => 1.0
    case Namespace.Pack         => GetPerChannelMaximum(asInstanceOf[Pack], channel)
    case Namespace.DepthStencil => GetPerChannelMaximum(asInstanceOf[DepthStencil], channel)
    case Namespace.Dxtc         => GetPerChannelMaximum(asInstanceOf[Dxtc], channel)
    case Namespace.Pvrtc        => GetPerChannelMaximum(asInstanceOf[Pvrtc], channel)
    case Namespace.Etc          => GetPerChannelMaximum(asInstanceOf[Etc], channel)
    case Namespace.Astc         => GetPerChannelMaximum(asInstanceOf[Astc], channel)
    case Namespace.Clut         => GetPerChannelMaximum(asInstanceOf[Clut], channel)

  def ByteSizeOfBlock: Int = namespace match
    case Namespace.Undefined    => 0
    case Namespace.Pack         => GetByteSizeOfBlock(asInstanceOf[Pack])
    case Namespace.DepthStencil => GetByteSizeOfBlock(asInstanceOf[DepthStencil])
    case Namespace.Dxtc         => GetByteSizeOfBlock(asInstanceOf[Dxtc])
    case Namespace.Pvrtc        => GetByteSizeOfBlock(asInstanceOf[Pvrtc])
    case Namespace.Etc          => GetByteSizeOfBlock(asInstanceOf[Etc])
    case Namespace.Astc         => GetByteSizeOfBlock(asInstanceOf[Astc])
    case Namespace.Clut         => GetByteSizeOfBlock(asInstanceOf[Clut])

  def IsHomogenous: Boolean = namespace match
    case Namespace.Undefined    => false
    case Namespace.Pack         => IsHomogenous(asInstanceOf[Pack])
    case Namespace.DepthStencil => IsHomogenous(asInstanceOf[DepthStencil])
    case Namespace.Dxtc         => IsHomogenous(asInstanceOf[Dxtc])
    case Namespace.Pvrtc        => IsHomogenous(asInstanceOf[Pvrtc])
    case Namespace.Etc          => IsHomogenous(asInstanceOf[Etc])
    case Namespace.Astc         => IsHomogenous(asInstanceOf[Astc])
    case Namespace.Clut         => IsHomogenous(asInstanceOf[Clut])

  def PhysicalToLogical(channel: PhysicalChannel): LogicalChannel = namespace match
    case Namespace.Undefined    => LogicalChannel.Const0
    case Namespace.Pack         => GetPhysicalToLogical(asInstanceOf[Pack], channel)
    case Namespace.DepthStencil => GetPhysicalToLogical(asInstanceOf[DepthStencil], channel)
    case Namespace.Dxtc         => GetPhysicalToLogical(asInstanceOf[Dxtc], channel)
    case Namespace.Pvrtc        => GetPhysicalToLogical(asInstanceOf[Pvrtc], channel)
    case Namespace.Etc          => GetPhysicalToLogical(asInstanceOf[Etc], channel)
    case Namespace.Astc         => GetPhysicalToLogical(asInstanceOf[Astc], channel)
    case Namespace.Clut         => GetPhysicalToLogical(asInstanceOf[Clut], channel)

  def LogicalToPhysical(channel: LogicalChannel): PhysicalChannel = namespace match
    case Namespace.Undefined    => PhysicalChannel.Const0
    case Namespace.Pack         => GetLogicalToPhysical(asInstanceOf[Pack], channel)
    case Namespace.DepthStencil => GetLogicalToPhysical(asInstanceOf[DepthStencil], channel)
    case Namespace.Dxtc         => GetLogicalToPhysical(asInstanceOf[Dxtc], channel)
    case Namespace.Pvrtc        => GetLogicalToPhysical(asInstanceOf[Pvrtc], channel)
    case Namespace.Etc          => GetLogicalToPhysical(asInstanceOf[Etc], channel)
    case Namespace.Astc         => GetLogicalToPhysical(asInstanceOf[Astc], channel)
    case Namespace.Clut         => GetLogicalToPhysical(asInstanceOf[Clut], channel)

  def FetchPixel(isOutputFloat: Boolean, outputBitWidth: Int, spaces: String): Option[String] = namespace match
    case Namespace.Pack => FetchPixel(asInstanceOf[Pack], isOutputFloat, outputBitWidth, spaces)
    case _              => None

  def PutPixel(outputBitWidth: Int, spaces: String): Option[String] = namespace match
    case Namespace.Pack => PutPixel(asInstanceOf[Pack], outputBitWidth, spaces)
    case _              => None

  case Undefined extends GeneratorCode(0)

  case Pack(
      blockSize: PackBlockSize,
      channel0: PackColour,
      channel1: PackColour,
      channel2: PackColour,
      channel3: PackColour,
  ) extends GeneratorCode({
        val rc = PhysicalChannel._0.ordinal
        val gc = PhysicalChannel._1.ordinal
        val bc = PhysicalChannel._2.ordinal
        val ac = PhysicalChannel._3.ordinal
        Namespace.Pack.ordinal |
          ToPosition(blockSize.ordinal, PackSpecialBeginShift, PackSpecialBits) |
          ToPosition(channel0.bitCount, PackBitsBeginShift + rc * PackBitCountBits, PackBitCountBits) |
          ToPosition(channel1.bitCount, PackBitsBeginShift + gc * PackBitCountBits, PackBitCountBits) |
          ToPosition(channel2.bitCount, PackBitsBeginShift + bc * PackBitCountBits, PackBitCountBits) |
          ToPosition(channel3.bitCount, PackBitsBeginShift + ac * PackBitCountBits, PackBitCountBits) |
          ToPosition(channel0.swizzle.ordinal, PackSwizzleBeginShift + rc * PackSwizzleBits, PackSwizzleBits) |
          ToPosition(channel1.swizzle.ordinal, PackSwizzleBeginShift + gc * PackSwizzleBits, PackSwizzleBits) |
          ToPosition(channel2.swizzle.ordinal, PackSwizzleBeginShift + bc * PackSwizzleBits, PackSwizzleBits) |
          ToPosition(channel3.swizzle.ordinal, PackSwizzleBeginShift + ac * PackSwizzleBits, PackSwizzleBits) |
          ToPosition(channel0.packType.ordinal, PackTypeBeginShift + rc * PackTypeBits, PackTypeBits) |
          ToPosition(channel1.packType.ordinal, PackTypeBeginShift + gc * PackTypeBits, PackTypeBits) |
          ToPosition(channel2.packType.ordinal, PackTypeBeginShift + bc * PackTypeBits, PackTypeBits) |
          ToPosition(channel3.packType.ordinal, PackTypeBeginShift + ac * PackTypeBits, PackTypeBits)
      })

  case DepthStencil(
      totalSize: DepthStencilTotalSize,
      channel0: DepthStencilChannel,
      channel1: DepthStencilChannel,
  ) extends GeneratorCode({
        val dc = PhysicalChannel._0.ordinal
        val sc = PhysicalChannel._1.ordinal
        Namespace.DepthStencil.ordinal |
          ToPosition(totalSize.ordinal, DSTotalSizeBeginShift, DSTotalSizeBits) |
          ToPosition(channel0.bitCount.ordinal, DSBitCountBeginShift + dc * DSBitCountBits, DSBitCountBits) |
          ToPosition(channel1.bitCount.ordinal, DSBitCountBeginShift + sc * DSBitCountBits, DSBitCountBits) |
          ToPosition(channel0.swizzle.ordinal, DSSwizzleBeginShift + dc * DSSwizzleBits, DSSwizzleBits) |
          ToPosition(channel1.swizzle.ordinal, DSSwizzleBeginShift + sc * DSSwizzleBits, DSSwizzleBits) |
          ToPosition(channel0.dsType.ordinal, DSTypeBeginShift + dc * DSTypeBits, DSTypeBits) |
          ToPosition(channel1.dsType.ordinal, DSTypeBeginShift + sc * DSTypeBits, DSTypeBits)
      })

  case Dxtc(
      dxtcType: DxtcType,
      alpha: DxtcAlpha,
      blockBytes: DxtcBlockBytes,
      channelCount: Int,
      modeCount: DxtcModeCount,
  ) extends GeneratorCode({
        Namespace.Dxtc.ordinal |
          ToPosition(dxtcType.ordinal, DxtcTypeBeginShift, DxtcTypeBits) |
          ToPosition(alpha.ordinal, DxtcAlphaBeginShift, DxtcAlphaBits) |
          ToPosition(blockBytes.ordinal, DxtcBlockBytesBeginShift, DxtcBlockBytesBits) |
          ToPosition(channelCount, DxtcChannelCountBitsBeginShift, DxtcChannelCountBits) |
          ToPosition(modeCount.ordinal, DxtcModeCountBitsBeginShift, DxtcModeCountBits)
      })
  case Pvrtc(
      pvrtcType: PvrtcType,
      version: PvrtcVersion,
      bits: PvrtcBits,
  ) extends GeneratorCode({
        Namespace.Pvrtc.ordinal |
          ToPosition(pvrtcType.ordinal, PvrtcTypeBeginShift, PvrtcTypeBits) |
          ToPosition(version.ordinal, PvrtcVersionBeginShift, PvrtcVersionBits) |
          ToPosition(bits.ordinal, PvrtcBitsBeginShift, PvrtcBitsBits)

      })
  case Etc(
      etcType: EtcType,
      bits: EtcBits,
      alpha: EtcAlpha,
      channelCount: Int,
  ) extends GeneratorCode({
        Namespace.Etc.ordinal |
          ToPosition(etcType.ordinal, EtcTypeBeginShift, EtcTypeBits) |
          ToPosition(bits.ordinal, EtcBitsBeginShift, EtcBitsBits) |
          ToPosition(alpha.ordinal, EtcAlphaBeginShift, EtcAlphaBits) |
          ToPosition(channelCount, EtcChannelCountBeginShift, EtcChannelCountBits)
      })
  case Astc(
      astcType: AstcType,
      blockWidth: AstcSize,
      blockHeight: AstcSize,
      blockDepth: AstcSize,
  ) extends GeneratorCode({
        Namespace.Astc.ordinal |
          ToPosition(astcType.ordinal, AstcTypeBeginShift, AstcTypeBits) |
          ToPosition(blockWidth.ordinal, AstcBlockWidthBeginShift, AstcSizeBits) |
          ToPosition(blockHeight.ordinal, AstcBlockHeightBeginShift, AstcSizeBits) |
          ToPosition(blockDepth.ordinal, AstcBlockDepthBeginShift, AstcSizeBits)
      })
  case Clut(
      blockSize: ClutBlockSize,
      channel0: ClutChannel,
      channel1: ClutChannel,
  ) extends GeneratorCode({
        val c0 = PhysicalChannel._0.ordinal
        val c1 = PhysicalChannel._1.ordinal
        Namespace.Clut.ordinal |
          ToPosition(blockSize.ordinal, ClutBlockSizeBeginShift, ClutBlockSizeBits) |
          ToPosition(channel0.clutType.ordinal, ClutTypeBeginShift + c0 * ClutTypeBits, ClutTypeBits) |
          ToPosition(channel1.clutType.ordinal, ClutTypeBeginShift + c1 * ClutTypeBits, ClutTypeBits) |
          ToPosition(channel0.bitCount.ordinal, ClutBitsBeginShift + c0 * ClutBitsBits, ClutBitsBits) |
          ToPosition(channel1.bitCount.ordinal, ClutBitsBeginShift + c1 * ClutBitsBits, ClutBitsBits)
      })
