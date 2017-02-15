package com.imp.lf.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.imp.lf.entities.Words;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
public interface WordsMapper extends AutoMapper<Words>{


   void deleteWordsById(@Param("id") Long id);

   List<Words> findWordsById(@Param("id") Long id);
}
