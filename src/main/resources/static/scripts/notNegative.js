

const numberFields = document.querySelectorAll('#price, #rooms, #bathrooms, #sqMetres');
const telFields = document.querySelectorAll('#phone, #phoneNumber');


document.getElementById("createButton").addEventListener('click', function(event) {
    let isPositive = true
    let isTelephone = true
    numberFields.forEach(field => {
        const value = parseFloat(field.value);

        if (value <= 0) {
            isPositive = false
            alert(`Field ${field.id} should be positive or 0.`)
        }
    })

    telFields.forEach(field => {
        if (field.value.length < 9) {
            isTelephone = false
            alert("Field Phone Number should be 9 number length.")
        }

    })

    if (isPositive && isTelephone) {
        event.preventDefault()
        document.getElementById("form").submit()
    } else {
        event.preventDefault()
        return false;
    }

})







