package com.yupi.springbootinit.model.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuestionSubmitLanguageEnum {
    JAVA("java", "java"),
    CPLUSPLUS("cpp", "cpp"),
    GOLANG("go", "go");

    private final String text;

    private final String value;

    QuestionSubmitLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }


    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }


    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues(){
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitLanguageEnum getByEnumValue(String value){
        if (ObjectUtil.isEmpty(value)){
            return null;
        } else {
            for (QuestionSubmitLanguageEnum anEnum : QuestionSubmitLanguageEnum.values()) {
                if (anEnum.value.equals(value)){
                    return anEnum;
                }
            }
            return null;
        }

    }


}
