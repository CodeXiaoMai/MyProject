package com.xiaomai.myproject.demo;

/**
 * Created by XiaoMai on 2016/11/20.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.xiaomai.myproject.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView textViewResource = (TextView) findViewById(R.id.text_html_resource);
        textViewResource.setText(
                Html.fromHtml(getResources().getString(R.string.link_text_manual)));
        textViewResource.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textViewHtml = (TextView) findViewById(R.id.text_html_program);
        String string = "<b>text_html_program: Constructed from HTML programmatically.</b>"
                + "  Text with a <a href=\"http://www.google.com\">link</a> "
                + "created in the Java source code using HTML.";
        //开始写代码，请将上面的字符串设置到textviewhtml里，实现超链接。
        textViewHtml.setText(string);


        //end_code
        SpannableString ss = new SpannableString(
                "text_spannable: Manually created spans. Click here to dial the phone.");
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, 39,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new URLSpan("tel:4155551212"), 40 + 6, 40 + 10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textViewSpan = (TextView) findViewById(R.id.text_spannable);
        textViewSpan.setText(ss);
        textViewSpan.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
