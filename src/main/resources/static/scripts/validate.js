
var button = document.getElementById('createButton');

button.addEventListener('click', function(event) {

    var form = document.getElementById('form');

    event.preventDefault();

    var fields = form.querySelectorAll('input');
    var fieldsEmpty = false;

    fields.forEach(function (field) {
        if (field.value.trim() === '') {
            fieldsEmpty = true;
            field.style.borderColor = 'red';
        }
    })

    if (!fieldsEmpty) {
        form.submit();
    } else {
        alert("All fields should be filled");
    }
})







