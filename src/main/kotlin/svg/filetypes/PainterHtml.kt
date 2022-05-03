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
class PainterHtml : Painter() {
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
brush = SolidColor(Color(239, 101, 42, 255))
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
brush = SolidColor(Color(241, 125, 75, 255))
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
generalPathText!!.moveTo(170.31699f, 402.0f)
generalPathText!!.lineTo(153.26477f, 402.0f)
generalPathText!!.lineTo(153.26477f, 368.9635f)
generalPathText!!.lineTo(119.16037f, 368.9635f)
generalPathText!!.lineTo(119.16037f, 402.0f)
generalPathText!!.lineTo(102.10817f, 402.0f)
generalPathText!!.lineTo(102.10817f, 321.94077f)
generalPathText!!.lineTo(119.16037f, 321.94077f)
generalPathText!!.lineTo(119.16037f, 354.25385f)
generalPathText!!.lineTo(153.26477f, 354.25385f)
generalPathText!!.lineTo(153.26477f, 321.94077f)
generalPathText!!.lineTo(170.31699f, 321.94077f)
generalPathText!!.lineTo(170.31699f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(244.24431f, 335.89258f)
generalPathText!!.lineTo(221.4047f, 335.89258f)
generalPathText!!.lineTo(221.4047f, 402.0f)
generalPathText!!.lineTo(204.31804f, 402.0f)
generalPathText!!.lineTo(204.31804f, 335.89258f)
generalPathText!!.lineTo(181.51288f, 335.89258f)
generalPathText!!.lineTo(181.51288f, 321.94077f)
generalPathText!!.lineTo(244.24431f, 321.94077f)
generalPathText!!.lineTo(244.24431f, 335.89258f)
generalPathText!!.close()
generalPathText!!.moveTo(328.48907f, 402.0f)
generalPathText!!.lineTo(328.48907f, 353.42706f)
generalPathText!!.quadraticBezierTo(328.48907f, 346.70953f, 329.14362f, 336.51266f)
generalPathText!!.lineTo(328.73022f, 336.51266f)
generalPathText!!.quadraticBezierTo(327.18002f, 344.22922f, 326.2499f, 346.81287f)
generalPathText!!.lineTo(306.51068f, 402.0f)
generalPathText!!.lineTo(292.9378f, 402.0f)
generalPathText!!.lineTo(272.923f, 347.3296f)
generalPathText!!.quadraticBezierTo(272.1996f, 345.36603f, 270.40823f, 336.51266f)
generalPathText!!.lineTo(269.92596f, 336.51266f)
generalPathText!!.quadraticBezierTo(270.61493f, 346.60617f, 270.61493f, 356.73416f)
generalPathText!!.lineTo(270.61493f, 402.0f)
generalPathText!!.lineTo(255.21628f, 402.0f)
generalPathText!!.lineTo(255.21628f, 321.94077f)
generalPathText!!.lineTo(280.01947f, 321.94077f)
generalPathText!!.lineTo(297.27838f, 370.307f)
generalPathText!!.quadraticBezierTo(299.44867f, 376.30112f, 300.20654f, 381.5718f)
generalPathText!!.lineTo(300.58548f, 381.5718f)
generalPathText!!.quadraticBezierTo(302.1701f, 375.02652f, 303.89258f, 370.20367f)
generalPathText!!.lineTo(321.1859f, 321.94077f)
generalPathText!!.lineTo(345.3346f, 321.94077f)
generalPathText!!.lineTo(345.3346f, 402.0f)
generalPathText!!.lineTo(328.48907f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(410.99414f, 402.0f)
generalPathText!!.lineTo(364.0403f, 402.0f)
generalPathText!!.lineTo(364.0403f, 321.94077f)
generalPathText!!.lineTo(381.09253f, 321.94077f)
generalPathText!!.lineTo(381.09253f, 388.0482f)
generalPathText!!.lineTo(410.99414f, 388.0482f)
generalPathText!!.lineTo(410.99414f, 402.0f)
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

