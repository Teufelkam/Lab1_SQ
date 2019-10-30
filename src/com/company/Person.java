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
        if (birthdate == null){
            throw new NullPointerException();
        }
        else if (birthdate.compareTo(Calendar.getInstance().getTime()) == 1) {
            throw new IllegalArgumentException();
        }
        else
        {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(birthdate);


            int birthYear = calendar.get(Calendar.YEAR);
            int birthMonth = calendar.get(Calendar.MONTH);
            int birthDay = calendar.get(Calendar.DATE);

            if (currentMonth > birthMonth ||
                (currentMonth == birthMonth && currentDay >= birthDay))
            {
                return currentYear - birthYear;
            }
            else
            {
                return currentYear - birthYear - 1;
            }
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
