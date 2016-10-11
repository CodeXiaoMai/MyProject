package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.utils.Const;
import com.xiaomai.myproject.utils.QuestionViewBuilder;
import com.xiaomai.myproject.utils.TagHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/10/11 16:51.
 */
public class TextAndImageViewDemoActivity extends BaseActivity {

    private SpannableStringBuilder mBuilder = new SpannableStringBuilder();

    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        String content = "<p style=\"text-align:center;\">" +
                "<img src=\"/images/main/news/news_579ae6db4d9e692365.png\" " +
                "title=\"\" alt=\"image\" /></p>" +
                "<p><span style=\"line-height:1.5;\">体检须知：</span></p>" +
                "<p>1、请务必将体检表及化验单上要求您详细填写的项目工整填写清楚。<br /> <br /> " +
                "2、体检前日应清淡饮食，禁酒，保证睡眠。<br /> <br /> " +
                "3、请空腹抽血化验，自备食物于采血后进食。<br /> <br /> " +
                "4、抽血后请在针孔处按压五分钟，不要揉，不要抬起棉签看针孔，以免造成局部淤血。<br /> <br /> " +
                "北大医学部医院</p>";
        String source = handleImgTag(content);
        mContainer = (LinearLayout) findViewById(R.id.container);
        mContainer.removeAllViews();
        List<Object> objectList = new ArrayList<Object>();
        mBuilder = (SpannableStringBuilder) Html.fromHtml(source, null, new TagHelper.BaseHtmlHandler());
        TagHelper.PictureSpan[] lss = mBuilder.getSpans(0, mBuilder.length(), TagHelper.PictureSpan.class);
        int end = 0;
        for (TagHelper.PictureSpan ls : lss) {
            int start = mBuilder.getSpanStart(ls);
            if (start > end) {
                SpannableStringBuilder ssb = new SpannableStringBuilder(mBuilder.subSequence(
                        end, start));
                replaceBlanks(ssb);
                objectList.add(ssb);
            }
            objectList.add(ls);
            end = start + 1;
        }
        if (end < mBuilder.length()) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(mBuilder.subSequence(end,
                    mBuilder.length()));
            replaceBlanks(ssb);
            objectList.add(ssb);
        }
        int mTextLevel = 15;
        QuestionViewBuilder ad = new QuestionViewBuilder(mContext, mTextLevel);
        ad.setDataToContainer(mContainer, objectList);
    }

    public static SpannableStringBuilder replaceBlanks(SpannableStringBuilder ssb) {
        int length = ssb.length();
        int first = 0;
        for (int i = 0; i < length; i++) {
            first = i;
            if (ssb.charAt(i) >= 32) {
                break;
            }
        }
        ssb.delete(0, first);

        length = ssb.length();
        int last = length;
        for (int i = length - 1; i >= 0; i--) {
            if (ssb.charAt(i) >= 32) {
                break;
            }
            last = i;
        }
        ssb.delete(last, length);
        return ssb;
    }

    public static String handleImgTag(String src) {
        if (TextUtils.isEmpty(src)) {
            return src;
        }
        String retStr = src;
        while (retStr.indexOf("<img") >= 0) {
            int start = retStr.indexOf("<img");
            int end = retStr.indexOf("/>", start) + 2;
            if (start > end) {
                retStr = retStr.replace("<img", "");
                continue;
            }
            String imgString = retStr.substring(start, end);
            retStr = retStr.replace(imgString, replaceOneImg(imgString));
        }
        return retStr;
    }

    private static String replaceOneImg(String imgString) {
        String str = "";
        String src = "";
        String title = "";
        if (imgString.contains("src=\"")) {
            int srcStart = imgString.indexOf("src=\"") + 5;
            int srcEnd = imgString.indexOf("\"", srcStart);
            if (srcEnd >= srcStart) {
                src = imgString.substring(srcStart, srcEnd);
            }
        }
        if (imgString.contains("title=\"")) {
            int titleStart = imgString.indexOf("title=\"") + 7;
            int titleEnd = imgString.indexOf("\"", titleStart);
            if (titleEnd >= titleStart) {
                title = imgString.substring(titleStart, titleEnd);
            }
        }
        if (imgString.contains("alt=\"")) {
            int altStart = imgString.indexOf("alt=\"") + 5;
            int altEnd = imgString.indexOf("\"", altStart);
            String alt = "";
            if (altEnd >= altStart) {
                alt = imgString.substring(altStart, altEnd);
            }
            if (alt.equals(Const.IMG_ALT_LATEX)) {
                String[] latex = src.split("/");
                str = getTag(Const.TAG_TEX, true) + latex[latex.length - 1] + getTag(Const.TAG_TEX, false);
            } else if (alt.equals(Const.IMG_ALT_IMAGE)) {
                str = getTag(Const.TAG_PICTURE, true) + src + getTag(Const.TAG_PICTURE, false);
            } else if (alt.equals(Const.IMG_ALT_VIDEO)) {
                str = getTag(Const.TAG_VIDEO, true) + src + Const.PARAMS_SEPARATOR + title + getTag(Const.TAG_VIDEO, false);
            } else if (alt.equals(Const.IMG_ALT_BLANK)) {
                str = getTag(Const.TAG_BLANK, true) + title + getTag(Const.TAG_BLANK, false);
            } else if (alt.equals(Const.IMG_ALT_CODE)) {
                str = getTag(Const.IMG_ALT_CODE, true) + src + getTag(Const.IMG_ALT_CODE, false);
            }
        }
        return str;
    }

    public static String getTag(String tagName, boolean openning) {
        if (openning) {
            return "<" + tagName + ">";
        } else {
            return "</" + tagName + ">";
        }
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_text_image_view;
    }
}
