import static org.junit.Assert.assertEquals;

import java.beans.Transient;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

	public class myMedicalTest {
        private User user;
        private Record record1;
        private Record record2;
        private Record record3;

    
    @BeforeEach
    public void setUp(){
        user = new User("1", "example@gmail.com", "1234", "John", "Doe");
        record1 = new Record("1", 30.0, 23.0, 100.0, 120.0, "record 1", "10/10/2020");
        record2 = new Record("2", 60.0, 20.0, 60.0, 120.0, "record 2", "20/10/2020");
        record3 = new Record("3", 80.0, 22.0, 68.0, 115.0, "record 3", "12/12/2020");
    }

    @Test
    public void nameChangeTest(){
        user.editProfile("jane", "slow");
        assertEquals("jane", user.getFirstName());
        assertEquals("slow", user.getSurname());
    }
    }
    