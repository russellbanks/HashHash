package svg

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
class PainterClipboard : Painter() {
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
withTransform({
transform(
Matrix(values=floatArrayOf(
0.001953125f, 0.0f, 0.0f, 0.0f,
0.0f, 0.001953125f, 0.0f, 0.0f,
0.0f, 0.0f, 1.0f, 0.0f,
-0.0f, -0.0f, 0.0f, 1.0f)
))}){
// _0
alphaStack.add(0, alpha)
alpha *= 1.0f
blendModeStack.add(0, BlendMode.SrcOver)
blendMode = BlendMode.SrcOver
// _0_0
if (generalPath == null) {
   generalPath = Path()
} else {
   generalPath!!.reset()
}
generalPath?.run {
    moveTo(375.46667f, 34.13333f)
    lineTo(363.296f, 34.13333f)
    cubicTo(359.78842f, 34.1298f, 356.64026f, 31.980255f, 355.36f, 28.714666f)
    cubicTo(348.6488f, 11.420892f, 332.01166f, 0.018845538f, 313.46133f, -7.1054274E-15f)
    lineTo(198.53867f, 0.0f)
    cubicTo(179.98834f, 0.01883925f, 163.35118f, 11.420886f, 156.64f, 28.714666f)
    cubicTo(155.35973f, 31.980255f, 152.21158f, 34.1298f, 148.704f, 34.13333f)
    lineTo(136.53333f, 34.13333f)
    cubicTo(98.83069f, 34.133335f, 68.26666f, 64.697365f, 68.26666f, 102.4f)
    lineTo(68.26666f, 443.73334f)
    cubicTo(68.26667f, 481.43597f, 98.83069f, 512.0f, 136.53333f, 512.0f)
    lineTo(375.46667f, 512.0f)
    cubicTo(413.1693f, 512.0f, 443.73334f, 481.43597f, 443.73334f, 443.73334f)
    lineTo(443.73334f, 102.4f)
    cubicTo(443.73334f, 64.697365f, 413.1693f, 34.133335f, 375.46667f, 34.13333f)
    close()
    moveTo(324.128f, 102.4f)
    lineTo(187.872f, 102.4f)
    cubicTo(178.47612f, 102.35798f, 170.88148f, 94.729294f, 170.88148f, 85.333336f)
    cubicTo(170.88148f, 75.93738f, 178.47612f, 68.30869f, 187.872f, 68.26667f)
    lineTo(324.128f, 68.26667f)
    cubicTo(333.52386f, 68.30869f, 341.1185f, 75.93738f, 341.1185f, 85.333336f)
    cubicTo(341.1185f, 94.729294f, 333.52386f, 102.35798f, 324.128f, 102.4f)
    close()
}
shape = Outline.Generic(generalPath!!)
brush = SolidColor(Color(255, 149, 51, 255))
drawOutline(outline = shape!!, style=Fill, brush=brush!!, alpha=alpha, blendMode = blendMode)
alpha = alphaStack.removeAt(0)
blendMode = blendModeStack.removeAt(0)
}
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
            return 0.13333332538604736
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
            return 0.7333333492279053
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 1.0
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

