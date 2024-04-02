
document.querySelector('[id^="deleteButton_"]').addEventListener('click', function() {

    var message = confirm("Do you want to delete the entity?")
    var id = this.id.replace("deleteButton_", "");
    var buttonClass = this.dataset.entity;
    if (message) {
        if (buttonClass === "agency") {
            window.location.href = "/agency/deleteAgency/" + id
        } else if (buttonClass === "owner") {
            window.location.href = "/owner/deleteOwner/" + id
        } else if (buttonClass === "property") {
            window.location.href = "/property/deleteProperty/" + id
        }
    } else {
        alert("Deletion cancelled");
    }
});