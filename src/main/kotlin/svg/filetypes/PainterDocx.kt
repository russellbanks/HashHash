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
class PainterDocx : Painter() {
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
    moveTo(104.14926f, 402.0f)
    lineTo(104.14926f, 321.94077f)
    lineTo(131.77727f, 321.94077f)
    quadraticBezierTo(174.425f, 321.94077f, 174.425f, 360.97137f)
    quadraticBezierTo(174.425f, 379.50488f, 162.47124f, 390.76965f)
    quadraticBezierTo(150.55193f, 402.0f, 131.67392f, 402.0f)
    lineTo(104.14926f, 402.0f)
    close()
    moveTo(121.20146f, 335.89258f)
    lineTo(121.20146f, 388.0482f)
    lineTo(130.50267f, 388.0482f)
    quadraticBezierTo(142.62868f, 388.0482f, 149.5529f, 380.81393f)
    quadraticBezierTo(156.47713f, 373.57965f, 156.47713f, 361.2125f)
    quadraticBezierTo(156.47713f, 349.29318f, 149.24286f, 342.6101f)
    quadraticBezierTo(142.00859f, 335.89258f, 130.43376f, 335.89258f)
    lineTo(121.20146f, 335.89258f)
    close()
    moveTo(182.8822f, 363.0383f)
    quadraticBezierTo(182.8822f, 344.22922f, 193.8714f, 332.41324f)
    quadraticBezierTo(204.8606f, 320.59726f, 222.87737f, 320.59726f)
    quadraticBezierTo(240.03291f, 320.59726f, 250.50539f, 332.0343f)
    quadraticBezierTo(260.97784f, 343.4369f, 260.97784f, 361.38477f)
    quadraticBezierTo(260.97784f, 380.0905f, 250.092f, 391.76868f)
    quadraticBezierTo(239.20615f, 403.4124f, 221.60275f, 403.4124f)
    quadraticBezierTo(204.37831f, 403.4124f, 193.63026f, 392.11316f)
    quadraticBezierTo(182.8822f, 380.81393f, 182.8822f, 363.0383f)
    close()
    moveTo(200.83008f, 362.1082f)
    quadraticBezierTo(200.83008f, 373.78635f, 206.44525f, 381.19287f)
    quadraticBezierTo(212.09486f, 388.59937f, 221.9128f, 388.59937f)
    quadraticBezierTo(231.93742f, 388.59937f, 237.51814f, 381.5029f)
    quadraticBezierTo(243.09888f, 374.40643f, 243.09888f, 362.4182f)
    quadraticBezierTo(243.09888f, 349.91327f, 237.65594f, 342.64456f)
    quadraticBezierTo(232.24747f, 335.37582f, 222.36064f, 335.37582f)
    quadraticBezierTo(212.33601f, 335.37582f, 206.58304f, 342.8168f)
    quadraticBezierTo(200.83008f, 350.25775f, 200.83008f, 362.1082f)
    close()
    moveTo(331.59808f, 399.1063f)
    quadraticBezierTo(322.8825f, 403.4124f, 308.82736f, 403.4124f)
    quadraticBezierTo(290.7417f, 403.4124f, 280.097f, 392.59546f)
    quadraticBezierTo(269.48672f, 381.74405f, 269.48672f, 363.69284f)
    quadraticBezierTo(269.48672f, 344.74594f, 281.33716f, 332.6888f)
    quadraticBezierTo(293.22202f, 320.59726f, 311.96222f, 320.59726f)
    quadraticBezierTo(323.7093f, 320.59726f, 331.59808f, 323.6632f)
    lineTo(331.59808f, 340.1298f)
    quadraticBezierTo(323.5026f, 335.37582f, 313.20236f, 335.37582f)
    quadraticBezierTo(301.48975f, 335.37582f, 294.4622f, 342.8857f)
    quadraticBezierTo(287.4346f, 350.3611f, 287.4346f, 362.45267f)
    quadraticBezierTo(287.4346f, 374.2342f, 294.08322f, 381.43402f)
    quadraticBezierTo(300.76633f, 388.59937f, 312.13446f, 388.59937f)
    quadraticBezierTo(322.81363f, 388.59937f, 331.59808f, 383.43204f)
    lineTo(331.59808f, 399.1063f)
    close()
    moveTo(410.05545f, 402.0f)
    lineTo(389.7995f, 402.0f)
    lineTo(375.4343f, 375.4399f)
    quadraticBezierTo(374.64197f, 373.99304f, 373.81522f, 370.20367f)
    lineTo(373.60852f, 370.20367f)
    quadraticBezierTo(373.19513f, 372.0639f, 371.74826f, 375.5777f)
    lineTo(357.34863f, 402.0f)
    lineTo(336.92044f, 402.0f)
    lineTo(362.61932f, 361.93594f)
    lineTo(339.1596f, 321.94077f)
    lineTo(359.9323f, 321.94077f)
    lineTo(371.85162f, 346.43393f)
    quadraticBezierTo(373.29846f, 349.431f, 374.33194f, 353.04813f)
    lineTo(374.53864f, 353.04813f)
    quadraticBezierTo(375.641f, 349.80994f, 377.1912f, 346.19278f)
    lineTo(390.35068f, 321.94077f)
    lineTo(409.43536f, 321.94077f)
    lineTo(385.25223f, 361.6259f)
    lineTo(410.05545f, 402.0f)
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

