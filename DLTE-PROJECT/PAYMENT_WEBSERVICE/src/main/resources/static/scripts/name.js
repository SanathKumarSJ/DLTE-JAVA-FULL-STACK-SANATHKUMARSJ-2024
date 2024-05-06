$(document).ready(function () {
    function getUserName() {
        $.ajax({
            type: "GET",
            url: "/payee/username",
            dataType: 'text',
            contentType:"application/json;charset=utf-8",

            success: function (response) {
                $('#username').text("Hi, " + response);
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                $('#username').text("Error fetching name");
            }
        });
    }
    getUserName();
});