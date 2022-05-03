package svg.filetypes

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import java.lang.ref.WeakReference
import java.util.*
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class PainterSvg : Painter() {
    @Suppress("UNUSED_VARIABLE") private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE") private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE") private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE") private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE") private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)

	private fun _paint0(drawScope : DrawScope) {
@Suppress("UNUSED_VARIABLE") var shapeText: Outline?
@Suppress("UNUSED_VARIABLE") var generalPathText: Path? = null
@Suppress("UNUSED_VARIABLE") var alphaText = 0.0f
@Suppress("UNUSED_VARIABLE") var blendModeText = DrawScope.DefaultBlendMode
with(drawScope) {
// 
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(129.50488f, 0.0f)
    cubicTo(89.86562f, 0.0f, 57.954094f, 31.911526f, 57.954094f, 71.55078f)
    lineTo(57.954094f, 440.44922f)
    cubicTo(57.954094f, 480.08847f, 89.865616f, 512.0f, 129.50488f, 512.0f)
    lineTo(382.49512f, 512.0f)
    cubicTo(422.13437f, 512.0f, 454.0459f, 480.08847f, 454.0459f, 440.44922f)
    lineTo(454.0459f, 159.9375f)
    lineTo(292.65527f, 0.0f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(230, 74, 25, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_1
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(454.0459f, 159.9375f)
    lineTo(292.65527f, 159.9375f)
    lineTo(292.65527f, 0.0f)
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(235, 110, 70, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0_2
            generalPathText = null
            alphaText = alpha
            blendModeText = blendMode
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
brush = SolidColor(Color(255, 255, 255, 255))
if (generalPathText == null) {
   generalPathText = Path()
} else {
   generalPathText!!.reset()
}
generalPathText?.run {
    moveTo(118.30927f, 376.08957f)
    quadraticBezierTo(130.75514f, 386.36514f, 146.56715f, 386.36514f)
    quadraticBezierTo(155.514f, 386.36514f, 160.03172f, 383.30905f)
    quadraticBezierTo(164.54944f, 380.20865f, 164.54944f, 375.3366f)
    quadraticBezierTo(164.54944f, 371.17322f, 160.96184f, 367.45276f)
    quadraticBezierTo(157.37424f, 363.73227f, 142.04944f, 357.35434f)
    quadraticBezierTo(117.91065f, 347.12302f, 117.91065f, 327.59055f)
    quadraticBezierTo(117.91065f, 313.24014f, 128.85062f, 305.312f)
    quadraticBezierTo(139.79057f, 297.33957f, 157.77286f, 297.33957f)
    quadraticBezierTo(172.8762f, 297.33957f, 183.10751f, 301.2815f)
    lineTo(183.10751f, 321.87695f)
    quadraticBezierTo(172.74333f, 314.83463f, 158.83586f, 314.83463f)
    quadraticBezierTo(150.73055f, 314.83463f, 145.85849f, 317.80215f)
    quadraticBezierTo(141.03073f, 320.72537f, 141.03073f, 325.73032f)
    quadraticBezierTo(141.03073f, 329.71652f, 144.35258f, 333.08267f)
    quadraticBezierTo(147.67444f, 336.4045f, 160.78467f, 342.1181f)
    quadraticBezierTo(176.10948f, 348.71753f, 181.86736f, 356.02557f)
    quadraticBezierTo(187.62523f, 363.33365f, 187.62523f, 373.4321f)
    quadraticBezierTo(187.62523f, 388.2254f, 177.12817f, 396.02066f)
    quadraticBezierTo(166.63113f, 403.81595f, 147.27582f, 403.81595f)
    quadraticBezierTo(129.60356f, 403.81595f, 118.30927f, 398.05807f)
    lineTo(118.30927f, 376.08957f)
    close()
    moveTo(289.5396f, 299.06693f)
    lineTo(253.7965f, 402.0f)
    lineTo(228.99335f, 402.0f)
    lineTo(193.73744f, 299.06693f)
    lineTo(217.389f, 299.06693f)
    lineTo(239.62326f, 371.8376f)
    quadraticBezierTo(241.30634f, 377.19684f, 241.70496f, 381.98032f)
    lineTo(242.10358f, 381.98032f)
    quadraticBezierTo(242.63507f, 377.06396f, 244.36244f, 371.57184f)
    lineTo(266.59668f, 299.06693f)
    lineTo(289.5396f, 299.06693f)
    close()
    moveTo(385.78473f, 395.09055f)
    quadraticBezierTo(370.72565f, 403.81595f, 348.71286f, 403.81595f)
    quadraticBezierTo(324.13116f, 403.81595f, 309.91364f, 389.99704f)
    quadraticBezierTo(295.69614f, 376.13385f, 295.69614f, 352.17224f)
    quadraticBezierTo(295.69614f, 327.8563f, 311.33096f, 312.62006f)
    quadraticBezierTo(327.0101f, 297.33957f, 352.56622f, 297.33957f)
    quadraticBezierTo(369.0426f, 297.33957f, 381.3556f, 301.99014f)
    lineTo(381.3556f, 322.80707f)
    quadraticBezierTo(369.3969f, 315.80905f, 352.1233f, 315.80905f)
    quadraticBezierTo(337.2414f, 315.80905f, 327.98453f, 325.50885f)
    quadraticBezierTo(318.7719f, 335.20865f, 318.7719f, 351.10925f)
    quadraticBezierTo(318.7719f, 367.2313f, 327.0987f, 376.26672f)
    quadraticBezierTo(335.42545f, 385.30215f, 349.86444f, 385.30215f)
    quadraticBezierTo(358.41266f, 385.30215f, 363.8605f, 382.64468f)
    lineTo(363.8605f, 361.5177f)
    lineTo(343.08786f, 361.5177f)
    lineTo(343.08786f, 343.93405f)
    lineTo(385.78473f, 343.93405f)
    lineTo(385.78473f, 395.09055f)
    close()
}
shapeText = Outline.Generic(generalPathText!!)
drawOutline(outline = shapeText!!, style = Fill, brush=brush!!, alpha = alphaText, blendMode = blendModeText)
alphaText = alpha * 1.0f
blendModeText = BlendMode.SrcOver
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)

}
}



    private fun innerPaint(drawScope: DrawScope) {
	    _paint0(drawScope)


	    shape = null
	    generalPath = null
	    brush = null
	    stroke = null
	    clip = null
	    alpha = 1.0f
	}
	
    companion object {
        /**
         * Returns the X of the bounding box of the original SVG image.
         *
         * @return The X of the bounding box of the original SVG image.
         */
        fun getOrigX(): Double {
            return 57.95409393310547
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 0.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 396.091796875
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 512.0
        }

        
    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        clipRect {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = size.width / fullOrigWidth
            val coef2 = size.height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()

            // Use the original icon bounding box and the current icon dimension to compute
            // the offset pivot for the scaling
            var translateX = -getOrigX()
            var translateY = -getOrigY()
            if (coef1 != coef2) {
                if (coef1 < coef2) {
                    val extraDy = ((fullOrigWidth - fullOrigHeight) / 2.0f).toFloat()
                    translateY += extraDy
                } else {
                    val extraDx = ((fullOrigHeight - fullOrigWidth) / 2.0f).toFloat()
                    translateX += extraDx
                }
            }
            val translateXDp = translateX.toFloat().toDp().value
            val translateYDp = translateY.toFloat().toDp().value

            // Create a combined scale + translate + clip transform before calling the transcoded painting instructions
            withTransform({
                scale(scaleX = coef, scaleY = coef, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(left = 0.0f, top = 0.0f, right = fullOrigWidth.toFloat(), bottom = fullOrigHeight.toFloat(), clipOp = ClipOp.Intersect)
            }) {
                innerPaint(this)
            }
        }
    }
}

