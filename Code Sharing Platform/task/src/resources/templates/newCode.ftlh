<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
<#--    <script type="text/javascript" src="send.js"></script>-->
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction").value,
                "views": document.getElementById("views_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status == 200) {
                alert("Success!");
            }
        }
    </script>
</head>
<body>
  <form>
    <label for="code_snippet"></label><textarea id="code_snippet"></textarea><br>
      Time restriction: <label for="time_restriction"></label><input id="time_restriction" type="text"/><br>
      Views restriction: <label for="views_restriction"></label><input id="views_restriction" type="text"/><br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
  </form>
</body>
</html>