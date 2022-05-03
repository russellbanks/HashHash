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
class PainterXls : Painter() {
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
brush = SolidColor(Color(122, 194, 141, 255))
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
brush = SolidColor(Color(145, 205, 160, 255))
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
    moveTo(221.54134f, 402.0f)
    lineTo(195.49802f, 402.0f)
    lineTo(177.02853f, 367.85138f)
    quadraticBezierTo(176.00983f, 365.99115f, 174.94684f, 361.11908f)
    lineTo(174.68109f, 361.11908f)
    quadraticBezierTo(174.1496f, 363.51083f, 172.28935f, 368.02853f)
    lineTo(153.77557f, 402.0f)
    lineTo(127.51081f, 402.0f)
    lineTo(160.55215f, 350.48917f)
    lineTo(130.38976f, 299.06693f)
    lineTo(157.09743f, 299.06693f)
    lineTo(172.42224f, 330.55807f)
    quadraticBezierTo(174.28247f, 334.4114f, 175.6112f, 339.062f)
    lineTo(175.87695f, 339.062f)
    quadraticBezierTo(177.29428f, 334.89862f, 179.2874f, 330.24802f)
    lineTo(196.20668f, 299.06693f)
    lineTo(220.7441f, 299.06693f)
    lineTo(189.65157f, 350.09055f)
    lineTo(221.54134f, 402.0f)
    close()
    moveTo(294.53348f, 402.0f)
    lineTo(234.16437f, 402.0f)
    lineTo(234.16437f, 299.06693f)
    lineTo(256.0886f, 299.06693f)
    lineTo(256.0886f, 384.062f)
    lineTo(294.53348f, 384.062f)
    lineTo(294.53348f, 402.0f)
    close()
    moveTo(303.83466f, 376.08957f)
    quadraticBezierTo(316.28052f, 386.36514f, 332.09253f, 386.36514f)
    quadraticBezierTo(341.03937f, 386.36514f, 345.5571f, 383.30905f)
    quadraticBezierTo(350.0748f, 380.20865f, 350.0748f, 375.3366f)
    quadraticBezierTo(350.0748f, 371.17322f, 346.4872f, 367.45276f)
    quadraticBezierTo(342.89963f, 363.73227f, 327.5748f, 357.35434f)
    quadraticBezierTo(303.43604f, 347.12302f, 303.43604f, 327.59055f)
    quadraticBezierTo(303.43604f, 313.24014f, 314.37598f, 305.312f)
    quadraticBezierTo(325.31595f, 297.33957f, 343.29825f, 297.33957f)
    quadraticBezierTo(358.40158f, 297.33957f, 368.63287f, 301.2815f)
    lineTo(368.63287f, 321.87695f)
    quadraticBezierTo(358.2687f, 314.83463f, 344.36124f, 314.83463f)
    quadraticBezierTo(336.25592f, 314.83463f, 331.38388f, 317.80215f)
    quadraticBezierTo(326.55612f, 320.72537f, 326.55612f, 325.73032f)
    quadraticBezierTo(326.55612f, 329.71652f, 329.87796f, 333.08267f)
    quadraticBezierTo(333.1998f, 336.4045f, 346.31006f, 342.1181f)
    quadraticBezierTo(361.63486f, 348.71753f, 367.39273f, 356.02557f)
    quadraticBezierTo(373.1506f, 363.33365f, 373.1506f, 373.4321f)
    quadraticBezierTo(373.1506f, 388.2254f, 362.65356f, 396.02066f)
    quadraticBezierTo(352.1565f, 403.81595f, 332.80118f, 403.81595f)
    quadraticBezierTo(315.12894f, 403.81595f, 303.83466f, 398.05807f)
    lineTo(303.83466f, 376.08957f)
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

