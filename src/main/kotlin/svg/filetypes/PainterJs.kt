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
class PainterJs : Painter() {
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
brush = SolidColor(Color(233, 195, 20, 255))
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
brush = SolidColor(Color(241, 223, 32, 255))
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
generalPathText!!.moveTo(232.93527f, 361.6063f)
generalPathText!!.quadraticBezierTo(232.93527f, 381.98032f, 223.50122f, 392.9203f)
generalPathText!!.quadraticBezierTo(214.11145f, 403.81595f, 196.57208f, 403.81595f)
generalPathText!!.quadraticBezierTo(188.8211f, 403.81595f, 182.1774f, 401.11417f)
generalPathText!!.lineTo(182.1774f, 380.4744f)
generalPathText!!.quadraticBezierTo(187.89098f, 384.77066f, 195.19905f, 384.77066f)
generalPathText!!.quadraticBezierTo(211.01106f, 384.77066f, 211.01106f, 360.80905f)
generalPathText!!.lineTo(211.01106f, 299.06693f)
generalPathText!!.lineTo(232.93527f, 299.06693f)
generalPathText!!.lineTo(232.93527f, 361.6063f)
generalPathText!!.close()
generalPathText!!.moveTo(250.98401f, 376.08957f)
generalPathText!!.quadraticBezierTo(263.42987f, 386.36514f, 279.24188f, 386.36514f)
generalPathText!!.quadraticBezierTo(288.18872f, 386.36514f, 292.70645f, 383.30905f)
generalPathText!!.quadraticBezierTo(297.22415f, 380.20865f, 297.22415f, 375.3366f)
generalPathText!!.quadraticBezierTo(297.22415f, 371.17322f, 293.63657f, 367.45276f)
generalPathText!!.quadraticBezierTo(290.04898f, 363.73227f, 274.72415f, 357.35434f)
generalPathText!!.quadraticBezierTo(250.58539f, 347.12302f, 250.58539f, 327.59055f)
generalPathText!!.quadraticBezierTo(250.58539f, 313.24014f, 261.52533f, 305.312f)
generalPathText!!.quadraticBezierTo(272.4653f, 297.33957f, 290.4476f, 297.33957f)
generalPathText!!.quadraticBezierTo(305.55093f, 297.33957f, 315.78223f, 301.2815f)
generalPathText!!.lineTo(315.78223f, 321.87695f)
generalPathText!!.quadraticBezierTo(305.41806f, 314.83463f, 291.5106f, 314.83463f)
generalPathText!!.quadraticBezierTo(283.40527f, 314.83463f, 278.53323f, 317.80215f)
generalPathText!!.quadraticBezierTo(273.70547f, 320.72537f, 273.70547f, 325.73032f)
generalPathText!!.quadraticBezierTo(273.70547f, 329.71652f, 277.0273f, 333.08267f)
generalPathText!!.quadraticBezierTo(280.34915f, 336.4045f, 293.4594f, 342.1181f)
generalPathText!!.quadraticBezierTo(308.7842f, 348.71753f, 314.54208f, 356.02557f)
generalPathText!!.quadraticBezierTo(320.29996f, 363.33365f, 320.29996f, 373.4321f)
generalPathText!!.quadraticBezierTo(320.29996f, 388.2254f, 309.80292f, 396.02066f)
generalPathText!!.quadraticBezierTo(299.30585f, 403.81595f, 279.95053f, 403.81595f)
generalPathText!!.quadraticBezierTo(262.2783f, 403.81595f, 250.98401f, 398.05807f)
generalPathText!!.lineTo(250.98401f, 376.08957f)
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

