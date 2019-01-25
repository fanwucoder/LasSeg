package csu.edu.cn.seg;

import csu.edu.cn.trie.bintrie.BinTrie;
import csu.edu.cn.util.Constans;
import csu.edu.cn.util.TextUtility;

import java.io.*;
import java.util.TreeMap;

/**
 * @program: LasSeg    @author: shan junwei
 * @description
 * @create: 2019-01-24 11:03
 **/
public class TrieSegment {
    public BinTrie trie = new BinTrie();
    public int MAX_WORD_LEN = 1;   // �ֵ��дʵ���󳤶�

    public TrieSegment() {
        initDict(Constans.lasDicPath);
    }
  /*  static {
        initDict(Constans.lasDicPath);
    }*/
    /**
     * ���ļ���ʼ���ֵ�
     *
     * @param dicPath
     */
    public void initDict(String dicPath) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        try {
            // ��utf-8��ȡ�ļ�
            FileInputStream fis = new FileInputStream(dicPath);
            InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
                String  temp  =  str.substring(0,str.indexOf("-"));   // ȥ����׺ -�Ժ���ַ�
                if(TextUtility.isChineseStr(temp)){
                    str  = temp;
                }
                treeMap.put(str, 0);
                MAX_WORD_LEN = Math.max(str.split("-").length, MAX_WORD_LEN);  // �����ֵ���ʵ������
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        trie.build(treeMap);
    }

    public static void main(String[] args) {

        System.out.println("����-  dm".substring(0,"����-  dm".indexOf("-")));
    }

}
