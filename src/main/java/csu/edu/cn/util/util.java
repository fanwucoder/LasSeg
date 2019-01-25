package csu.edu.cn.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class util {
    //   ��������ʶ��
    public static boolean CaCaseRecognition(String segment) {
        // ��������������֤
        String regEx = "\\\\xe0\\\\xbd\\\\x91";
        // ����������ʽ
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(segment);
        /*while (matcher.find()){
            System.out.println(matcher.group());
        }*/
        return matcher.find();  // �ַ����Ƿ���������ʽ��ƥ��
    }

    // ��byte[] ת���� ʮ�������ַ���
    public static String bytesToHexStr(byte[] bytes) {
         char[] HEX_CHAR = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int index = 0;
        char[] hexChar = new char[bytes.length * 2];
        for(int i = 0; i < bytes.length; i++) {
            hexChar[index++] = HEX_CHAR[bytes[i] >> 4 & 0xF];
            hexChar[index++] = HEX_CHAR[bytes[i] & 0xF];
        }
        return new String(hexChar);
    }

    //  16�����ַ���ת�� �ַ���


    public static void main(String[] args) throws Exception {

        String  utf16Str  =    bytesToHexStr("�Ϻ�".getBytes());

        System.out.println(unicode(utf16Str));
        System.out.println(bytesToHexStr("�Ϻ�".getBytes()));  // \xe4\xb8\x8a\xe6\xb5\xb7

    }

    public static String unicode(String source) {
        StringBuffer sb = new StringBuffer();
        char[] source_char = source.toCharArray();
        String unicode = null;
        for (int i = 0; i < source_char.length; i++) {
            unicode = Integer.toHexString(source_char[i]);
            if (unicode.length() <= 2) {
                unicode = "00" + unicode;
            }
            sb.append("\\u" + unicode);
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static String decodeUnicode(String unicode) {
        StringBuffer sb = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            sb.append((char) data);
        }
        return sb.toString();
    }
}
