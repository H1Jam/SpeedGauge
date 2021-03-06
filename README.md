![workflow](https://github.com/H1Jam/SpeedGauge/actions/workflows/gradle.yml/badge.svg)
# SpeedGauge
This is a simple gauge for Android. The entire implemention has been done in Kotlin.<br>
The main Kotlin file for this view is **GaugeView.kt** plus two image assets for the **needle** and the **wheel**.

<p float="left">
<img src="images/gauge2.gif"/>
<img src="images/Screenshot1.jpg" alt="drawing" width="200"/>
<img src="app/src/main/res/drawable/gauge_back_tr.png" width="250"/>
<img src="app/src/main/res/drawable/needle_big_ol.png" height="250"/>
</p>

## How to use it:
First, you need to add it to your layout:
```xml

<com.hjam.uikotlin.GaugeView
    android:id="@+id/gauge1"
    android:layout_width="200dp"
    android:layout_height="200dp" />

```
Then you can initialize and use it in your codes like this:
```kotlin
val gaugeView: GaugeView =findViewById(R.id.gauge1)
gaugeView.gaugeValueUnitText ="Km/h" // Gauge Unit 
gaugeView.maxValue = 200F // Maximum value
gaugeView.arcGreenMaxVal = 100F // Maximum green zone (Safe Zange)
gaugeView.arcYellowMaxVal= 140F // Maximum yellow zone (Warning Zone)
gaugeView.arcRedMaxVal = 180F // Maximum red zone (Critical Zone)
gaugeView.setGaugeVal(100F)
```
## Todo:
- Add more description.
- Add XML properties(Make it more configurable in XML layout instead of codes).

### PS
if you are a graphic designer or have any new idea or somthing in your mind please share it with me I will try to make good looking widget base on your idea.
