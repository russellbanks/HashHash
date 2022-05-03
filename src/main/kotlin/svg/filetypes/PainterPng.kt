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
class PainterPng : Painter() {
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
brush = SolidColor(Color(9, 182, 90, 255))
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
brush = SolidColor(Color(60, 211, 131, 255))
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
generalPathText!!.moveTo(132.11711f, 366.12402f)
generalPathText!!.lineTo(132.11711f, 402.0f)
generalPathText!!.lineTo(110.19289f, 402.0f)
generalPathText!!.lineTo(110.19289f, 299.06693f)
generalPathText!!.lineTo(145.58167f, 299.06693f)
generalPathText!!.quadraticBezierTo(183.98227f, 299.06693f, 183.98227f, 331.62106f)
generalPathText!!.quadraticBezierTo(183.98227f, 347.43307f, 172.37793f, 357.0f)
generalPathText!!.quadraticBezierTo(160.8179f, 366.52264f, 143.41139f, 366.12402f)
generalPathText!!.lineTo(132.11711f, 366.12402f)
generalPathText!!.close()
generalPathText!!.moveTo(132.11711f, 316.0748f)
generalPathText!!.lineTo(132.11711f, 349.2933f)
generalPathText!!.lineTo(141.59546f, 349.2933f)
generalPathText!!.quadraticBezierTo(160.95078f, 349.2933f, 160.95078f, 332.5069f)
generalPathText!!.quadraticBezierTo(160.95078f, 316.0748f, 141.81691f, 316.0748f)
generalPathText!!.lineTo(132.11711f, 316.0748f)
generalPathText!!.close()
generalPathText!!.moveTo(291.07874f, 402.0f)
generalPathText!!.lineTo(268.62302f, 402.0f)
generalPathText!!.lineTo(225.04033f, 335.4301f)
generalPathText!!.quadraticBezierTo(221.6742f, 330.24802f, 220.124f, 327.05905f)
generalPathText!!.lineTo(219.81395f, 327.05905f)
generalPathText!!.quadraticBezierTo(220.38974f, 332.37402f, 220.38974f, 343.44684f)
generalPathText!!.lineTo(220.38974f, 402.0f)
generalPathText!!.lineTo(199.6171f, 402.0f)
generalPathText!!.lineTo(199.6171f, 299.06693f)
generalPathText!!.lineTo(223.53442f, 299.06693f)
generalPathText!!.lineTo(265.5226f, 363.77658f)
generalPathText!!.quadraticBezierTo(269.686f, 370.2431f, 270.61612f, 371.97046f)
generalPathText!!.lineTo(270.97046f, 371.97046f)
generalPathText!!.quadraticBezierTo(270.3061f, 368.25f, 270.3061f, 357.75296f)
generalPathText!!.lineTo(270.3061f, 299.06693f)
generalPathText!!.lineTo(291.07874f, 299.06693f)
generalPathText!!.lineTo(291.07874f, 402.0f)
generalPathText!!.close()
generalPathText!!.moveTo(398.64026f, 395.09055f)
generalPathText!!.quadraticBezierTo(383.58118f, 403.81595f, 361.5684f, 403.81595f)
generalPathText!!.quadraticBezierTo(336.9867f, 403.81595f, 322.76917f, 389.99704f)
generalPathText!!.quadraticBezierTo(308.55167f, 376.13385f, 308.55167f, 352.17224f)
generalPathText!!.quadraticBezierTo(308.55167f, 327.8563f, 324.1865f, 312.62006f)
generalPathText!!.quadraticBezierTo(339.86563f, 297.33957f, 365.42175f, 297.33957f)
generalPathText!!.quadraticBezierTo(381.89813f, 297.33957f, 394.21112f, 301.99014f)
generalPathText!!.lineTo(394.21112f, 322.80707f)
generalPathText!!.quadraticBezierTo(382.25244f, 315.80905f, 364.97882f, 315.80905f)
generalPathText!!.quadraticBezierTo(350.09692f, 315.80905f, 340.84006f, 325.50885f)
generalPathText!!.quadraticBezierTo(331.62744f, 335.20865f, 331.62744f, 351.10925f)
generalPathText!!.quadraticBezierTo(331.62744f, 367.2313f, 339.95422f, 376.26672f)
generalPathText!!.quadraticBezierTo(348.28098f, 385.30215f, 362.71997f, 385.30215f)
generalPathText!!.quadraticBezierTo(371.2682f, 385.30215f, 376.71603f, 382.64468f)
generalPathText!!.lineTo(376.71603f, 361.5177f)
generalPathText!!.lineTo(355.9434f, 361.5177f)
generalPathText!!.lineTo(355.9434f, 343.93405f)
generalPathText!!.lineTo(398.64026f, 343.93405f)
generalPathText!!.lineTo(398.64026f, 395.09055f)
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

