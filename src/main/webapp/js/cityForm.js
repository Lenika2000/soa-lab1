const addCityForm = document.forms.namedItem("addCityForm");
addCityForm.addEventListener('submit', function (ev) {
    let formData = new FormData(addCityForm);
    let request = new XMLHttpRequest();
    const newCity = '<city>' +
        '         <id>0</id>' +
        '         <name>'+ formData.get('name') + '</name>' +
        '         <coordinates>' +
        '            <x>'+ formData.get('x') + '</x>' +
        '            <y>'+ formData.get('y') + '</y>' +
        '         </coordinates>' +
        '         <area>'+ formData.get('area') + '</area>' +
        '         <population>'+ formData.get('population') + '</population>' +
        '         <metersAboveSeaLevel>'+ formData.get('metersAboveSeaLevel') + '</metersAboveSeaLevel>' +
        '         <timezone>'+ formData.get('timezone') + '</timezone>' +
        '         <government>'+ formData.get('government') + '</government>' +
        '         <standardOfLiving>'+ formData.get('standardOfLiving') + '</standardOfLiving>' +
        '         <governor>' +
        '            <height>'+ formData.get('height') + '</height>' +
        '            <birthday>' + formData.get('birthday-date') + "T" + formData.get('birthday-time') + ":00.000" +'</birthday>' +
        '         </governor>' +
        '      </city>'
    request.open("POST", "/lab1/add");
    request.send(newCity);
    request.onload = function (oEvent) {
        if (request.status === 400) {
            console.log(request.response);
            $('.error-msg__text').remove();
            $('.error-msg').append("<p class='error-msg__text alert alert-danger'>" + request.response.replace('<string>', '').replace('</string>', '') +"</p>");
        } else {
            window.location = '/lab1/';
        }
    };
    ev.preventDefault();
}, false);
