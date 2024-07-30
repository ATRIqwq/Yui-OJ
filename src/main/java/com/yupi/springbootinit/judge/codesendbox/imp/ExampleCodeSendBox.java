package com.yupi.springbootinit.judge.codesendbox.imp;

import com.yupi.springbootinit.judge.codesendbox.CodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;
import com.yupi.springbootinit.judge.codesendbox.model.JudgeInfo;
import com.yupi.springbootinit.model.enums.JudgeInfoMessageEnum;
import com.yupi.springbootinit.model.enums.QuestionSubmitStatusEnum;

import java.util.List;


public class ExampleCodeSendBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(inputList);
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        executeCodeResponse.setMessage("测试执行成功");
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        System.out.println("实例沙箱");
        return executeCodeResponse;
    }
}
