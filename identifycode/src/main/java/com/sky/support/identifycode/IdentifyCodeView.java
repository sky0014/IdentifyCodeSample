package com.sky.support.identifycode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by sky on 2015/10/30.
 * <p>
 * android验证码控件
 */
public class IdentifyCodeView extends TextView {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int DEFAULT_CODE_LENGTH = 4;

    protected String code;

    protected Paint paint;

    public IdentifyCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = getPaint();

        generateCode();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int len = code.length();
        int left = 0;
        Random random = new Random();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        for (int i = 0; i < len; i++) {
            String c = String.valueOf(code.charAt(i));
            paint.setTextSkewX(random.nextFloat() - 0.5f);
            canvas.drawText(c, left, -paint.ascent(), paint);
            left += paint.measureText(c);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect rect = new Rect();
        paint.getTextBounds(code, 0, code.length(), rect);

        int width = rect.width() + getPaddingLeft() + getPaddingRight();
        int height = rect.height() + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(width, height);
    }

    /**
     * 生成随机验证码
     *
     * @param length 验证码位数
     */
    public void generateCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        int cl = CHARS.length();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(cl)));
        }
        code = sb.toString();
        invalidate();
    }

    /**
     * 生成随机验证码
     */
    public void generateCode() {
        generateCode(DEFAULT_CODE_LENGTH);
    }

    /**
     * 验证
     *
     * @param code 要验证的字符串
     * @return 是否通过验证
     */
    public boolean verifyCode(String code) {
        return this.code.toLowerCase().equals(code.toLowerCase());
    }
}
