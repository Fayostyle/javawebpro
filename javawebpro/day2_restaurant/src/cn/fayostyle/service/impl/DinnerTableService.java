package cn.fayostyle.service.impl;

import cn.fayostyle.dao.IDinnerTableDao;
import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.entity.TableStatus;
import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IDinnerTableService;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public class DinnerTableService implements IDinnerTableService {

    private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao",IDinnerTableDao.class);


    @Override
    public List<DinnerTable> findNoUseTable() {
        return dinnerTableDao.findByStatus(TableStatus.Free);
    }

    @Override
    public DinnerTable findById(int id) {
        return dinnerTableDao.findById(id);
    }
}
