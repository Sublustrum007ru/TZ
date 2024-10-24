package controller;

import java.util.Scanner;

public class MyModifier {
    /**
     * 1. Пользователь вводит текст одной строкой и нажимает “enter”.
     * 2. В тексте могут присутствовать различные специальные символы, которые надо обрабатывать согласно условиям ниже:
     * a. Если в тексте между словами присутствует несколько пробелов подряд, надо оставить только один из них.
     * Для реализации этого подпункта нельзя пользоваться методами replace() и replaceAll().
     * b. Если в тексте присутствует знак минус (-), это значит, что символ слева от этого знака надо поменять местами с символом,
     * который стоит справа от этого знака. Обратите внимание, что символом может быть не только буква,
     * но и цифра или любой другой знак/символ, в том числе символ пробела. После смены символов местами,
     * знак минус (-) надо удалить из строки результата.
     * c. Если в тексте присутствует знак плюс (+), вам необходимо заменить его на восклицательный знак (!).
     * d. В тексте могут присутствовать цифры (от 0 до 9). Вам необходимо посчитать их сумму и удалить из текста.
     * Сумму цифр вам нужно добавить в конец результирующей строки через пробел (пробел должен стоять перед суммой цифр).
     * Если цифр в тексте не было - "0" (ноль) в конце строки выводить не нужно.
     * Все манипуляции с текстом должны выполняться ровно в той последовательности, которая описана в пункте 2.
     * То есть, сначала выполняется пункт A, затем пункт B, затем C и D. Это важно для получения корректного итогового результата.
     * Пример ввода:
     * генрих1  играет+2   л-июбит0школу
     * Пример вывода:
     * генрих играет! илюбитшколу 3
     * Пример ввода №2:
     * Я ю-лбю-л джаву   всем сердцем+
     * Пример вывода:
     * Я люблю джаву всем сердцем!
     * Ab 1  2   3-1   2  3+
     * Ab ! 12
     * Hell0 w0rld!!-
     */

    public String textModifier() {
        String result = null;
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        String[] arrLine = scanner.nextLine().split("");

        /**
         * a. Если в тексте между словами присутствует несколько пробелов подряд, надо оставить только один из них.
         * Для реализации этого подпункта нельзя пользоваться методами replace() и replaceAll().
         */
        while (check(arrLine)) {
            for (int i = 0; i < arrLine.length; i++) {
                if (arrLine[i].equals(" ") && arrLine[i + 1].equals(" ")) {
                    arrLine = myDelChar(arrLine, i);
//                    arrLine = test(arrLine, i);
                }
            }
        }

        /**
         * b. Если в тексте присутствует знак минус (-), это значит, что символ слева от этого знака надо поменять местами с символом,
         * который стоит справа от этого знака. Обратите внимание, что символом может быть не только буква,
         * но и цифра или любой другой знак/символ, в том числе символ пробела. После смены символов местами,
         * знак минус (-) надо удалить из строки результата.
         */
        arrLine = myChange(arrLine, "-");

        /**
         * c. Если в тексте присутствует знак плюс (+), вам необходимо заменить его на восклицательный знак (!).
         */
        arrLine = myChangeChar(arrLine, "+", "!");

        /**
         * В тексте могут присутствовать цифры (от 0 до 9). Вам необходимо посчитать их сумму и удалить из текста.
         * Сумму цифр вам нужно добавить в конец результирующей строки через пробел (пробел должен стоять перед суммой цифр).
         * Если цифр в тексте не было - "0" (ноль) в конце строки выводить не нужно.
         */
        if (sum(arrLine) > 0) {
            String[] temp = delNumb(arrLine);
            result = String.join("", temp) + " " + sum(arrLine);
        } else {
            String[] temp = delNumb(arrLine);
            result = String.join("", temp);
        }
        return result;
    }

    /**
     * Метод для проверки, есть ли в тексе два подряд идущих пробела.
     *
     * @param line - исходный массив для проверки.
     * @return - возвращаем либо True(если есть), либо False(если отсутствуют).
     */
    private boolean check(String[] line) {
        int count = 0;
        for (int i = 0; i < line.length; i++) {
            if (line[i].equals(" ") && line[i + 1].equals(" ")) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * Метод удаления символа, путем копирования массива в новый.
     *
     * @param line          - исходный массив.
     * @param indexToRemove - индекс символа который надо удалить.
     * @return - возвращаемый измененный массив.
     */
    private String[] myDelChar(String[] line, int indexToRemove) {
        String[] result = new String[line.length - 1];
        System.arraycopy(line, 0, result, 0, indexToRemove);
        System.arraycopy(line, indexToRemove + 1, result, indexToRemove, line.length - indexToRemove - 1);
        return result;
    }

    /**
     * Метод удаления символа, через цикл. Когда каждый символ копируется по отдельности.
     *
     * @param line-          исходный массив.
     * @param indexToRemove- индекс символа который надо удалить.
     * @return - возвращаемый измененный массив.
     */
    private String[] testDel(String[] line, int indexToRemove) {
        String[] result = new String[line.length - 1];
        for (int i = 0; i < result.length; i++) {
            if (i < indexToRemove) {
                result[i] = line[i];
            } else {
                result[i] = line[i + 1];
            }
        }
        return result;
    }

    /**
     * Метод меняет местами два символа.
     *
     * @param line - исходный массив
     * @return - возвращаем измененный массив.
     */
    private String[] myChange(String[] line, String targetChar) {
        for (int i = 0; i < line.length; i++) {
            if (line[i].equals(targetChar)) {
                String temp = line[i - 1];
                line[i - 1] = line[i + 1];
                line[i + 1] = temp;
                line = myDelChar(line, i);
            }
        }
        return line;
    }

    /**
     * Метод в для замены символа "+" на символ "!".
     */
    private String[] myChangeChar(String[] line, String intChar, String outChar) {
        for (int i = 0; i < line.length; i++) {
            if (line[i].equals(intChar)) {
                line[i] = outChar;
            }
        }
        return line;
    }

    /**
     * Метод подсчета суммы чисел встречаемых в тексте.
     *
     * @param line - исходный массив.
     * @return - возвращаемая сумма.
     */
    private int sum(String[] line) {
        int result = 0;
        if (checkNumb(line)) {
            for (int i = 0; i < line.length; i++) {
                if (isNumber(line[i])) {
                    result = result + Integer.parseInt(line[i]);
                }
            }
        }
        return result;
    }

    /**
     * Метод проверка наличия чисел в тексте.
     *
     * @param line - исходный массив.
     * @return - возвращаем либо True(если есть), либо False(если отсутствуют).
     */
    private boolean checkNumb(String[] line) {
        for (int i = 0; i < line.length; i++) {
            if (isNumber(line[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод удаления числа из массива
     *
     * @param line - исходный массив
     * @return - возвращаем измененный массив.
     */
    private String[] delNumb(String[] line) {
        for (int i = 0; i < line.length; i++) {
            if (isNumber(line[i])) {
                line = myDelChar(line, i);
            }
        }
        return line;
    }

    /**
     * Метод провеки
     *
     * @param line
     * @return
     */
    private boolean isNumber(String line) {
        boolean result = false;
        try {
            Integer.parseInt(line);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

}
