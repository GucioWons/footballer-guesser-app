package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.R;

public class ImageRequestManager {
    public ImageRequest getPngRequest(String url, ImageView imageView, Integer min, Integer max){
        return new ImageRequest(url,
                response -> imageView.setImageBitmap(response),
                min, max, ImageView.ScaleType.CENTER, null,
                error -> imageView.setImageBitmap(BitmapFactory.decodeResource(imageView.getResources(), R.drawable.badge)));
    }
}
