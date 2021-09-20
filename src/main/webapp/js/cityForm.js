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
    request.open("POST", "/lab1/cities");
    request.send(newCity);
    request.onload = function (oEvent) {
        getErrorMsg(request);
    };
    ev.preventDefault();
}, false);

function updateCity() {
    const updateCityForm = document.forms.namedItem("updateCityForm");
    let formData = new FormData(updateCityForm);
    let request = new XMLHttpRequest();
    const newCity = '<city>' +
        '         <id>'+ formData.get('id') + '</id>' +
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
    request.open("PUT", "/lab1/cities");
    request.onload = function (oEvent) {
        getErrorMsg(request);
    };
    request.send(newCity);
}

function  getErrorMsg(request) {
    if (request.status === 200) {
        window.location = '/lab1/';
    } else {
        console.log(request.response);
        $('.error-msg__text').remove();
        $('.error-msg').append("<p class='error-msg__text alert alert-danger'>" + request.response +"</p>");
    }
};
