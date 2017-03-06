package common.utils;

import common.exceptions.DAOException;
import models.Lection;
import models.Student;
import models.dao.JournalDAO2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mordr on 24.02.2017.
 */
public class LectionNotifyer {
    @Autowired
    JournalDAO2 journalDAO2;

    public void notyfy(Lection lection) {
        try {
            List<Student> students = journalDAO2.lectionJournal(lection.getId());
            for(Student student:students) {
                Mailer.sendMail("a","","");
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
