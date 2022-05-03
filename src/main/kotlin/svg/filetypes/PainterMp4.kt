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
class PainterMp4 : Painter() {
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
brush = SolidColor(Color(228, 36, 44, 255))
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
brush = SolidColor(Color(236, 100, 108, 255))
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
generalPathText!!.moveTo(202.24138f, 402.0f)
generalPathText!!.lineTo(202.24138f, 339.5492f)
generalPathText!!.quadraticBezierTo(202.24138f, 330.91238f, 203.08292f, 317.80215f)
generalPathText!!.lineTo(202.55142f, 317.80215f)
generalPathText!!.quadraticBezierTo(200.5583f, 327.72342f, 199.36244f, 331.04526f)
generalPathText!!.lineTo(173.9835f, 402.0f)
generalPathText!!.lineTo(156.53271f, 402.0f)
generalPathText!!.lineTo(130.79944f, 331.70963f)
generalPathText!!.quadraticBezierTo(129.86932f, 329.18503f, 127.56618f, 317.80215f)
generalPathText!!.lineTo(126.9461f, 317.80215f)
generalPathText!!.quadraticBezierTo(127.831924f, 330.7795f, 127.831924f, 343.80118f)
generalPathText!!.lineTo(127.831924f, 402.0f)
generalPathText!!.lineTo(108.03369f, 402.0f)
generalPathText!!.lineTo(108.03369f, 299.06693f)
generalPathText!!.lineTo(139.92346f, 299.06693f)
generalPathText!!.lineTo(162.11342f, 361.25195f)
generalPathText!!.quadraticBezierTo(164.90378f, 368.95865f, 165.87819f, 375.73523f)
generalPathText!!.lineTo(166.36539f, 375.73523f)
generalPathText!!.quadraticBezierTo(168.4028f, 367.3199f, 170.61736f, 361.11908f)
generalPathText!!.lineTo(192.85161f, 299.06693f)
generalPathText!!.lineTo(223.89984f, 299.06693f)
generalPathText!!.lineTo(223.89984f, 402.0f)
generalPathText!!.lineTo(202.24138f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(269.87427f, 366.12402f)
generalPathText!!.lineTo(269.87427f, 402.0f)
generalPathText!!.lineTo(247.95004f, 402.0f)
generalPathText!!.lineTo(247.95004f, 299.06693f)
generalPathText!!.lineTo(283.33884f, 299.06693f)
generalPathText!!.quadraticBezierTo(321.7394f, 299.06693f, 321.7394f, 331.62106f)
generalPathText!!.quadraticBezierTo(321.7394f, 347.43307f, 310.1351f, 357.0f)
generalPathText!!.quadraticBezierTo(298.57504f, 366.52264f, 281.16855f, 366.12402f)
generalPathText!!.lineTo(269.87427f, 366.12402f)
generalPathText!!.close()
generalPathText!!.moveTo(269.87427f, 316.0748f)
generalPathText!!.lineTo(269.87427f, 349.2933f)
generalPathText!!.lineTo(279.3526f, 349.2933f)
generalPathText!!.quadraticBezierTo(298.70792f, 349.2933f, 298.70792f, 332.5069f)
generalPathText!!.quadraticBezierTo(298.70792f, 316.0748f, 279.57407f, 316.0748f)
generalPathText!!.lineTo(269.87427f, 316.0748f)
generalPathText!!.close()
generalPathText!!.moveTo(393.18137f, 299.06693f)
generalPathText!!.lineTo(393.18137f, 364.44095f)
generalPathText!!.lineTo(407.70892f, 364.44095f)
generalPathText!!.lineTo(407.70892f, 380.07578f)
generalPathText!!.lineTo(393.18137f, 380.07578f)
generalPathText!!.lineTo(393.18137f, 402.0f)
generalPathText!!.lineTo(373.6489f, 402.0f)
generalPathText!!.lineTo(373.6489f, 380.07578f)
generalPathText!!.lineTo(327.67447f, 380.07578f)
generalPathText!!.lineTo(327.67447f, 364.30807f)
generalPathText!!.quadraticBezierTo(341.93628f, 348.3632f, 354.42645f, 330.55807f)
generalPathText!!.quadraticBezierTo(366.9166f, 312.75296f, 373.29456f, 299.06693f)
generalPathText!!.lineTo(393.18137f, 299.06693f)
generalPathText!!.close()
generalPathText!!.moveTo(346.67545f, 364.44095f)
generalPathText!!.lineTo(373.6489f, 364.44095f)
generalPathText!!.lineTo(373.6489f, 326.7047f)
generalPathText!!.quadraticBezierTo(370.4599f, 332.68405f, 364.48056f, 341.188f)
generalPathText!!.quadraticBezierTo(358.54553f, 349.69193f, 346.67545f, 364.44095f)
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

