Версия Java 8u241

Не получилось сделать авторизацию для бота, поэтому для него пришлось дать доступ к сообщениям без авторизации

Все команды в чате должны содержать знак '/' в начале сообщения

Команда /help показывает все команды для бота

/getpost - для получания ссылки на пост habr

До отправки ссылки поста на habr, бот проверяет, не удален ли пост

Для проверки уникальности ссылки для каждого пользователя используется HashSet