package com.yupi.springbootinit.judge.codesendbox;

import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSendBoxProxy implements CodeSendBox{

    private final CodeSendBox codeSendBox;

    public CodeSendBoxProxy(CodeSendBox codeSendBox) {
        this.codeSendBox = codeSendBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSendBox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
