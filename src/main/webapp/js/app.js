function getPage(page) {
    var input = document.getElementById("pageHiddenInput");
    input.value = page;
}

function navigationBtnFunc(action) {
    var actionBtn = action;
    var input = document.getElementById("navigationBtnInput");
    input.value = actionBtn;
}