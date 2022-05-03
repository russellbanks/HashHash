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
class PainterCss : Painter() {
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
brush = SolidColor(Color(45, 116, 176, 255))
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
brush = SolidColor(Color(61, 140, 206, 255))
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
generalPathText!!.moveTo(210.39099f, 398.27954f)
generalPathText!!.quadraticBezierTo(199.18527f, 403.81595f, 181.11441f, 403.81595f)
generalPathText!!.quadraticBezierTo(157.86145f, 403.81595f, 144.17543f, 389.90848f)
generalPathText!!.quadraticBezierTo(130.53369f, 375.9567f, 130.53369f, 352.74802f)
generalPathText!!.quadraticBezierTo(130.53369f, 328.3878f, 145.76991f, 312.88583f)
generalPathText!!.quadraticBezierTo(161.05043f, 297.33957f, 185.14493f, 297.33957f)
generalPathText!!.quadraticBezierTo(200.24828f, 297.33957f, 210.39099f, 301.2815f)
generalPathText!!.lineTo(210.39099f, 322.45276f)
generalPathText!!.quadraticBezierTo(199.98251f, 316.34055f, 186.73941f, 316.34055f)
generalPathText!!.quadraticBezierTo(171.68036f, 316.34055f, 162.64491f, 325.99606f)
generalPathText!!.quadraticBezierTo(153.60948f, 335.60727f, 153.60948f, 351.15353f)
generalPathText!!.quadraticBezierTo(153.60948f, 366.30118f, 162.15771f, 375.55807f)
generalPathText!!.quadraticBezierTo(170.75023f, 384.77066f, 185.36638f, 384.77066f)
generalPathText!!.quadraticBezierTo(199.0967f, 384.77066f, 210.39099f, 378.12695f)
generalPathText!!.lineTo(210.39099f, 398.27954f)
generalPathText!!.close()
generalPathText!!.moveTo(223.8777f, 376.08957f)
generalPathText!!.quadraticBezierTo(236.32356f, 386.36514f, 252.13557f, 386.36514f)
generalPathText!!.quadraticBezierTo(261.08243f, 386.36514f, 265.60013f, 383.30905f)
generalPathText!!.quadraticBezierTo(270.11786f, 380.20865f, 270.11786f, 375.3366f)
generalPathText!!.quadraticBezierTo(270.11786f, 371.17322f, 266.53027f, 367.45276f)
generalPathText!!.quadraticBezierTo(262.94266f, 363.73227f, 247.61786f, 357.35434f)
generalPathText!!.quadraticBezierTo(223.47908f, 347.12302f, 223.47908f, 327.59055f)
generalPathText!!.quadraticBezierTo(223.47908f, 313.24014f, 234.41904f, 305.312f)
generalPathText!!.quadraticBezierTo(245.359f, 297.33957f, 263.34128f, 297.33957f)
generalPathText!!.quadraticBezierTo(278.44464f, 297.33957f, 288.67593f, 301.2815f)
generalPathText!!.lineTo(288.67593f, 321.87695f)
generalPathText!!.quadraticBezierTo(278.31177f, 314.83463f, 264.40427f, 314.83463f)
generalPathText!!.quadraticBezierTo(256.29895f, 314.83463f, 251.42691f, 317.80215f)
generalPathText!!.quadraticBezierTo(246.59915f, 320.72537f, 246.59915f, 325.73032f)
generalPathText!!.quadraticBezierTo(246.59915f, 329.71652f, 249.921f, 333.08267f)
generalPathText!!.quadraticBezierTo(253.24286f, 336.4045f, 266.3531f, 342.1181f)
generalPathText!!.quadraticBezierTo(281.6779f, 348.71753f, 287.43576f, 356.02557f)
generalPathText!!.quadraticBezierTo(293.19363f, 363.33365f, 293.19363f, 373.4321f)
generalPathText!!.quadraticBezierTo(293.19363f, 388.2254f, 282.6966f, 396.02066f)
generalPathText!!.quadraticBezierTo(272.19955f, 403.81595f, 252.84424f, 403.81595f)
generalPathText!!.quadraticBezierTo(235.17198f, 403.81595f, 223.8777f, 398.05807f)
generalPathText!!.lineTo(223.8777f, 376.08957f)
generalPathText!!.close()
generalPathText!!.moveTo(305.72812f, 376.08957f)
generalPathText!!.quadraticBezierTo(318.17398f, 386.36514f, 333.986f, 386.36514f)
generalPathText!!.quadraticBezierTo(342.93283f, 386.36514f, 347.45056f, 383.30905f)
generalPathText!!.quadraticBezierTo(351.96826f, 380.20865f, 351.96826f, 375.3366f)
generalPathText!!.quadraticBezierTo(351.96826f, 371.17322f, 348.38068f, 367.45276f)
generalPathText!!.quadraticBezierTo(344.7931f, 363.73227f, 329.46826f, 357.35434f)
generalPathText!!.quadraticBezierTo(305.3295f, 347.12302f, 305.3295f, 327.59055f)
generalPathText!!.quadraticBezierTo(305.3295f, 313.24014f, 316.26944f, 305.312f)
generalPathText!!.quadraticBezierTo(327.2094f, 297.33957f, 345.1917f, 297.33957f)
generalPathText!!.quadraticBezierTo(360.29504f, 297.33957f, 370.52634f, 301.2815f)
generalPathText!!.lineTo(370.52634f, 321.87695f)
generalPathText!!.quadraticBezierTo(360.16217f, 314.83463f, 346.2547f, 314.83463f)
generalPathText!!.quadraticBezierTo(338.14938f, 314.83463f, 333.27734f, 317.80215f)
generalPathText!!.quadraticBezierTo(328.4496f, 320.72537f, 328.4496f, 325.73032f)
generalPathText!!.quadraticBezierTo(328.4496f, 329.71652f, 331.77142f, 333.08267f)
generalPathText!!.quadraticBezierTo(335.09326f, 336.4045f, 348.20352f, 342.1181f)
generalPathText!!.quadraticBezierTo(363.52832f, 348.71753f, 369.2862f, 356.02557f)
generalPathText!!.quadraticBezierTo(375.04407f, 363.33365f, 375.04407f, 373.4321f)
generalPathText!!.quadraticBezierTo(375.04407f, 388.2254f, 364.54703f, 396.02066f)
generalPathText!!.quadraticBezierTo(354.04996f, 403.81595f, 334.69464f, 403.81595f)
generalPathText!!.quadraticBezierTo(317.0224f, 403.81595f, 305.72812f, 398.05807f)
generalPathText!!.lineTo(305.72812f, 376.08957f)
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

