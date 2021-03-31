# Олег Мусатов - "GetFood"

## Пользовательские сценарии

### Группа: 10 - МИ - 1
### Электронная почта: moleg1709@gmail.com
### VK: www.vk.com/zuvirre

####[Сценарий 1 - регистрация аккаунта клиента]
* Пользователь вводит адрес электронной почты, с которому будет привязан аккаунт.
* Если аккаунт с такой почтой уже существует, то пользователю будет предложено зайти в уже существующий аккаунт.
* Пользователь вводит имя, под которым он будет виден другим пользователям.
* Пользователь вводит свой номер телефона, который будет использоваться для связи ресторана с клиентом при надобности.
* Пользователь вводит пароль, по которому он будет заходить в систему.
* Если введённый адрес электронной почты не соответствует формату, то система выводит сообщение об ошибке и просит ввести адрес еще раз
* Пользователь нажимает на кнопку “Register”
* Если введенный пароль содержит меньше 8 или больше 30 символов, а также если он содержит недопустимые символы, система сообщает пользователю, что он должен придумать новый пароль
* Если все введенные данные соответствуют требованиям регистрации, то система отправляет на почту письмо для подтверждения почты
* Пользователь нажимает на кнопку “Подтвердить” в письме и переходит в приложение
* После подтверждения почты пользователь переходит на главный экран приложения

####[Сценарий 2 - регистрация аккаунта Управляющего]
* Пользователь вводит адрес электронной почты, с которому будет привязан аккаунт.
* Если аккаунт с такой почтой уже существует, то пользователю будет предложено зайти в уже существующий аккаунт.
* Пользователь вводит название ресторана, которое будет видно другим пользователям.
* Пользователь вводит пароль, по которому он будет заходить в систему.
* Если введённый адрес электронной почты не соответствует формату, то система выводит сообщение об ошибке и просит ввести адрес еще раз
* Пользователь нажимает на кнопку “Register”
* Если введенный пароль содержит меньше 8 или больше 30 символов, а также если он содержит недопустимые символы, система сообщает пользователю, что он должен придумать новый пароль
* Если все введенные данные соответствуют требованиям регистрации, то система отправляет на почту письмо для подтверждения почты
* Пользователь нажимает на кнопку “Подтвердить” в письме и переходит в приложение
* После подтверждения почты пользователь переходит на экран редактирования информации о ресторане

####[Сценарий 3 - бронь столика в ресторане клиентом]
* Пользователь находится на главной странице, перед ним окно поиска ресторана по названию. Пользователь вводит название, выбирает нужный ресторан из перечня подходящих по названию.
* На экране выбор из двух функций: "Бронь стола", "Посмотреть меню". Пользователь нажимает на надпись "Бронь стола"
* Перед пользователям появляется примерный план ресторана, изображены столы , цветом столы разделены на забронированные, занятые в данный момент и свободные, <br>и при нажатии на стол, показывается информация о нем:
