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
class PainterExe : Painter() {
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
brush = SolidColor(Color(151, 119, 168, 255))
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
brush = SolidColor(Color(168, 142, 181, 255))
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
generalPathText!!.moveTo(200.12646f, 402.0f)
generalPathText!!.lineTo(139.22588f, 402.0f)
generalPathText!!.lineTo(139.22588f, 299.06693f)
generalPathText!!.lineTo(197.73474f, 299.06693f)
generalPathText!!.lineTo(197.73474f, 317.0049f)
generalPathText!!.lineTo(161.15009f, 317.0049f)
generalPathText!!.lineTo(161.15009f, 341.27658f)
generalPathText!!.lineTo(195.16583f, 341.27658f)
generalPathText!!.lineTo(195.16583f, 359.12598f)
generalPathText!!.lineTo(161.15009f, 359.12598f)
generalPathText!!.lineTo(161.15009f, 384.062f)
generalPathText!!.lineTo(200.12646f, 384.062f)
generalPathText!!.lineTo(200.12646f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(299.67126f, 402.0f)
generalPathText!!.lineTo(273.62796f, 402.0f)
generalPathText!!.lineTo(255.15846f, 367.85138f)
generalPathText!!.quadraticBezierTo(254.13977f, 365.99115f, 253.07678f, 361.11908f)
generalPathText!!.lineTo(252.81104f, 361.11908f)
generalPathText!!.quadraticBezierTo(252.27953f, 363.51083f, 250.4193f, 368.02853f)
generalPathText!!.lineTo(231.90552f, 402.0f)
generalPathText!!.lineTo(205.64075f, 402.0f)
generalPathText!!.lineTo(238.68208f, 350.48917f)
generalPathText!!.lineTo(208.51968f, 299.06693f)
generalPathText!!.lineTo(235.22737f, 299.06693f)
generalPathText!!.lineTo(250.55217f, 330.55807f)
generalPathText!!.quadraticBezierTo(252.4124f, 334.4114f, 253.74115f, 339.062f)
generalPathText!!.lineTo(254.0069f, 339.062f)
generalPathText!!.quadraticBezierTo(255.42422f, 334.89862f, 257.41733f, 330.24802f)
generalPathText!!.lineTo(274.3366f, 299.06693f)
generalPathText!!.lineTo(298.87402f, 299.06693f)
generalPathText!!.lineTo(267.7815f, 350.09055f)
generalPathText!!.lineTo(299.67126f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(373.1949f, 402.0f)
generalPathText!!.lineTo(312.29428f, 402.0f)
generalPathText!!.lineTo(312.29428f, 299.06693f)
generalPathText!!.lineTo(370.80316f, 299.06693f)
generalPathText!!.lineTo(370.80316f, 317.0049f)
generalPathText!!.lineTo(334.2185f, 317.0049f)
generalPathText!!.lineTo(334.2185f, 341.27658f)
generalPathText!!.lineTo(368.23425f, 341.27658f)
generalPathText!!.lineTo(368.23425f, 359.12598f)
generalPathText!!.lineTo(334.2185f, 359.12598f)
generalPathText!!.lineTo(334.2185f, 384.062f)
generalPathText!!.lineTo(373.1949f, 384.062f)
generalPathText!!.lineTo(373.1949f, 402.0f)
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

