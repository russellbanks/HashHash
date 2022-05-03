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
class PainterDwg : Painter() {
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
brush = SolidColor(Color(12, 60, 108, 255))
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
brush = SolidColor(Color(18, 86, 154, 255))
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
generalPathText!!.moveTo(113.41122f, 402.0f)
generalPathText!!.lineTo(113.41122f, 316.22232f)
generalPathText!!.lineTo(143.01263f, 316.22232f)
generalPathText!!.quadraticBezierTo(188.7066f, 316.22232f, 188.7066f, 358.04077f)
generalPathText!!.quadraticBezierTo(188.7066f, 377.8981f, 175.899f, 389.9675f)
generalPathText!!.quadraticBezierTo(163.12833f, 402.0f, 142.90192f, 402.0f)
generalPathText!!.lineTo(113.41122f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(131.68143f, 331.17065f)
generalPathText!!.lineTo(131.68143f, 387.05164f)
generalPathText!!.lineTo(141.64699f, 387.05164f)
generalPathText!!.quadraticBezierTo(154.63913f, 387.05164f, 162.05794f, 379.30066f)
generalPathText!!.quadraticBezierTo(169.47676f, 371.54965f, 169.47676f, 358.29913f)
generalPathText!!.quadraticBezierTo(169.47676f, 345.52844f, 161.72575f, 338.368f)
generalPathText!!.quadraticBezierTo(153.97476f, 331.17065f, 141.57317f, 331.17065f)
generalPathText!!.lineTo(131.68143f, 331.17065f)
generalPathText!!.close()
generalPathText!!.moveTo(314.14355f, 316.22232f)
generalPathText!!.lineTo(291.29657f, 402.0f)
generalPathText!!.lineTo(270.95944f, 402.0f)
generalPathText!!.lineTo(256.3433f, 346.0821f)
generalPathText!!.quadraticBezierTo(255.19908f, 341.54224f, 254.97763f, 336.59634f)
generalPathText!!.lineTo(254.75618f, 336.59634f)
generalPathText!!.quadraticBezierTo(254.20253f, 342.35425f, 253.20598f, 346.0821f)
generalPathText!!.lineTo(238.25763f, 402.0f)
generalPathText!!.lineTo(217.14539f, 402.0f)
generalPathText!!.lineTo(194.2984f, 316.22232f)
generalPathText!!.lineTo(214.34027f, 316.22232f)
generalPathText!!.lineTo(227.07405f, 374.35477f)
generalPathText!!.quadraticBezierTo(227.88605f, 378.19336f, 228.21823f, 383.98816f)
generalPathText!!.lineTo(228.62424f, 383.98816f)
generalPathText!!.quadraticBezierTo(228.88261f, 379.4483f, 230.32208f, 374.0964f)
generalPathText!!.lineTo(246.26698f, 316.22232f)
generalPathText!!.lineTo(265.7183f, 316.22232f)
generalPathText!!.lineTo(280.22372f, 374.7977f)
generalPathText!!.quadraticBezierTo(281.10956f, 378.3041f, 281.5894f, 383.87744f)
generalPathText!!.lineTo(281.88467f, 383.87744f)
generalPathText!!.quadraticBezierTo(282.1061f, 379.0792f, 283.1396f, 374.4655f)
generalPathText!!.lineTo(295.615f, 316.22232f)
generalPathText!!.lineTo(314.14355f, 316.22232f)
generalPathText!!.close()
generalPathText!!.moveTo(394.86465f, 396.24213f)
generalPathText!!.quadraticBezierTo(382.31543f, 403.51328f, 363.9714f, 403.51328f)
generalPathText!!.quadraticBezierTo(343.48663f, 403.51328f, 331.63867f, 391.99753f)
generalPathText!!.quadraticBezierTo(319.7907f, 380.44485f, 319.7907f, 360.4768f)
generalPathText!!.quadraticBezierTo(319.7907f, 340.2135f, 332.8198f, 327.51663f)
generalPathText!!.quadraticBezierTo(345.88574f, 314.78284f, 367.18253f, 314.78284f)
generalPathText!!.quadraticBezierTo(380.91287f, 314.78284f, 391.1737f, 318.65833f)
generalPathText!!.lineTo(391.1737f, 336.0058f)
generalPathText!!.quadraticBezierTo(381.20813f, 330.1741f, 366.81345f, 330.1741f)
generalPathText!!.quadraticBezierTo(354.41183f, 330.1741f, 346.69775f, 338.2573f)
generalPathText!!.quadraticBezierTo(339.02057f, 346.34045f, 339.02057f, 359.59097f)
generalPathText!!.quadraticBezierTo(339.02057f, 373.02603f, 345.95956f, 380.55557f)
generalPathText!!.quadraticBezierTo(352.89856f, 388.0851f, 364.93106f, 388.0851f)
generalPathText!!.quadraticBezierTo(372.0546f, 388.0851f, 376.59445f, 385.87054f)
generalPathText!!.lineTo(376.59445f, 368.2647f)
generalPathText!!.lineTo(359.2839f, 368.2647f)
generalPathText!!.lineTo(359.2839f, 353.61163f)
generalPathText!!.lineTo(394.86465f, 353.61163f)
generalPathText!!.lineTo(394.86465f, 396.24213f)
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

