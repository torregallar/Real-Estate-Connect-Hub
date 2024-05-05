
var selectOwners = document.querySelector('[id^="selectOwners_"]');

var actualOwner = selectOwners.id.replace("selectOwners_", "");

for (var i = 0; i < selectOwners.options.length; i++) {
    if (selectOwners.options[i].value === actualOwner) {
        selectOwners.options[i].selected = true;
        break;
    }
}

selectOwners.addEventListener("change", function() {

    var ownerSelected = selectOwners.value.toString();
    var actualProperty = document.querySelector('[id^="property_"]');
    actualProperty = actualProperty.id.replace("property_", "");

    window.location.href = "/property/modify/" + actualProperty + "/modifyOwner/" + ownerSelected;
})


function showPopUp() {
    var popup = document.getElementById("popup");

    popup.classList.replace("hidden", "block")
}

function hidePopUp() {
    var popup = document.getElementById("popup");

    popup.classList.replace("block", "hidden")
}
