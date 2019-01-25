package csu.edu.cn.pojo;

import java.io.Serializable;

/**
 * �洢ÿ���з� �����ǵ�һЩͳ����Ϣ
 */
public class Term implements Serializable {
    public String seg;   // �з�Ƭ��

    public int leftBound;  // ��߽�  ��ԭ���̾��е���ʼ����λ��
    public int rightBound;  // �ұ߽�

    public Term() {
    }
    public Term(String seg, int leftBound, int rightBound) {
        this.seg = seg;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(int leftBound) {
        this.leftBound = leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public void setRightBound(int rightBound) {
        this.rightBound = rightBound;
    }


    @Override
    public String toString() {
        return seg;
    }

    public String toTotalString() {
        return "Term{" +
                "seg='" + seg + '\'' +
                ", leftBound=" + leftBound +
                ", rightBound=" + rightBound +
                '}';
    }
}
