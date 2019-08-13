package com.rsoni.edittextwithclear;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.rsoni.edittextwithclear.R;

public class EditText extends AppCompatEditText {

    Drawable closeButton;

    public EditText(Context context) {
        super(context);
        init();
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        closeButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_opaque_24dp, null);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float start;
                boolean isClicked = false;
                start = getWidth() - getPaddingEnd() - closeButton.getIntrinsicWidth();

                if (event.getX() > start)
                    isClicked = true;

                if (isClicked) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        closeButton =
                                ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.ic_close_black_24dp, null);
                        showButton();
                        return true;
                    }

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        closeButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_opaque_24dp,null);
                        getText().clear();
                        hideButton();
                        return false;
                    }
                }

                return false;
            }
        });
    }

    private void showButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, closeButton, null);
    }

    private void hideButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);

    }

}
