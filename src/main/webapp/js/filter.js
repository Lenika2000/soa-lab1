var fields = ["Name", "X", "Y", "Date", "Area", "Population", "MetersAboveSeaLevel", "Timezone", "Government", "StandardOfLiving", "Height", "Birthday"]

fields.forEach((element) => {
    $('.is' + element + 'Disabled').change(function () {
        if ($('.is' + element + 'Disabled').is(':checked')) {
            $('.' + element).removeAttr("disabled");
        } else {
            $('.' + element).attr("disabled", "true");
        }
    });
})

function parseDate(date, time) {
    let year = date.children[0].textContent;
    let month = date.children[1].textContent;
    let day = date.children[2].textContent;
    let hours = time.children[0].textContent;
    let min = time.children[1].textContent;
    // yyyy-MM-dd HH:mm
    return year + "-" + month + "-" + day + " " + hours + ":" + min;
}

function filterListener(form, url, ev) {
    let formData = new FormData(form);
    let request = new XMLHttpRequest();
    request.responseType = 'document';
    let getStr = "?";
    for (let pair of formData.entries()) {
        getStr += pair[0] + '=' + pair[1] + '&';
    }
    getStr = url + getStr.substr(0, getStr.length - 1);
    request.open("GET", getStr);

    request.onload = function (oEvent) {
        if (request.status === 200) {
            console.log(request)
            let filteredCities = [];
            let rawData = request.response.getElementsByTagName("cities")[0].getElementsByTagName("cities")[0];
            let k, i, j, oneRecord, oneObject, innerObject;
            for (i = 0; i < rawData.children.length; i++) {
                oneRecord = rawData.children[i];
                oneObject = filteredCities[filteredCities.length] = {};
                for (j = 0; j < oneRecord.children.length; j++) {
                    if (oneRecord.children[j].children.length !== 0 && !oneRecord.children[j].tagName.includes('creationDate')) {
                        innerObject = oneObject[oneRecord.children[j].tagName] = {};
                        for (k = 0; k < oneRecord.children[j].children.length; k++) {
                            console.log(oneRecord.children[j].children[k].tagName);
                            if (oneRecord.children[j].children[k].tagName.includes('birthday')) {
                                let birthdayDate = oneRecord.children[j].children[k].children[0];
                                let birthdayTime = oneRecord.children[j].children[k].children[1];
                                innerObject[oneRecord.children[j].children[k].tagName] = parseDate(birthdayDate, birthdayTime);
                            } else {
                                innerObject[oneRecord.children[j].children[k].tagName] = oneRecord.children[j].children[k].textContent;
                            }
                        }
                        oneObject[oneRecord.children[j].tagName] = innerObject;
                    } else {
                        if (oneRecord.children[j].tagName.includes('creationDate')) {
                            let dateTime = oneRecord.children[j].children[0];
                            let date = dateTime.children[0];
                            let time = dateTime.children[1];
                            oneObject[oneRecord.children[j].tagName] = parseDate(date, time);
                        } else {
                            oneObject[oneRecord.children[j].tagName] = oneRecord.children[j].textContent;
                        }
                    }
                }
            }
            $('.table-rows').remove();
            let html;
            for (i = 0; i < filteredCities.length; i++) {
                html += "<tr class='table-rows'><td>" + filteredCities[i].id + "</td><td>" + filteredCities[i].name + "</td><td>" + filteredCities[i].coordinates.x
                    + "</td><td>" + filteredCities[i].coordinates.y + "</td><td>" + filteredCities[i].creationDate + "</td><td>" + filteredCities[i].area
                    + "</td><td>" + filteredCities[i].population + "</td><td>" + filteredCities[i].metersAboveSeaLevel
                    + "</td><td>" + filteredCities[i].timezone + "</td><td>" + filteredCities[i].government
                    + "</td><td>" + filteredCities[i].standardOfLiving + "</td><td>" + filteredCities[i].governor.height
                    + "</td><td>" + filteredCities[i].governor.birthday + "</td>" +
                    "<td><a href=edit?id=" + filteredCities[i].id + ">Edit</a>" +
                    "    <button class='btn btn-primary mx-auto mt-2' onclick='deleteCity(${city.id});'>Delete</button></td></tr>";
            }
            $('table').append(html);
            console.log(filteredCities);
        } else {
            console.log("Server filter error " + request.status);
        }
    };
    request.send(formData);
    ev.preventDefault();
}

const filterForm = document.forms.namedItem("filterForm");
filterForm.addEventListener('submit', function (ev) {
    filterListener(filterForm, '/lab1/filter', ev);
}, false);

const filterByName = document.forms.namedItem("filterByName");
filterByName.addEventListener('submit',
    function (ev) {
        filterListener(filterByName, '/lab1/filterByName', ev);
    }, false);

const filterByMetersAboveSeaLevel = document.forms.namedItem("findCitiesMetersAboveSeaLevelMore");
filterByMetersAboveSeaLevel.addEventListener('submit',
    function (ev) {
        filterListener(filterByMetersAboveSeaLevel, '/lab1/findCitiesMetersAboveSeaLevelMore', ev);
    }, false);

