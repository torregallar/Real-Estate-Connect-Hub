
var button = document.getElementById('createButton');
var form = document.getElementById('form');


button.addEventListener('click', function(event) { // Comprobación campos vacíos

    event.preventDefault();

    var fields = form.querySelectorAll('input');
    var numberFields = form.querySelectorAll('input[type="number"]')
    var telephone = form.querySelectorAll('input[type="tel"]')
    var email = document.querySelectorAll('input[type="email"]')
    var isValidTel = true
    var fieldsEmpty = false
    var isValidNumber = true
    var isEmail = true

    fields.forEach(function (field) {
        if (field.value.trim() === '') {
            fieldsEmpty = true;
            field.style.borderColor = 'red';
        }
    })
    if (email.length>0) {
        isEmail = email[0].value.trim().includes('@')
    }

    if (telephone.length>0) {
        isValidTel = /^\d{9}$/.test(telephone[0].value.trim())
    }

    if (numberFields.length>0) {
        numberFields.forEach(function (field) {
            if (!(field.id === "sqMetres" || field.id === "price")) {
                if (!(/^\d*$/.test(field.value.trim()))) {
                    isValidNumber = false
                }
            }
        })
    }


    if (fieldsEmpty) {
        Swal.fire({
            title: "WARNING",
            text: "Fields should not be empty"
        });
    } else if (!isValidTel) {
        Swal.fire({
            title: "WARNING",
            text: 'Telephone should be 9 numbers'
        });
    } else if (!isValidNumber) {
        Swal.fire({
            title: "WARNING",
            text: 'Integer inputs should cointain only numbers'
        });
    } else if (!isEmail) {
        Swal.fire({
            title: "WARNING",
            text: 'Use "@" in your EMAIL'
        });
    } else {
        form.submit();
    }
})
