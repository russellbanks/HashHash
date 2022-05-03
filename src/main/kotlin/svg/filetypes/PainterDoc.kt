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
class PainterDoc : Painter() {
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
brush = SolidColor(Color(4, 100, 212, 255))
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
brush = SolidColor(Color(76, 148, 220, 255))
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
    moveTo(109.32921f, 402.0f)
    lineTo(109.32921f, 299.06693f)
    lineTo(144.85088f, 299.06693f)
    quadraticBezierTo(199.68355f, 299.06693f, 199.68355f, 349.24902f)
    quadraticBezierTo(199.68355f, 373.07776f, 184.31445f, 387.56104f)
    quadraticBezierTo(168.98965f, 402.0f, 144.71799f, 402.0f)
    lineTo(109.32921f, 402.0f)
    close()
    moveTo(131.25343f, 317.0049f)
    lineTo(131.25343f, 384.062f)
    lineTo(143.21208f, 384.062f)
    quadraticBezierTo(158.80264f, 384.062f, 167.7052f, 374.76083f)
    quadraticBezierTo(176.60776f, 365.45966f, 176.60776f, 349.55905f)
    quadraticBezierTo(176.60776f, 334.23425f, 167.30658f, 325.64172f)
    quadraticBezierTo(158.0054f, 317.0049f, 143.1235f, 317.0049f)
    lineTo(131.25343f, 317.0049f)
    close()
    moveTo(210.55707f, 351.9065f)
    quadraticBezierTo(210.55707f, 327.72342f, 224.686f, 312.5315f)
    quadraticBezierTo(238.81496f, 297.33957f, 261.9793f, 297.33957f)
    quadraticBezierTo(284.0364f, 297.33957f, 297.50098f, 312.04428f)
    quadraticBezierTo(310.96555f, 326.7047f, 310.96555f, 349.78052f)
    quadraticBezierTo(310.96555f, 373.83072f, 296.96948f, 388.84546f)
    quadraticBezierTo(282.97342f, 403.81595f, 260.34055f, 403.81595f)
    quadraticBezierTo(238.19487f, 403.81595f, 224.37598f, 389.2884f)
    quadraticBezierTo(210.55707f, 374.76083f, 210.55707f, 351.9065f)
    close()
    moveTo(233.63286f, 350.71063f)
    quadraticBezierTo(233.63286f, 365.7254f, 240.85236f, 375.24802f)
    quadraticBezierTo(248.11613f, 384.77066f, 260.73917f, 384.77066f)
    quadraticBezierTo(273.62793f, 384.77066f, 280.80313f, 375.64664f)
    quadraticBezierTo(287.97833f, 366.52264f, 287.97833f, 351.10925f)
    quadraticBezierTo(287.97833f, 335.0315f, 280.98032f, 325.686f)
    quadraticBezierTo(274.02658f, 316.34055f, 261.31494f, 316.34055f)
    quadraticBezierTo(248.42618f, 316.34055f, 241.02951f, 325.90747f)
    quadraticBezierTo(233.63286f, 335.4744f, 233.63286f, 350.71063f)
    close()
    moveTo(401.7628f, 398.27954f)
    quadraticBezierTo(390.5571f, 403.81595f, 372.4862f, 403.81595f)
    quadraticBezierTo(349.23328f, 403.81595f, 335.54724f, 389.90848f)
    quadraticBezierTo(321.90552f, 375.9567f, 321.90552f, 352.74802f)
    quadraticBezierTo(321.90552f, 328.3878f, 337.14172f, 312.88583f)
    quadraticBezierTo(352.42224f, 297.33957f, 376.51672f, 297.33957f)
    quadraticBezierTo(391.6201f, 297.33957f, 401.7628f, 301.2815f)
    lineTo(401.7628f, 322.45276f)
    quadraticBezierTo(391.35434f, 316.34055f, 378.1112f, 316.34055f)
    quadraticBezierTo(363.05215f, 316.34055f, 354.01672f, 325.99606f)
    quadraticBezierTo(344.9813f, 335.60727f, 344.9813f, 351.15353f)
    quadraticBezierTo(344.9813f, 366.30118f, 353.5295f, 375.55807f)
    quadraticBezierTo(362.12204f, 384.77066f, 376.7382f, 384.77066f)
    quadraticBezierTo(390.4685f, 384.77066f, 401.7628f, 378.12695f)
    lineTo(401.7628f, 398.27954f)
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

