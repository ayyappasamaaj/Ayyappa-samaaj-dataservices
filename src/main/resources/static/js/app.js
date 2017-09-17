window.onload = function() {
    $("#ebook").load("ebook.html");
    $("#event").load("events.html");
}

function doLogin() {
    var user_id = $("#txtUserId").val();
    var password = $("#txtPassword").val();

    if (user_id == "aysa_admin" && password == "18!Padikal18000") {
    //if (user_id == "" && password == "") {
        $("#loginPanel").addClass("hidden");
        $("#buttonPanel").removeClass("hidden");
    } else {
        $("#txtErrorMsg").removeClass("hidden");
    }
}

function addEbook() {
   $("#buttonPanel").addClass("hidden");
   $("#ebook").removeClass("hidden");
}

function addEvent() {
   $("#buttonPanel").addClass("hidden");
   $("#event").removeClass("hidden");
}

function goBack() {
    $("#buttonPanel").removeClass("hidden");
    $("#ebook").addClass("hidden");
    $("#event").addClass("hidden");
    $("#ebookErrorMsg").addClass("hidden");
    $("#ebookSuccessMsg").addClass("hidden");
    $("#eventErrorMsg").addClass("hidden");
    $("#eventSuccessMsg").addClass("hidden");
    resetAllValues();
}

function resetAllValues() {
  $('.input-group').find('input').val('');
}

function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

