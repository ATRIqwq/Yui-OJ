package com.yupi.springbootinit.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;


@Data
public class QuestionSubmitAddRequest implements Serializable {
    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;
    private static final long serialVersionUID = 7001665736530108522L;
}
