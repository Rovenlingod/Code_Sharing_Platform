let xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        document.getElementById("code_x").innerHTML = JSON.parse(xhr.responseText).date + '\n' + JSON.parse(xhr.responseText).code;
    }
};
xhr.open("GET", '/api/code', true);
xhr.send();