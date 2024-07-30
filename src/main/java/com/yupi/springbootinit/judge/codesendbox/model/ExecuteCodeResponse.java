package com.yupi.springbootinit.judge.codesendbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {


    /**
     * 输出用例
     */
    private List<String> output;

    /**
     * 接口信息
     */
    private String message;

    /**
     * 执行结果
     */
    private Integer status;

    /**
     * 执行信息
     */
    private JudgeInfo judgeInfo;

}
