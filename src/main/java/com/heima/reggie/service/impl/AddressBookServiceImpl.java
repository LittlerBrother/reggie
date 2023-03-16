package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.domain.AddressBook;
import com.heima.reggie.mapper.AddressBookMapper;
import com.heima.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;


/**
 * @Description
 * @Author rdm
 * @data 2022/5/29 - 23:04
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
