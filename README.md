
# Тестовое задание на Android разработчика в лабораторию ZenCar.tech
## ZenCar

Приложение представлять из себя электронную очередь людей и состоять из 3х экранов:

 - Экран входа
 - Экран регистрации
 - Экран со списком пользователей

# Применямемые технологии
- Архитектура MVVM/MVI
- Прменяемые технологии - Jetpack Compose, Coroutines, Room, Coil, Jetpack Navigation, Hilt, DataStore
## Описание экранов

### Экран авторизации и регистрации
Возможность зарегистрироваться и войти пользователю. В качестве базы данных выступает локальная база данных. Обработаны ошибки ввода неверной почты и пароля при входе. Обработана ошибка не заполненых полей при регистрации
<p align="center">
  <img src="https://github.com/GrachevAl/assets/blob/main/SignIn.jpg" width="200" />
  <img src="https://github.com/GrachevAl/assets/blob/main/SignInError.jpg" width="200" />
  <img src="https://github.com/GrachevAl/assets/blob/main/SignUp.jpg" width="200" />
  <img src="https://github.com/GrachevAl/assets/blob/main/SignUpError.jpg" width="200" />
</p>

### Экран список пользователей
Возможность просмотреть список пользователей. Возможность удалить пользователя, который зарегистрирован позже тебя.
В данном экране я не отображаю в списке текущего пользователя. Информация о нем только в верхнем title экрана.
<p align="center">
  <img src="https://github.com/GrachevAl/assets/blob/main/listUsers.jpg" width="200" />
  <img src="https://github.com/GrachevAl/assets/blob/main/listUsersDelete.jpg" width="200" />
  <img src="https://github.com/GrachevAl/assets/blob/main/dialog.jpg" width="200" />

</p>



