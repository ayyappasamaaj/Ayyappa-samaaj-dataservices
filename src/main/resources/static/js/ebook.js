function saveEbook() {

    $("#ebookErrorMsg").addClass("hidden");
    $("#ebookSuccessMsg").addClass("hidden");

    var bookTitle = capitalize($("#bookTitle").val());
    var bookCategory = capitalize($("#bookCategory").val());
    var bookSubCategory = capitalize($("#bookSubCategory").val());
    var bookFilePath = $("#bookFilePath").val();
    var bookLanguage = capitalize($("#bookLanguage").val());
    var bookSequence = ($("#bookSequence").val() == '') ? 0 : Number($("#bookSequence").val());

    if (bookTitle == '' || bookCategory == '' || bookFilePath == '' || bookLanguage == '') {
        alert('Please enter all the required fields.');
        return;
    }

    var dataObj = new Object();
    dataObj.itemTitle = bookTitle
    dataObj.category = bookCategory;
    dataObj.subCategory = bookSubCategory;
    dataObj.fileUrl = bookFilePath;
    dataObj.language = bookLanguage;
    dataObj.sequence = bookSequence;
    var jsonObj = JSON.stringify(dataObj);
    var url = "http://" + window.location.hostname + ":8080/aysa/saveEbook"
    postMethod(url, jsonObj, saveEbookCallBack);

}

function saveEbookCallBack (data) {
    if (data.status == "SUCCESS") {
        displaySuccess();
        resetAllValues();
    } else {
        displayError();
    }
}


function displayError() {
    $("#ebookErrorMsg").removeClass("hidden");
}

function displaySuccess() {
    $("#ebookSuccessMsg").removeClass("hidden");
}