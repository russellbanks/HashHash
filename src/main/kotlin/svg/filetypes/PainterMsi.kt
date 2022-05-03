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
class PainterMsi : Painter() {
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
generalPath!!.moveTo(129.50488f, 0.0f)
generalPath!!.cubicTo(89.86562f, 0.0f, 57.954094f, 31.911526f, 57.954094f, 71.55078f)
generalPath!!.lineTo(57.954094f, 440.44922f)
generalPath!!.cubicTo(57.954094f, 480.08847f, 89.865616f, 512.0f, 129.50488f, 512.0f)
generalPath!!.lineTo(382.49512f, 512.0f)
generalPath!!.cubicTo(422.13437f, 512.0f, 454.0459f, 480.08847f, 454.0459f, 440.44922f)
generalPath!!.lineTo(454.0459f, 159.9375f)
generalPath!!.lineTo(292.65527f, 0.0f)
generalPath!!.close()
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(18, 131, 197, 255))
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
generalPath!!.moveTo(454.0459f, 159.9375f)
generalPath!!.lineTo(292.65527f, 159.9375f)
generalPath!!.lineTo(292.65527f, 0.0f)
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(21, 146, 220, 255))
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
generalPathText!!.moveTo(225.16216f, 402.0f)
generalPathText!!.lineTo(225.16216f, 339.5492f)
generalPathText!!.quadraticBezierTo(225.16216f, 330.91238f, 226.0037f, 317.80215f)
generalPathText!!.lineTo(225.4722f, 317.80215f)
generalPathText!!.quadraticBezierTo(223.47908f, 327.72342f, 222.28322f, 331.04526f)
generalPathText!!.lineTo(196.90428f, 402.0f)
generalPathText!!.lineTo(179.45349f, 402.0f)
generalPathText!!.lineTo(153.72021f, 331.70963f)
generalPathText!!.quadraticBezierTo(152.7901f, 329.18503f, 150.48695f, 317.80215f)
generalPathText!!.lineTo(149.86687f, 317.80215f)
generalPathText!!.quadraticBezierTo(150.7527f, 330.7795f, 150.7527f, 343.80118f)
generalPathText!!.lineTo(150.7527f, 402.0f)
generalPathText!!.lineTo(130.95447f, 402.0f)
generalPathText!!.lineTo(130.95447f, 299.06693f)
generalPathText!!.lineTo(162.84424f, 299.06693f)
generalPathText!!.lineTo(185.0342f, 361.25195f)
generalPathText!!.quadraticBezierTo(187.82455f, 368.95865f, 188.79897f, 375.73523f)
generalPathText!!.lineTo(189.28616f, 375.73523f)
generalPathText!!.quadraticBezierTo(191.32358f, 367.3199f, 193.53813f, 361.11908f)
generalPathText!!.lineTo(215.77238f, 299.06693f)
generalPathText!!.lineTo(246.82062f, 299.06693f)
generalPathText!!.lineTo(246.82062f, 402.0f)
generalPathText!!.lineTo(225.16216f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(266.13165f, 376.08957f)
generalPathText!!.quadraticBezierTo(278.5775f, 386.36514f, 294.38953f, 386.36514f)
generalPathText!!.quadraticBezierTo(303.33636f, 386.36514f, 307.8541f, 383.30905f)
generalPathText!!.quadraticBezierTo(312.3718f, 380.20865f, 312.3718f, 375.3366f)
generalPathText!!.quadraticBezierTo(312.3718f, 371.17322f, 308.7842f, 367.45276f)
generalPathText!!.quadraticBezierTo(305.19662f, 363.73227f, 289.8718f, 357.35434f)
generalPathText!!.quadraticBezierTo(265.73303f, 347.12302f, 265.73303f, 327.59055f)
generalPathText!!.quadraticBezierTo(265.73303f, 313.24014f, 276.67297f, 305.312f)
generalPathText!!.quadraticBezierTo(287.61295f, 297.33957f, 305.59525f, 297.33957f)
generalPathText!!.quadraticBezierTo(320.69858f, 297.33957f, 330.92987f, 301.2815f)
generalPathText!!.lineTo(330.92987f, 321.87695f)
generalPathText!!.quadraticBezierTo(320.5657f, 314.83463f, 306.65823f, 314.83463f)
generalPathText!!.quadraticBezierTo(298.55292f, 314.83463f, 293.68088f, 317.80215f)
generalPathText!!.quadraticBezierTo(288.85312f, 320.72537f, 288.85312f, 325.73032f)
generalPathText!!.quadraticBezierTo(288.85312f, 329.71652f, 292.17496f, 333.08267f)
generalPathText!!.quadraticBezierTo(295.4968f, 336.4045f, 308.60706f, 342.1181f)
generalPathText!!.quadraticBezierTo(323.93185f, 348.71753f, 329.68973f, 356.02557f)
generalPathText!!.quadraticBezierTo(335.4476f, 363.33365f, 335.4476f, 373.4321f)
generalPathText!!.quadraticBezierTo(335.4476f, 388.2254f, 324.95056f, 396.02066f)
generalPathText!!.quadraticBezierTo(314.4535f, 403.81595f, 295.09818f, 403.81595f)
generalPathText!!.quadraticBezierTo(277.42593f, 403.81595f, 266.13165f, 398.05807f)
generalPathText!!.lineTo(266.13165f, 376.08957f)
generalPathText!!.close()
generalPathText!!.moveTo(374.55685f, 299.06693f)
generalPathText!!.lineTo(374.55685f, 402.0f)
generalPathText!!.lineTo(352.63263f, 402.0f)
generalPathText!!.lineTo(352.63263f, 299.06693f)
generalPathText!!.lineTo(374.55685f, 299.06693f)
generalPathText!!.close()
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

