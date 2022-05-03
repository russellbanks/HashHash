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
class PainterIso : Painter() {
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
brush = SolidColor(Color(113, 194, 133, 255))
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
brush = SolidColor(Color(144, 207, 160, 255))
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
    moveTo(167.07405f, 299.06693f)
    lineTo(167.07405f, 402.0f)
    lineTo(145.14984f, 402.0f)
    lineTo(145.14984f, 299.06693f)
    lineTo(167.07405f, 299.06693f)
    close()
    moveTo(186.14148f, 376.08957f)
    quadraticBezierTo(198.58734f, 386.36514f, 214.39935f, 386.36514f)
    quadraticBezierTo(223.3462f, 386.36514f, 227.86392f, 383.30905f)
    quadraticBezierTo(232.38164f, 380.20865f, 232.38164f, 375.3366f)
    quadraticBezierTo(232.38164f, 371.17322f, 228.79404f, 367.45276f)
    quadraticBezierTo(225.20644f, 363.73227f, 209.88164f, 357.35434f)
    quadraticBezierTo(185.74286f, 347.12302f, 185.74286f, 327.59055f)
    quadraticBezierTo(185.74286f, 313.24014f, 196.68282f, 305.312f)
    quadraticBezierTo(207.62277f, 297.33957f, 225.60506f, 297.33957f)
    quadraticBezierTo(240.7084f, 297.33957f, 250.93971f, 301.2815f)
    lineTo(250.93971f, 321.87695f)
    quadraticBezierTo(240.57553f, 314.83463f, 226.66806f, 314.83463f)
    quadraticBezierTo(218.56274f, 314.83463f, 213.69069f, 317.80215f)
    quadraticBezierTo(208.86293f, 320.72537f, 208.86293f, 325.73032f)
    quadraticBezierTo(208.86293f, 329.71652f, 212.18478f, 333.08267f)
    quadraticBezierTo(215.50664f, 336.4045f, 228.61687f, 342.1181f)
    quadraticBezierTo(243.94168f, 348.71753f, 249.69955f, 356.02557f)
    quadraticBezierTo(255.45743f, 363.33365f, 255.45743f, 373.4321f)
    quadraticBezierTo(255.45743f, 388.2254f, 244.96037f, 396.02066f)
    quadraticBezierTo(234.46333f, 403.81595f, 215.10802f, 403.81595f)
    quadraticBezierTo(197.43576f, 403.81595f, 186.14148f, 398.05807f)
    lineTo(186.14148f, 376.08957f)
    close()
    moveTo(266.26453f, 351.9065f)
    quadraticBezierTo(266.26453f, 327.72342f, 280.39346f, 312.5315f)
    quadraticBezierTo(294.5224f, 297.33957f, 317.68677f, 297.33957f)
    quadraticBezierTo(339.74387f, 297.33957f, 353.20844f, 312.04428f)
    quadraticBezierTo(366.673f, 326.7047f, 366.673f, 349.78052f)
    quadraticBezierTo(366.673f, 373.83072f, 352.6769f, 388.84546f)
    quadraticBezierTo(338.68085f, 403.81595f, 316.04797f, 403.81595f)
    quadraticBezierTo(293.9023f, 403.81595f, 280.0834f, 389.2884f)
    quadraticBezierTo(266.26453f, 374.76083f, 266.26453f, 351.9065f)
    close()
    moveTo(289.3403f, 350.71063f)
    quadraticBezierTo(289.3403f, 365.7254f, 296.55978f, 375.24802f)
    quadraticBezierTo(303.82358f, 384.77066f, 316.4466f, 384.77066f)
    quadraticBezierTo(329.3354f, 384.77066f, 336.5106f, 375.64664f)
    quadraticBezierTo(343.6858f, 366.52264f, 343.6858f, 351.10925f)
    quadraticBezierTo(343.6858f, 335.0315f, 336.68774f, 325.686f)
    quadraticBezierTo(329.734f, 316.34055f, 317.0224f, 316.34055f)
    quadraticBezierTo(304.1336f, 316.34055f, 296.73697f, 325.90747f)
    quadraticBezierTo(289.3403f, 335.4744f, 289.3403f, 350.71063f)
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

