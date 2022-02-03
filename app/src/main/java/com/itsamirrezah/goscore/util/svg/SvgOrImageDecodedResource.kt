package com.itsamirrezah.goscore.util.svg

import android.graphics.Bitmap
import com.caverock.androidsvg.SVG

data class SvgOrImageDecodedResource(
    val svg: SVG? = null,
    val bitmap: Bitmap? = null
)
