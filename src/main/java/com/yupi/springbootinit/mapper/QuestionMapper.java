package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.springbootinit.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86136
* @description 针对表【question(题目表)】的数据库操作Mapper
* @createDate 2024-07-17 16:38:27
* @Entity com.yupi.springbootinit.model.entity.Question
*/

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




