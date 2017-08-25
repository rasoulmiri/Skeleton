# Placeholder Android
Simple yet powerful placeholder animation for all view in andoird 
<br/><br/>
![alt tag](https://github.com/rasoulmiri/Placeholder/blob/master/demoFile/1.gif)
<br/><br/>
See [sample project](https://github.com/rasoulmiri/Placeholder/tree/master/sample)
<br/>
See [sample APK](https://github.com/rasoulmiri/Placeholder/blob/master/demoFile/sample.apk)
<br/>

## Usage:
#### Step 1

Add JitPack repository in your root build.gradle at the end of repositories.

    allprojects {
        repositories {
    	    ...
    	    maven { url 'https://jitpack.io' }
        }
    }
   
Add dependency in your app level build.gradle.

    dependencies {
	        compile 'com.github.rasoulmiri:Placeholder:v1.0.0'
	}

#### Step 2
add name space on top layout
```xml
xmlns:Placeholder="http://schemas.android.com/apk/res-auto" 
```
#### Step 3
use PlaceholderGroup and PlaceholderView in layout 
```xml
<io.rmiri.placeholder.PlaceholderGroup
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

         <io.rmiri.placeholder.PlaceholderView ...>
            <View ... />
        </io.rmiri.placeholder.PlaceholderView>

        <io.rmiri.placeholder.PlaceholderView ...>
            <View ... />
        </io.rmiri.placeholder.PlaceholderView>

</io.rmiri.placeholder.PlaceholderGroup>
```
Example:
```xml
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:Placeholder="http://schemas.android.com/apk/res-auto"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardBackgroundColor="@color/white"
    app:cardCornerRadius="10dp"
    app:cardElevation="10dp"
    app:cardUseCompatPadding="true"
    app:contentPadding="0dp">

    <io.rmiri.placeholder.PlaceholderGroup
        android:id="@+id/placeHolderGroup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        Placeholder:PH_BackgroundViewsColor="#EEEEEE"
        Placeholder:PH_animationAutoStart="true"
        Placeholder:PH_animationDirection="LTR"
        Placeholder:PH_animationDuration="1000"
        Placeholder:PH_animationFinishType="gradient"
        Placeholder:PH_animationNormalType="gradient"
        Placeholder:PH_backgroundMainColor="@android:color/transparent"
        Placeholder:PH_highLightColor="#DEDEDE">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <io.rmiri.placeholder.PlaceholderView
                android:id="@+id/placeHolderViewPhoto"
                android:layout_width="match_parent"
                android:layout_height="300dp"
                Placeholder:PH_cornerRadius="0dp"
                Placeholder:PH_padding="0dp"
                Placeholder:PH_shapeType="rect">

                <android.support.v7.widget.AppCompatImageView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:scaleType="centerCrop"
                    app:srcCompat="@drawable/photoTest" />

            </io.rmiri.placeholder.PlaceholderView>

            <io.rmiri.placeholder.PlaceholderView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_below="@+id/placeHolderViewPhoto"
                Placeholder:PH_cornerRadius="10dp"
                Placeholder:PH_padding="5dp"
                Placeholder:PH_shapeType="rect">

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_alignParentBottom="true"
                    android:layout_alignParentEnd="true"
                    android:text="Title" />
            </io.rmiri.placeholder.PlaceholderView>
        </RelativeLayout>
    </io.rmiri.placeholder.PlaceholderGroup>
</android.support.v7.widget.CardView>
```
Final step

Nothing really! Just build your app, watch the magic happen ;) .


# Configure XML

### PlaceholderGroup
 * PH_animationAutoStart: true or false | defult value true
 * PH_animationDuration: time animation | default 1000 millisecond
 * PH_animationDirection: RTL,LTR,BTT,TTB | default value is LTR
 * PH_animationNormalType: none,alpha,gradient | default value is gradient
 * PH_animationFinishType: none,alpha,gradient | default value is gradient
 * PH_backgroundMainColor: background total PlaceholderGroup 
 * PH_BackgroundViewsColor: background PlaceholderViews in this PlaceholderGroup
 * PH_highLightColor: highLight color animation


### PlaceholderView
 * PH_shapeType: rect, oval,text | defult value rect
 * PH_cornerRadius: just use for shape type rect | defult value 0dp
 * PH_cornerRadiusTopLeft
 * PH_cornerRadiusTopRight
 * PH_cornerRadiusBottomLeft
 * PH_cornerRadiusBottomLRight
 * PH_padding: padding view if PH_shapeType equals rect | default value is 0dp
 * PH_paddingTop
 * PH_paddingRight
 * PH_paddingLeft
 * PH_paddingBottom
 * PH_textLineNumber: just use for shape type text  | default value is 3
 * PH_textLineLastWidth: full, threeQuarters, half, quarter | default value is threeQuarters
 * PH_textLineHeight: hieght of line | defualt value is 24dp
 * PH_textLineSpaceVertical: space vertical between lines | default value is threeQuarters 4dp
 
 # Configure Java
 
 ```java
 placeHolderGroup.setAutoPlay(true);
 placeHolderGroup.setShowPlaceHolder(true);
 placeHolderGroup.startAnimation();
 placeHolderGroup.finishAnimation();
```

```java
 placeholderGroup.setPlaceholderListener(new PlaceholderGroup.PlaceholderListener() {
            @Override
            public void onStartAnimation() {
		...
            }

            @Override
            public void onFinishAnimation() {
		...
            }
 });
```


for use in RecyclerView and Adapter See sample 1 activity in [this project](https://github.com/rasoulmiri/Placeholder/tree/master/sample)
<br/>

## Contributing

You are welcome to contribute with issues, PRs or suggestions.
