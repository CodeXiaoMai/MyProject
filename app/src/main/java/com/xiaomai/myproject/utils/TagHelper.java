
package com.xiaomai.myproject.utils;

import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;

import org.xml.sax.XMLReader;

public class TagHelper {

    public static class LatexSpan {

        public String src = null;

    }

    public static class PictureSpan {

        public String name;

    }

    public static class VideoSpan extends PictureSpan {

        public String name;

    }

    public static class CodeSpan extends PictureSpan {

        public String name;

    }

    public static class QuestionSpan {
    }

    public static class ChoiceSpan {
    }

    public static class SepSpan {
    }

    public static class AnswerSpan {
    }

    public static class AnalysisSpan {
    }

    public static class LoadedSpan {
    }

    public static class BaseHtmlHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable text, XMLReader xmlReader) {
            if (Const.TAG_PICTURE.equals(tag)) {
                int len = text.length();
                if (opening) {
                    text.setSpan(new PictureSpan(), len, len, Spannable.SPAN_MARK_MARK);
                } else {
                    Object obj = getLastSpanned(text, PictureSpan.class);
                    if (obj == null) {
                        return;
                    }
                    PictureSpan span = (PictureSpan) obj;
                    int start = text.getSpanStart(span);
                    CharSequence str = text.subSequence(start, text.length());
                    text.replace(start, len, "\uFFFC");
                    span.name = str.toString();
                }
            } else if (Const.TAG_VIDEO.equals(tag)) {
                int len = text.length();
                if (opening) {
                    text.setSpan(new VideoSpan(), len, len, Spannable.SPAN_MARK_MARK);
                } else {
                    Object obj = getLastSpanned(text, VideoSpan.class);
                    if (obj == null) {
                        return;
                    }
                    VideoSpan span = (VideoSpan) obj;
                    int start = text.getSpanStart(span);
                    CharSequence str = text.subSequence(start, text.length());
                    text.replace(start, len, "\uFFFC");
                    span.name = str.toString();
                }
            } else if (Const.TAG_CODE.equals(tag)) {
                int len = text.length();
                if (opening) {
                    text.setSpan(new CodeSpan(), len, len, Spannable.SPAN_MARK_MARK);
                } else {
                    Object obj = getLastSpanned(text, CodeSpan.class);
                    if (obj == null) {
                        return;
                    }
                    CodeSpan span = (CodeSpan) obj;
                    int start = text.getSpanStart(span);
                    CharSequence str = text.subSequence(start, text.length());
                    text.replace(start, len, "\uFFFC");
                    span.name = str.toString();
                }
            }
        }
    }

    public static Object getLastSpanned(Spanned text, Class<?> kind) {
        Object[] objs = text.getSpans(0, text.length(), kind);
        if (objs.length == 0) {
            return null;
        } else {
            return objs[objs.length - 1];
        }
    }
}
