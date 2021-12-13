

function login() {
    var url = "http://limla.ml:8080/api/v1/user/login";

    var a = "admin"
    var b = "admin"
    var c = "6zdKaj1bfBK3XpQl1hQG"

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
            var text = xhr.responseText;
        }
    }
    console.log("hello world")
    xhr.send(JSON.stringify({"nachName": b, "password": c, "vorName": a}));
}

