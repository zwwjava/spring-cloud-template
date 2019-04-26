package com.ww.common.utils;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 * @ClassName: StringTools
 * @Description: 字符串处理工具类
 * @author huxb
 * @date 2016年8月28日 下午5:36:08
 *
 */
public class StringTools {

    private static Random random = new Random();

    private StringTools() {
	}

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }


    public static int getLength(String src) {
        return null != src && !"".equals(src)?src.getBytes().length:0;
    }

    public static String nvl(Object o) {
        return null == o?"":o.toString().trim();
    }

    public static String getMsg(String value, Object[] params) {
        try {
            return MessageFormat.format(value, params);
        } catch (Exception var3) {
            return value;
        }
    }

    public static String fenToYuan(String s, float def) {
        float fen = toFloat(s, 0.0F);
        String sFen = (int)Math.floor((double)fen) + "";
        int len = sFen.length();
        String yuan;
        if(len == 1) {
            yuan = "0.0" + sFen;
        } else if(len == 2) {
            yuan = "0." + sFen;
        } else {
            yuan = sFen.substring(0, len - 2) + '.' + sFen.substring(len - 2);
        }

        return fen < 0.0F?'-' + yuan:yuan;
    }

    public static int yuanToFen(String s, int def) {
        int fen;
        try {
            BigDecimal e = new BigDecimal(s);
            fen = e.movePointRight(2).intValue();
        } catch (Exception var4) {
            fen = def * 100;
        }

        return fen;
    }

    public static float toFloat(String s, float def) {
        float f;
        try {
            f = Float.parseFloat(s);
        } catch (Exception var4) {
            f = def;
        }

        return f;
    }

    public static int toInt(String s, int def) {
        int value;
        try {
            value = Integer.parseInt(s);
        } catch (Exception var4) {
            value = def;
        }

        return value;
    }

    public static double toDouble(String s, Double def) {
        Double value;
        try {
            value = Double.valueOf(s);
        } catch (Exception var4) {
            value = def;
        }

        return value.doubleValue();
    }

    public static Long toLong(String s, long def) {
        long value;
        try {
            value = Long.parseLong(s);
        } catch (Exception var6) {
            value = def;
        }

        return Long.valueOf(value);
    }

    public static String toView(String text) {
        text = replace(text, "&amp;", "&");
        text = replace(text, "&lt;", "<");
        text = replace(text, "&gt;", ">");
        text = replace(text, "&quot;", "\"");
        text = replace(text, "&apos;", "\'");
        return text;
    }

    public static String text2Html(String text) {
        text = replace(text, "&", "&amp;");
        text = replace(text, "<", "&lt;");
        text = replace(text, ">", "&gt;");
        text = replace(text, "\"", "&quot;");
        text = replace(text, "\'", "&apos;");
        return text;
    }

    public static String escForWml(String text) {
        text = replace(text, "$", "$$");
        return text;
    }

    public static String replace(String text, String find, String replace) {
        if(text != null && find != null && replace != null) {
            int findLen = find.length();
            int textLen = text.length();
            if(textLen != 0 && findLen != 0) {
                StringBuffer sb = new StringBuffer(256);
                int begin = 0;

                for(int i = text.indexOf(find); i != -1; i = text.indexOf(find, begin)) {
                    sb.append(text.substring(begin, i));
                    sb.append(replace);
                    begin = i + findLen;
                }

                if(begin < textLen) {
                    sb.append(text.substring(begin));
                }

                return sb.toString();
            } else {
                return text;
            }
        } else {
            return text;
        }
    }

    public static String convertDiscount(String discount) {
        return discount != null && !"".equals(discount.trim())?(discount.endsWith("0")?discount.substring(0, discount.length() - 1):discount):"";
    }

    public static boolean isEmpty(String str) {
        return str == null || 0 == str.trim().length();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static Collection<String> toCollection(Collection<String> collection, String str, String splitStr) {
        if(null != str && !"".equals(str) && null != splitStr && !"".equals(splitStr)) {
            String[] strArray = str.trim().split(splitStr);
            String[] var4 = strArray;
            int var5 = strArray.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String tmpStr = var4[var6];
                collection.add(tmpStr.trim());
            }
        }

        return collection;
    }

    public static boolean isDigtial(String str) {
        return isEmpty(str)?false:str.matches("\\d+");
    }

    public static String replaceBacklash(String str) {
        return isEmpty(str)?"":str.replace("\\", "/");
    }

    public static String subString(String str, int begin, int end) {
        if(str == null) {
            return str;
        } else {
            int b = Math.max(begin, 0);
            int e = Math.min(end, str.length());
            return str.substring(b, e);
        }
    }

    public static boolean matches(String base, String[] matches) {
        if(null != matches && 0 != matches.length) {
            for(int i = 0; i < matches.length; ++i) {
                if(matches[i].equals(base)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static String replaceFullSpaceToNbsp(String str) {
        if(!isEmpty(str) && !isEmpty(str.replace("　", "").trim())) {
            str = str.trim();
            str = str.replaceAll("[　]+$", "");
            return str.replace("　", "&nbsp;&nbsp;");
        } else {
            return "";
        }
    }

    public static String getUserDefinedName(String rule) {
        if(!isEmpty(rule) && rule.indexOf("/") >= 0) {
            String tempString = rule.split("/")[1];
            return text2Html(tempString.substring(0, tempString.length() - 1));
        } else {
            return "";
        }
    }

    public static String getTrim(String str, String def) {
        if(str != null && !"null".equals(str)) {
            String t = str.trim();
            return t.length() == 0?def:t;
        } else {
            return def;
        }
    }

    public static String getStrBylength(String str, int len, String des) {
        if(null == str) {
            return "";
        } else {
            int strByteLen = str.getBytes().length;
            int strLen = str.length();
            if(len >= strByteLen) {
                return str;
            } else if(strByteLen <= strLen) {
                return str.substring(0, len) + des;
            } else {
                StringBuffer sb = new StringBuffer();
                char[] charSet = str.toCharArray();
                int i = 0;

                for(int j = 0; i < strLen && j < len; ++i) {
                    if(String.valueOf(charSet[i]).matches("[^x00-xff]")) {
                        sb.append(charSet[i]);
                        j += 2;
                    } else {
                        sb.append(charSet[i]);
                        ++j;
                    }
                }

                return sb.toString() + des;
            }
        }
    }

    public static String changeDiscount(int orgDiscount) {
        String discount = "";
        if(orgDiscount / 10 == 0) {
            discount = "0." + String.valueOf(orgDiscount % 10);
        } else if(orgDiscount % 10 == 0) {
            discount = String.valueOf(orgDiscount / 10);
        } else {
            discount = orgDiscount / 10 + "." + orgDiscount % 10;
        }

        return discount;
    }

    public static String subConetnXml(String nodeString, String xml) {
        int start = xml.indexOf(">", xml.indexOf("<" + nodeString));
        int end = xml.indexOf("</" + nodeString + ">");
        return xml.substring(start + 1, end);
    }

    public static String subStrByTod(String instr) {
        String moneyStri = instr;
        if(instr.length() < 4) {
            return instr;
        } else {
            if(instr.length() < 7) {
                moneyStri = instr.substring(0, instr.length() - 3) + "," + instr.substring(instr.length() - 3, instr.length());
            } else if(instr.length() < 10) {
                moneyStri = instr.substring(0, instr.length() - 6) + "," + instr.substring(instr.length() - 6, instr.length() - 3) + "," + instr.substring(instr.length() - 3, instr.length());
            }

            return moneyStri;
        }
    }

    public static String isNull(String str) {
        return str == null?"":str;
    }

    public static String isNull(Object o) {
        if(o == null) {
            return "";
        } else {
            String str = "";
            if(o instanceof String) {
                str = (String)o;
            } else {
                str = o.toString();
            }

            return str;
        }
    }

    public static String UrlDecoder(String sStr) {
        String sReturnCode = sStr;

        try {
            sReturnCode = URLDecoder.decode(sStr, "utf-8");
        } catch (Exception var3) {
            ;
        }

        return sReturnCode;
    }

    public static String formatFloatNumber(double value) {
        if(value != 0.0D) {
            DecimalFormat df = new DecimalFormat("########.00");
            return df.format(value);
        } else {
            return "0.00";
        }
    }

    public static String subDoublePoint(Double value) {
        if(value == null) {
            return null;
        } else if(value.doubleValue() != 0.0D) {
            DecimalFormat df = new DecimalFormat("##########");
            return df.format(value);
        } else {
            return "0";
        }
    }

    public static Object isNullThenZero(Object o) {
        return o == null?Integer.valueOf(0):o;
    }

    public static String signMobile(String mobile) {
        return isEmpty(mobile)?"":mobile.substring(0, mobile.length() - mobile.substring(3).length()) + "****" + mobile.substring(7);
    }

    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0?"char":"num";
            if("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0?65:97;
                val = val + (char)(random.nextInt(26) + temp);
            } else if("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static String getStringRandom() {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < 6; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0?"char":"num";
            if("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0?65:97;
                val = val + (char)(random.nextInt(26) + temp);
            } else if("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }


    /**
     * 是否包含特殊字符或数字
     * @param str
     * @return
     */
    public static boolean containSpecialCharAndNum(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|[0-9]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
