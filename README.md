# Skeleton Android
Simple yet powerful skeleton animation for all view in android 
<br/>
[![](https://jitpack.io/v/rasoulmiri/skeleton.svg)](https://jitpack.io/#rasoulmiri/skeleton)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Skeleton-orange.svg?style=flat)](https://android-arsenal.com/details/1/6120)
Minimum API 17
<br/><br/>
![alt tag](https://github.com/rasoulmiri/Skeleton/blob/master/demoFile/1.gif)
<br/><br/>
See [demo project](https://github.com/rasoulmiri/Skeleton/tree/master/sample)
<br/>
See [demo APK](https://github.com/rasoulmiri/Skeleton/blob/master/demoFile/sample.apk)
<br/>

## Usage:
#### Step 1:

Add JitPack repository in your root build.gradle at the end of repositories.

    allprojects {
        repositories {
    	    ...
    	    maven { url 'https://jitpack.io' }
        }
    }
   
Add dependency in your app level build.gradle.

    dependencies {
	      compile 'com.github.rasoulmiri:Skeleton:v1.0.5'
	}

#### Step 2:
add name space on top layout
```xml
xmlns:Skeleton="http://schemas.android.com/apk/res-auto" 
```
#### Step 3:
use SkeletonGroup and SkeletonView in layout 
```xml
<io.rmiri.skeleton.SkeletonGroup
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

         <io.rmiri.skeleton.SkeletonView ...>
            <View ... />
        </io.rmiri.skeleton.SkeletonView>

        <io.rmiri.skeleton.SkeletonView ...>
            <View ... />
        </io.rmiri.skeleton.SkeletonView>

</io.rmiri.skeleton.SkeletonGroup>
```
Example:
```xml
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:Skeleton="http://schemas.android.com/apk/res-auto"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardBackgroundColor="@color/white"
    app:cardCornerRadius="10dp"
    app:cardElevation="10dp"
    app:cardUseCompatPadding="true"
    app:contentPadding="0dp">

    <io.rmiri.skeleton.SkeletonGroup
        android:id="@+id/skeletonGroup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        Skeleton:SK_BackgroundViewsColor="#EEEEEE"
        Skeleton:SK_animationAutoStart="true"
        Skeleton:SK_animationDirection="LTR"
        Skeleton:SK_animationDuration="1000"
        Skeleton:SK_animationFinishType="gradient"
        Skeleton:SK_animationNormalType="gradient"
        Skeleton:SK_backgroundMainColor="@android:color/transparent"
        Skeleton:SK_highLightColor="#DEDEDE">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <io.rmiri.skeleton.SkeletonView
                android:id="@+id/skeletonViewPhoto"
                android:layout_width="match_parent"
                android:layout_height="300dp"
                Skeleton:SK_cornerRadius="0dp"
                Skeleton:SK_padding="0dp"
                Skeleton:SK_shapeType="rect">

                <android.support.v7.widget.AppCompatImageView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:scaleType="centerCrop"
                    app:srcCompat="@drawable/photoTest" />

            </io.rmiri.skeleton.SkeletonView>

            <io.rmiri.skeleton.SkeletonView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_below="@+id/skeletonViewPhoto"
                Skeleton:SK_cornerRadius="10dp"
                Skeleton:SK_padding="5dp"
                Skeleton:SK_shapeType="rect">

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_alignParentBottom="true"
                    android:layout_alignParentEnd="true"
                    android:text="Title" />
            </io.rmiri.skeleton.SkeletonView>
        </RelativeLayout>
    </io.rmiri.skeleton.SkeletonGroup>
</android.support.v7.widget.CardView>
```
#### Final step

Nothing really! Just build your app, watch the magic happen ;) .


# Configure XML

### SkeletonGroup
 * **SK_animationAutoStart:** true or false | defult value true
 * **SK_animationDuration:** time animation | default 1000 millisecond
 * **SK_animationDirection:** RTL,LTR,BTT,TTB | default value is LTR
 * **SK_animationNormalType:** none,alpha,gradient | default value is gradient
 * **SK_animationFinishType:** none,alpha,gradient | default value is gradient
 * **SK_backgroundMainColor:** background total SkeletonGroup 
 * **SK_BackgroundViewsColor:** background SkeletonViews in this SkeletonGroup
 * **SK_highLightColor:** highLight color animation


### SkeletonView
 * **SK_shapeType:** rect, oval,text | defult value rect
 * **SK_cornerRadius:** just use for shape type rect | defult value 0dp
 * **SK_cornerRadiusTopLeft**
 * **SK_cornerRadiusTopRight**
 * **SK_cornerRadiusBottomLeft**
 * **SK_cornerRadiusBottomLRight**
 * **SK_padding:** padding view if SK_shapeType equals rect | default value is 0dp
 * **SK_paddingTop**
 * **SK_paddingRight**
 * **SK_paddingLeft**
 * **SK_paddingBottom**
 * **SK_textLineNumber:** just use for shape type text  | default value is 3
 * **SK_textLineLastWidth:** full, threeQuarters, half, quarter | default value is threeQuarters
 * **SK_textLineHeight:** hieght of line | defualt value is 24dp
 * **SK_textLineSpaceVertical:** space vertical between lines | default value is threeQuarters 4dp
 
 # Configure Java
 
 ```java
 skeletonGroup.setAutoPlay(true);
 skeletonGroup.setShowSkeleton(true);
 skeletonGroup.startAnimation();
 skeletonGroup.finishAnimation();
```

```java
 skeletonGroup.setSkeletonListener(new SkeletonGroup.SkeletonListener() {
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


for use in RecyclerView and Adapter See sample 1 activity in [this project](https://github.com/rasoulmiri/Skeleton/tree/master/sample)
<br/>

## Contributing

You are welcome to contribute with issues, PRs or suggestions.