const getUniqueValuesOfMetersAboveSeaLevel = document.forms.namedItem("getUniqueValuesOfMetersAboveSeaLevel");
getUniqueValuesOfMetersAboveSeaLevel.addEventListener('submit',
    function (ev) {
        let formData = new FormData(getUniqueValuesOfMetersAboveSeaLevel);
        let request = new XMLHttpRequest();
        request.responseType = 'document';
        request.open("GET", "/lab1/getUniqueValuesOfMetersAboveSeaLevel");

        request.onload = function (oEvent) {
            if (request.status === 200) {
                let metersAboveSeaLevel = [];
                let rawData = request.response.getElementsByTagName("metersAboveSeaLevel")[0].getElementsByTagName("meters")[0];
                for (i = 0; i < rawData.children.length; i++) {
                    metersAboveSeaLevel[i] = rawData.children[i].textContent;
                }
                $('.metersAboveSeaLevel').remove();
                let html;
                for (i = 0; i < metersAboveSeaLevel.length; i++) {
                    html += "<tr class='metersAboveSeaLevel'><td>" + metersAboveSeaLevel[i] + "</td></tr>";
                }
                $('.uniqueValuesOfMetersAboveSeaLevel').append(html);
            } else {
                console.log("Error ger unique values " + request.status);
            }
        };
        request.send(formData);
        ev.preventDefault();
    }, false);


const sortForm = document.forms.namedItem("sortForm");
sortForm.addEventListener('submit',
    function (ev) {
        let url = "/lab1/sort?";
        $('input[type=radio]').filter(':checked').each(function () {
            var inputField = $(this);
            console.log(inputField.attr('name'))
            console.log(inputField.val())
            url += inputField.attr('name') + "=" + inputField.val() + "&";
        });
        url = url.substring(0, url.length - 1);
        let request = new XMLHttpRequest();
        request.responseType = 'document';
        request.open("GET", url);

        request.onload = function (oEvent) {
            if (request.status === 200) {
                console.log(request)
                let filteredCities = [];
                let rawData = request.response.getElementsByTagName("cities")[0].getElementsByTagName("cities")[0];
                let k, i, j, oneRecord, oneObject, innerObject;
                for (i = 0; i < rawData.children.length; i++) {
                    oneRecord = rawData.children[i];
                    oneObject = filteredCities[filteredCities.length] = {};
                    for (j = 0; j < oneRecord.children.length; j++) {
                        if (oneRecord.children[j].children.length !== 0 && !oneRecord.children[j].tagName.includes('creationDate')) {
                            innerObject = oneObject[oneRecord.children[j].tagName] = {};
                            for (k = 0; k < oneRecord.children[j].children.length; k++) {
                                console.log(oneRecord.children[j].children[k].tagName);
                                if (oneRecord.children[j].children[k].tagName.includes('birthday')) {
                                    let birthdayDate = oneRecord.children[j].children[k].children[0];
                                    let birthdayTime = oneRecord.children[j].children[k].children[1];
                                    innerObject[oneRecord.children[j].children[k].tagName] = parseDate(birthdayDate, birthdayTime);
                                } else {
                                    innerObject[oneRecord.children[j].children[k].tagName] = oneRecord.children[j].children[k].textContent;
                                }
                            }
                            oneObject[oneRecord.children[j].tagName] = innerObject;
                        } else {
                            if (oneRecord.children[j].tagName.includes('creationDate')) {
                                let dateTime = oneRecord.children[j].children[0];
                                let date = dateTime.children[0];
                                let time = dateTime.children[1];
                                oneObject[oneRecord.children[j].tagName] = parseDate(date, time);
                            } else {
                                oneObject[oneRecord.children[j].tagName] = oneRecord.children[j].textContent;
                            }
                        }
                    }
                }
                $('.table-rows').remove();
                let html;
                for (i = 0; i < filteredCities.length; i++) {
                    html += "<tr class='table-rows'><td>" + filteredCities[i].id + "</td><td>" + filteredCities[i].name + "</td><td>" + filteredCities[i].coordinates.x
                        + "</td><td>" + filteredCities[i].coordinates.y + "</td><td>" + filteredCities[i].creationDate + "</td><td>" + filteredCities[i].area
                        + "</td><td>" + filteredCities[i].population + "</td><td>" + filteredCities[i].metersAboveSeaLevel
                        + "</td><td>" + filteredCities[i].timezone + "</td><td>" + filteredCities[i].government
                        + "</td><td>" + filteredCities[i].standardOfLiving + "</td><td>" + filteredCities[i].governor.height
                        + "</td><td>" + filteredCities[i].governor.birthday + "</td>" +
                        "<td><a href=edit?id=" + filteredCities[i].id + ">Edit</a>" +
                        "    <button class='btn btn-primary mx-auto mt-2' onclick='deleteCity(${city.id});'>Delete</button></td></tr>";
                }
                $('table').append(html);
                console.log(filteredCities);
            } else {
                console.log("Error sort " + request.status);
            }
        };
        request.send();
        ev.preventDefault();
    }, false);

function deleteCity(id) {
    let request = new XMLHttpRequest();
    request.open("DELETE", "/lab1/delete?id=" + id);
    request.onload = function (oEvent) {
        window.location = '/lab1/';
    };
    request.send();
}
