package examples.springjdbc.service;

import examples.springjdbc.dao.LogDao;
import examples.springjdbc.dto.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {
    @Autowired
    LogDao logDao;

    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addInfo(Log log){
        return logDao.insert(log);
    }
}
