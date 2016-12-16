# android-widget-plus

## Function
ProgressImageView - progress percentage display on imageView

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/progress_image_view.png" width="350px">

ServerConfig - target server selection function

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/server_selection_config.png" width="350px">

ClickableDrawableTextView - drawable of textView and editText clickable function

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/clickable_drawable_text_view.png" width="350px">

## Gradle
```
compile 'com.purestation:android-widget-plus:0.0.5'
```

## How to deploy to bintray (do not run. it's for owner)
./gradlew :widgetpluslibrary:clean :widgetpluslibrary:build :widgetpluslibrary:bintrayUpload -PdryRun=true -PbintrayUser= -PbintrayKey=
