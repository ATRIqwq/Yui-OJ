package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.QuestionSubmit;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86136
* @description 针对表【question_submit(题目提交)】的数据库操作Mapper
* @createDate 2024-07-19 10:59:22
* @Entity com.yupi.springbootinit.model.entity.QuestionSubmit
*/
@Mapper
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {

}




