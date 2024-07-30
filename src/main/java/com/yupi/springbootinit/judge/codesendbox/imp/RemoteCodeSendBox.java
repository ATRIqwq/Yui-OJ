package com.yupi.springbootinit.judge.codesendbox.imp;

import com.yupi.springbootinit.judge.codesendbox.CodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;

public class RemoteCodeSendBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程沙箱调用");
        return null;

    }
}
