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
class PainterPptx : Painter() {
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
brush = SolidColor(Color(246, 113, 46, 255))
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
brush = SolidColor(Color(246, 132, 73, 255))
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
generalPathText!!.moveTo(139.36464f, 374.0964f)
generalPathText!!.lineTo(139.36464f, 402.0f)
generalPathText!!.lineTo(122.31244f, 402.0f)
generalPathText!!.lineTo(122.31244f, 321.94077f)
generalPathText!!.lineTo(149.8371f, 321.94077f)
generalPathText!!.quadraticBezierTo(179.7043f, 321.94077f, 179.7043f, 347.2607f)
generalPathText!!.quadraticBezierTo(179.7043f, 359.55896f, 170.67868f, 366.9999f)
generalPathText!!.quadraticBezierTo(161.68753f, 374.40643f, 148.14911f, 374.0964f)
generalPathText!!.lineTo(139.36464f, 374.0964f)
generalPathText!!.close()
generalPathText!!.moveTo(139.36464f, 335.16916f)
generalPathText!!.lineTo(139.36464f, 361.00583f)
generalPathText!!.lineTo(146.73671f, 361.00583f)
generalPathText!!.quadraticBezierTo(161.79086f, 361.00583f, 161.79086f, 347.94968f)
generalPathText!!.quadraticBezierTo(161.79086f, 335.16916f, 146.90895f, 335.16916f)
generalPathText!!.lineTo(139.36464f, 335.16916f)
generalPathText!!.close()
generalPathText!!.moveTo(208.91695f, 374.0964f)
generalPathText!!.lineTo(208.91695f, 402.0f)
generalPathText!!.lineTo(191.86475f, 402.0f)
generalPathText!!.lineTo(191.86475f, 321.94077f)
generalPathText!!.lineTo(219.3894f, 321.94077f)
generalPathText!!.quadraticBezierTo(249.2566f, 321.94077f, 249.2566f, 347.2607f)
generalPathText!!.quadraticBezierTo(249.2566f, 359.55896f, 240.23099f, 366.9999f)
generalPathText!!.quadraticBezierTo(231.23984f, 374.40643f, 217.70142f, 374.0964f)
generalPathText!!.lineTo(208.91695f, 374.0964f)
generalPathText!!.close()
generalPathText!!.moveTo(208.91695f, 335.16916f)
generalPathText!!.lineTo(208.91695f, 361.00583f)
generalPathText!!.lineTo(216.28902f, 361.00583f)
generalPathText!!.quadraticBezierTo(231.34317f, 361.00583f, 231.34317f, 347.94968f)
generalPathText!!.quadraticBezierTo(231.34317f, 335.16916f, 216.46126f, 335.16916f)
generalPathText!!.lineTo(208.91695f, 335.16916f)
generalPathText!!.close()
generalPathText!!.moveTo(316.63864f, 335.89258f)
generalPathText!!.lineTo(293.79904f, 335.89258f)
generalPathText!!.lineTo(293.79904f, 402.0f)
generalPathText!!.lineTo(276.71237f, 402.0f)
generalPathText!!.lineTo(276.71237f, 335.89258f)
generalPathText!!.lineTo(253.90721f, 335.89258f)
generalPathText!!.lineTo(253.90721f, 321.94077f)
generalPathText!!.lineTo(316.63864f, 321.94077f)
generalPathText!!.lineTo(316.63864f, 335.89258f)
generalPathText!!.close()
generalPathText!!.moveTo(391.89227f, 402.0f)
generalPathText!!.lineTo(371.63632f, 402.0f)
generalPathText!!.lineTo(357.27112f, 375.4399f)
generalPathText!!.quadraticBezierTo(356.4788f, 373.99304f, 355.65204f, 370.20367f)
generalPathText!!.lineTo(355.44534f, 370.20367f)
generalPathText!!.quadraticBezierTo(355.03195f, 372.0639f, 353.58508f, 375.5777f)
generalPathText!!.lineTo(339.18546f, 402.0f)
generalPathText!!.lineTo(318.75726f, 402.0f)
generalPathText!!.lineTo(344.45615f, 361.93594f)
generalPathText!!.lineTo(320.99643f, 321.94077f)
generalPathText!!.lineTo(341.76913f, 321.94077f)
generalPathText!!.lineTo(353.68845f, 346.43393f)
generalPathText!!.quadraticBezierTo(355.13528f, 349.431f, 356.16876f, 353.04813f)
generalPathText!!.lineTo(356.37546f, 353.04813f)
generalPathText!!.quadraticBezierTo(357.4778f, 349.80994f, 359.028f, 346.19278f)
generalPathText!!.lineTo(372.1875f, 321.94077f)
generalPathText!!.lineTo(391.2722f, 321.94077f)
generalPathText!!.lineTo(367.08905f, 361.6259f)
generalPathText!!.lineTo(391.89227f, 402.0f)
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

