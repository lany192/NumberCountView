# NumberCountView
## Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
	
## Step 2. Add the dependency

    dependencies {
        implementation 'com.github.lany192:NumberCountView:1.0.4'
    }

## Step 3. use

    <com.lany.count.NumberView
        android:id="@+id/number_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:nv_btn_background="#dddddd"
        app:nv_max="10"
        app:nv_min="1"
        app:nv_value="5"
        app:nv_value_background="#f2f2f2"
        app:nv_value_text_color="@android:color/holo_red_light"
        app:nv_value_text_size="18" />
    