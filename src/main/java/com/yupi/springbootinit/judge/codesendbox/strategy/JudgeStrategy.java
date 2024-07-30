package com.yupi.springbootinit.judge.codesendbox.strategy;


import com.yupi.springbootinit.judge.codesendbox.model.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {


    JudgeInfo doJudge(JudgeContext judgeContext);
}
