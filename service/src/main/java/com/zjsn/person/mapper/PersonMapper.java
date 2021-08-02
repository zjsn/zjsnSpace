package com.zjsn.person.mapper;

import com.zjsn.person.model.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yingluo
 * @since 2021-08-02
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
