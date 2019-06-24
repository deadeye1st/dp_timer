package com.mrcoder.sbjpamultidb;

import com.mrcoder.sbjpamultidb.entity.master.StudentDao;
//import com.mrcoder.sbjpamultidb.entity.slave.TeacherDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbJpaMultidbApplicationTests {
    @Autowired
    private StudentDao studentDao;

//    @Autowired
//    private TeacherDao teacherDao;


    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate masterTempleate;


    @Autowired
    @Qualifier("slaveJdbcTemplate")
    protected JdbcTemplate slaveTempleate;

    @Test
    public void contextLoads() {

        masterTempleate.update("update  STUDENT_PLUS sp set sp.name='小芳' where sp.id=2");
        System.out.println(studentDao.findAll());
//        System.out.println(teacherDao.findAll());
        List<Map<String, Object>> students = masterTempleate.queryForList("select * from STUDENT_PLUS");
        for (Map<String, Object> student : students) {
            System.out.println(student.get("NAME"));
        }
        List<Map<String, Object>> teachers = slaveTempleate.queryForList("select * from teacher");
        for (Map<String, Object> teacher : teachers) {
            System.out.println(teacher.get("NAME"));
        }

    }

}
