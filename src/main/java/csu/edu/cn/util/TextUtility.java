package csu.edu.cn.util;

import csu.edu.cn.pojo.Term;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * �ı�������
 */
public class TextUtility {
    /**
     * �������ƥ��
     * @param str
     * @return
     */
    /*public  List<String> leftMax(String str) {

        List<String> results = new ArrayList<String>();
        String input = str;

        while( input.length() > 0 ) {
            String subSeq;
            if( input.length() < MAX_LENGTH)  // ÿ��ȡС�ڻ��ߵ�������ֵ䳤�ȵ��Ӵ�����ƥ��
                subSeq = input;
            else
                subSeq = input.substring(0, MAX_LENGTH);

            while( subSeq.length() > 0 ) {
                // ����ֵ��к��и��Ӵ������Ӵ�������Ϊ1���Ӵ�ƥ��ɹ�
                if( dictionary.contains(subSeq) || subSeq.length() == 1) {
                    results.add(subSeq);
                    // �����д�ǰ���ȥ���Ѿ�ƥ����Ӵ�
                    input = input.substring(subSeq.length());
                    break;      // �˳�ѭ����������һ��ƥ��
                } else {
                    // ȥ��ƥ���ֶ�������һ����
                    subSeq = subSeq.substring(0, subSeq.length() - 1);
                }
            }

        }
        return results;
    }*/

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
