package org.example;

//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
//Фамилия Имя Отчество датарождения номертелефона пол
//
//Форматы данных:
//фамилия, имя, отчество - строки
//датарождения - строка формата dd.mm.yyyy
//номертелефона - целое беззнаковое число без форматирования
//пол - символ латиницей f или m.
//
//Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//
//Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//
//Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
//
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//Не забудьте закрыть соединение с файлом.
//
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.public class Main {


import ru.my.hw_exception.exception_strings.Exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileSaver saver = new FileSaver();
        saver.inputData();
        saver.save();


    }

    private static class FileSaver {

        private String[] fio;
        private String birthDate;

        private String phoneNumber;

        private String sex;

        public void inputData() {
            try (Scanner in = new Scanner(System.in)) {
                System.out.println("Введите ФИО через пробел");
                fio = in.nextLine().split(" ");
                Exceptions.checkFIO(fio);
                System.out.println("Введите дату рождения в формате dd.mm.yyyy");
                birthDate = in.nextLine();
                Exceptions.checkBirthdate(birthDate);
                System.out.println("Введите номер телефона");
                phoneNumber = in.nextLine();
                Exceptions.checkPhoneNumber(phoneNumber);
                System.out.println("Введите пол");
                sex = in.nextLine();
                Exceptions.checkSex(sex);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void save() {
            try (FileWriter fw = new FileWriter(fio[0] + ".txt", true)) {
                fw.write(String.format("%s %s %s %s %s %s\n", fio[0], fio[1], fio[2], birthDate, phoneNumber, sex));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}