package com.yupi.springbootinit.judge;

import com.yupi.springbootinit.model.vo.QuestionSubmitVO;
import org.springframework.stereotype.Service;

/**
 * 判题服务
 */
@Service
public interface JudgeService {

    /**
     * 判题
     * @param questionSumbitId
     * @return
     */
    QuestionSubmitVO doJudge(long questionSumbitId);

}
