package cc.isotopestudio.Entitled.type;

import cc.isotopestudio.Entitled.util.S;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public enum AttriType {

    CRITICAL("����"), //��������.�����˺�����
    ADDITIONAL("�����˺�"),
    LIFE("����ֵ"),
    DODGE("����"),
    SPEED("�ƶ��ٶ�"),
    DEFENSE("����"),
    EXPERIENCE("�౶����"),
    VAMPIRIC("����͵ȡ");

    final String name;

    AttriType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription(double[] parameters) {
        String result = "" /*S.toBoldGold(this.toString())*/;
        switch (this) {
            case CRITICAL:
                result += S.toAqua(" + " +
                        (int) (parameters[0] * 100) + "% �������" + parameters[1] + "���˺�");
                break;
            case ADDITIONAL:
                result += S.toAqua(" + " + parameters[0] + " �����˺�");
                break;
            case LIFE:
                result += S.toAqua(" + " + parameters[0] + " ����ֵ");
                break;
            case DEFENSE:
                result += S.toAqua(" + " + parameters[0] + " ����ֵ");
                break;
            case DODGE:
                result += S.toAqua(" " + (int) (parameters[0] * 100) + "% ����");
                break;
            case SPEED:
                result += S.toAqua(" + " + parameters[0] + " �ƶ��ٶ�");
                break;
            case EXPERIENCE:
                result += S.toAqua(" + " + parameters[0] + " ������");
                break;
            case VAMPIRIC:
                result += S.toAqua(" + " + parameters[0] + "% ����͵ȡ");
                break;
        }
        return result;
    }
}
