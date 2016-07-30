package cc.isotopestudio.Entitled.type;

import cc.isotopestudio.Entitled.util.S;

/**
 * Created by Mars on 7/30/2016.
 * Copyright ISOTOPE Studio
 */
public enum AttriType {

    CRITICAL("暴击"), //暴击几率.暴击伤害倍率
    ADDITIONAL("攻击伤害"),
    LIFE("生命值"),
    REGENERATION("生命恢复"),
    DODGE("闪避"),
    SPEED("移动速度"),
    STEADY("击退抗性"),
    EXPERIENCE("多倍经验"),
    VAMPIRIC("生命偷取");

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
                        (int) (parameters[0] * 100) + "% 几率造成" + parameters[1] + "倍伤害");
                break;
            case ADDITIONAL:
                result += S.toAqua(" + " + parameters[0] + " 攻击伤害");
                break;
            case LIFE:
                result += S.toAqua(" + " + parameters[0] + " 生命值");
                break;
            case REGENERATION:
                result += S.toAqua(" + " + parameters[0] + " 生命恢复");
                break;
            case DODGE:
                result += S.toAqua(" " + (int) (parameters[0] * 100) + "% 闪避");
                break;
            case SPEED:
                result += S.toAqua(" + " + parameters[0] + " 移动速度");
                break;
            case STEADY:
                result += S.toAqua(" + " + parameters[0] + " 击退抗性");
                break;
            case EXPERIENCE:
                result += S.toAqua(" + " + parameters[0] + " 倍经验");
                break;
            case VAMPIRIC:
                result += S.toAqua(" + " + parameters[0] + "% 生命偷取");
                break;
        }
        return result;
    }
}
