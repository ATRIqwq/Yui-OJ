package com.yupi.springbootinit.judge;

import cn.hutool.json.JSONUtil;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.judge.codesendbox.CodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.CodeSendBoxFactory;
import com.yupi.springbootinit.judge.codesendbox.CodeSendBoxProxy;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;
import com.yupi.springbootinit.judge.codesendbox.model.JudgeInfo;
import com.yupi.springbootinit.judge.codesendbox.strategy.JudgeContext;
import com.yupi.springbootinit.model.dto.question.JudgeCase;
import com.yupi.springbootinit.model.entity.Question;
import com.yupi.springbootinit.model.entity.QuestionSubmit;
import com.yupi.springbootinit.model.enums.QuestionSubmitStatusEnum;
import com.yupi.springbootinit.model.vo.QuestionSubmitVO;
import com.yupi.springbootinit.service.QuestionService;
import com.yupi.springbootinit.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;
    
    @Resource
    private JudgeManager judgeManager;

    @Value("${codesend.type:example}")
    private  String type;

    @Override
    public QuestionSubmitVO doJudge(long questionSumbitId) {
        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSumbitId);
        if (questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        
        // 2）如果题目不是等待中，就不执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSumbitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新错误");
        }
        // 4）调用代码沙箱执行代码，得到执行返回结果
        CodeSendBox codeSendBox = CodeSendBoxFactory.newInstance(type);
        codeSendBox = new CodeSendBoxProxy(codeSendBox);
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codeSendBox.executeCode(executeCodeRequest);
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        List<String> outputResult = executeCodeResponse.getOutput();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        //根据不同判题策略设置返回值
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(judgeInfo);
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputResult);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfoResult = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSumbitId);
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResult));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        boolean updated = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updated){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSumbitId);


        return null;

    }
}
