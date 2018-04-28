package com.example.saint.aukg.ui.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by saint on 15.04.2018.
 */

public class TextFontBold extends AppCompatTextView{

    public TextFontBold(Context context) {
        super(context);
        init();
    }

    public TextFontBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextFontBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        if(!isInEditMode()){

            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
            setTypeface(typeface);
        }
    }
}
