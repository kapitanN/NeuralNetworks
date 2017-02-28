<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 28.02.2017
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="eng">

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="author" content="Antoniuk">
  <link href="style.css" rel="stylesheet">
</head>

<body>
<div class="container">
  <div id="first" class="ck-button"></div>
  <div id="second" class="ck-button"></div>
  <div id="third" class="ck-button"></div>
  <div id="four" class="ck-button"></div>
  <select name="col" id="col">
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
  </select>
  <select name="row" id="row">
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
  </select>
  <button onclick="getValue('first'); getValue('second'); getValue('third'); getValue('four');">Пересчитать</button>
  <section id="description"></section>
</div>
</body>
<script>
    var desc = document.getElementById('description');
    // ___ Ф-я для создания инпутов в элементе с задаными размерами
    function createBlock(thisElement, cols, rows) {
        var n = 1;
        var cols = cols || document.getElementById('col').value;
        var rows = rows || document.getElementById('row').value;
        thisElement.innerHTML = "";
        do {
            thisElement.innerHTML += '<label><input type="checkbox" value="1"><div></div></label>';
            if (n % rows == 0) thisElement.innerHTML += '<br />';
            ++n;
        } while (n <= cols * rows);
    }
    // _____ Ф-я для вывода бинарного массива
    function getValue(element) {
        var checks = document.getElementById(element).getElementsByTagName('input');
        var length = checks.length;
        var arrayResult = [];
        var count = 0;
        do {
            arrayResult[count] = Number(checks[count].checked);
            count++;
        } while (count < length)
        desc.innerHTML += arrayResult+ '<br>';
        return arrayResult;
    };
    // ____ Main function
    (function() {
        // находим все элементы, которые должны менять размер
        var place = document.getElementsByClassName('ck-button');
        var strCol = "", strRow = "", strStart = "",
            w1 = [], w2 = [], w3 = [], w4 = [];
        for (var i = 0; i < place.length; i++) {
            // получаем ид элемента
            var name = place[i].id;
            // пишем код функции: добавляем обработчик для каждого элемента
            strCol = strCol + "createBlock(" + name + ", this.value); ";
            strRow = strRow + "createBlock(" + name + ", '', this.value); ";
            strStart += "createBlock(" + name + ");";
        }
        // приваем значение строки как функцию
        col.onchange = function() { eval(strCol) };
        row.onchange = function() { eval(strRow) };
        // выводим при первом открытии
        eval(strStart);
        desc.innerHTML = "Задается множество М: <br>";
    })()
</script>

</html>
