package services.servicesimpl;

import common.exceptions.DAOException;
import models.Lection;
import models.dao.LectionDAO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */

@Service
public class LectionService {
    @Autowired
    LectionDAO2 lectionDAO2;

    public List<Lection> getLections() throws DAOException {
        return lectionDAO2.list();
    }
    public Lection getLection(Integer id) throws DAOException {
        return lectionDAO2.get(id);
    }
    public void createLection(Lection lection) throws DAOException {
        lectionDAO2.add(lection);
    }
    public void updateLection(Lection lection) throws DAOException {
        lectionDAO2.edit(lection);
    }
    public void deleteLection(Integer id) throws DAOException {
        lectionDAO2.delete(id);
    }

    public List<Lection> getNeariestLections() throws DAOException{
        return lectionDAO2.getNeariest();
    }
}
