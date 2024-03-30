
document.getElementById('submitButton').addEventListener('click', function(event) {

    var message = window.confirm("Are you sure you want to modify the entity?");

    if (message) {
        document.getElementById("entityForm").submit();
    } else {
        //alert("Modification cancelled");
    }
});
