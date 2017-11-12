package com.tulip.girlTest.Service;

import com.tulip.girlTest.domain.Girl;
import com.tulip.girlTest.properties.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    public Girl findOne(Integer id){
        return girlRepository.findOne(id);

    }

    //事务管理
    @Transactional
    public void insertTwo(){
        Girl girl_1 = new Girl();
        girl_1.setAge(19);
        girl_1.setCupSize("B");
        girl_1.setMoney(129.0);
        girlRepository.save(girl_1);

        Girl girl_2 = new Girl();
        girl_2.setAge(20);
        girl_2.setCupSize("C");
        girl_2.setMoney(1.1);
        girlRepository.save(girl_2);
    }


}
