package com.yupi.springbootinit.judge.codesendbox.imp;

import com.yupi.springbootinit.judge.codesendbox.CodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;

public class ThirdPartCodeSendBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方沙箱调用");
        return null;
    }
}
