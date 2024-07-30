package com.yupi.springbootinit.judge;

import com.yupi.springbootinit.judge.codesendbox.model.JudgeInfo;
import com.yupi.springbootinit.judge.codesendbox.strategy.DefaultJudgeStategy;
import com.yupi.springbootinit.judge.codesendbox.strategy.JavaLanguageJudgeStategy;
import com.yupi.springbootinit.judge.codesendbox.strategy.JudgeContext;
import com.yupi.springbootinit.judge.codesendbox.strategy.JudgeStrategy;
import com.yupi.springbootinit.model.enums.QuestionSubmitLanguageEnum;


/**
 * 判题管理（简化调用）
 */
public class JudgeManager {

    /**
     * 执行判题后，返回值的设置
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        String language = judgeContext.getQuestionSubmit().getLanguage();
        JudgeStrategy judgeStategy = new DefaultJudgeStategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)){
            judgeStategy = new JavaLanguageJudgeStategy();
        }
        return judgeStategy.doJudge(judgeContext);
    }

}
