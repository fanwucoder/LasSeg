package csu.edu.cn.util;

import csu.edu.cn.pojo.Term;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * �ı�������
 */
public class TextUtility {
    /**
     * ���쳣תΪ�ַ���
     *
     * @param e
     * @return
     */
    public static String exceptionToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     *  ʶ�������ַ�
     */
    public static boolean isChineseStr(String text) {   // ����ʶ������
        return Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(text).matches();
    }

    /**
     * ��ת�ַ���
     */
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * ��׼��ƫ��λ�ò�ֵ,Ŀ����Ϊ���ò����ֵ�ĳ��ȴ�С�̶�
     * TODO: ���������Ӳ�����,�����100����
     */
    public static String getShiftStandardPosition(int left, int right) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTriNum(left)+"->"+getTriNum(right));
        return stringBuilder.toString();
    }


    public static String getTriNum(int num) {
        StringBuilder triNumStr = new StringBuilder();
        String str = String.valueOf(num);
        if (str.length() == 1) {
            triNumStr.append("00").append(str);
        } else if (str.length() == 2) {
            triNumStr.append("0").append(str);
        } else {
            triNumStr.append(str);
        }
        return triNumStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(getTriNum(2));
    }

    /**
     * ���ó�ȡ�����Ĵʶ�ԭ������зִ���   ���Ǵ����ȡ��ִ�ת��Ĺ���
     * ��������exactWords�ǰ�˳������
     */
    public static String handleSentenceWithExtractWords(String sentence, List<Term> exactWords, boolean right) {
        StringBuilder stringBuilder = new StringBuilder(sentence);
        int shift = 0;  // ƫ����
        for (Term term : exactWords) {
            stringBuilder.insert(term.rightBound + shift, getShiftStandardPosition(term.leftBound, term.rightBound));  // ��Ϊ��ֵ��ԭ��,λ�÷���ƫ��
            if (!right) shift = shift + 8;
        }
        sentence = stringBuilder.toString();
        for (Term word : exactWords) {
            String hatWord = word.getSeg() + getShiftStandardPosition(word.leftBound, word.rightBound);  // ���߽�Ĵ�
            sentence = sentence.replaceAll(hatWord, " " + word.getSeg() + " ");    // ��������һ������ ���� 5 25 ;����ƽ ��ƽ
        }
        //System.out.println("��������Ԫ����-->" + sentence);
        sentence = sentence.trim();  // ȥ��β�ո�
        sentence = sentence.replaceAll("\\s{1,}", " ");  // ȥ�����ո�
        return sentence;
    }

    public static List<String> handleSentenceWithExtractWordsToList(String sentence, List<Term> exactWords, boolean right) {
        String str = handleSentenceWithExtractWords(sentence, exactWords, right);
        String[] array = str.split(" ");
        return Arrays.asList(array);
    }


}
