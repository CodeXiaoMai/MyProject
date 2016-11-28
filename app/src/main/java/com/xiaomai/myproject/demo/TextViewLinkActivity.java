package com.xiaomai.myproject.demo;

/**
 * Created by XiaoMai on 2016/11/20.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.xiaomai.myproject.R;

public class TextViewLinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_textview_link);

        TextView textViewResource = (TextView) findViewById(R.id.text_html_resource);
        textViewResource.setText(
                Html.fromHtml("<b>text_html_program: Constructed from HTML programmatically.</b>"
                        + "  Text with a <a href=\"http://www.baidu.com\">link</a> "
                        + "created in the Java source code using HTML."));
        textViewResource.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textViewHtml = (TextView) findViewById(R.id.text_html_program);
        String string = "<b>text_html_program: Constructed from HTML programmatically.</b>"
                + "  Text with a <a href=\"http://www.baidu.com\">link</a> "
                + "created in the Java source code using HTML.";
        //开始写代码，请将上面的字符串设置到textviewhtml里，实现超链接。
        textViewHtml.setText(Html.fromHtml(string));
        textViewHtml.setMovementMethod(LinkMovementMethod.getInstance());
        //end_code
        Spannable spannable = new SpannableString("123456789");
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("http://www.baidu.com"), 5, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        SpannableString ss = new SpannableString(
//                "text_spannable: Manually created spans. Click here to dial the phone.");
//        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, 39,
//                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        ss.setSpan(new URLSpan("tel:4155551212"), 40 + 6, 40 + 10,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textViewSpan = (TextView) findViewById(R.id.text_spannable);
        textViewSpan.setText(spannable);
        textViewSpan.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
