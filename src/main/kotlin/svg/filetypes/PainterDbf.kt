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
class PainterDbf : Painter() {
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
brush = SolidColor(Color(233, 99, 96, 255))
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
brush = SolidColor(Color(237, 120, 117, 255))
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
    moveTo(126.3371f, 402.0f)
    lineTo(126.3371f, 299.06693f)
    lineTo(161.85876f, 299.06693f)
    quadraticBezierTo(216.69144f, 299.06693f, 216.69144f, 349.24902f)
    quadraticBezierTo(216.69144f, 373.07776f, 201.32234f, 387.56104f)
    quadraticBezierTo(185.99754f, 402.0f, 161.72588f, 402.0f)
    lineTo(126.3371f, 402.0f)
    close()
    moveTo(148.26132f, 317.0049f)
    lineTo(148.26132f, 384.062f)
    lineTo(160.21997f, 384.062f)
    quadraticBezierTo(175.81053f, 384.062f, 184.71309f, 374.76083f)
    quadraticBezierTo(193.61565f, 365.45966f, 193.61565f, 349.55905f)
    quadraticBezierTo(193.61565f, 334.23425f, 184.31447f, 325.64172f)
    quadraticBezierTo(175.01329f, 317.0049f, 160.1314f, 317.0049f)
    lineTo(148.26132f, 317.0049f)
    close()
    moveTo(234.0315f, 402.0f)
    lineTo(234.0315f, 299.06693f)
    lineTo(270.57187f, 299.06693f)
    quadraticBezierTo(287.35828f, 299.06693f, 296.438f, 305.4449f)
    quadraticBezierTo(305.562f, 311.8228f, 305.562f, 323.11713f)
    quadraticBezierTo(305.562f, 331.4882f, 299.89273f, 337.82184f)
    quadraticBezierTo(294.26773f, 344.1112f, 285.71948f, 346.50296f)
    lineTo(285.71948f, 346.7687f)
    quadraticBezierTo(296.4823f, 348.09744f, 303.0817f, 354.74115f)
    quadraticBezierTo(309.68112f, 361.38483f, 309.68112f, 371.43896f)
    quadraticBezierTo(309.68112f, 385.43503f, 299.71555f, 393.71753f)
    quadraticBezierTo(289.75f, 402.0f, 272.47638f, 402.0f)
    lineTo(234.0315f, 402.0f)
    close()
    moveTo(255.95572f, 315.41043f)
    lineTo(255.95572f, 340.87796f)
    lineTo(266.36417f, 340.87796f)
    quadraticBezierTo(273.8937f, 340.87796f, 278.18997f, 337.29034f)
    quadraticBezierTo(282.53052f, 333.70276f, 282.53052f, 327.2362f)
    quadraticBezierTo(282.53052f, 315.41043f, 265.2569f, 315.41043f)
    lineTo(255.95572f, 315.41043f)
    close()
    moveTo(255.95572f, 357.39862f)
    lineTo(255.95572f, 385.6565f)
    lineTo(268.97736f, 385.6565f)
    quadraticBezierTo(277.25986f, 385.6565f, 281.95474f, 381.80316f)
    quadraticBezierTo(286.6496f, 377.9498f, 286.6496f, 371.08466f)
    quadraticBezierTo(286.6496f, 364.57382f, 281.99902f, 360.9862f)
    quadraticBezierTo(277.34842f, 357.39862f, 268.75592f, 357.39862f)
    lineTo(255.95572f, 357.39862f)
    close()
    moveTo(385.68506f, 317.0049f)
    lineTo(348.96753f, 317.0049f)
    lineTo(348.96753f, 343.44684f)
    lineTo(382.7618f, 343.44684f)
    lineTo(382.7618f, 361.38483f)
    lineTo(348.96753f, 361.38483f)
    lineTo(348.96753f, 402.0f)
    lineTo(327.0433f, 402.0f)
    lineTo(327.0433f, 299.06693f)
    lineTo(385.68506f, 299.06693f)
    lineTo(385.68506f, 317.0049f)
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

