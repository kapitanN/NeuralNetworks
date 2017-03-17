<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 28.02.2017
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="eng">

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="author" content="Shapovalov">
  <link href="../../resources/css/style.css" rel="stylesheet">

  <spring:url value="../../resources/js/jquery-3.1.0.min.js"
              var="jqueryJs" />
  <script src="${jqueryJs}"></script>
</head>

<body>
<div class="container">
  <div id="first" class="ck-button"></div>
  <div id="second" class="ck-button"></div>
  <div id="third" class="ck-button"></div>
  <div id="four" class="ck-button"></div>
  <div id="recognize" class="ck-button"></div>
  <%--<button onclick="getValue(['first','second','third','four'])">Пересчитать</button>--%>
  <%--<button onclick="getValue(['recognize'])">Пересчитать</button>--%>
  <section id="description"></section>
</div>
<form id = "search-form">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" id="bth-search"
              class="btn btn-primary btn-lg">Training</button>
    </div>
  </div>
</form>
<form id = "recognize-form">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" id="recognize-button"
              class="btn btn-primary btn-lg">Recognize</button>
    </div>
  </div>
</form>
<div>
  <p id="result"></p>
</div>

<%--<form id = "submit_for_recognize">--%>
  <%--<button type="submit" id="recognize_button">Распознать</button>--%>
<%--</form>--%>
</body>

<script>
    jQuery(document).ready(function($) {

        $("#search-form").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            searchViaAjax();
        });
        $("#recognize-form").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            recognizeViaAjax();
        });
    });

    function searchViaAjax() {

        var search = {};
        search["value"] = getValue(['first','second','third','four']);

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/training",
            data : JSON.stringify(search),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                //display(data.result);
                $("#result").text(data.result);
            },
            error : function(data) {
                console.log("ERROR: ", data);
                $("#result").text(JSON.stringify(data));
            },
            done : function(data) {
                console.log("DONE");
                //display(data);
                $("#result").text(JSON.stringify(data));
            }
        });

    }

    function recognizeViaAjax() {

        var search = {};
        search["recognize"] = getValue(['recognize']);

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/recognising",
            data : JSON.stringify(search),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                //display(data.result);
                $("#result").text(data.result);
            },
            error : function(data) {
                console.log("ERROR: ", data);
                $("#result").text(JSON.stringify(data));
            },
            done : function(data) {
                console.log("DONE");
                //display(data);
                $("#result").text(JSON.stringify(data));
            }
        });

    }

//    function display(data) {
//        var json = "<h4>Ajax Response</h4><pre>"
//            + JSON.stringify(data, null, 4) + "</pre>";
//        $('#feedback').html(json);
//    }

    var desc = document.getElementById('description');
    var numberOfImages = 4;
    var numberOfInputs = 25;

    // ___ Ф-я для создания инпутов в элементе с задаными размерами
    function createBlock(thisElement) {
        var n = 1;
        var cols = 5;
        var rows = 5;
        thisElement.innerHTML = "";
        do {
            thisElement.innerHTML += '<label><input type="checkbox" value="1"><div></div></label>';
            if (n % rows == 0) thisElement.innerHTML += '<br />';
            ++n;
        } while (n <= cols * rows);
    }

    function getValue(element) {
        var resultArray = [];
        var elements = [];
        var count = 0;
        for (var i = 0; i<element.length; i++){
            elements[i] = document.getElementById(element[i]).getElementsByTagName('input');
            resultArray[i] = [];
            for (var j = 0; j<numberOfInputs; j++){
                if(Number(elements[i][j].checked)){
                    resultArray[i][j] = 1;
                }
                else resultArray[i][j] = -1;
            }
//            desc.innerHTML += resultArray[i]+ '<br>';
        }
        return resultArray;
    };

    // ____ Main function
    (function() {
        // находим все изображения
        var image = document.getElementsByClassName('ck-button');
        var strStart = "",
            w1 = [], w2 = [], w3 = [], w4 = [];
        for (var i = 0; i < image.length; i++) {
            // получаем ид элемента
            var imageId = image[i].id;
            // пишем код функции: добавляем обработчик для каждого элемента
            strStart += "createBlock(" + imageId + ");";
        }
        // выводим при первом открытии
        eval(strStart);
//        desc.innerHTML = "Задается множество М: <br>";
    })()
</script>
</html>
