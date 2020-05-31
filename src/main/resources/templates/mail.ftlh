<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body style='padding-top:50px; padding-left: 50px; min-height: 40vh; background-color: #283593; display: flex; font-family: Calibri; margin-left: 15%; margin-right: 15%; flex-direction: column;'>
<div class="Title" style="flex: 1; font-weight: bold; font-size: 24px; color: white;">
    Подтверждение регистрации
    <div class="Title-Sub" style=' margin-top: 2%; font-weight: bold; font-size: 15px;'>Нажмите кнопку для подтверждения
        регистрации
    </div>
</div>
<div class="Button" style='display: flex; align-items: flex-end; margin-left: 100px;margin-top: 100px'>
    <form action="http://localhost:3000/confirm/${code}">
        <Button class="Button-Text" style='padding: 3px; color: white ;background-color: transparent; border-radius: 5px; border: 2px solid white;'>
            Подтвердить
        </Button>
    </form>
</div>
</body>
</html>