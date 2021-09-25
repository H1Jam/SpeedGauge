# SpeedGauge
This is a simple gauge for Android. The entire implemention has been done in Kotlin.

<img src="images/gauge2.gif"/>
![](./images/Screenshot1.jpg =250x)

## How to use it
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

