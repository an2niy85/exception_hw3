package ru.my.hw_exception.exception_strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exceptions {
    public static boolean nameCapitalized(String str) throws Exception {
        if (!Character.isUpperCase(str.charAt(0)))
            throw new Exception("Первая буква не заглавная");
        return true;
    }

    public static boolean nameContainsDigit(String str) throws Exception {
        int length = str.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(str.charAt(i)))
                throw new Exception("Слово содержит цифру");
        }
        return false;
    }

    public static void checkFIO (String[] strs) throws Exception {
        if (strs.length != 3)
            throw new Exception("ФИО имеет не верное кол-во слов");
        for (int i = 0; i < 3; i++) {
            if (!nameCapitalized(strs[i]) || nameContainsDigit(strs[i]))
                throw new Exception("Не все слова ФИО начинаются с заглавной буквы или содержат цифры");
        }
    }

    public static void checkBirthdate (String str) {
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date birthDate;
        try {
            birthDate = format.parse(str);
            if (!birthDate.after(new Date(1923)) && !birthDate.before(new Date(2023)))
                throw new Exception("Не валидный год");
        } catch (ParseException e) {
            throw new RuntimeException("Неверный формат даты рождения", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkPhoneNumber(String str) throws Exception {
        long phoneNumber;
        if (str.length() != 11)
            throw new Exception("Неверная длина номера");
        phoneNumber = Long.parseLong(str);
    }

    public static void checkSex(String str) throws Exception {
        if (!str.equals("f") && !str.equals("m"))
            throw new Exception("Неверный пол m или f");
    }
}
