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
class PainterCsv : Painter() {
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
brush = SolidColor(Color(20, 155, 85, 255))
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
brush = SolidColor(Color(25, 191, 105, 255))
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
generalPathText!!.moveTo(202.6843f, 398.27954f)
generalPathText!!.quadraticBezierTo(191.47858f, 403.81595f, 173.40771f, 403.81595f)
generalPathText!!.quadraticBezierTo(150.15475f, 403.81595f, 136.46873f, 389.90848f)
generalPathText!!.quadraticBezierTo(122.827f, 375.9567f, 122.827f, 352.74802f)
generalPathText!!.quadraticBezierTo(122.827f, 328.3878f, 138.06322f, 312.88583f)
generalPathText!!.quadraticBezierTo(153.34373f, 297.33957f, 177.43823f, 297.33957f)
generalPathText!!.quadraticBezierTo(192.54158f, 297.33957f, 202.6843f, 301.2815f)
generalPathText!!.lineTo(202.6843f, 322.45276f)
generalPathText!!.quadraticBezierTo(192.27582f, 316.34055f, 179.03271f, 316.34055f)
generalPathText!!.quadraticBezierTo(163.97366f, 316.34055f, 154.93822f, 325.99606f)
generalPathText!!.quadraticBezierTo(145.90279f, 335.60727f, 145.90279f, 351.15353f)
generalPathText!!.quadraticBezierTo(145.90279f, 366.30118f, 154.45102f, 375.55807f)
generalPathText!!.quadraticBezierTo(163.04353f, 384.77066f, 177.65968f, 384.77066f)
generalPathText!!.quadraticBezierTo(191.39f, 384.77066f, 202.6843f, 378.12695f)
generalPathText!!.lineTo(202.6843f, 398.27954f)
generalPathText!!.close()
generalPathText!!.moveTo(216.171f, 376.08957f)
generalPathText!!.quadraticBezierTo(228.61687f, 386.36514f, 244.42888f, 386.36514f)
generalPathText!!.quadraticBezierTo(253.37573f, 386.36514f, 257.89343f, 383.30905f)
generalPathText!!.quadraticBezierTo(262.41116f, 380.20865f, 262.41116f, 375.3366f)
generalPathText!!.quadraticBezierTo(262.41116f, 371.17322f, 258.82358f, 367.45276f)
generalPathText!!.quadraticBezierTo(255.23596f, 363.73227f, 239.91116f, 357.35434f)
generalPathText!!.quadraticBezierTo(215.77238f, 347.12302f, 215.77238f, 327.59055f)
generalPathText!!.quadraticBezierTo(215.77238f, 313.24014f, 226.71234f, 305.312f)
generalPathText!!.quadraticBezierTo(237.6523f, 297.33957f, 255.63458f, 297.33957f)
generalPathText!!.quadraticBezierTo(270.73795f, 297.33957f, 280.96924f, 301.2815f)
generalPathText!!.lineTo(280.96924f, 321.87695f)
generalPathText!!.quadraticBezierTo(270.60507f, 314.83463f, 256.69757f, 314.83463f)
generalPathText!!.quadraticBezierTo(248.59227f, 314.83463f, 243.72021f, 317.80215f)
generalPathText!!.quadraticBezierTo(238.89246f, 320.72537f, 238.89246f, 325.73032f)
generalPathText!!.quadraticBezierTo(238.89246f, 329.71652f, 242.21431f, 333.08267f)
generalPathText!!.quadraticBezierTo(245.53616f, 336.4045f, 258.6464f, 342.1181f)
generalPathText!!.quadraticBezierTo(273.9712f, 348.71753f, 279.72906f, 356.02557f)
generalPathText!!.quadraticBezierTo(285.48694f, 363.33365f, 285.48694f, 373.4321f)
generalPathText!!.quadraticBezierTo(285.48694f, 388.2254f, 274.9899f, 396.02066f)
generalPathText!!.quadraticBezierTo(264.49286f, 403.81595f, 245.13754f, 403.81595f)
generalPathText!!.quadraticBezierTo(227.46529f, 403.81595f, 216.171f, 398.05807f)
generalPathText!!.lineTo(216.171f, 376.08957f)
generalPathText!!.close()
generalPathText!!.moveTo(387.4013f, 299.06693f)
generalPathText!!.lineTo(351.6582f, 402.0f)
generalPathText!!.lineTo(326.85504f, 402.0f)
generalPathText!!.lineTo(291.59915f, 299.06693f)
generalPathText!!.lineTo(315.25073f, 299.06693f)
generalPathText!!.lineTo(337.485f, 371.8376f)
generalPathText!!.quadraticBezierTo(339.16806f, 377.19684f, 339.56668f, 381.98032f)
generalPathText!!.lineTo(339.9653f, 381.98032f)
generalPathText!!.quadraticBezierTo(340.4968f, 377.06396f, 342.22415f, 371.57184f)
generalPathText!!.lineTo(364.4584f, 299.06693f)
generalPathText!!.lineTo(387.4013f, 299.06693f)
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

