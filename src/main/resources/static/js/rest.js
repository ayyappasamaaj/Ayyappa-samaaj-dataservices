/* Common JS to make rest calls */

function callService(url, cbFunction) {

    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        success: cbFunction
    })
}

function postMethod(url, dataObject, cbFunction) {

    $.ajax({
        url: url,
        type: 'POST',
        data: dataObject,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: cbFunction
    })
}
