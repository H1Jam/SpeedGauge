/*******************************************************************************
 * Copyright 2021 Hossein Jamshidi
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.hjam.uikotlin

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import java.text.DecimalFormat
import kotlin.math.cos
import kotlin.math.sin


class GaugeView : View {
    private var rTop =0F
    private var rLeft =0F
    private var rW =0F
    private var rH =0F
    private var gText: String =""
    private val df  = DecimalFormat("#")
    private val df2  = DecimalFormat("#.##")
    private var dyT = 0.0F
    private var lc = 0.0F
    var maxValue =250F // Maximum number on the gauge
    var arcGreenMaxVal = 150F // Maximum safe number (The green range)
    var arcYellowMaxVal = 200F // Maximum warning number (The yellow range)
    var arcRedMaxVal = 225F // Maximum dangerous number (The red range)
    private var arcGreenDeg = 0F
    private var arcYellowDeg = 0F
    private var arcRedDeg = 0F
    private val paintTexT: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
    private val options : BitmapFactory.Options = BitmapFactory.Options()
    private val needle: Bitmap
    private val gaugeBack: Bitmap
    private var gaugeHandValue =45.0F// 45 to 315(-45)
    private var xc = 0F
    private var yc = 0F
    private var minDim = 0f
    private var gValueText="0"
    var gaugeValueUnitText = "Km/h" // The Unit text
    private var rectWheel: RectF = RectF(0F,0F,0F,0F)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        options.inScaled = false
        needle = BitmapFactory.decodeResource(resources,
            com.hjam.uikotlin.R.drawable.needle_big_ol,options)
        gaugeBack =BitmapFactory.decodeResource(resources,
            com.hjam.uikotlin.R.drawable.gauge_back_tr,options)
        xc = gaugeBack.width/2F
        yc = gaugeBack.height/2F
        with(paintTexT) {
            textSize = 64F
            strokeWidth = 0F
            setShadowLayer(5F, -3F, 3F, Color.BLACK)
            style = Paint.Style.FILL
            color = Color.argb(255,17,44,10);
        }
        with(paintLine) {
            color = Color.argb(255,234,28,58);
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 5F
            isAntiAlias = true
        }
    }

    fun setGaugeVal(gVal: Float) {
        //gVal must be >0 <270
        gaugeHandValue = (gVal*270.0F/maxValue)+45.0F
        gValueText=df2.format(gVal);
        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        minDim= width.coerceAtMost(height).coerceAtMost(gaugeBack.width).toFloat()
        canvas?.scale(minDim/gaugeBack.width,minDim/gaugeBack.height)
        canvas?.drawBitmap(gaugeBack,0F,0F,paintLine)
        paintTexT.color = Color.BLACK
        if(gValueText.length>6){
            gValueText=gValueText.substring(0,6);
        }
        canvas?.drawText(gValueText, xc   -(gValueText.length*paintTexT.textSize/3.5f), yc *1.7F, paintTexT)
        canvas?.drawText(gaugeValueUnitText, xc   -(gaugeValueUnitText.length*paintTexT.textSize/3.5f), yc *0.8f, paintTexT)
        rLeft = xc - (gaugeBack.width*0.432F)
        rTop = yc - (gaugeBack.height*0.435F)
        rW = xc + (gaugeBack.width*0.431F)
        rH = yc + (gaugeBack.height*0.43F)
        arcGreenDeg =(arcGreenMaxVal/maxValue)*270.0F
        arcYellowDeg=((arcYellowMaxVal/maxValue)*270.0F) - arcGreenDeg
        arcRedDeg = ((arcRedMaxVal/maxValue)*270.0F) - arcYellowDeg-arcGreenDeg
        rectWheel.set(rLeft,rTop,rW,rH)
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = gaugeBack.width/40F
        paintLine.color = Color.argb(255, 50, 200, 0);
        canvas?.drawArc(rectWheel, 135F, arcGreenDeg, false, paintLine)
        paintLine.color = Color.YELLOW
        canvas?.drawArc(rectWheel, 135F+arcGreenDeg, arcYellowDeg, false, paintLine)
        paintLine.color = Color.RED;
        canvas?.drawArc(rectWheel, 135F+arcGreenDeg+arcYellowDeg, arcRedDeg, false, paintLine)
        paintTexT.style = Paint.Style.FILL
        paintTexT.color = Color.WHITE
        paintTexT.strokeWidth = gaugeBack.width/120f
        dyT = paintTexT.textSize * 0.32f
        lc = (gaugeBack.width/2) * 0.65f;
        for (i in 0..10) {
            val x1 = xc - lc * sin(Math.PI/4.0F+(i * 27.0F *Math.PI / 180F)).toFloat() * 1.2F
            val y1 = yc + lc * cos(Math.PI/4.0F+(i * 27.0F *Math.PI / 180F)).toFloat() * 1.2F
            val x2 = xc - lc * sin(Math.PI/4.0F+(i * 27.0F *Math.PI / 180F)).toFloat() * 1.4F
            val y2 = yc + lc * cos(Math.PI/4.0F+(i * 27.0F *Math.PI / 180F)).toFloat() * 1.4F
            gText = df.format(maxValue / 10.0f * i)
            val dxT = gText.length * paintTexT.textSize * 0.32f
            paintTexT.color = Color.WHITE
            canvas?.drawLine(x1, y1, x2, y2, paintTexT)
            paintTexT.color = Color.BLACK
            canvas?.drawText(
                gText,
                xc - lc * sin(Math.PI/4F+ (i * 27.0F *Math.PI / 180F)).toFloat() - dxT,
                yc + lc * cos(Math.PI/4F+ (i * 27.0F *Math.PI / 180F))
                    .toFloat() + dyT,
                paintTexT
            )
        }
        canvas?.rotate(gaugeHandValue,(gaugeBack.width/2F),(gaugeBack.height/2F))
        canvas?.drawBitmap(needle,(gaugeBack.width/2F)-30,(gaugeBack.height/2F)-90,paintLine)
    }

}