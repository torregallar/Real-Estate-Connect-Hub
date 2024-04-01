
var button = document.getElementById('createButton');
var form = document.getElementById('form');


button.addEventListener('click', function(event) { // Comprobación campos vacíos

    event.preventDefault();

    var fields = form.querySelectorAll('input');
    var numberFields = form.querySelectorAll('input[type="number"]')
    var email = document.getElementById("email")
    var fieldsEmpty = false;
    var intOrLong = []
    var doubles = []
    var isValidNumber = true
    var isEmail = true

    fields.forEach(function (field) {
        if (field.value.trim() === '') {
            fieldsEmpty = true;
            field.style.borderColor = 'red';
        }
    })

    numberFields.forEach(function (field) {
        if (field.id === "sqMetres" || field.id === "price") {
            doubles.push(field);
        } else {
            intOrLong.push(field);
        }
    });

    intOrLong.forEach(function (field) {
        isValidNumber = /^\d+$/.test(field.value.trim());
    })

    doubles.forEach(function (field) {
        isValidNumber = /^\d*\.?\d*$/.test(field.value.trim());
    })

    isEmail = email.value.trim().includes('@')

    if (fieldsEmpty) {
        Swal.fire({
            title: "WARNING",
            text: "Fields should not be empty"
        });
    } else if (isValidNumber) {
        Swal.fire({
            title: "WARNING",
            text: 'Use ONLY NUMBERS in number fields, and use "." to split a real number'
        });
    } else if (isEmail) {
        Swal.fire({
            title: "WARNING",
            text: 'Use "@" in your EMAIL'
        });
    } else {
        form.submit();
    }
})
