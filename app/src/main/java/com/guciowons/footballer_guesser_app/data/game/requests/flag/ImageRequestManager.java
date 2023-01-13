package com.guciowons.footballer_guesser_app.data.game.requests.flag;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageRequest;
import com.guciowons.footballer_guesser_app.R;

public class ImageRequestManager {
    public ImageRequest getPngRequest(String url, ImageView imageView, Integer min, Integer max){
        return new ImageRequest(url,
                response -> imageView.setImageBitmap(response),
                min, max, ImageView.ScaleType.CENTER, null,
                error -> imageView.setImageBitmap(BitmapFactory.decodeResource(imageView.getResources(), R.drawable.flag)));
    }
}
