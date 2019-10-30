/**
 * [Class description:] testing using the equivalence partitioning technique
 *
 * @alpotocki
 * @version
 *
 * Valid : Birthdate is in the past, starting from AD
 *   Partition: Birthday today or past; Expected result: age = year - birthyear
 *     Subpartition: Year is current
 *       Testcase: Yesterday {testGetAgeYesterday},
 *       Testcase: Today {testGetAgeToday},
 *     Subpartition: Year is current+250
 *       Testcase: Yesterday {testGetAgeYesterday},
 *       Testcase: Today {testGetAgeToday},
 *   Partition: no birthday yet in this year; Expected result: age = year - birthyear - 1
 *     Subpartition: Year is current
 *       Testcase: Tomorrow {testGetAgeTomorrow}
 *     Subpartition: Year is current+250
 *       Testcase: Tomorrow {testGetAgeTomorrow}
 *
 * Invalid area: Birthdate in the future, Uninitialized, BC, Boundaries
 *   Partition: Future; Expected result: AssertionError
 *     Testcase: Today {testGetAgeOfBornTomorrow}
 *   Partition: Uninitialized; Expected result: 0
 *     Testcase: Today  {testGetAgeWithAnUninitializedDate}
 */
package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PersonTest {

    protected Person person;
    protected int currentYear;
    protected int currentMonth;
    protected int currentDay;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Calendar currentDate = Calendar.getInstance();
        return Arrays.asList(new Object[][] {
                { currentDate.get(Calendar.DATE), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR) },
                { currentDate.get(Calendar.DATE), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR) + 250 }
        });


    }

    public PersonTest(int currentDay, int currentMonth, int currentYear){
        this.currentDay = currentDay;
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
    }

    @Before
    public void setUp() throws Exception { person = new Person(currentDay, currentMonth, currentYear); }

    @Test
    public void testGetAgeToday() {
        GregorianCalendar calendar = new GregorianCalendar(1971, currentMonth, currentDay);
        person.setBirthDate(calendar.getTime());

        int actual = person.getAge();

        Calendar birthDate = new GregorianCalendar();
        birthDate.setTime(person.birthdate);
        int expected = currentYear - birthDate.get(Calendar.YEAR);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetAgeYesterday() {
        GregorianCalendar calendar = new GregorianCalendar(1971, currentMonth, currentDay);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        person.setBirthDate(calendar.getTime());

        int actual = person.getAge();
        int expected;

        Calendar birthDate = new GregorianCalendar();
        birthDate.setTime(person.birthdate);

        expected = currentYear - birthDate.get(Calendar.YEAR);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAgeTomorrow() {
        GregorianCalendar calendar = new GregorianCalendar(1971, currentMonth, currentDay);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        person.setBirthDate(calendar.getTime());

        int actual = person.getAge();
        int expected;

        Calendar birthDate = new GregorianCalendar();
        birthDate.setTime(person.birthdate);

        expected = currentYear - birthDate.get(Calendar.YEAR) - 1;

        assertEquals(expected, actual);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testGetAgeOfBornTomorrow() {
        GregorianCalendar calendar = new GregorianCalendar(currentYear, currentMonth, currentDay);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        person.setBirthDate(calendar.getTime());
        person.getAge();

    }

    @Test(expected= NullPointerException.class)
    public void testGetAgeWithAnUninitializedDate() {
        person.getAge();
    }


    @After
    public void tearDown() throws Exception { }
}