package com.zjsn.person.service.impl;

import com.zjsn.person.model.Person;
import com.zjsn.person.mapper.PersonMapper;
import com.zjsn.person.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingluo
 * @since 2021-08-02
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
