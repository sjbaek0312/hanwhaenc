package examples.springjdbc.service;

import examples.springjdbc.dao.InfoDao;
import examples.springjdbc.dto.Info;
import examples.springjdbc.dto.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfoService {
    @Autowired
    InfoDao infoDao;

    @Autowired
    LogService logService;

    @Transactional
    public int addInfo(Info info){
        int pk = infoDao.insert(info);

        Log log = new Log();
        log.setData("addInfo log");

        logService.addInfo(log);

        if(1 == 1)
            throw new RuntimeException("addInfo error");
        return pk;
    }
}
