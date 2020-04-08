package club.lazy.common.util.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

    /**
     * 过滤所有HTML 标签
     *
     * @param htmlStr
     * @return
     */
    public static String filterHTMLTag(String htmlStr) {
        //定义HTML标签的正则表达式
        String reg_html = "<[^>]+>";
        Pattern pattern = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(""); //过滤html标签
        return htmlStr;
    }

    /**
     * 过滤标签，通过标签名
     *
     * @param htmlStr
     * @param tagName
     * @return
     */
    public static String filterTagByName(String htmlStr, String tagName) {
        String reg_html = "<" + tagName + "[^>]*?>[\\s\\S]*?</" + tagName + ">";
        Pattern pattern = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(""); //过滤html标签
        return htmlStr;
    }

    /**
     * 过滤标签上的 style 样式
     *
     * @param htmlStr
     * @return
     */
    public static String filterHTMLTagInStyle(String htmlStr) {
        String reg_html = "style=(['\"])(.*?)(['\"])";
        Pattern pattern = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        htmlStr = matcher.replaceAll(""); //过滤html标签
        return htmlStr;
    }

    /**
     * 替换表情
     *
     * @param htmlStr
     * @return
     */
    public static String replayFace(String htmlStr) {
        String reg_html = "\\[em_\\d+]";
        Pattern pattern = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                String num = matcher.group(0);
                String number = num.substring(num.lastIndexOf('_') + 1, num.length() - 1);
                htmlStr = htmlStr.replace(num, "<img src='/face/arclist/" + number + ".gif' border='0' />");
            }
        }
        return htmlStr;
    }
}
