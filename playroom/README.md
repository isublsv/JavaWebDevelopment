# Task. Объектно-ориентированное программирование

## Требования
* При реализации иерархии наследования производный класс должен расширять
суперкласс новыми свойствами, для чего следует разобраться в предметной области
задачи.
* При описании полей и разработке методов в случае возвращения null использовать класс
Optional.
* Классы-сущности должны быть отделены от классов с методами бизнес-логики.
* При создании объектов использовать паттерн Factory Method.
* Использовать перечисления (enum) для описания полей с ограниченным набором значений.
* Для хранения коллекции объектов использовать шаблон Repository.
* Разработать методы по добавлению, удалению объектов репозитория.
* Разработать спецификации по поиску объектов в репозитории. По ID, по имени, по другим
(например: найти все объекты имена которых начинаются на заданную букву, найти все
объекты идентификаторы которых заключены в заданном интервале и т п)
* Для сортировок объектов репозитория использовать реализацию интерфейса Comparator и
его возможности. В частности thenComparing.
* Все классы приложения должны быть грамотно структурированы по пакетам.
* Оформление кода должно соответствовать Java Code Convention.
* Параметры, необходимые для создания объектов, должны вводиться чтением данных из
файла (.txt). Среди данных в файле должна быть заведомо некорректная информация.
Должна присутствовать обработка некорректных данных инициализации объекта.
* Для записи логов использовать Log4J2.
* Код должен быть покрыт Unit-тестами. Использовать TestNG.

## Задание
**Игровая комната.** Подготовить игровую комнату для детей разных возрастных групп.
Игрушек должно быть фиксированное количество в пределах выделенной суммы денег.
Должны встречаться игрушки различных категорий: маленькие, средние и большие
машины, куклы, мячи, кубики. Провести сортировку игрушек на основе одного и
нескольких параметров. Найти игрушки в комнате, соответствующие заданным
параметрам.
