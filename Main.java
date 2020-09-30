package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
                "  \n" +
                "        //Создаем двусвязный неотсортированный список\n" +
                "        DoubleLinkedList list = new DoubleLinkedList()\n" +
                "        int N = -1\n" +
                "        //Делаем проверку ввода\n" +
                "        while (N <= 0) {\n" +
                "            try {\n" +
                "                System.out.println(\"Введите кол-во телефонных номеров в двунаправленном неупорядоченном списке:\")\n" +
                "                N = Integer.valueOf(cs.nextLine())\n" +
                "                if (N <= 0) throw new InputMismatchException()\n" +
                "            } catch (Exception e) {\n" +
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
        final String id = "[A-z[\\\\u00C0-\\\\u00D6[\\\\u00D8-\\\\u00F6[\\\\u00F8-\\\\u00FF[\\\\u0100-\\\\uFFFE]]]]]+";
        //Множество операндов
        HashMap<String, Integer> operands = new HashMap<>();
        //Множество операторов
        HashMap<String, Integer> operators = new LinkedHashMap<>();
        //Множество ключевых слов
        HashSet<String> keyWords = new HashSet<>();
        //Biba - это строка с кодом программы целиком

        //Здесь мы избавляемся от всех комментариев в коде программы
        biba = biba.replaceAll("(?s:/\\*.*?\\*/)|//.*", "");

        //Здесь мы ищем строковые литералы
        Matcher m = Pattern.compile("\"(?:\\\\\"|[^\"])*?\"").matcher(biba);
        while (m.find()) {
            //Каждый строковы    литерал ммы добавляем в множество операндов
            //operands.put(m.group());
            String buf = m.group();
            if (!operands.containsKey(buf)) {
                operands.put(buf, 1);
            } else {
                operands.put(buf, operands.get(buf) + 1);
            }
        }


        //Теперь в исходном коде программы мы удаляем все строковые литералы
        biba = biba.replaceAll("\"(?:\\\\\"|[^\"])*?\"", "");

        //Из файла считываем все основые операторы и ключ слова
        Files.lines(Paths.get("keyWords.txt")).forEach(line -> keyWords.add(line));
        Files.lines(Paths.get("operators.txt")).forEach(line -> operators.put(line, 0));


        //Сюда будем класть "обрезанные методы"
        HashMap<String, Integer> methods = new HashMap<>();
        Files.lines(Paths.get("operators2.0.txt")).forEach(line -> methods.put(line, 0));


        //Устанавливаем шаблон дл поиска методов
        m = Pattern.compile(id + "[\\s]*\\(.*\\)[\\s]*\\{")
                .matcher(biba);

        //Покуда встречается "необрезанный" метод
        while (m.find()) {
            //Берем очередной необрезанный метод
            String match = m.group();
            //Извлекаем идентификатор метода
            String newMatch = match.split("[\\s\\(]")[0];
            if (!(operators.containsKey(newMatch) || keyWords.contains(newMatch))) {
                //Если не было, то добавляем (костыль от switch, if and so fourth...)
                if (!methods.containsKey(newMatch))
                    methods.put(newMatch, 0);
            }
        }

        //Добавление пробелов перед и после скобок, замена кучи пробелов одним
        biba = biba.replace("(", "( ").replace(")", " )").replaceAll(" +", " ");

        //Удаление классов(типов)
        String[] lines = biba.split("\\n");
        for (String line : lines) {
            String[] words = line.split(" +");
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i].matches(id) && words[i + 1].matches(id) && !(keyWords.contains(words[i]) || operators.containsKey(words[i]) || keyWords.contains(words[i + 1]) || operators.containsKey(words[i + 1]))) {
                    biba = biba.replace(words[i] + " " + words[i + 1], words[i + 1]);
                }
            }
        }
        //Удаление классов(объявление)
        biba = biba.replaceAll("class\\s+" + id + "\\s+[{]", " ");


        //Подсчет и удаление методов и управляющих операторов(все что со скобочками)
        StringBuilder sb = new StringBuilder(biba);
        Pattern p = Pattern.compile(id + "\\s*" + "[(]");
        m = p.matcher(sb);
        while (m.find()) {
            //Получаем имя метода/оператора
            String match = m.group().split("[\\s(]+")[0];
            if (methods.containsKey(match)) {
                int i = methods.get(match);
                methods.put(match, i + 1);
            } else {
                methods.put(match, 1);
            }
            //Удаление
            sb.delete(m.start(), m.end());
            m = p.matcher(sb);
        }

        //Подсчет и удаление оставшихся операторов
        for (String op : operators.keySet()) {
            int start = sb.indexOf(op);
            while (start != -1) {
                operators.put(op, operators.get(op) + 1);
                sb.replace(start, start + op.length(), " ");
                start = sb.indexOf(op);
            }
        }


        biba = sb.toString().trim();


        //Удаление ключ слов(почти)
        for (String keyWord : keyWords) {
            biba = biba.replaceAll("\\s+" + keyWord + "\\s+", " ");
        }

        //Подсчет оставшихся операндов
        String[] ops = biba.split("\\s+");
        for (String operand : ops) {
            if (operands.containsKey(operand)) {
                operands.put(operand, operands.get(operand) + 1);
            } else {
                operands.put(operand, 1);
            }
        }


        System.out.println(operands);
        System.out.println(methods);
        System.out.println(operators);
    }
}
