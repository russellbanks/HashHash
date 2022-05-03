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
class PainterJson : Painter() {
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
generalPathText!!.moveTo(149.15675f, 370.5826f)
generalPathText!!.quadraticBezierTo(149.15675f, 386.4291f, 141.81914f, 394.938f)
generalPathText!!.quadraticBezierTo(134.51596f, 403.4124f, 120.87421f, 403.4124f)
generalPathText!!.quadraticBezierTo(114.84565f, 403.4124f, 109.678314f, 401.31104f)
generalPathText!!.lineTo(109.678314f, 385.25784f)
generalPathText!!.quadraticBezierTo(114.12222f, 388.59937f, 119.80629f, 388.59937f)
generalPathText!!.quadraticBezierTo(132.10454f, 388.59937f, 132.10454f, 369.96252f)
generalPathText!!.lineTo(132.10454f, 321.94077f)
generalPathText!!.lineTo(149.15675f, 321.94077f)
generalPathText!!.lineTo(149.15675f, 370.5826f)
generalPathText!!.close()
generalPathText!!.moveTo(163.19467f, 381.84738f)
generalPathText!!.quadraticBezierTo(172.87482f, 389.83954f, 185.17307f, 389.83954f)
generalPathText!!.quadraticBezierTo(192.13174f, 389.83954f, 195.64554f, 387.46255f)
generalPathText!!.quadraticBezierTo(199.15932f, 385.05115f, 199.15932f, 381.26178f)
generalPathText!!.quadraticBezierTo(199.15932f, 378.02356f, 196.36896f, 375.12985f)
generalPathText!!.quadraticBezierTo(193.5786f, 372.23615f, 181.65929f, 367.2755f)
generalPathText!!.quadraticBezierTo(162.88463f, 359.3178f, 162.88463f, 344.12585f)
generalPathText!!.quadraticBezierTo(162.88463f, 332.96442f, 171.39351f, 326.79807f)
generalPathText!!.quadraticBezierTo(179.90239f, 320.59726f, 193.88864f, 320.59726f)
generalPathText!!.quadraticBezierTo(205.63571f, 320.59726f, 213.5934f, 323.6632f)
generalPathText!!.lineTo(213.5934f, 339.68195f)
generalPathText!!.quadraticBezierTo(205.53236f, 334.2046f, 194.71541f, 334.2046f)
generalPathText!!.quadraticBezierTo(188.41127f, 334.2046f, 184.62189f, 336.51266f)
generalPathText!!.quadraticBezierTo(180.86696f, 338.7863f, 180.86696f, 342.67902f)
generalPathText!!.quadraticBezierTo(180.86696f, 345.77942f, 183.45062f, 348.39752f)
generalPathText!!.quadraticBezierTo(186.03429f, 350.9812f, 196.23117f, 355.4251f)
generalPathText!!.quadraticBezierTo(208.15048f, 360.55798f, 212.62883f, 366.24203f)
generalPathText!!.quadraticBezierTo(217.1072f, 371.92612f, 217.1072f, 379.78046f)
generalPathText!!.quadraticBezierTo(217.1072f, 391.2864f, 208.94281f, 397.3494f)
generalPathText!!.quadraticBezierTo(200.77841f, 403.4124f, 185.72424f, 403.4124f)
generalPathText!!.quadraticBezierTo(171.97914f, 403.4124f, 163.19467f, 398.93405f)
generalPathText!!.lineTo(163.19467f, 381.84738f)
generalPathText!!.close()
generalPathText!!.moveTo(225.51273f, 363.0383f)
generalPathText!!.quadraticBezierTo(225.51273f, 344.22922f, 236.50192f, 332.41324f)
generalPathText!!.quadraticBezierTo(247.49112f, 320.59726f, 265.5079f, 320.59726f)
generalPathText!!.quadraticBezierTo(282.66345f, 320.59726f, 293.1359f, 332.0343f)
generalPathText!!.quadraticBezierTo(303.60837f, 343.4369f, 303.60837f, 361.38477f)
generalPathText!!.quadraticBezierTo(303.60837f, 380.0905f, 292.72253f, 391.76868f)
generalPathText!!.quadraticBezierTo(281.83667f, 403.4124f, 264.23328f, 403.4124f)
generalPathText!!.quadraticBezierTo(247.00883f, 403.4124f, 236.26079f, 392.11316f)
generalPathText!!.quadraticBezierTo(225.51273f, 380.81393f, 225.51273f, 363.0383f)
generalPathText!!.close()
generalPathText!!.moveTo(243.4606f, 362.1082f)
generalPathText!!.quadraticBezierTo(243.4606f, 373.78635f, 249.07578f, 381.19287f)
generalPathText!!.quadraticBezierTo(254.72539f, 388.59937f, 264.54333f, 388.59937f)
generalPathText!!.quadraticBezierTo(274.56796f, 388.59937f, 280.14868f, 381.5029f)
generalPathText!!.quadraticBezierTo(285.7294f, 374.40643f, 285.7294f, 362.4182f)
generalPathText!!.quadraticBezierTo(285.7294f, 349.91327f, 280.28647f, 342.64456f)
generalPathText!!.quadraticBezierTo(274.878f, 335.37582f, 264.99115f, 335.37582f)
generalPathText!!.quadraticBezierTo(254.96654f, 335.37582f, 249.21356f, 342.8168f)
generalPathText!!.quadraticBezierTo(243.4606f, 350.25775f, 243.4606f, 362.1082f)
generalPathText!!.close()
generalPathText!!.moveTo(388.28375f, 402.0f)
generalPathText!!.lineTo(370.81818f, 402.0f)
generalPathText!!.lineTo(336.92044f, 350.2233f)
generalPathText!!.quadraticBezierTo(334.30234f, 346.19278f, 333.09662f, 343.71246f)
generalPathText!!.lineTo(332.85547f, 343.71246f)
generalPathText!!.quadraticBezierTo(333.3033f, 347.84634f, 333.3033f, 356.45856f)
generalPathText!!.lineTo(333.3033f, 402.0f)
generalPathText!!.lineTo(317.1468f, 402.0f)
generalPathText!!.lineTo(317.1468f, 321.94077f)
generalPathText!!.lineTo(335.74918f, 321.94077f)
generalPathText!!.lineTo(368.40674f, 372.2706f)
generalPathText!!.quadraticBezierTo(371.64493f, 377.30014f, 372.36838f, 378.64365f)
generalPathText!!.lineTo(372.64395f, 378.64365f)
generalPathText!!.quadraticBezierTo(372.12723f, 375.74994f, 372.12723f, 367.58554f)
generalPathText!!.lineTo(372.12723f, 321.94077f)
generalPathText!!.lineTo(388.28375f, 321.94077f)
generalPathText!!.lineTo(388.28375f, 402.0f)
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

