package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Person extends PersonTest{

    protected Date birthdate;

    public Person(int currentDay, int currentMonth, int currentYear) {
        super(currentDay, currentMonth, currentYear);
    }

    public int getAge() {
        if (birthdate == null)
            return 0;
        else if (birthdate.compareTo(Calendar.getInstance().getTime()) == 1) {
            throw new IllegalArgumentException();
        }
        {
            int yearToday = currentYear;

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(birthdate);

            int birthYear = calendar.get(Calendar.YEAR);

            if (yearToday == birthYear)
                return yearToday - birthYear;
            else
                return yearToday - birthYear - 1;
        }
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setBirthDate(Date aBirthDate) {
        this.birthdate = aBirthDate;
    }
}
