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

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gaugeView: GaugeView =findViewById(R.id.gauge1)
        gaugeView.gaugeValueUnitText ="Km/h"
        gaugeView.maxValue = 200F
        gaugeView.arcGreenMaxVal = 100F
        gaugeView.arcYellowMaxVal= 140F
        gaugeView.arcRedMaxVal = 180F
        val seekBar: SeekBar =findViewById(R.id.seekBar1)
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    gaugeView.setGaugeVal(i.toFloat())
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            }
        )
    }
}



