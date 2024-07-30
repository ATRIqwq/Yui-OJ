package com.yupi.springbootinit.judge.codesendbox.strategy;

import com.yupi.springbootinit.judge.codesendbox.model.JudgeInfo;
import com.yupi.springbootinit.model.dto.question.JudgeCase;
import com.yupi.springbootinit.model.entity.Question;
import com.yupi.springbootinit.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
