# Система голосования за лучший ресторан
REST API реализованно с использованием технологий Spring, SpringMVC, Hibernate с делением по слоям на репозиторий,
сервис и контроллер.

Команды CURL:
1. Получаем список ресторанов: curl -X GET http://localhost:[port]/rest/restaurants
2. Получаем меню конкретного ресторана: curl -X GET http://localhost:[port]/rest/restaurants/100003/menus
3. Голосуем за ресторан: curl -X POST http://localhost:[port]/rest/restaurants/100004/votes/100002