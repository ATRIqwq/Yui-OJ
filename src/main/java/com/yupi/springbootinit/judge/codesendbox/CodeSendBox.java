package com.yupi.springbootinit.judge.codesendbox;

import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeRequest;
import com.yupi.springbootinit.judge.codesendbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSendBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
