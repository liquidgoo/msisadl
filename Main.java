package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        String biba = "//СИАОД, Лабораторная работа №1\n" +
                "//Задача №4\n" +
                "//Телефонные номера абонентов и спецслужб\n" +
                "\n" +
                "//Класс-узел для хранения номера телефона\n" +
                "class Node {\n" +
                "    public String phoneNumber\n" +
                "    public Node next\n" +
                "    public Node prev\n" +
                "\n" +
                "    public Node(String number) {\n" +
                "        phoneNumber = number\n" +
                "        next = null\n" +
                "        prev = null\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "//Класс для двусвязного неотсортированного списка\n" +
                "class DoubleLinkedList {\n" +
                "    private Node first        //Ссылка на первый элемент списка\n" +
                "    private Node last        //Ссылка на последний элемент списка\n" +
                "    private int size        //Кол-во элементов в списке\n" +
                "\n" +
                "    //Конструтор для создания пустого списка\n" +
                "    public DoubleLinkedList() {\n" +
                "        first = null\n" +
                "        last = null\n" +
                "        size = 0\n" +
                "    }\n" +
                "\n" +
                "    //Метод для добавления нового телефонного номера\n" +
                "    public void addNewNode(Node obj) {\n" +
                "        //Список пуст - назначаем и начало, и конец\n" +
                "        if (size == 0) {\n" +
                "            first = obj\n" +
                "            last = obj\n" +
                "        } //Иначе список не пуст\n" +
                "        else {\n" +
                "            //Движемся до самого конца списка\n" +
                "            Node curr = first, prev = curr\n" +
                "            while (curr != null) {\n" +
                "                prev = curr\n" +
                "                curr = curr.next\n" +
                "            }\n" +
                "            //Присоединяем новый элемент\n" +
                "            obj.prev = prev\n" +
                "            prev.next = obj\n" +
                "            //Меняем последнего\n" +
                "            last = obj\n" +
                "        }\n" +
                "        //Увеличиваем кол-во\n" +
                "        size++\n" +
                "    }\n" +
                "\n" +
                "    //Метод для вывода списка справа налево\n" +
                "    public void showFromRightToLeft() {\n" +
                "        if (size == 0) {\n" +
                "            System.out.println(\"Список пуст!\")\n" +
                "            return\n" +
                "        }\n" +
                "        Node curr = last\n" +
                "        while (curr != null) {\n" +
                "            System.out.println(curr.phoneNumber + \" \")\n" +
                "            curr = curr.prev\n" +
                "        }\n" +
                "        System.out.println()\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    //Метод для заполнения сортирующегося списка номерами абонентов\n" +
                "    public void fillOrderedList(OrderedList obj) {\n" +
                "        if (size == 0) {\n" +
                "            System.out.println(\"Нечем заполнить отсоортированный список\")\n" +
                "            return\n" +
                "        }\n" +
                "        Node curr = last\n" +
                "        while (curr != null) {\n" +
                "            if (curr.phoneNumber.matches(\"\\\\d{7}\"))\n" +
                "                obj.addNewNode(new Node(curr.phoneNumber))\n" +
                "            curr = curr.prev\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "//Класс-отсортированный список\n" +
                "class OrderedList {\n" +
                "    //Корень списка\n" +
                "    private Node first\n" +
                "    //Текущий размер списка\n" +
                "    private int size\n" +
                "\n" +
                "    //Конструктор для создания пустого списка\n" +
                "    public OrderedList() {\n" +
                "        first = null\n" +
                "        size = 0\n" +
                "    }\n" +
                "\n" +
                "    //Метод для добавления нового человека в список\n" +
                "    public void addNewNode(Node obj) {\n" +
                "        //Если список пуст, то выбираем новый корень\n" +
                "        if (size == 0)\n" +
                "            first = obj\n" +
                "        else {\n" +
                "            //Становимся в начало\n" +
                "            Node curr = first, prev = curr\n" +
                "            //Обеспечиваем лексикографический порядок вставки\n" +
                "            while (curr != null && (curr.phoneNumber.compareTo(obj.phoneNumber) < 0)) {\n" +
                "                //Передвигаемся вперед по списку\n" +
                "                prev = curr\n" +
                "                curr = curr.next\n" +
                "            }\n" +
                "\n" +
                "            //Вставка перед \"начальным\" элементом\n" +
                "            if (curr == first) {\n" +
                "                obj.next = first\n" +
                "                first = obj\n" +
                "            } else {\n" +
                "                //Вставка где-то в середине списка (конце)\n" +
                "                obj.next = curr\n" +
                "                prev.next = obj\n" +
                "            }\n" +
                "        }\n" +
                "        //Увеличиваем размер\n" +
                "        size++\n" +
                "    }\n" +
                "\n" +
                "    //Метод вывода всего списка\n" +
                "    public void show() {\n" +
                "        if (size == 0) System.out.println(\"Список пуст!\")\n" +
                "        else {\n" +
                "            int index = 1\n" +
                "            Node curr = first\n" +
                "\n" +
                "            while (curr != null) {\n" +
                "                System.out.println(index + \". \" + curr.phoneNumber)\n" +
                "                curr = curr.next\n" +
                "                index++\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "public class Main {\n" +
                "\n" +
                "    private static Scanner cs = new Scanner(System.in)\n" +
                "\n" +
                "    private static boolean checkNumber(String s) {\n" +
                "        return s.matches(\"\\\\d{3}\") || s.matches(\"\\\\d{7}\")\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "        //Создаем двусвязный неотсортированный список\n" +
                "        DoubleLinkedList list = new DoubleLinkedList()\n" +
                "        int N = -1\n" +
                "        //Делаем проверку ввода\n" +
                "        while (N <= 0) {\n" +
                "            try {\n" +
                "                System.out.println(\"Введите кол-во телефонных номеров в двунаправленном неупорядоченном списке:\")\n" +
                "                N = Integer.valueOf(cs.nextLine())\n" +
                "                if (N <= 0) throw new InputMismatchException()\n" +
                "            } catch (InputMismatchException | NumberFormatException e) {\n" +
                "                System.out.println(\"Повторите ввод!\")\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        //Запрашиваем N мобильных телефонов\n" +
                "        for (int i = 0; i < N; i++) {\n" +
                "            System.out.println(\"Введите номер телефона:\")\n" +
                "            String s = cs.nextLine()\n" +
                "            if (checkNumber(s))\n" +
                "                list.addNewNode(new Node(s))\n" +
                "            else\n" +
                "                System.out.println(\"Возможны телефонные номера либо спецслужб (три цифры), либо абонентов (семь цифр)!\")\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        //Вывод двусвязного списка\n" +
                "        System.out.println(\"Исходный неотсортированный двусвязный список:\")\n" +
                "        list.showFromRightToLeft()\n" +
                "        //Создание сортирующегося списка\n" +
                "        OrderedList ordList = new OrderedList()\n" +
                "        System.out.println(\"Отсортированный список только с телефонами абонентов:\")\n" +
                "        //Создание сортирующегося списка\n" +
                "        list.fillOrderedList(ordList)\n" +
                "        //Вывод списка\n" +
                "        ordList.show()\n" +
                "    }\n" +
                "}\n";

        //Множество операндов
        HashSet<String> operands = new HashSet<>();
        //Множество операторов
        HashSet<String> operators = new HashSet<>();

        //Biba - это строка с кодом программы целиком

        //Здесь мы избавляемся от всех комментариев в коде программы
        biba = biba.replaceAll("(?s:/\\*.*?\\*/)|//.*", "");

        //Здесь мы ищем строковые литералы
        Matcher m = Pattern.compile("\"(?:\\\\\"|[^\"])*?\"").matcher(biba);
        while (m.find()) {
            //Каждый строковы    литерал ммы добавляем в множество операндов
            operands.add(m.group());
        }


        //Теперь в исходном коде программы мы удаляем все строковые литералы
        biba = biba.replaceAll("\"(?:\\\\\"|[^\"])*?\"", "");


        //Из файла считываем все основые операнды
        Files.lines(Paths.get("ops.txt")).forEach(line -> operators.add(line));


        //Сюда будем класть "обрезанные методы"
        HashSet<String> methods = new HashSet<>();

        //Кол-во всех-всех встреченных методов (не вызовов!!!!)
        int allMethodsCount = 0;


        //Устанавливаем шаблон дл поиска методов
        m = Pattern.compile("[A-z[\\\\u00C0-\\\\u00D6[\\\\u00D8-\\\\u00F6[\\\\u00F8-\\\\u00FF[\\\\u0100-\\\\uFFFE]]]]]+[\\s\n]*\\(.*\\)[\\s\n]+\\{")
                .matcher(biba);

        //Покуда встречается "необрезанный" метод
        while (m.find()) {
            //Берем очередной необрезанный метод
            String match = m.group();
            //Извлекаем идентификатор метода
            String newMatch = match.split("[\\s\n\\(]")[0];
            if (!operators.contains(newMatch)) {
                //Если не было, то добавляем (костыль от switch, if and so fourth...)
                methods.add(newMatch);
                allMethodsCount++;
            }
        }


        System.out.println(operands);
        System.out.println(methods);
        System.out.println(allMethodsCount);
    }
}
