package cn.fayostyle.service.impl;

import cn.fayostyle.dao.IDinnerTableDao;
import cn.fayostyle.entity.DinnerTable;
import cn.fayostyle.factory.BeanFactory;
import cn.fayostyle.service.IDinnerTableService;

import java.util.List;

/**
 * Created by HuangPan on 2017/6/1.
 */
public class DinnerTableService implements IDinnerTableService  {

    private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao",IDinnerTableDao.class);
    @Override
    public void add(DinnerTable dt) {
        dinnerTableDao.add(dt);
    }

    @Override
    public void delete(int id) {
        dinnerTableDao.delete(id);
    }

    @Override
    public void update(DinnerTable dt) {
        dinnerTableDao.update(dt);
    }

    @Override
    public List<DinnerTable> query() {
        return dinnerTableDao.query();
    }

    @Override
    public DinnerTable findById(int id) {
        return dinnerTableDao.findById(id);
    }

    @Override
    public List<DinnerTable> query(String keyword) {
        return dinnerTableDao.query(keyword);
    }

    @Override
    public void quitTable(int id) {
        dinnerTableDao.quitTable(id);
    }
}
