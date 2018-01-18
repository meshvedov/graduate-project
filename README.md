# Система голосования за лучший ресторан
REST API реализованно с использованием технологий Spring, SpringMVC, Hibernate с делением по слоям на репозиторий,
сервис и контроллер.
В качестве базы данных выбрана HSQLDB. Применяются шесть таблиц:
1. users - хранятся данные пользователей.
2. user-roles - роли пользователей.
3. restaurant - данные ресторанов.
4. menu - меню ресторанов: название блюда и стоимость.
5. vote - данные за какой ресторан голосовал пользователь.
6. restaurant_user - администраторы ресторанов.

Для репозитория используется технология DATAJPA.

Точка времени последнего голосования для конкретного пользователя равняется 11 часам текущего дня.
Значение времени голосования можно изменять (в spring-app.xml).

Для сервисов и контроллеров написаны тесты.

Обмен с front-end идёт через JSON запросы.
Формат JSON:

Получение информации о ресторанах с колличеством голосов: GET /rest/restaurants .

Получение меню конкретного ресторана: GET /rest/restaurants/{restId}/menus.

Создание нового меню администратором ресторана: POST /rest/restaurants/{restId}/menu.

Редактирование блюда меню: PUT /rest/restaurants/{restId}/menu/{menuID}.

Удаление блюда меню: DELETE /rest/restaurants/{restId}/menu

Получение списка пользователей (без паролей) : GET /users.

Получение меню ресторана администратором ресторана: GET /users/{userID}/menus.


Голосование: POST /rest/restaurants/{restaurantID}/votes/{userID}

Команды CURL:
1. Получаем список ресторанов: curl -X GET http://localhost:[port]/rest/restaurants
2. Получаем меню конкретного ресторана: curl -X GET http://localhost:[port]/rest/restaurants/100003/menus
3. Голосуем за ресторан: curl -X POST http://localhost:[port]/rest/restaurants/100004/votes/100002