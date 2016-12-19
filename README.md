# android-widget-plus

## Function
ProgressImageView - progress percentage display on imageView

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/progress_image_view.png" width="350px">

```
ProgressImageView imageView = (ProgressImageView) findViewById(R.id.progressImageView);
imageView.setColor(Color.argb(128, 255, 255, 255));
imageView.setProgress(50);
```

ServerConfig - target server selection function

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/server_selection_config.png" width="350px">

```
List<ServerConfig> serverConfigList = new ArrayList();
list.add(new ServerConfig("DEV", "http://dev-naver.com"));
list.add(new ServerConfig("STAGE", "http://stage-naver.com"));
list.add(new ServerConfig("REAL", "http://naver.com"));

ServerConfig defaultConfig = new ServerConfig("REAL", "http://naver.com");

ServerConfigManager manager = new ServerConfigManager(this, serverConfigList, defaultConfig);

ServerConfigSelectionView serverConfigSelectionView = new ServerConfigSelectionView(this, manager);
final AlertDialog dialog = new AlertDialog.Builder(this).setMessage(R.string.select_server).setView(serverConfigSelectionView).show();
serverConfigSelectionView.setOnServerSelectedListener(new ServerConfigSelectionView.OnServerSelectedListener() {
  @Override
  public void onServerSelected(String url) {
    Toast.makeText(ServerConfigActivity.this, url + " selected.", Toast.LENGTH_SHORT).show();
    dialog.dismiss();
  }
});
```

ClickableDrawableTextView - drawable of textView and editText clickable function

<img src="https://github.com/puresprout/android-widget-plus/raw/master/images/clickable_drawable_text_view.png" width="350px">

```
ClickableDrawableEditText editText = (ClickableDrawableEditText) findViewById(R.id.editText);
editText.setOnDrawableClickListener(new ClickableDrawableTextView.OnDrawableClickListener() {
  @Override
  public void onClick(View v, ClickableDrawableTextView.DrawablePosition drawablePosition) {
    Toast.makeText(ClickableDrawableTextViewActivity.this, drawablePosition + " drawable clicked.", Toast.LENGTH_SHORT).show();
  }
});
```

## Gradle
```
compile 'com.purestation:android-widget-plus:0.0.5'
```
