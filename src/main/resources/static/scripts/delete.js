
document.querySelector('[id^="deleteButton_"]').addEventListener('click', function() {

    var message = confirm("Do you want to delete the entity?")
    var id = this.id.replace("deleteButton_", "");
    if (message) {
        window.location.href="/agency/deleteAgency/" + id
    } else {
        alert("Deletion cancelled");
    }
});