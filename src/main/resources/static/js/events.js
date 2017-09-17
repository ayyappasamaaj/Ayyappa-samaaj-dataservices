function saveEvent() {

    $("#eventErrorMsg").addClass("hidden");
    $("#eventSuccessMsg").addClass("hidden");

    var eventName = capitalize($("#eventName").val());
    var eventDesc = capitalize($("#eventDesc").val());
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var venue = capitalize($("#venue").val());
    var registrationLink = $("#registrationLink").val();

    if (eventName == '' || startDate == '' || endDate == '' || venue == '') {
        alert('Please enter all the required fields.');
        return;
    }

    var dataObj = new Object();
    dataObj.eventName = eventName
    dataObj.eventDesc = eventDesc;
    dataObj.startDate = startDate + ":00-08:00";
    dataObj.endDate = endDate + ":00-08:00";
    dataObj.venue = venue;
    dataObj.registrationLink = registrationLink;
    dataObj.isRecurring = false;

    var jsonObj = JSON.stringify(dataObj);
    var url = "http://" + window.location.hostname + ":8080/aysa/saveEvent"
    postMethod(url, jsonObj, saveEventCallBack);
}

function saveEventCallBack (data) {
    if (data.status == "SUCCESS") {
        showSuccess();
        resetAllValues();
    } else {
        showError();
    }
}

function showError() {
    $("#eventErrorMsg").removeClass("hidden");
}

function showSuccess() {
    $("#eventSuccessMsg").removeClass("hidden");
}

