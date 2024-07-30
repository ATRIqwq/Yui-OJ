package com.yupi.springbootinit.judge.codesendbox;

import com.yupi.springbootinit.judge.codesendbox.imp.ExampleCodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.imp.RemoteCodeSendBox;
import com.yupi.springbootinit.judge.codesendbox.imp.ThirdPartCodeSendBox;

/**
 * 代码沙箱工厂（根据传入参数创建相应的代码沙箱）
 */
public class CodeSendBoxFactory {

    /**
     * 创建代码沙箱示例
     *
     * @param type 沙箱类型
     * @return
     */
    public static CodeSendBox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSendBox();
            case "remote":
                return new RemoteCodeSendBox();
            case "thirdParty":
                return new ThirdPartCodeSendBox();
            default:
                return new ExampleCodeSendBox();
        }


    }


}
